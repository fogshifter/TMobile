package com.tmobile.service;

import com.tmobile.auth.ProviderUserDetails;
import com.tmobile.dao.ContractDAO;
import com.tmobile.dao.OptionDAO;
import com.tmobile.dao.TariffDAO;
import com.tmobile.dao.UserDAO;
import com.tmobile.dto.ContractInfoDTO;
import com.tmobile.dto.ContractsListEntryDTO;
import com.tmobile.entity.*;
import com.tmobile.exception.EntryNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class ContractService {

    private PasswordEncoder encoder;
    private UserDAO userDAO;
    private ContractDAO contractDAO;
    private TariffDAO tariffDAO;
    private OptionDAO optionDAO;
    private ModelMapper mapper;
    private Random rand;

    @Autowired
    public ContractService(PasswordEncoder encoder, UserDAO userDAO, ContractDAO contractDAO, TariffDAO tariffDAO, OptionDAO optionDAO, ModelMapper mapper) {
        this.encoder = encoder;
        this.userDAO = userDAO;
        this.contractDAO = contractDAO;
        this.tariffDAO = tariffDAO;
        this.optionDAO = optionDAO;
        this.mapper = mapper;

        this.rand = new Random();
        this.rand.setSeed(123456789);
    }

    @Transactional
    public void registerContract(ContractInfoDTO contractInfo) {
        User user = new User();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");

        try {
            Date date = formatter.parse(contractInfo.getBirthDate());
            user.setBirthDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        user.setAddress(contractInfo.getAddress());
        user.setEmail(contractInfo.getEmail());
        user.setFirstName(contractInfo.getFirstName());
        user.setLastName(contractInfo.getLastName());
        user.setPassword(encoder.encode(contractInfo.getPassword()));
        user.setPassportData(contractInfo.getPassportData());

        int existingUserId = userDAO.findId(user);
        if (existingUserId != 0) {
            user.setId(existingUserId);
        }

//        Contract contract = mapper.map(contractInfo, Contract.class);
        Contract contract = new Contract();
        contract.setPhone(contractInfo.getPhone());

        int tariffId = contractInfo.getTariffId();
        contract.setTariff(tariffDAO.find(tariffId));

        for (Integer optionId : contractInfo.getOptionIds()) {
            contract.addOption(optionDAO.find(optionId));
        }
//        user.addContract(contract);
        contract.setCustomer(user);

        Contract lastInserted = contractDAO.getLast();
        BigInteger phone = new BigInteger(lastInserted.getPhone());

        phone = phone.add(BigInteger.valueOf((rand.nextInt(20) + 27)));
        contract.setPhone(phone.toString());

        contractDAO.insert(contract);

        if (user.getId() != 0) {
            userDAO.update(user);
        } else {
            userDAO.insert(user);
        }
    }

    @Transactional
    public void editContract(ContractInfoDTO contractInfo) throws EntryNotFoundException {

        ProviderUserDetails userDetails = (ProviderUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        boolean customer = userDetails.getAuthorities()
                .stream().noneMatch(r -> r.getAuthority().contains("MANAGER"));

        Contract contract = contractDAO.findById(contractInfo.getContractId());

        if(contract == null) {
            throw new EntryNotFoundException("Contract not found");
        }

        if(customer && contract.getBlocked() == Contract.eBlocked.BLOCKED_BY_MANAGER) {
            // TODO throw exception (customer can't edit contract blocked by manager
        }

        if(contract.getBlocked() != Contract.eBlocked.NOT_BLOCKED && contract.getBlocked().getVal() == contractInfo.getBlocked()) {
            // TODO throw exception (trying to edit blocked contract)
        }

        if(customer && contractInfo.getBlocked() == Contract.eBlocked.BLOCKED_BY_MANAGER.getVal()) {
            // TODO throw exception (customer cannot block contract as manager)
        }

        if(customer) {
            User user = userDAO.findById(userDetails.getUserId());

            List<Contract> userContracts = user.getContracts();
            if(!userContracts.contains(contract)) {
                // TODO: throw exception (trying to edit contract that not belongs to current User)
            }
        }

        Tariff tariff = tariffDAO.find(contractInfo.getTariffId());

        if(tariff == null) {
            throw new EntryNotFoundException("Tariff not found");
        }

        List<Option> options = optionDAO.getByIds(contractInfo.getOptionIds());

        if(options.size() != contractInfo.getOptionIds().size()) {
            throw new EntryNotFoundException("Tariff option not found");
        }

        if(tariff.getCompatibleOptions().containsAll(options)) {
            // TODO throw exception
        }

        //Contract contract = contractDAO.findById(contractInfo.getContractId());
        contract.setOptions(options);
        contract.setTariff(tariff);

        contractDAO.update(contract);
    }

    @Transactional
    public ContractInfoDTO getDefaultContractInfo() {
        ContractInfoDTO contractInfo = new ContractInfoDTO();

        Tariff tariff = tariffDAO.getDefaultTariff();
        contractInfo.setTariffId(tariff.getId());

        /*for (TariffOptions option : tariff.getCompatibleOptions()) {
            contractInfo.addOptionId(option.getOption().getId());
        }*/
        for(Option option : tariff.getCompatibleOptions()) {
            contractInfo.addOptionId(option.getId());
        }
        return contractInfo;
    }


    @Transactional
    public List<ContractsListEntryDTO> getAllContracts() {
        List<Contract> contracts = contractDAO.getAll();

        Type targetListType = new TypeToken<List<ContractsListEntryDTO>>() {
        }.getType();
        return mapper.map(contracts, targetListType);
    }

    @Transactional
    public List<ContractsListEntryDTO> searchContractsByPhone(String phone) {
        List<Contract> contracts = contractDAO.findByPhone(phone);
        Type targetListType = new TypeToken<List<ContractsListEntryDTO>>() {
        }.getType();
        return mapper.map(contracts, targetListType);
    }

    @Transactional
    public List<ContractsListEntryDTO> getCustomerContracts(int customerId) {
        List<Contract> customerContracts = contractDAO.getCustomerContracts(customerId);

        Type targetListType = new TypeToken<List<ContractsListEntryDTO>>() {
        }.getType();
        return mapper.map(customerContracts, targetListType);
    }

    @Transactional
    public ContractInfoDTO getContract(int id) {
        Contract contract = contractDAO.findById(id);

        return mapper.map(contract, ContractInfoDTO.class);
    }
}

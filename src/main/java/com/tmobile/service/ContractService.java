package com.tmobile.service;

import com.tmobile.auth.TMobileUserDetails;
import com.tmobile.dao.ContractDAO;
import com.tmobile.dao.OptionDAO;
import com.tmobile.dao.TariffDAO;
import com.tmobile.dao.UserDAO;
import com.tmobile.dto.ContractInfoDTO;
import com.tmobile.dto.ContractsListEntryDTO;
import com.tmobile.entity.*;
import com.tmobile.exception.EntryNotFoundException;
import com.tmobile.exception.InconsistentDataException;
import com.tmobile.exception.TMobileException;
import com.tmobile.exception.UnauthorizedException;
import com.tmobile.util.Types;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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

    private boolean isContractAccessible(Contract contract) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        TMobileUserDetails userDetails = (TMobileUserDetails) authentication.getPrincipal();

        if(userDetails.getRole() == Types.Role.ROLE_MANAGER) {
            return true;
        }

        if(userDetails.getUserId() == contract.getCustomer().getId()) {
            return true;
        }
        return false;
    }

    @Transactional
    public void registerContract(ContractInfoDTO contractInfo) throws TMobileException {
        User user = new User();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

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

        try {
            int existingUserId = userDAO.findId(user);
            user.setId(existingUserId);
        }
        catch(EntryNotFoundException e) {

        }

//        Contract contract = mapper.map(contractInfo, Contract.class);
        Contract contract = new Contract();
        contract.setPhone(contractInfo.getPhone());

        int tariffId = contractInfo.getTariffId();
        contract.setTariff(tariffDAO.findById(tariffId, Tariff.class));

        for (Integer optionId : contractInfo.getOptionIds()) {
            contract.addOption(optionDAO.findById(optionId, Option.class));
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
    public void editContract(ContractInfoDTO contractInfo) throws TMobileException {

        TMobileUserDetails userDetails = (TMobileUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Contract contract = contractDAO.findById(contractInfo.getContractId(), Contract.class);

        if(userDetails.getRole() != Types.Role.ROLE_MANAGER && userDetails.getUserId() != contract.getCustomer().getId()) {
            throw new UnauthorizedException("You are unauthorized to do this action");
        }

        if(contract.getBlocked() != Types.ContractBlocked.UNBLOCKED) {
            throw new InconsistentDataException("Contract is blocked");
        }

        Tariff tariff = tariffDAO.findById(contractInfo.getTariffId(), Tariff.class);

        List<Option> options = optionDAO.getByIds(contractInfo.getOptionIds());

        if(tariff.getCompatibleOptions().containsAll(options)) {
            throw new InconsistentDataException("Incompatible options for chosen tariff");
        }

        //Contract contract = contractDAO.findById(contractInfo.getContractId());
        contract.setOptions(options);
        contract.setTariff(tariff);

        contractDAO.update(contract);
    }

//    @Transactional
//    public List<String> genPhones() {
//        List<Contract> allContracts = contractDAO.getAll();
//
//        List<String>
//    }
//    @Transactional
//    public ContractInfoDTO getDefaultContractInfo() {
//        ContractInfoDTO contractInfo = new ContractInfoDTO();
//
//        Tariff tariff = tariffDAO.getDefaultTariff();
//        contractInfo.setTariffId(tariff.getId());
//
//        /*for (TariffOptions option : tariff.getCompatibleOptions()) {
//            contractInfo.addOptionId(option.getOption().getId());
//        }*/
//        for(Option option : tariff.getCompatibleOptions()) {
//            contractInfo.addOptionId(option.getId());
//        }
//        return contractInfo;
//    }


    @Transactional
    public List<ContractsListEntryDTO> getAllContracts() {
        List<Contract> contracts = contractDAO.getAll(Contract.class);

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

//        if(customerContracts == null) {
//            throw new EntryNotFoundException("Customer not found");
//        }

        Type targetListType = new TypeToken<List<ContractsListEntryDTO>>() {
        }.getType();
        return mapper.map(customerContracts, targetListType);
    }

    @Transactional
    public ContractInfoDTO getContract(int id) throws TMobileException {

        Contract contract = contractDAO.findById(id, Contract.class);

        if(!isContractAccessible(contract)) {
            throw new UnauthorizedException("You are not authorized to get user details");
        }

        return mapper.map(contract, ContractInfoDTO.class);
    }

    @Transactional
    public void blockContract(int id, boolean block) throws TMobileException {
        Contract contract = contractDAO.findById(id, Contract.class);

        if(!isContractAccessible(contract)) {
            throw new UnauthorizedException("You are unauthorized");
        }

        if(block && contract.getBlocked() != Types.ContractBlocked.UNBLOCKED) {
            throw new InconsistentDataException("Contract is already blocked");
        }

        TMobileUserDetails userDetails = (TMobileUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(!block) {
            contract.setBlocked(Types.ContractBlocked.UNBLOCKED);
        }
        else if(userDetails.getRole() == Types.Role.ROLE_MANAGER) {
            contract.setBlocked(Types.ContractBlocked.BLOCKED_BY_MANAGER);
        }
        else {
            contract.setBlocked(Types.ContractBlocked.BLOCKED_BY_CUSTOMER);
        }

        contractDAO.update(contract);
    }
}

package com.tmobile.service;

import com.tmobile.dao.OptionDAO;
import com.tmobile.dao.TariffDAO;
import com.tmobile.dao.UserDAO;
import com.tmobile.dto.ContractInfoDTO;
import com.tmobile.entity.Contract;
import com.tmobile.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ContractService {

    private PasswordEncoder encoder;
    private UserDAO userDAO;
    private TariffDAO tariffDAO;
    private OptionDAO optionDAO;
    private ModelMapper mapper;

    @Autowired
    public ContractService(PasswordEncoder encoder, UserDAO userDAO, TariffDAO tariffDAO, OptionDAO optionDAO, ModelMapper mapper) {
        this.encoder = encoder;
        this.userDAO = userDAO;
        this.tariffDAO = tariffDAO;
        this.optionDAO = optionDAO;
        this.mapper = mapper;
    }

    @Transactional
    public void registerContract(ContractInfoDTO contractInfo) {
        User user = new User();

        SimpleDateFormat formatter = new SimpleDateFormat("dd MM yyyy");

        try {
            Date date = formatter.parse(contractInfo.getBirthDate());
            user.setBirthDate(date);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        user.setAddress(contractInfo.getAddress());
        user.setEmail(contractInfo.getEmail());
        user.setFirstName(contractInfo.getFirstName());
        user.setLastName(contractInfo.getLastName());
        user.setPassword(encoder.encode(contractInfo.getPassword()));
        user.setPassportData(contractInfo.getPassportData());

        int existingUserId = userDAO.findId(user);
        if(existingUserId != 0) {
            user.setId(existingUserId);
        }

//        Contract contract = mapper.map(contractInfo, Contract.class);
        Contract contract = new Contract();
        contract.setPhone(contractInfo.getPhone());

        int tariffId = contractInfo.getTariffId();
        contract.setTariff(tariffDAO.find(tariffId));

        for(Integer optionId : contractInfo.getOptionIds()) {
            contract.addOption(optionDAO.find(optionId));
        }
//        contract.setPhone(contractInfo.getPhone());

        user.addContract(contract);

        if(user.getId() != 0) {
            userDAO.update(user);
        }
        else {
            userDAO.insert(user);
        }
    }
}

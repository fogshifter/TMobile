package com.tmobile.converter;

import com.tmobile.dto.ContractInfoDTO;
import com.tmobile.entity.Contract;
import com.tmobile.entity.Option;
import org.modelmapper.AbstractConverter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ContractToContractInfoDTOConverter extends AbstractConverter<Contract, ContractInfoDTO> {

    @Override
    protected ContractInfoDTO convert(Contract contract) {

        ContractInfoDTO dto = new ContractInfoDTO();

        dto.setContractId(contract.getId());
        dto.setPhone(contract.getPhone());
        dto.setTariffId(contract.getTariff().getId());
        dto.setFirstName(contract.getCustomer().getFirstName());
        dto.setLastName(contract.getCustomer().getLastName());
        dto.setEmail(contract.getCustomer().getEmail());
        dto.setCustomerId(contract.getCustomer().getId());
        dto.setAddress(contract.getCustomer().getAddress());
        dto.setPassportData(contract.getCustomer().getPassportData());
        dto.setBlocked(contract.getBlocked().getVal());

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String birthDate = df.format(contract.getCustomer().getBirthDate());
        dto.setBirthDate(birthDate);

        for (Option option : contract.getOptions()) {
            dto.addOptionId(option.getId());
        }

        return dto;
    }
}

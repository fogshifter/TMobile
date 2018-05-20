package com.tmobile.converter;

import org.modelmapper.AbstractConverter;

import com.tmobile.dto.ContractsListEntryDTO;
import com.tmobile.entity.Contract;

public class ContractToContractEntryDTOConverter extends AbstractConverter<Contract, ContractsListEntryDTO> {

    @Override
    protected ContractsListEntryDTO convert(Contract contract) {

        ContractsListEntryDTO dto = new ContractsListEntryDTO();
        dto.setId(contract.getId());
        dto.setFirstName(contract.getCustomer().getFirstName());
        dto.setLastName(contract.getCustomer().getLastName());
        dto.setEmail(contract.getCustomer().getEmail());
        dto.setTariff(contract.getTariff().getName());
        dto.setPhone(contract.getPhone());

        return dto;
    }
}

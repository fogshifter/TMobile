package com.tmobile.converter;

import com.tmobile.dto.ContractsListEntryDTO;
import com.tmobile.entity.Contract;
import com.tmobile.entity.Tariff;
import com.tmobile.entity.User;
import org.junit.Assert;
import org.junit.Test;

public class ContractToContractEntryDTOConverterTest {

    private ContractToContractEntryDTOConverter converter = new ContractToContractEntryDTOConverter();
    private static final String EMAIL = "testEmail@test.com";

    private static final String FIRST_NAME = "First_name";
    private static final int ID = 1;
    private static final String LAST_NAME = "Last_name";
    private static final String PHONE = "88888888888";
    private static final String TARIFF = "Tariff";

    @Test
    public void convertTest() {
        ContractsListEntryDTO actualResult = converter.convert(createContract());
        Assert.assertEquals(expectedResult(), actualResult);
    }

    private Contract createContract() {
        Contract contract = new Contract();
        contract.setId(ID);
        User customer = new User();
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);
        customer.setEmail(EMAIL);
        contract.setCustomer(customer);
        contract.setBlocked(Contract.eBlocked.NOT_BLOCKED);
        contract.setPhone(PHONE);
        Tariff tariff = new Tariff();
        tariff.setName(TARIFF);
        contract.setTariff(tariff);
        return contract;
    }

    private ContractsListEntryDTO expectedResult() {
        ContractsListEntryDTO dto = new ContractsListEntryDTO();
        dto.setEmail(EMAIL);
        dto.setFirstName(FIRST_NAME);
        dto.setId(ID);
        dto.setLastName(LAST_NAME);
        dto.setPhone(PHONE);
        dto.setTariff(TARIFF);
        return dto;
    }
}
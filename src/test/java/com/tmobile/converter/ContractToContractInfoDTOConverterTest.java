package com.tmobile.converter;

import com.tmobile.dto.ContractInfoDTO;
import com.tmobile.entity.Contract;
import com.tmobile.entity.Option;
import com.tmobile.entity.Tariff;
import com.tmobile.entity.User;
import org.junit.Assert;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ContractToContractInfoDTOConverterTest {

    private ContractToContractInfoDTOConverter converter = new ContractToContractInfoDTOConverter();
    private static final DateFormat df = new SimpleDateFormat("dd.mm.yyyy");
    private final String ADDRESS = "Address";
    private final Date BIRTH_DATE = new Date();
    private final String EMAIL = "Email";
    private final String FIRST_NAME = "First_name";
    private final String LAST_NAME = "Last_name";
    private final int CONTRACT_ID = 1;
    private final int CUSTOMER_ID = 1;
    private final String PASSPORT_DATA = "Passport_data";
    private final String PHONE = "88888888888";
    private final int TARIFF_ID = 1;
    private final int OPTION_ID = 1;


    @Test
    public void converterTest() {
        ContractInfoDTO actualResult = converter.convert(createContract());
        Assert.assertEquals(expectedResult(), actualResult);
    }

    private Contract createContract() {
        Contract contract = new Contract();
        Tariff tariff = new Tariff();
        tariff.setId(TARIFF_ID);
        contract.setTariff(tariff);
        contract.setPhone(PHONE);
        contract.setId(CONTRACT_ID);
        User customer = new User();
        customer.setId(CUSTOMER_ID);
        customer.setEmail(EMAIL);
        customer.setLastName(LAST_NAME);
        customer.setFirstName(FIRST_NAME);
        customer.setBirthDate(BIRTH_DATE);
        customer.setAddress(ADDRESS);
        customer.setPassportData(PASSPORT_DATA);
        contract.setCustomer(customer);
        Option option = new Option();
        option.setId(OPTION_ID);
        contract.setOptions(Arrays.asList(option));
        return contract;
    }

    private ContractInfoDTO expectedResult() {
        ContractInfoDTO dto = new ContractInfoDTO();
        dto.setAddress(ADDRESS);
        dto.setBirthDate(df.format(BIRTH_DATE));
        dto.setContractId(CONTRACT_ID);
        dto.setCustomerId(CUSTOMER_ID);
        dto.setEmail(EMAIL);
        dto.setFirstName(FIRST_NAME);
        dto.setLastName(LAST_NAME);
        dto.setOptionIds(Arrays.asList(OPTION_ID));
        dto.setPassportData(PASSPORT_DATA);
        dto.setPhone(PHONE);
        dto.setTariffId(TARIFF_ID);
        return dto;
    }
}

package com.tmobile.converter;

import com.tmobile.dto.OptionDTO;
import com.tmobile.entity.Option;
import org.junit.Assert;
import org.junit.Test;

public class OptionToOptionDTOConverterTest {

    private OptionToOptionDTOConverter converter = new OptionToOptionDTOConverter();
    private final int OPTION_ID = 1;
    private final int PRICE = 1;
    private final int PAYMENT = 1;
    private final String NAME = "";
    private final String DESCRIPTION = "";
    private final boolean COMPATIBLE = true;

    @Test
    public void converterTest() {
        OptionDTO actualResult = converter.convert(createOption());
        Assert.assertEquals(expectedResult(), actualResult);
    }

    private Option createOption() {
        Option option = new Option();
        option.setId(OPTION_ID);
        option.setCompatible(COMPATIBLE);
        option.setDescription(DESCRIPTION);
        option.setName(NAME);
        option.setPrice(PRICE);
        option.setPayment(PAYMENT);
        return option;
    }

    private OptionDTO expectedResult() {
        OptionDTO dto = new OptionDTO();
        dto.setId(OPTION_ID);
        dto.setPrice(PRICE);
        dto.setPayment(PAYMENT);
        dto.setName(NAME);
        dto.setDescription(DESCRIPTION);
        dto.setCompatible(COMPATIBLE);
        return dto;
    }
}

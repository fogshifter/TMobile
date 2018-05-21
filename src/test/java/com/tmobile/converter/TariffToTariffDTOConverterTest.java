package com.tmobile.converter;

import com.tmobile.dto.TariffDTO;
import com.tmobile.entity.Option;
import com.tmobile.entity.Tariff;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class TariffToTariffDTOConverterTest {

    private TariffToTariffDTOConverter converter = new TariffToTariffDTOConverter();
    private final int TARIFF_ID = 1;
    private final String DESCRIPTION = "Description";
    private final String NAME = "Name";
    private final int PRICE = 1;
    private final int OPTION_ID = 1;

    @Test
    public void converterTest() {
        TariffDTO actualResult = converter.convert(createTariff());
        Assert.assertEquals(expectedResult(), actualResult);
    }

    private Tariff createTariff() {
        Tariff tariff = new Tariff();
        tariff.setId(TARIFF_ID);
        tariff.setName(NAME);
        tariff.setDescription(DESCRIPTION);
        tariff.setPrice(PRICE);
        Option option = new Option();
        option.setId(OPTION_ID);
        tariff.setCompatibleOptions(Arrays.asList(option));
        return tariff;
    }

    private TariffDTO expectedResult() {
        TariffDTO dto = new TariffDTO();
        dto.setId(TARIFF_ID);
        dto.setDescription(DESCRIPTION);
        dto.setName(NAME);
        dto.setPrice(PRICE);
        dto.setCompatibleOptions(Arrays.asList(OPTION_ID));
        return dto;
    }
}

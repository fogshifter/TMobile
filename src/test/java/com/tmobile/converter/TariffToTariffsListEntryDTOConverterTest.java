package com.tmobile.converter;

import com.tmobile.dto.TariffsListEntryDTO;
import com.tmobile.entity.Tariff;
import org.junit.Assert;
import org.junit.Test;

public class TariffToTariffsListEntryDTOConverterTest {

    public TariffToTariffsListEntryDTOConverter converter = new TariffToTariffsListEntryDTOConverter();
    private final int TARIFF_ID = 1;
    private final String NAME = "Name";
    private final String DESCRIPTION = "Description";
    private final int PRICE = 1;

    @Test
    public void converterTest() {
        TariffsListEntryDTO actualResult = converter.convert(createTariff());
        Assert.assertEquals(expectedResult(), actualResult);
    }

    private Tariff createTariff() {
        Tariff tariff = new Tariff();
        tariff.setPrice(PRICE);
        tariff.setDescription(DESCRIPTION);
        tariff.setName(NAME);
        tariff.setId(TARIFF_ID);
        return tariff;
    }

    private TariffsListEntryDTO expectedResult() {
        TariffsListEntryDTO dto = new TariffsListEntryDTO();
        dto.setId(TARIFF_ID);
        dto.setName(NAME);
        dto.setDescription(DESCRIPTION);
        dto.setPrice(PRICE);
        return dto;
    }
}

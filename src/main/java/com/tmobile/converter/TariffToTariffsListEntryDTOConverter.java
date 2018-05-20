package com.tmobile.converter;

import com.tmobile.dto.TariffsListEntryDTO;
import com.tmobile.entity.Tariff;
import org.modelmapper.AbstractConverter;

public class TariffToTariffsListEntryDTOConverter extends AbstractConverter<Tariff, TariffsListEntryDTO> {
    @Override
    protected TariffsListEntryDTO convert(Tariff tariff) {
        TariffsListEntryDTO tariffDTO = new TariffsListEntryDTO();

        tariffDTO.setId(tariff.getId());
        tariffDTO.setName(tariff.getName());
        tariffDTO.setDescription(tariff.getDescription());
        tariffDTO.setPrice(tariff.getPrice());

        return tariffDTO;
    }
}

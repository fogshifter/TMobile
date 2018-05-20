package com.tmobile.converter;

import com.tmobile.dto.TariffDTO;
import com.tmobile.entity.Option;
import com.tmobile.entity.Tariff;
import org.modelmapper.AbstractConverter;

import java.util.List;
import java.util.stream.Collectors;

public class TariffToTariffDTOConverter extends AbstractConverter<Tariff, TariffDTO> {
    @Override
    protected TariffDTO convert(Tariff tariff) {
        TariffDTO tariffDTO = new TariffDTO();
        tariffDTO.setId(tariff.getId());
        tariffDTO.setDescription(tariff.getDescription());
        tariffDTO.setName(tariff.getName());
        tariffDTO.setPrice(tariff.getPrice());

        List<Integer> compatibleOptionsIds = tariff.getCompatibleOptions().stream().map(option -> option.getId()).collect(Collectors.toList());
        tariffDTO.setCompatibleOptions(compatibleOptionsIds);

        return tariffDTO;
    }
}

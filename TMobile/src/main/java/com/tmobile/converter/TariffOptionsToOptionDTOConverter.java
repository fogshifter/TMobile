package com.tmobile.converter;

import com.tmobile.dto.OptionDTO;
import com.tmobile.entity.Option;
import com.tmobile.entity.TariffOptions;
import org.modelmapper.AbstractConverter;

public class TariffOptionsToOptionDTOConverter extends AbstractConverter<TariffOptions, OptionDTO> {

    @Override
    protected OptionDTO convert(TariffOptions tariffOptions) {
        OptionDTO optionDTO = new OptionDTO();

        Option option = tariffOptions.getOption();
        optionDTO.setCompatible(option.isCompatible());
        optionDTO.setDescription(option.getDescription());
        optionDTO.setId(option.getId());
        optionDTO.setName(option.getName());
        optionDTO.setPayment(option.getPayment());
        optionDTO.setPrice(option.getPrice());

        return optionDTO;
    }
}










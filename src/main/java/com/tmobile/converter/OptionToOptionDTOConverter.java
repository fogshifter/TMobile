package com.tmobile.converter;

import com.tmobile.dto.OptionDTO;
import com.tmobile.entity.Option;
import org.modelmapper.AbstractConverter;

public class OptionToOptionDTOConverter extends AbstractConverter<Option, OptionDTO> {
    @Override
    protected OptionDTO convert(Option option) {
        OptionDTO optionDTO = new OptionDTO();

        optionDTO.setId(option.getId());
        optionDTO.setPrice(option.getPrice());
        optionDTO.setPayment(option.getPayment());
        optionDTO.setName(option.getName());
        optionDTO.setDescription(option.getDescription());
        optionDTO.setCompatible(option.isCompatible());

        for(Option compatibleOption : option.getCompatibleOptions()) {
            optionDTO.getCompatibleOptions().add(compatibleOption.getId());
        }

        for(Option requiredOption : option.getRequiredOptions()) {
            optionDTO.getCompatibleOptions().add(requiredOption.getId());
        }

        return optionDTO;
    }
}

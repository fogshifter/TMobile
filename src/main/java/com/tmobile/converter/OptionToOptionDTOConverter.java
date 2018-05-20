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

        /*for(Option compatibleOption : option.getCompatibleOptions()) {
            OptionDTO compatibleOptDTO = new OptionDTO();
            compatibleOptDTO.setId(compatibleOption.getId());
            compatibleOptDTO.setPayment(compatibleOption.getPayment());
            compatibleOptDTO.setDescription(compatibleOption.getDescription());
            compatibleOptDTO.setPrice(compatibleOption.getPrice());
            compatibleOptDTO.setName(compatibleOption.getName());
            compatibleOptDTO.setCompatible(compatibleOption.isCompatible());

            optionDTO.addCompatibleOption(compatibleOptDTO);
        }

        for(Option requiredOption : option.getRequiredOptions()) {
            OptionDTO requiredOptDTO = new OptionDTO();
            requiredOptDTO.setId(requiredOption.getId());
            requiredOptDTO.setCompatible(requiredOption.isCompatible());
            requiredOptDTO.setName(requiredOption.getName());
            requiredOptDTO.setPrice(requiredOption.getPrice());
            requiredOptDTO.setDescription(requiredOption.getDescription());
            requiredOptDTO.setPayment(requiredOption.getPayment());

            optionDTO.addRequiredOptions(requiredOptDTO);
        }*/

        return optionDTO;
    }
}

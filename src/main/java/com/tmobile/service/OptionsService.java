package com.tmobile.service;

import com.tmobile.dao.OptionDAO;
//import com.tmobile.dto.OptionsListEntryDTO;
import com.tmobile.dto.OptionDTO;
import com.tmobile.entity.Option;
import com.tmobile.exception.EntryNotFoundException;
import com.tmobile.exception.TMobileException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OptionsService {

    private OptionDAO optionDAO;
    private ModelMapper modelMapper;

    private boolean isCompatible(List<Option> options) {
        for(Option firstOption : options) {

            if(firstOption.isCompatible()) {
                continue;
            }

            for(Option secondOption : options) {
                if(secondOption.isCompatible() || firstOption.equals(secondOption)) {
                    break;
                }

                if(!secondOption.getCompatibleOptions().contains(firstOption)) {
                    return false;
                }
            }
        }

        return true;
    }

    private List<Option> getCompatible(List<Option> options) {
        List<Option> resultList = optionDAO.getAll();

        for(Option option : options) {

            if(option.isCompatible()) {
                continue;
            }

            List<Option> compatibleOptions = option.getCompatibleOptions();

            resultList = resultList.stream().filter(opt -> compatibleOptions.contains(opt)).collect(Collectors.toList());

        }

        return resultList;
    }

    private boolean isCompatibleToAll(List<Option> options) {

        for(Option option : options) {
            if(!option.isCompatible()) {
                return false;
            }
        }

        return true;
    }

    private void fillOption(OptionDTO optionDTO, List<Integer> compatibleOptionsIds, List<Integer> requiredOptionsIds, Option option) throws TMobileException {
        option.setDescription(optionDTO.getDescription());
        option.setName(optionDTO.getName());
        option.setPayment(optionDTO.getPayment());
        option.setPrice(optionDTO.getPrice());
        option.setCompatible(option.isCompatible());

        if(compatibleOptionsIds.isEmpty() && requiredOptionsIds.isEmpty()) {
//            optionDAO.insert(option);
            return;
        }

        List<Option> requiredOptions = optionDAO.getByIds(requiredOptionsIds);
        if(requiredOptions.size() != requiredOptionsIds.size()) {
            throw new EntryNotFoundException("Required option not found");
        }

        for(Option reqOp : requiredOptions) {
            boolean dependentRequiredOptionIncluded = reqOp.getRequiredOptions().stream().anyMatch(op -> requiredOptions.contains(op));
            if(!dependentRequiredOptionIncluded) {
                // TODO throw exception
            }
        }

        option.setRequiredOptions(requiredOptions);

        boolean requiredOptionsCompatibleToAll = isCompatibleToAll(requiredOptions);

        if(requiredOptionsCompatibleToAll && option.isCompatible()) {
            //TODO: throw exception (option cannot be compatible to all because required options restrict the list of complatible options for option)
        }

        if(!requiredOptionsCompatibleToAll && !isCompatible(requiredOptions)) {
            // TODO: throw exception
        }

        List<Option> requiredCompatibleOptions = getCompatible(requiredOptions);

        if (compatibleOptionsIds.size() > requiredCompatibleOptions.size()) {
            // TODO: throw exception
        }

        List<Option> compatibleOptions = optionDAO.getByIds(compatibleOptionsIds);
        if(compatibleOptions.size() != compatibleOptionsIds.size()) {
            throw new EntryNotFoundException("Compatible option not found");
        }

        for(Option requiredCompatOption : requiredCompatibleOptions) {
            if(!compatibleOptions.contains(requiredCompatOption)) {
                // TODO: throw exception
            }
        }

        option.setCompatibleOptions(compatibleOptions);
    }


    @Autowired
    public OptionsService(OptionDAO optionDAO, ModelMapper mapper) {
        this.optionDAO = optionDAO;
        this.modelMapper = mapper;
    }

    @Transactional
    public OptionDTO getOption(int optionId) throws EntryNotFoundException {

        Option option = optionDAO.find(optionId);
        if(option == null) {
            throw new EntryNotFoundException("Option not found");
        }

        return modelMapper.map(option, OptionDTO.class);
    }

    @Transactional
    public List<OptionDTO> getAll() {

        List<Option> options = optionDAO.getAll();

        Type targetListType = new TypeToken<List<OptionDTO>>() {}.getType();
        return modelMapper.map(options, targetListType);
    }

    @Transactional
    public void updateOption(OptionDTO optionDTO, List<Integer> compatibleOptions, List<Integer> requiredOptions) throws TMobileException {
        Option option = optionDAO.find(optionDTO.getId());

        if(option == null) {
            throw new EntryNotFoundException("Option not found");
        }

        fillOption(optionDTO, compatibleOptions, requiredOptions, option);
        optionDAO.update(option);
    }

    @Transactional
    public void createOption(OptionDTO optionDTO, List<Integer> compatibleOptions, List<Integer> requiredOptions) throws TMobileException {

        Option option = new Option();
        fillOption(optionDTO, compatibleOptions, requiredOptions, option);

        optionDAO.insert(option);
    }

    @Transactional
    public void removeOption(int id) throws EntryNotFoundException {
        Option option = optionDAO.find(id);

        if(option == null) {
            throw new EntryNotFoundException("Option not found");
        }

        optionDAO.remove(option);
    }

    @Transactional
    public List<OptionDTO> getCompatibleOptions(int id) {
        Option option = optionDAO.find(id);

        if(option == null) {
            // TODO: throw exception
        }

        if(option.isCompatible()) {
            return getAll();
        }

        Type targetListType = new TypeToken<List<OptionDTO>>() {}.getType();
        return modelMapper.map(option.getCompatibleOptions(), targetListType);
    }

    @Transactional
    public List<OptionDTO> getRequiredOptions(int id) throws EntryNotFoundException {
        Option option = optionDAO.find(id);

        if(option == null) {
            throw new EntryNotFoundException("option not found");
        }

        List<Option> requiredOptions = option.getRequiredOptions();
        if(requiredOptions.isEmpty()) {
            return new ArrayList<>();
        }

        Type targetListType = new TypeToken<List<OptionDTO>>() {}.getType();
        return modelMapper.map(option.getCompatibleOptions(), targetListType);
    }

    @Transactional
    Map<String, List<OptionDTO>> getOptionRestrictions(List<Integer> requiredOptionsIds) {
        Map<String, List<OptionDTO>> restrictions = new HashMap<>();

        // Get compatible options
        List<Option> requiredOptions = optionDAO.getByIds(requiredOptionsIds);
        if(requiredOptions.size() != requiredOptionsIds.size()) {
            // TODO: throw exception
        }

        List<Option> compatibleOptions = null;
        if(isCompatibleToAll(requiredOptions)) {
            compatibleOptions = optionDAO.getAll();
        }
        else if(isCompatible(requiredOptions)) {
            compatibleOptions = getCompatible(requiredOptions);
        }
        else {
            // TODO throw exception (required options incompatible)
        }

        Type targetListType = new TypeToken<List<OptionDTO>>() {}.getType();
        restrictions.put("compatible", modelMapper.map(compatibleOptions, targetListType));

        // Get required options
        for(Option o : requiredOptions) {
            List<Option> reqOptions = o.getRequiredOptions();
            if(reqOptions.isEmpty()) {
                continue;
            }

            for(Option reqO : o.getRequiredOptions()) {
                if(!requiredOptions.contains(reqO)) {
                    requiredOptions.add(reqO);
                }
            }
        }
        restrictions.put("required", modelMapper.map(requiredOptions, targetListType));

        return restrictions;
    }

    @Transactional
    public List<OptionDTO> getCompatibleOptions(List<Integer> requiredOptionsIds) {
        List<Option> requiredOptions = optionDAO.getByIds(requiredOptionsIds);

        if(requiredOptions.size() != requiredOptionsIds.size()) {
            // TODO: throw exception
        }

        List<Option> compatibleOptions = getCompatible(requiredOptions);

        Type targetListType = new TypeToken<List<OptionDTO>>() {}.getType();
        return modelMapper.map(compatibleOptions, targetListType);
    }

    @Transactional
    public List<OptionDTO> getRequiredOptions(List<Integer> requiredOptionsIds) {
        List<Option> requiredOptions = optionDAO.getByIds(requiredOptionsIds);

        if(requiredOptions.size() != requiredOptionsIds.size()) {
            // TODO: throw exception
        }

//        if(isCompatibleToAll(requiredOptions)) {
//            // TODO: throw exception
//        }

        Set<Option> resultSet = new HashSet<>();
        for(Option option : requiredOptions) {
            List<Option> rOptions = option.getRequiredOptions();
            if(!rOptions.isEmpty()) {
                resultSet.addAll(rOptions);
            }
        }

        Type targetListType = new TypeToken<List<OptionDTO>>() {}.getType();
        return modelMapper.map(resultSet, targetListType);
    }
    /* @Transactional
    public void createOption() {

        Option cOpt = optionDAO.find(1);
        Option cOpt1 = optionDAO.find(2);

        Option option = new Option();

        option.setName("test1");
        option.setDescription("test1 desc");

        CompatibleOptions compatibleOptions = new CompatibleOptions();
        compatibleOptions.setRequired(true);
        compatibleOptions.setOption(option);
        compatibleOptions.setCompatibleOption(cOpt);
        option.addCompatibleOption(compatibleOptions);

        compatibleOptions = new CompatibleOptions();
        compatibleOptions.setOption(option);
        compatibleOptions.setCompatibleOption(cOpt1);
        option.addCompatibleOption(compatibleOptions);

        optionDAO.insert(option);
    }*/
}

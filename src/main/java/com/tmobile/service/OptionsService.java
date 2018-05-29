package com.tmobile.service;

import com.tmobile.dao.OptionDAO;
//import com.tmobile.dto.OptionsListEntryDTO;
import com.tmobile.dto.OptionDTO;
import com.tmobile.entity.Option;
import com.tmobile.exception.EntryNotFoundException;
import com.tmobile.exception.InconsistentDataException;
import com.tmobile.exception.TMobileException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.util.*;

@Service
public class OptionsService {

    private OptionDAO optionDAO;
    private ModelMapper modelMapper;

    private boolean isCompatible(Collection<Option> options) {
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

    private List<Option> getCompatible(Collection<Option> options) {
        List<Option> resultList = optionDAO.getAll(Option.class);
        resultList.removeAll(options);

        for(Option option : options) {

            if(option.isCompatible()) {
                continue;
            }

            resultList.removeIf(opt -> !option.getCompatibleOptions().contains(opt));
        }

        return resultList;
    }

    private boolean isCompatibleToAll(Collection<Option> options) {

        for(Option option : options) {
            if(!option.isCompatible()) {
                return false;
            }
        }

        return true;
    }

    private void fillOption(OptionDTO optionDTO, Option option) throws TMobileException {

        option.setDescription(optionDTO.getDescription());
        option.setName(optionDTO.getName());
        option.setPayment(optionDTO.getPayment());
        option.setPrice(optionDTO.getPrice());
        option.setCompatible(optionDTO.isCompatible());

        List<Integer> compatibleOptionsIds = optionDTO.getCompatibleOptions();
        List<Integer> requiredOptionsIds = optionDTO.getRequiredOptions();

        if (compatibleOptionsIds.isEmpty() && requiredOptionsIds.isEmpty()) {
//            optionDAO.insert(option);
            return;
        }

        if (compatibleOptionsIds.contains(option.getId()) || requiredOptionsIds.contains(option.getId())) {
            throw new InconsistentDataException("Option self referencing");
        }

        List<Option> requiredCompatibleOptions = null;
        if (!requiredOptionsIds.isEmpty()) {
            List<Option> requiredOptions = optionDAO.getByIds(requiredOptionsIds);

            for (Option reqOp : requiredOptions) {

                if(!reqOp.getRequiredOptions().isEmpty()) {
                    boolean dependentRequiredOptionIncluded = reqOp.getRequiredOptions().stream().anyMatch(op -> requiredOptions.contains(op));

                    if (!dependentRequiredOptionIncluded) {
                        throw new InconsistentDataException("Required options are not included");
                    }
                }
            }

            option.setRequiredOptions(requiredOptions);


            boolean requiredOptionsCompatibleToAll = isCompatibleToAll(requiredOptions);
//
            if(!requiredOptionsCompatibleToAll && option.isCompatible()) {
                throw new InconsistentDataException("Partial compatibility is required");
            }

            if (!requiredOptionsCompatibleToAll && !isCompatible(requiredOptions)) {
                throw new InconsistentDataException("Required options incompatible");
            }


            requiredCompatibleOptions = getCompatible(requiredOptions);

            if (compatibleOptionsIds.size() > requiredCompatibleOptions.size()) {
                throw new InconsistentDataException("Compatible options are not allowed for chosen required options");
            }
        }

        if (!compatibleOptionsIds.isEmpty()) {
            List<Option> compatibleOptions = optionDAO.getByIds(compatibleOptionsIds);

            for (Option compatOption : compatibleOptions) {
//            if(!compatibleOptions.contains(requiredCompatOption)) {
                if (!requiredOptionsIds.isEmpty() && !requiredCompatibleOptions.contains(compatOption)) {
                    throw new InconsistentDataException("Chosen compatible option is not allowed for required options");
                }
            }

            option.setCompatibleOptions(compatibleOptions);

        }
    }

    @Autowired
    public OptionsService(OptionDAO optionDAO, ModelMapper mapper) {
        this.optionDAO = optionDAO;
        this.modelMapper = mapper;
    }

    @Transactional
    public OptionDTO getOption(int optionId) throws EntryNotFoundException {

        Option option = optionDAO.findById(optionId, Option.class);

        return modelMapper.map(option, OptionDTO.class);
    }

    @Transactional
    public List<OptionDTO> getAll() {

        List<Option> options = optionDAO.getAll(Option.class);

        Type targetListType = new TypeToken<List<OptionDTO>>() {}.getType();
        return modelMapper.map(options, targetListType);
    }

    @Transactional
    public void updateOption(OptionDTO optionDTO) throws TMobileException {
        Option option = optionDAO.findById(optionDTO.getId(), Option.class);

        fillOption(optionDTO, option);
        optionDAO.update(option);
    }

    @Transactional
//    public void createOption(OptionDTO optionDTO, List<Integer> compatibleOptions, List<Integer> requiredOptions) throws TMobileException {
    public void createOption(OptionDTO optionDTO) throws TMobileException {


        Option option = new Option();
//        fillOption(optionDTO, compatibleOptions, requiredOptions, option);
        fillOption(optionDTO, option);

        optionDAO.insert(option);
    }

    @Transactional
    public void removeOptions(List<Integer> optionsIds) throws EntryNotFoundException {
        List<Option> options = optionDAO.getByIds(optionsIds);

        for(Option o : options) {

            o.setRequiredOptions(null);
            o.setCompatibleOptions(null);
        }

        optionDAO.remove(options);
    }

    @Transactional
    public List<OptionDTO> getCompatibleOptions(int id) throws EntryNotFoundException {

        Option option = optionDAO.findById(id, Option.class);

        List<Option> compatibleOptions = null;
        if(option.isCompatible()) {
            compatibleOptions = optionDAO.getAll(Option.class);
        }
        else if(!option.getRequiredOptions().isEmpty()){
            compatibleOptions = getCompatible(option.getRequiredOptions());
        }
        else {
            compatibleOptions = option.getCompatibleOptions();
        }

        compatibleOptions.remove(option);

        Type targetListType = new TypeToken<List<OptionDTO>>() {}.getType();
        return modelMapper.map(compatibleOptions, targetListType);
    }

    @Transactional
    public List<OptionDTO> getRequiredOptions(int id) throws EntryNotFoundException {
        Option option = optionDAO.findById(id, Option.class);

        List<Option> requiredOptions = option.getRequiredOptions();

        Type targetListType = new TypeToken<List<OptionDTO>>() {}.getType();
        return modelMapper.map(requiredOptions, targetListType);
    }

    @Transactional
    public Map<String, Set<OptionDTO>> getRequiredOptionsRestrictions(List<Integer> requiredOptionsIds) throws TMobileException {
        Map<String, Set<OptionDTO>> restrictions = new HashMap<>();

        List<Option> requiredOptions = optionDAO.getByIds(requiredOptionsIds);

        // Get required options
        Set<Option> reqOptionsWithDeps = new HashSet<>(requiredOptions);
//        reqOptionsWithDeps.addAll(requiredOptions);

        for(Option o : requiredOptions) {
            List<Option> reqDeps = o.getRequiredOptions();
            reqOptionsWithDeps.addAll(reqDeps);
        }

        Type targetListType = new TypeToken<Set<OptionDTO>>() {}.getType();
        restrictions.put("required", modelMapper.map(reqOptionsWithDeps, targetListType));

        // Get compatible options

        List<Option> compatibleOptions = null;
        if(isCompatibleToAll(reqOptionsWithDeps) || isCompatible(reqOptionsWithDeps)) {
            compatibleOptions = getCompatible(reqOptionsWithDeps);
        }
        else {
            throw new InconsistentDataException("required options incompatible");
        }

//        targetListType = new TypeToken<List<OptionDTO>>() {}.getType();
        restrictions.put("compatible", modelMapper.map(compatibleOptions, targetListType));
        return restrictions;
    }

    @Transactional
    public List<OptionDTO> getCompatibleOptions(List<Integer> requiredOptionsIds) throws TMobileException {
        List<Option> requiredOptions = optionDAO.getByIds(requiredOptionsIds);

        List<Option> compatibleOptions = getCompatible(requiredOptions);

        Type targetListType = new TypeToken<List<OptionDTO>>() {}.getType();
        return modelMapper.map(compatibleOptions, targetListType);
    }

    @Transactional
    public List<OptionDTO> getRequiredOptions(List<Integer> optionsIds) throws TMobileException {
        List<Option> options = optionDAO.getByIds(optionsIds);

        Set<Option> resultSet = new HashSet<>();

        for(Option option : options) {
            List<Option> rOptions = option.getRequiredOptions();
            if(!rOptions.isEmpty()) {
                resultSet.addAll(rOptions);
            }
        }

        Type targetListType = new TypeToken<List<OptionDTO>>() {}.getType();
        return modelMapper.map(resultSet, targetListType);
    }
}

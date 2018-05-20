package com.tmobile.controller;


import com.tmobile.dto.OptionDTO;
import com.tmobile.dto.StatusDTO;
import com.tmobile.exception.EntryNotFoundException;
import com.tmobile.exception.TMobileException;
import com.tmobile.service.OptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@RestController
@RequestMapping("/options")
public class OptionsController {

    private OptionsService service;

    @Autowired
    public OptionsController(OptionsService service) {
        this.service = service;
    }

    @GetMapping
    public List<OptionDTO> getAll() {

        return service.getAll();
    }

    @DeleteMapping("/{optionId}")
    public StatusDTO remove(@PathVariable int optionId) throws EntryNotFoundException {

        service.removeOption(optionId);
        // return success status
        return new StatusDTO();
    }

    @GetMapping("/{optionId}")
    public OptionDTO getOption(@PathVariable int optionId) throws EntryNotFoundException {

        OptionDTO option = service.getOption(optionId);
        return option;
    }

    @PostMapping
    public void createOption(OptionDTO option, List<Integer> compatibleOptions, List<Integer> requiredOptions) throws TMobileException {
        service.createOption(option, compatibleOptions, requiredOptions);
    }

    @PutMapping
    public void updateOption(OptionDTO option, List<Integer> compatibleOptions, List<Integer> requiredOptions) throws TMobileException {
        service.updateOption(option, compatibleOptions, requiredOptions);
    }

    @GetMapping("/compatible")
    public List<OptionDTO> getCompatibleOptions(@RequestParam List<Integer> requiredOptions) {
//    public List<OptionDTO> getCompatibleOptions(@RequestBody List<Integer> requiredOptions) {
        return service.getCompatibleOptions(requiredOptions);
    }

    @GetMapping("required")
    public List<OptionDTO> getRequredOptions(@RequestParam List<Integer> requiredOptionsIds) {
        return service.getRequiredOptions(requiredOptionsIds);
    }
}

package com.tmobile.controller;


import com.tmobile.dto.OptionDTO;
import com.tmobile.dto.StatusDTO;
import com.tmobile.exception.EntryNotFoundException;
import com.tmobile.exception.TMobileException;
import com.tmobile.service.OptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

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

//    @DeleteMapping("/{optionId}")
//    public StatusDTO removeTariffs(@PathVariable int optionId) throws EntryNotFoundException {
    @DeleteMapping
    @ResponseStatus(value = HttpStatus.OK)
//    public void removeTariffs(@RequestParam("optionsIds") List<Integer> optionsIds) throws EntryNotFoundException {
        public void remove(@RequestBody List<Integer> optionsIds) throws EntryNotFoundException {
        service.removeOptions(optionsIds);
//        service.removeOption(optionId);
        // return success status
//        return new StatusDTO();
    }

    @GetMapping("/{optionId}")
    public OptionDTO getOption(@PathVariable int optionId) throws EntryNotFoundException {

        OptionDTO option = service.getOption(optionId);
        return option;
    }

    @PostMapping
//    public void createOption(OptionDTO option, List<Integer> compatibleOptions, List<Integer> requiredOptions) throws TMobileException {
    public void createOption(@RequestBody OptionDTO option) throws TMobileException {
        service.createOption(option);
    }

    @PutMapping
//    public void updateOption(OptionDTO option, List<Integer> compatibleOptions, List<Integer> requiredOptions) throws TMobileException {
    public void updateOption(@RequestBody OptionDTO option) throws TMobileException {

//        service.updateOption(option, compatibleOptions, requiredOptions);
        service.updateOption(option);

    }

    @GetMapping("/compatible")
    public List<OptionDTO> getCompatibleOptions(@RequestParam("requiredOptions") List<Integer> requiredOptions) {
//    public List<OptionDTO> getCompatibleOptions(@RequestBody List<Integer> requiredOptions) {
        return service.getCompatibleOptions(requiredOptions);
    }

    @GetMapping("/required")
    public List<OptionDTO> getRequredOptions(@RequestParam("optionsIds") List<Integer> optionsIds) {
        return service.getRequiredOptions(optionsIds);
    }

    @GetMapping("/restrictions")
    public Map<String, Set<OptionDTO>> getRequiredOptionsRestrictions(@RequestParam("requiredOptions") List<Integer> requiredOptionsIds) {
        return service.getRequiredOptionsRestrictions(requiredOptionsIds);
    }
}

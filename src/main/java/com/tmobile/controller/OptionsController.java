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

    @DeleteMapping
    public HttpStatus remove(@RequestBody List<Integer> optionsIds) throws EntryNotFoundException {
        service.removeOptions(optionsIds);
        return HttpStatus.OK;
    }

    @GetMapping("/{optionId}")
    public OptionDTO getOption(@PathVariable int optionId) throws EntryNotFoundException {

        OptionDTO option = service.getOption(optionId);
        return option;
    }

    @PostMapping
    public HttpStatus createOption(@RequestBody OptionDTO option) throws TMobileException {
        service.createOption(option);
        return HttpStatus.OK;
    }

    @PutMapping
    public HttpStatus updateOption(@RequestBody OptionDTO option) throws TMobileException {

        service.updateOption(option);
        return HttpStatus.OK;
    }

    @GetMapping("/compatible")
    public List<OptionDTO> getCompatibleOptions(@RequestParam("requiredOptions") List<Integer> requiredOptions) throws TMobileException {

        return service.getCompatibleOptions(requiredOptions);
    }

    @GetMapping("/required")
    public List<OptionDTO> getRequiredOptions(@RequestParam("optionsIds") List<Integer> optionsIds) throws TMobileException {
        return service.getRequiredOptions(optionsIds);
    }

    @GetMapping("/restrictions")
    public Map<String, Set<OptionDTO>> getRequiredOptionsRestrictions(@RequestParam("requiredOptions") List<Integer> requiredOptionsIds) throws TMobileException {
        return service.getRequiredOptionsRestrictions(requiredOptionsIds);
    }
}

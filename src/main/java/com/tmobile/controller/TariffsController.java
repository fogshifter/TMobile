package com.tmobile.controller;

import com.tmobile.dto.OptionDTO;
import com.tmobile.dto.TariffDTO;
import com.tmobile.dto.TariffsListEntryDTO;
import com.tmobile.exception.EntryNotFoundException;
import com.tmobile.exception.TMobileException;
import com.tmobile.service.OptionsService;
import com.tmobile.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tariffs")
public class TariffsController {

    private TariffService service;

    @Autowired
    public TariffsController(TariffService service) {
        this.service = service;
    }

    @GetMapping
    public List<TariffsListEntryDTO> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{tariffId}")
    @ResponseStatus(value = HttpStatus.OK)
    public void remove(@PathVariable int tariffId) throws TMobileException {
        service.removeTariff(tariffId);
    }

    @GetMapping("/{tariffId")
    public TariffDTO getTariff(@PathVariable int tariffId) throws TMobileException {

        TariffDTO tariff = service.getTariff(tariffId);
        return tariff;
    }

    @GetMapping("/compatible/{tariffId}")
    public List<OptionDTO> getCompatibleOptions(@PathVariable int tariffId) throws EntryNotFoundException {
        return service.getCompatibleOptions(tariffId);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public void createOption(@RequestBody TariffDTO tariff) throws TMobileException {

        service.createTariff(tariff);
    }

    @PutMapping
    @ResponseStatus(value = HttpStatus.OK)
//    public void updateTariff(TariffDTO tariff, List<Integer> compatibleOptions) throws TMobileException {
    public void updateTariff(@RequestBody TariffDTO tariff) throws TMobileException {
        service.updateTariff(tariff);
    }
}

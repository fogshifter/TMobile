package com.tmobile.service;


import com.tmobile.dao.OptionDAO;
import com.tmobile.dao.TariffDAO;
import com.tmobile.dto.OptionDTO;
import com.tmobile.dto.TariffDTO;
import com.tmobile.dto.TariffsListEntryDTO;
import com.tmobile.entity.Option;
import com.tmobile.entity.Tariff;
import com.tmobile.exception.EntryNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.util.List;

@Service
public class TariffService {

    private TariffDAO tariffDAO;
    private OptionDAO optionDAO;
    private ModelMapper modelMapper;

    public TariffService(TariffDAO tariffDAO, OptionDAO optionDAO, ModelMapper mapper) {
        this.optionDAO = optionDAO;
        this.tariffDAO = tariffDAO;
        this.modelMapper = mapper;
    }

    @Transactional
    public List<TariffsListEntryDTO> getAll() {
        List<Tariff> tariffs = tariffDAO.getAll();

        Type targetListType = new TypeToken<List<TariffsListEntryDTO>>() {
        }.getType();
        return modelMapper.map(tariffs, targetListType);
    }

    // TODO : use validation to check for not empty tariff in new/edit contract
    /*@Transactional
    public List<OptionDTO> getCompatibleOptions() {

        Tariff defaultTariff = tariffDAO.getDefaultTariff();

        if (defaultTariff == null) {
            return new ArrayList<OptionDTO>();
        }

        Type targetListType = new TypeToken<List<OptionDTO>>() {
        }.getType();
        return modelMapper.map(defaultTariff.getCompatibleOptions(), targetListType);
    }*/

    @Transactional
    public List<OptionDTO> getCompatibleOptions(int tariffId) throws EntryNotFoundException {

        Tariff tariff = tariffDAO.find(tariffId);

        if(tariff == null) {
            throw new EntryNotFoundException("Tariff not found");
        }

        Type targetListType = new TypeToken<List<OptionDTO>>() {
        }.getType();
        return modelMapper.map(tariff.getCompatibleOptions(), targetListType);
    }

    @Transactional
    public TariffDTO getTariff(int id) throws EntryNotFoundException {
        Tariff tariff = tariffDAO.find(id);

        if(tariff == null) {
            throw new EntryNotFoundException("Tariff not found");
        }

        return modelMapper.map(tariff, TariffDTO.class);
    }

    @Transactional
    public void removeTariff(int id) throws EntryNotFoundException {
        Tariff tariff = tariffDAO.find(id);

        if(tariff == null) {
            throw new EntryNotFoundException("Tariff not found");
        }

        tariffDAO.remove(tariff);
    }

    // TODO: move converting DTO to Entities from services to converters layer
    @Transactional
    public void createTariff(TariffDTO tariffDTO) throws EntryNotFoundException {

        List<Integer> compatibleOptionsIds = tariffDTO.getCompatibleOptions();
        List<Option> compatibleOptions = optionDAO.getByIds(tariffDTO.getCompatibleOptions());
        if(compatibleOptions.size() != compatibleOptionsIds.size()) {
            throw new EntryNotFoundException("Compatible option not found");
        }

        Tariff tariff = modelMapper.map(tariffDTO, Tariff.class);

//        for(Option option : compatibleOptions) {
//            tariff.addCompatibleOption(option);
//        }
        tariff.setCompatibleOptions(compatibleOptions);

        tariffDAO.insert(tariff);
//        TariffOptions compatibleOptions = new TariffOptions();
    }

    @Transactional
    public void updateTariff(TariffDTO tariffDTO) throws EntryNotFoundException {

        Tariff tariff = tariffDAO.find(tariffDTO.getId());

        if(tariff == null) {
            throw new EntryNotFoundException("Tariff not found");
        }

        List<Option> compatibleOptions = optionDAO.getByIds(tariffDTO.getCompatibleOptions());

        if(compatibleOptions.size() != tariffDTO.getCompatibleOptions().size()) {
            throw new EntryNotFoundException("Compatible option not found");
        }

        tariff.setName(tariffDTO.getName());
        tariff.setDescription(tariffDTO.getDescription());
        tariff.setPrice(tariffDTO.getPrice());

        tariff.setCompatibleOptions(compatibleOptions);

        tariffDAO.update(tariff);
    }
}

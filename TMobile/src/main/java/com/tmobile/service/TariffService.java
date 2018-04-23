package com.tmobile.service;


import com.tmobile.dao.TariffDAO;
import com.tmobile.dto.OptionDTO;
import com.tmobile.dto.TariffDTO;
import com.tmobile.entity.Tariff;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class TariffService {

    private TariffDAO tariffDAO;
    private ModelMapper modelMapper;

    public TariffService(TariffDAO tariffDAO, ModelMapper mapper) {
        this.tariffDAO = tariffDAO;
        this.modelMapper = mapper;
    }

    @Transactional
    public List<TariffDTO> getAll() {
        List<Tariff> tariffs = tariffDAO.getAll();

        Type targetListType = new TypeToken<List<TariffDTO>>() {}.getType();
        return modelMapper.map(tariffs, targetListType);
    }

    @Transactional
    public List<OptionDTO> getDefaultTariffOptions() {

        Tariff defaultTariff = tariffDAO.getDefaultTariff();

        if(defaultTariff == null) {
            return new ArrayList<OptionDTO>();
        }

        Type targetListType = new TypeToken<List<OptionDTO>>() {}.getType();
        return modelMapper.map(defaultTariff.getDefaultOptions(), targetListType);
    }
}

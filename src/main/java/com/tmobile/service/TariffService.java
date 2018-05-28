package com.tmobile.service;


import com.tmobile.dao.ContractDAO;
import com.tmobile.dao.OptionDAO;
import com.tmobile.dao.TariffDAO;
import com.tmobile.dto.OptionDTO;
import com.tmobile.dto.TariffDTO;
import com.tmobile.dto.TariffsListEntryDTO;
import com.tmobile.entity.Contract;
import com.tmobile.entity.Option;
import com.tmobile.entity.Tariff;
import com.tmobile.exception.EntryNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.util.List;

@Service
public class TariffService {

    private TariffDAO tariffDAO;
    private OptionDAO optionDAO;
    private ContractDAO contractDAO;
    private ModelMapper modelMapper;
    private MessageService messageService;

    @Autowired
    public TariffService(TariffDAO tariffDAO, OptionDAO optionDAO, ContractDAO contractDAO, ModelMapper mapper, MessageService messageService) {
        this.optionDAO = optionDAO;
        this.tariffDAO = tariffDAO;
        this.contractDAO = contractDAO;
        this.modelMapper = mapper;
        this.messageService = messageService;
    }

    @Transactional
    public List<TariffsListEntryDTO> getAll() {
        List<Tariff> tariffs = tariffDAO.getAll(Tariff.class);

        Type targetListType = new TypeToken<List<TariffsListEntryDTO>>() {
        }.getType();
        return modelMapper.map(tariffs, targetListType);
    }

    @Transactional
    public List<OptionDTO> getCompatibleOptions(int tariffId) throws EntryNotFoundException {

        Tariff tariff = tariffDAO.findById(tariffId, Tariff.class);

        Type targetListType = new TypeToken<List<OptionDTO>>() {
        }.getType();
        return modelMapper.map(tariff.getCompatibleOptions(), targetListType);
    }

    @Transactional
    public TariffDTO getTariff(int id) throws EntryNotFoundException {
        Tariff tariff = tariffDAO.findById(id, Tariff.class);

        return modelMapper.map(tariff, TariffDTO.class);
    }

    @Transactional
//    public void removeTariff(int id) throws EntryNotFoundException {
    public void removeTariffs(List<Integer> tariffsIds) throws EntryNotFoundException {
//        Tariff tariff = tariffDAO.findById(id);

        List<Tariff> tariffs = tariffDAO.getByIds(tariffsIds);

//        if(tariff == null) {
        if(tariffs.size() != tariffsIds.size()) {
            throw new EntryNotFoundException("Tariff not found");
        }

        for(Tariff tariff : tariffs) {
            List<Contract> contracts = tariff.getTariffContracts();

            // change contract tariff to default tariff
            for(Contract contract : contracts) {
                contract.setTariff(tariffDAO.getDefaultTariff());
                contractDAO.update(contract);
            }
            tariff.setTariffContracts(null);
        }

        tariffDAO.remove(tariffs);
        messageService.sendMessage();
    }

    // TODO: move converting DTO to Entities from services to converters layer
    @Transactional
    public void createTariff(TariffDTO tariffDTO) throws EntryNotFoundException {

        List<Integer> compatibleOptionsIds = tariffDTO.getCompatibleOptions();
        List<Option> compatibleOptions = optionDAO.getByIds(tariffDTO.getCompatibleOptions());

        Tariff tariff = modelMapper.map(tariffDTO, Tariff.class);

//        for(Option option : compatibleOptions) {
//            tariff.addCompatibleOption(option);
//        }
        tariff.setCompatibleOptions(compatibleOptions);

        tariffDAO.insert(tariff);
//        TariffOptions compatibleOptions = new TariffOptions();
        messageService.sendMessage();
    }

    @Transactional
    public void updateTariff(TariffDTO tariffDTO) throws EntryNotFoundException {

        Tariff tariff = tariffDAO.findById(tariffDTO.getId(), Tariff.class);

        List<Option> compatibleOptions = optionDAO.getByIds(tariffDTO.getCompatibleOptions());

        tariff.setName(tariffDTO.getName());
        tariff.setDescription(tariffDTO.getDescription());
        tariff.setPrice(tariffDTO.getPrice());

        tariff.setCompatibleOptions(compatibleOptions);

        tariffDAO.update(tariff);

        messageService.sendMessage();
    }
}

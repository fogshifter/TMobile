package com.tmobile.service;

import com.tmobile.dao.ContractDAO;
import com.tmobile.dao.OptionDAO;
import com.tmobile.dao.TariffDAO;
import com.tmobile.dto.OptionDTO;
import com.tmobile.dto.TariffDTO;
import com.tmobile.dto.TariffsListEntryDTO;
import com.tmobile.entity.Option;
import com.tmobile.entity.Tariff;
import com.tmobile.exception.EntryNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TariffServiceTest {

    private static final Integer TARIFF_ID = 1;
    private static final Integer OPTION_ID = 1;
    private static final String TARIFF_NAME = "name";

    @Mock
    private TariffDAO tariffDAO;
    @Mock
    private OptionDAO optionDAO;
    @Mock
    private ContractDAO contractDAO;
    @Mock
    private MessageService messageService;

    private ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private TariffService service;


    @Before
    public void init() {
        service = new TariffService(tariffDAO, optionDAO, contractDAO, modelMapper, messageService);
    }

    @Test
    public void getAll() {
        when(tariffDAO.getAll(Tariff.class)).thenReturn(Arrays.asList(createTariffEntity(TARIFF_ID)));
        List<TariffsListEntryDTO> result = service.getAll();
        Assert.assertArrayEquals(Arrays.asList(createTariffListEntryDto(TARIFF_ID)).toArray(),
                result.toArray());
    }

    @Test
    public void getCompatibleOptions() throws EntryNotFoundException {
        Tariff tariff = createTariffEntity(TARIFF_ID);
        tariff.setCompatibleOptions(Arrays.asList(createOptionEntity(OPTION_ID)));
        when(tariffDAO.findById(TARIFF_ID, Tariff.class)).thenReturn(tariff);
        List<OptionDTO> result = service.getCompatibleOptions(TARIFF_ID);
        Assert.assertArrayEquals(Arrays.asList(createOptionDto(OPTION_ID)).toArray(),
                result.toArray());
    }

    @Test
    public void getTariff() throws EntryNotFoundException {
        when(tariffDAO.findById(TARIFF_ID, Tariff.class)).thenReturn(createTariffEntity(TARIFF_ID));
        TariffDTO result = service.getTariff(TARIFF_ID);
        Assert.assertEquals(createTariffDto(TARIFF_ID), result);
    }

    @Test
    public void removeTariffs() throws EntryNotFoundException {
        List<Tariff> tariffs = Arrays.asList(createTariffEntity(TARIFF_ID));
        when(tariffDAO.getByIds(Arrays.asList(TARIFF_ID))).thenReturn(tariffs);

        service.removeTariffs(Arrays.asList(TARIFF_ID));

        verify(tariffDAO).remove(tariffs);
        verify(messageService).sendMessage();
    }

    @Test(expected = EntryNotFoundException.class)
    public void removeTariffsException() throws EntryNotFoundException {
        when(tariffDAO.getByIds(Arrays.asList(TARIFF_ID))).thenReturn(new ArrayList<>());
        service.removeTariffs(Arrays.asList(TARIFF_ID));
    }

    @Test
    public void createTariff() throws EntryNotFoundException {
        TariffDTO dto = createTariffDto(TARIFF_ID);
        dto.setCompatibleOptions(Arrays.asList(OPTION_ID));
        List<Option> options = Arrays.asList(createOptionEntity(OPTION_ID));
        when(optionDAO.getByIds(dto.getCompatibleOptions())).thenReturn(options);
        service.createTariff(dto);

        Tariff tariff = createTariffEntity(TARIFF_ID);
        tariff.setCompatibleOptions(options);
        verify(tariffDAO).insert(tariff);
        verify(messageService).sendMessage();
    }

    @Test
    public void updateTariff() throws EntryNotFoundException {
        TariffDTO dto = createTariffDto(TARIFF_ID);
        dto.setName(TARIFF_NAME);
        Tariff tariff = createTariffEntity(TARIFF_ID);

        when(tariffDAO.findById(dto.getId(), Tariff.class)).thenReturn(createTariffEntity(TARIFF_ID));
        when(optionDAO.getByIds(dto.getCompatibleOptions())).thenReturn(new ArrayList<>());
        service.updateTariff(dto);

        tariff.setName(TARIFF_NAME);
        tariff.setCompatibleOptions(new ArrayList<>());

        verify(tariffDAO).update(tariff);
        verify(messageService).sendMessage();
    }

    private Tariff createTariffEntity(Integer tariffId) {
        Tariff tariff = new Tariff();
        tariff.setId(tariffId);
        return tariff;
    }

    private TariffsListEntryDTO createTariffListEntryDto(Integer tariffId) {
        TariffsListEntryDTO dto = new TariffsListEntryDTO();
        dto.setId(tariffId);
        return dto;
    }


    private TariffDTO createTariffDto (Integer tariffId) {
        TariffDTO dto = new TariffDTO();
        dto.setId(tariffId);
        return dto;
    }

    private Option createOptionEntity(Integer optionId) {
        Option option = new Option();
        option.setId(optionId);
        return option;
    }

    private OptionDTO createOptionDto(Integer optionId) {
        OptionDTO option = new OptionDTO();
        option.setId(optionId);
        return option;
    }
}

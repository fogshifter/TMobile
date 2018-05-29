package com.tmobile.service;

import com.tmobile.config.AppConfig;
import com.tmobile.config.HibernateConfig;
import com.tmobile.dao.OptionDAO;
import com.tmobile.dto.OptionDTO;
import com.tmobile.entity.Option;
import com.tmobile.exception.EntryNotFoundException;
import com.tmobile.exception.TMobileException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OptionServiceTest {

    private static final Integer OPTION_ID_1 = 1;
    private static final Integer OPTION_ID_2 = 2;
    private static final String OPTION_NAME = "name";

    private ModelMapper modelMapper = new ModelMapper();

    @Mock
    private OptionDAO optionDAO;

    private OptionsService optionsService;

    @Before
    public void init() {
        optionsService = new OptionsService(optionDAO, modelMapper);
    }

    @Test
    public void getOption() throws EntryNotFoundException {
        when(optionDAO.findById(OPTION_ID_1, Option.class)).thenReturn(createOptionEntity(OPTION_ID_1));
        OptionDTO result = optionsService.getOption(OPTION_ID_1);
        Assert.assertEquals(createOptionDto(OPTION_ID_1), result);
    }

    @Test
    public void getAll() {
        when(optionDAO.getAll(Option.class)).thenReturn(Arrays.asList(createOptionEntity(OPTION_ID_1)));
        List<OptionDTO> result = optionsService.getAll();
        Assert.assertArrayEquals(Arrays.asList(createOptionDto(OPTION_ID_1)).toArray(), result.toArray());
    }

    @Test
    public void updateOption() throws TMobileException {
        Option option = createOptionEntity(OPTION_ID_1);
        when(optionDAO.findById(OPTION_ID_1, Option.class)).thenReturn(option);
        optionsService.updateOption(createOptionDto(OPTION_ID_1));
        verify(optionDAO).update(option);
    }

    @Test
    public void createOption() throws TMobileException {
        OptionDTO dto = new OptionDTO();
        dto.setName(OPTION_NAME);
        optionsService.createOption(dto);
        Option option = new Option();
        option.setName(OPTION_NAME);
        verify(optionDAO).insert(option);
    }

    @Test
    public void removeOptions() throws EntryNotFoundException {
        List<Integer> request = Arrays.asList(OPTION_ID_1, OPTION_ID_2);
        Option option1 = createOptionEntity(OPTION_ID_1);
        option1.setCompatibleOptions(new ArrayList<>());
        option1.setRequiredOptions(new ArrayList<>());
        Option option2 = createOptionEntity(OPTION_ID_2);
        option1.setCompatibleOptions(new ArrayList<>());
        option1.setRequiredOptions(new ArrayList<>());
        when(optionDAO.getByIds(request)).thenReturn(Arrays.asList(option1, option2));
        optionsService.removeOptions(request);
        verify(optionDAO).remove(Arrays.asList(option1, option2));
    }

    @Test
    public void getRequiredOptionsRestrictionsWithOneCompatibleOptions() throws TMobileException {
        Option option = createOptionEntity(OPTION_ID_1);
        option.setCompatible(false);
        option.setCompatibleOptions(Arrays.asList(createOptionEntity(OPTION_ID_2)));
        when(optionDAO.findById(OPTION_ID_1, Option.class)).thenReturn(option);
        List<OptionDTO> result = optionsService.getCompatibleOptions(OPTION_ID_1);
        Assert.assertArrayEquals(Arrays.asList(createOptionDto(OPTION_ID_2)).toArray(), result.toArray());
    }

    @Test
    public void getRequiredOptionsRestrictionsIfCompatible() throws TMobileException {
        Option option = createOptionEntity(OPTION_ID_1);
        when(optionDAO.findById(OPTION_ID_1, Option.class)).thenReturn(option);
        when(optionDAO.getAll(Option.class)).thenReturn(Arrays.asList(
                createOptionEntity(OPTION_ID_1),
                createOptionEntity(OPTION_ID_2)));
        List<OptionDTO> result = optionsService.getCompatibleOptions(OPTION_ID_1);
        Assert.assertArrayEquals(Arrays.asList(createOptionDto(OPTION_ID_2)).toArray(), result.toArray());
    }

    @Test
    public void getRequiredOptionsRestrictionsWithRequiredOptions() throws TMobileException {
        Option option = createOptionEntity(OPTION_ID_1);
        option.setCompatible(false);
        option.setRequiredOptions(Arrays.asList(createOptionEntity(OPTION_ID_2)));
        when(optionDAO.findById(OPTION_ID_1, Option.class)).thenReturn(option);
        List<Option> request = new ArrayList<>();
        request.add(createOptionEntity(OPTION_ID_1));
        request.add(createOptionEntity(OPTION_ID_2));
        when(optionDAO.getAll(Option.class)).thenReturn(request);
        List<OptionDTO> result = optionsService.getCompatibleOptions(OPTION_ID_1);
        Assert.assertArrayEquals(Arrays.asList(createOptionDto(OPTION_ID_1)).toArray(), result.toArray());
    }

    @Test
    public void getRequiredOptions() throws TMobileException {
        Option option = createOptionEntity(OPTION_ID_1);
        option.setRequiredOptions(new ArrayList<>());
        when(optionDAO.findById(OPTION_ID_1, Option.class)).thenReturn(option);
        List<OptionDTO> result = optionsService.getRequiredOptions(OPTION_ID_1);
        Assert.assertTrue(result.isEmpty());
    }

    private Option createOptionEntity(Integer optionId) {
        Option option = new Option();
        option.setId(optionId);
        return option;
    }

    private OptionDTO createOptionDto(Integer optionId) {
        OptionDTO dto = new OptionDTO();
        dto.setId(optionId);
        return dto;
    }
}

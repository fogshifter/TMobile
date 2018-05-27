package com.tmobile.service;

import com.tmobile.dao.ContractDAO;
import com.tmobile.dao.OptionDAO;
import com.tmobile.dao.TariffDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TariffServiceTest {

    @Mock
    private TariffDAO tariffDAO;
    @Mock
    private OptionDAO optionDAO;
    @Mock
    private ContractDAO contractDAO;
    @Mock
    private MessageService messageService;

    @InjectMocks
    private TariffService service;

    @Test
    public void getCompatibleOptions() {

    }

    @Test
    public void getTariff() {

    }

    @Test
    public void removeTariffs() {

    }

    @Test
    public void createTariff() {

    }

    @Test
    public void updateTariff() {

    }
}

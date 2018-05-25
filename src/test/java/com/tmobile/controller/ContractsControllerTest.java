package com.tmobile.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmobile.config.AppConfig;
import com.tmobile.dto.ContractInfoDTO;
import com.tmobile.exception.EntryNotFoundException;
import com.tmobile.service.ContractService;
import com.tmobile.service.TariffService;
import com.tmobile.service.UserService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
@WebAppConfiguration
public class ContractsControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private TariffService tariffService;

    @Mock
    private ContractService contractService;

    @InjectMocks
    private ContractsController controller;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    private static final int TARIFF_ID = 1;

    @Before
    public void setup() throws EntryNotFoundException {
        MockitoAnnotations.initMocks(this);

        controller = wac.getBean(ContractsController.class);

        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
        //when(userService.isEmailAvailable()).thenReturn();
        when(tariffService.getCompatibleOptions(TARIFF_ID)).thenReturn(new ArrayList<>());
        //when(contractService.registerContract()).thenReturn(Optional.empty());
        //when(contractService.editContract()).thenReturn(Optional.empty());
    }

    @Test
    public void syncNewContractInfoTest() throws Exception {
        String url = "/contracts/sync_new_contract_info";
        ContractInfoDTO dto = new ContractInfoDTO();
        dto.setTariffId(TARIFF_ID);

        this.mockMvc.perform(post(url)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }
}

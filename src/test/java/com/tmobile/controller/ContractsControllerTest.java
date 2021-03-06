package com.tmobile.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmobile.auth.TMobileUserDetails;
import com.tmobile.config.AppConfig;
import com.tmobile.dto.ContractInfoDTO;
import com.tmobile.exception.EntryNotFoundException;
import com.tmobile.exception.TMobileException;
import com.tmobile.service.ContractService;
import com.tmobile.service.TariffService;
import com.tmobile.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringJUnitWebConfig(value = AppConfig.class)
public class ContractsControllerTest {

    private static final Integer CONTRACT_ID = 1;


    @Mock
    private UserService userService;

    @Mock
    private TariffService tariffService;

    @Mock
    private ContractService contractService;

    @InjectMocks
    private ContractsController controller;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    private MockMvc mockMvc;

    private static final int TARIFF_ID = 1;
    private static final String EMAIL = "email";

    @Before
    public void setup() throws TMobileException {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .alwaysDo(print())
                .apply(springSecurity(springSecurityFilterChain))
                .build();

        when(contractService.getContract(CONTRACT_ID)).thenReturn(new ContractInfoDTO());
        when(userService.isEmailAvailable(EMAIL)).thenReturn(true);
        when(tariffService.getCompatibleOptions(TARIFF_ID)).thenReturn(new ArrayList<>());
    }

    @Test
    @WithMockUser(roles = "MANAGER")
    public void isEmailAvailable() throws Exception {
        String url = "/contracts/check_email_uniqueness";
        ContractInfoDTO dto = new ContractInfoDTO();
        dto.setEmail(EMAIL);
        dto.setTariffId(TARIFF_ID);

        this.mockMvc.perform(get(url).param("email", EMAIL)
                .with(csrf())
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "CUSTOMER")
    public void getContract() throws Exception {
        String url = "/contracts/{contractId}";

        this.mockMvc.perform(get(url, CONTRACT_ID)
                .with(csrf())
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(view().name("control_template"));
    }

    @Test
    @WithMockUser(roles = "MANAGER")
    public void createContract() throws Exception {
        String url = "/contracts";
        ContractInfoDTO dto = new ContractInfoDTO();
        dto.setTariffId(TARIFF_ID);

        this.mockMvc.perform(post(url)
                .with(csrf())
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "MANAGER")
//    @WithUserDetails(userDetailsServiceBeanName = "providerUserDetailsService")
    public void editContract() throws Exception {
        String url = "/contracts";
        ContractInfoDTO dto = new ContractInfoDTO();
        dto.setTariffId(TARIFF_ID);

        this.mockMvc.perform(put(url)
                .with(csrf())
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }
}

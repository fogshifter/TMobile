package com.tmobile.controller;


import com.tmobile.config.AppConfig;
import com.tmobile.dto.ContractInfoDTO;
import com.tmobile.dto.ContractsListEntryDTO;
import com.tmobile.exception.TMobileException;
import com.tmobile.service.ContractService;
import com.tmobile.service.OptionsService;
import com.tmobile.service.TariffService;
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

import javax.servlet.http.HttpSession;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringJUnitWebConfig(value = AppConfig.class)
public class CustomerControllerTest {

    private static final Integer USER_ID = 1;

    @Mock
    private HttpSession session;

    @Mock
    private TariffService tariffService;

    @Mock
    private ContractService contractService;

    @Mock
    private OptionsService optionsService;

    @InjectMocks
    private CustomerController controller;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .alwaysDo(print())
                .apply(springSecurity(springSecurityFilterChain))
                .build();
        when(contractService.getCustomerContracts(USER_ID)).thenReturn(new ArrayList<>());
    }

    @Test
    @WithMockUser(roles = "CUSTOMER")
//    @WithUserDetails(value="email@email.com", userDetailsServiceBeanName = "providerUserDetailsService")
    public void getProfile() throws Exception {
        String url = "/customer";

        this.mockMvc.perform(get(url)
                .with(csrf())
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(view().name("control_template"));
    }
}


package com.tmobile.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmobile.config.AppConfig;
import com.tmobile.dto.ContractInfoDTO;
import com.tmobile.service.ContractService;
import com.tmobile.service.OptionsService;
import com.tmobile.service.TariffService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringJUnitWebConfig(value = AppConfig.class)
public class ManagerControllerTest {

    private static final Integer CONTRACT_ID = 1;
    private static final Integer OPTION_ID = 2;
    private static final Integer TARIFF_ID = 3;
    private static final String PHONE = "89997776655";

    @Mock
    private TariffService tariffService;

    @Mock
    private ContractService contractService;

    @Mock
    private OptionsService optionsService;

    @InjectMocks
    private ManagerController controller;

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
        //when(contractService.getContract(CONTRACT_ID)).thenReturn(new ContractInfoDTO());
    }

    @Test
    @WithMockUser(roles = "MANAGER")
    public void controlPanel() throws Exception {
        String url = "/manager";

        this.mockMvc.perform(get(url)
                .with(csrf())
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(view().name("control_template"));
    }

    @Test
    @WithMockUser(roles = "MANAGER")
    public void newContract() throws Exception {
        String url = "/manager/new_contract";

        this.mockMvc.perform(get(url)
                .with(csrf())
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(view().name("control_template"));
    }

    @Test
    @WithMockUser(roles = "MANAGER")
    public void syncNewContractInfoTest() throws Exception {
        String url = "/manager/sync_new_contract_info";
        ContractInfoDTO dto = new ContractInfoDTO();
        dto.setTariffId(TARIFF_ID);

        this.mockMvc.perform(put(url)
                .with(csrf())
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }


    @Test
    @WithMockUser(roles = "MANAGER")
    public void customerProfile() throws Exception {
        String url = "/manager/contract/{id}";

        this.mockMvc.perform(get(url, CONTRACT_ID)
                .with(csrf())
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(view().name("control_template"));
    }

    @Test
    @WithMockUser(roles = "MANAGER")
    public void getContractsByPhone() throws Exception {
        String url = "/manager/filterByPhone";

        this.mockMvc.perform(get(url)
                .param("phone", PHONE)
                .with(csrf())
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "MANAGER")
    public void listOptions() throws Exception {
        String url = "/manager/options";

        this.mockMvc.perform(get(url)
                .with(csrf())
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(view().name("control_template"));
    }

    @Test
    @WithMockUser(roles = "MANAGER")
    @Ignore
    public void editOption() throws Exception {
        String url = "/manager/options/{optionId}";

        this.mockMvc.perform(get(url, OPTION_ID)
                .with(csrf())
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(view().name("control_template"));
    }

    @Test
    @WithMockUser(roles = "MANAGER")
    public void createOption() throws Exception {
        String url = "/manager/options/new";

        this.mockMvc.perform(get(url)
                .with(csrf())
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(view().name("control_template"));
    }

    @Test
    @WithMockUser(roles = "MANAGER")
    public void listTariffs() throws Exception {
        String url = "/manager/tariffs";

        this.mockMvc.perform(get(url)
                .with(csrf())
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(view().name("control_template"));
    }

    @Test
    @WithMockUser(roles = "MANAGER")
    public void editTariff() throws Exception {
        String url = "/manager/tariffs/{tariffId}";

        this.mockMvc.perform(get(url, TARIFF_ID)
                .with(csrf())
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(view().name("control_template"));
    }
    @Test
    @WithMockUser(roles = "MANAGER")
    public void createTariff() throws Exception {
        String url = "/manager/tariffs/new";

        this.mockMvc.perform(get(url)
                .with(csrf())
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(view().name("control_template"));
    }
}

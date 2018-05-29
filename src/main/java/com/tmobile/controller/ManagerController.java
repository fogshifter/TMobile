package com.tmobile.controller;

import com.tmobile.dto.*;
import com.tmobile.exception.EntryNotFoundException;
import com.tmobile.exception.TMobileException;
import com.tmobile.service.ContractService;
import com.tmobile.service.OptionsService;
import com.tmobile.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.tmobile.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    private TariffService tariffService;
    private ContractService contractService;
    private OptionsService optionsService;

    @Autowired
    public ManagerController(TariffService tariffService, UserService userService, ContractService contractService, OptionsService optionsService) {
        this.tariffService = tariffService;
        this.contractService = contractService;
        this.optionsService = optionsService;
    }

    @GetMapping
    public ModelAndView controlPanel() {
        ModelAndView view = new ModelAndView("control_template");

        List<ContractsListEntryDTO> contracts = contractService.getAllContracts();

        view.addObject("page", "CONTRACTS");
        view.addObject("contracts", contracts);
//        view.addObject("user", "MANAGER");
        return view;
    }

    @PutMapping("/sync_new_contract_info")
    @ResponseBody
    public HttpStatus syncNewContractInfo(@RequestBody ContractInfoDTO contractInfoDTO, HttpSession session) {

        session.setAttribute("newContractInfo", contractInfoDTO);

        return HttpStatus.OK;
    }

    @GetMapping("/new_contract")
    public ModelAndView newContract(HttpSession session) throws EntryNotFoundException {
        ModelAndView view = new ModelAndView("control_template");

        ContractInfoDTO contractInfo = (ContractInfoDTO) session.getAttribute("newContractInfo");
        List<OptionDTO> options = null;

        if (contractInfo != null) {
            try {
                options = tariffService.getCompatibleOptions(contractInfo.getTariffId());
            } catch(EntryNotFoundException e) {
                session.removeAttribute("newContractInfo");
            }
        }
        else {
            contractInfo = new ContractInfoDTO();
        }

        List<TariffsListEntryDTO> tariffs = tariffService.getAll();

        view.addObject("contractInfo", contractInfo);

        view.addObject("tariffs", tariffs);
        view.addObject("options", options);
        view.addObject("page", "NEW_CONTRACT");
        return view;
    }

    @GetMapping("/contract/{id}")
    public ModelAndView customerProfile(@PathVariable int id) throws TMobileException {

        ContractInfoDTO contractInfo = contractService.getContract(id);

        ModelAndView view = new ModelAndView("control_template");

        view.addObject("page", "EDIT_CONTRACT");
//        view.addObject("user", "MANAGER");
        view.addObject("contractInfo", contractInfo);
        view.addObject("options", null);
        view.addObject("tariffs", tariffService.getAll());
        return view;
    }

    @GetMapping("/filterByPhone")
    public @ResponseBody
    List<ContractsListEntryDTO> getContractsByPhone(@RequestParam("phone") String phone) {
        if (phone.isEmpty()) {
            return contractService.getAllContracts();
        }
        List<ContractsListEntryDTO> contracts = contractService.searchContractsByPhone(phone);
        return contracts;
    }

    @GetMapping("/options")
    public ModelAndView listOptions() {
        ModelAndView view = new ModelAndView("control_template");
//        view.addObject("user", "MANAGER");
        view.addObject("page", "OPTIONS");
        view.addObject("options", optionsService.getAll());
        return view;
    }

    @GetMapping("/options/{optionId}")
    public ModelAndView editOption(@PathVariable int optionId) throws TMobileException {

        OptionDTO option = optionsService.getOption(optionId);

        List<OptionDTO> allCompatibleOptions = optionsService.getCompatibleOptions(option.getId());

        ModelAndView view = new ModelAndView("control_template");
        view.addObject("page", "OPTION");
        view.addObject("option", option);
        view.addObject("compatibleOptions", allCompatibleOptions);
        view.addObject("requiredOptions", optionsService.getRequiredOptions(optionId));

        return view;
    }

    @GetMapping("options/new")
    public ModelAndView createOption() {
        ModelAndView view = new ModelAndView("control_template");
//        view.addObject("user", "MANAGER");
        view.addObject("page", "NEW_OPTION");
        view.addObject("option", new OptionDTO());
//        view.addObject("compatibleOptions", optionsService.getAll());
        view.addObject("compatibleOptions", optionsService.getAll());

        return view;
    }

    @GetMapping("/tariffs")
    public ModelAndView listTariffs() {
        ModelAndView view = new ModelAndView("control_template");
//        view.addObject("user", "MANAGER");
        view.addObject("page", "TARIFFS");
        view.addObject("tariffs", tariffService.getAll());
        return view;
    }

    @GetMapping("/tariffs/{tariffId}")
    public ModelAndView editTariff(@PathVariable int tariffId) throws EntryNotFoundException {

        TariffDTO tariff = tariffService.getTariff(tariffId);
        List<OptionDTO> options = tariffService.getCompatibleOptions(tariffId);

        ModelAndView view = new ModelAndView("control_template");
//        view.addObject("user", "MANAGER");
        view.addObject("page", "TARIFF");
        view.addObject("tariff", tariff);
        view.addObject("allOptions", optionsService.getAll());

        return view;
    }

    @GetMapping("/tariffs/new")
    public ModelAndView createTariff() {
        ModelAndView view = new ModelAndView("control_template");

//        view.addObject("user", "MANAGER");
        view.addObject("page", "NEW_TARIFF");
        view.addObject("tariff", new TariffDTO());
        view.addObject("allOptions", optionsService.getAll());
        return view;
    }
}

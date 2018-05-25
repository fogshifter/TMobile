package com.tmobile.controller;

import com.tmobile.dto.*;
import com.tmobile.exception.EntryNotFoundException;
import com.tmobile.service.ContractService;
import com.tmobile.service.OptionsService;
import com.tmobile.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
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
        view.addObject("user", "MANAGER");
        return view;
    }

    @GetMapping("/new_contract")
    public ModelAndView newContract(HttpSession session) {
        ModelAndView view = new ModelAndView("control_template");

        ContractInfoDTO contractInfo = (ContractInfoDTO) session.getAttribute("newContractInfo");

        if (contractInfo == null) {
            contractInfo = contractService.getDefaultContractInfo();
        }

        view.addObject("contractInfo", contractInfo);

        List<TariffsListEntryDTO> tariffs = tariffService.getAll();

//        List<OptionDTO> allOptions = optionsService.getAll();

        view.addObject("tariffs", tariffs);
        view.addObject("options", null);
        view.addObject("page", "NEW_CONTRACT");
        view.addObject("user", "MANAGER");
        return view;
    }

    @GetMapping("/contract/{id}")
    public ModelAndView customerProfile(@PathVariable int id) {

        ContractInfoDTO contractInfo = contractService.getContract(id);

        ModelAndView view = new ModelAndView("control_template");

        view.addObject("page", "EDIT_CONTRACT");
        view.addObject("user", "MANAGER");
        view.addObject("contractInfo", contractInfo);
        view.addObject("options", null);
        view.addObject("tariffs", tariffService.getAll());
        return view;
    }

//    @PostMapping("/sync_new_contract_info")
//    @ResponseBody
//    public List<OptionDTO> syncNewContractInfo(@RequestBody ContractInfoDTO contractInfo, HttpSession session) {
//        session.setAttribute("newContractInfo", contractInfo);
//
//        return tariffService.getCompatibleOptions(contractInfo.getTariffId());
//    }

//    @PostMapping("/sync_contract_info")
//    public @ResponseBody
//    List<OptionDTO> syncContractInfo(@RequestBody ContractInfoDTO contractInfo, HttpSession session) {
//        session.setAttribute("contractInfo" + String.valueOf(contractInfo.getContractId()), contractInfo);
//
//        return tariffService.getCompatibleOptions(contractInfo.getTariffId());
//    }

//    @PostMapping("/register_contract")
//    public @ResponseBody
//    void registerContract(@RequestBody ContractInfoDTO contractInfo, HttpSession session) {
//        contractService.registerContract(contractInfo);
//
//        session.removeAttribute("newContractInfo");
//    }

//    @PostMapping("/edit_contract")
//    public @ResponseBody
//    void editContract(@RequestBody ContractInfoDTO contractInfo, HttpSession session) {
//        contractService.editContract(contractInfo);
//        session.removeAttribute("contractInfo" + String.valueOf(contractInfo.getContractId()));
//    }


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
        view.addObject("user", "MANAGER");
        view.addObject("page", "OPTIONS");
        view.addObject("options", optionsService.getAll());
        return view;
    }

    @GetMapping("/options/{optionId}")
    public ModelAndView editOption(@PathVariable int optionId) throws EntryNotFoundException {

        OptionDTO option = optionsService.getOption(optionId);

//        List<OptionDTO> compatibleOptions = optionsService.getCompatibleOptions(option.getId());
//        List<OptionDTO> requiredOptions = optionsService.getRequiredOptions(option.getId());
//        List<OptionDTO> possibleCompatibleOptions =

        ModelAndView view = new ModelAndView("control_template");
        view.addObject("user", "MANAGER");
        view.addObject("page", "OPTION");
        view.addObject("option", option);
        view.addObject("allOptions", optionsService.getAll());
//        view.addObject("options", optionsService.getAll());
//        view.addObject("compatibleOptions", optionDTO);

//        view.addObject("compatibleOptions", compatibleOptions);
//        view.addObject("requiredOptions", requiredOptions);

        return view;
    }

    @GetMapping("options/new")
    public ModelAndView createOption() {
        ModelAndView view = new ModelAndView("control_template");
        view.addObject("user", "MANAGER");
        view.addObject("page", "NEW_OPTION");
        view.addObject("option", new OptionDTO());
//        view.addObject("compatibleOptions", optionsService.getAll());
        view.addObject("allOptions", optionsService.getAll());

        return view;
    }

    @GetMapping("/tariffs")
    public ModelAndView listTariffs() {
        ModelAndView view = new ModelAndView("control_template");
        view.addObject("user", "MANAGER");
        view.addObject("page", "TARIFFS");
        view.addObject("tariffs", tariffService.getAll());
        return view;
    }

    @GetMapping("/tariffs/{tariffId}")
    public ModelAndView editTariff(@PathVariable int tariffId) throws EntryNotFoundException {

        TariffDTO tariff = tariffService.getTariff(tariffId);
        List<OptionDTO> options = tariffService.getCompatibleOptions(tariffId);

        ModelAndView view = new ModelAndView("control_template");
        view.addObject("user", "MANAGER");
        view.addObject("page", "TARIFF");
        view.addObject("tariff", tariff);
        view.addObject("allOptions", optionsService.getAll());

        return view;
    }

    @GetMapping("/tariffs/new")
    public ModelAndView createTariff() {
        ModelAndView view = new ModelAndView("control_template");

        view.addObject("user", "MANAGER");
        view.addObject("page", "NEW_TARIFF");
        view.addObject("tariff", new TariffDTO());
        view.addObject("allOptions", optionsService.getAll());
        return view;
    }


}

package com.tmobile.controller;

import com.tmobile.auth.TMobileUserDetails;
import com.tmobile.dto.ContractInfoDTO;
import com.tmobile.service.ContractService;
import com.tmobile.service.OptionsService;
import com.tmobile.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/customer")
public class CustomerController {

    private ContractService contractService;
    private OptionsService optionsService;
    private TariffService tariffService;

    @Autowired
    public CustomerController(ContractService contractService, OptionsService optionsService, TariffService tariffService) {
        this.contractService = contractService;
        this.optionsService = optionsService;
        this.tariffService = tariffService;
    }

    @GetMapping
    public ModelAndView getProfile() {

        TMobileUserDetails userDetails = (TMobileUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        ModelAndView view = new ModelAndView("control_template");
        view.addObject("page", "CONTRACTS");
        view.addObject("user", "CUSTOMER");
        view.addObject("contracts", contractService.getCustomerContracts(userDetails.getUserId()));
        return view;
    }

//    @GetMapping("/{contractId}")
//    public ModelAndView getContract(@PathVariable int contractId, HttpSession session) {
//
//        ModelAndView view = new ModelAndView("control_template");
//
//
//        ContractInfoDTO cachedContractInfo = (ContractInfoDTO) session.getAttribute("contractInfo" + String.valueOf(contractId));
//        ContractInfoDTO contractInfo = contractService.getContract(contractId);
//
//        if (cachedContractInfo != null) {
//            contractInfo.setOptionIds(cachedContractInfo.getOptionIds());
//            contractInfo.setTariffId(cachedContractInfo.getTariffId());
//        }
//
//        view.addObject("page", "EDIT_CONTRACT");
//        view.addObject("user", "CUSTOMER");
//        view.addObject("contractInfo", contractInfo);
//        view.addObject("options", optionsService.getAll());
//        view.addObject("tariffs", tariffService.getAll());
//
//        return view;
//    }
//
//    @PostMapping("/sync_contract_info")
//    public @ResponseBody
//    List<OptionDTO> syncContractInfo(@RequestBody ContractInfoDTO contractInfo, HttpSession session) {
//        session.setAttribute("contractInfo" + String.valueOf(contractInfo.getContractId()), contractInfo);
//
//        return tariffService.getCompatibleOptions(contractInfo.getTariffId());
//    }

//    @PostMapping("/edit_contract")
//    public @ResponseBody
//    void editContract(@RequestBody ContractInfoDTO contractInfo, HttpSession session) {
//        contractService.editContract(contractInfo);
//        session.removeAttribute("contractInfo" + String.valueOf(contractInfo.getContractId()));
//    }
}

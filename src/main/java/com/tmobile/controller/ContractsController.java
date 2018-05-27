package com.tmobile.controller;


import com.tmobile.auth.TMobileUserDetails;
import com.tmobile.dto.ContractInfoDTO;
import com.tmobile.dto.OptionDTO;
import com.tmobile.exception.EntryNotFoundException;
import com.tmobile.exception.TMobileException;
import com.tmobile.service.ContractService;
import com.tmobile.service.TariffService;
import com.tmobile.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/contracts")
public class ContractsController {

    private UserService userService;
    private TariffService tariffService;
    private ContractService contractService;

    @Autowired
    public ContractsController(TariffService tariffService, UserService userService, ContractService contractService) {
        this.tariffService = tariffService;
        this.userService = userService;
        this.contractService = contractService;
    }

//    @PutMapping("/sync_new_contract_info")
//    @ResponseBody
//    public HttpStatus syncNewContractInfo(@RequestBody ContractInfoDTO contractInfoDTO, HttpSession session) throws EntryNotFoundException {
//
//        session.setAttribute("newContractInfo", contractInfoDTO);
//
//        return HttpStatus.OK;
//    }

    @GetMapping("/check_email_uniqueness")
    @ResponseBody
    public String isEmailAvailable(@RequestParam String email) {
        return String.join(":", "valid", userService.isEmailAvailable(email).toString());
    }

    @PostMapping
    @ResponseBody
    public HttpStatus createContract(@RequestBody ContractInfoDTO contractInfo, HttpSession session) {
        contractService.registerContract(contractInfo);
        session.removeAttribute("newContractInfo");

        return HttpStatus.OK;
    }

    @PutMapping
    @ResponseBody
    public HttpStatus editContract(@RequestBody ContractInfoDTO contractInfo) throws TMobileException {

        contractService.editContract(contractInfo);
        return HttpStatus.OK;
    }

    @GetMapping("/{contractId}")
    public ModelAndView getContract(@PathVariable int contractId) throws TMobileException {

        ModelAndView view = new ModelAndView("control_template");

        ContractInfoDTO contaractInfo = contractService.getContract(contractId);

        view.addObject("page", "EDIT_CONTRACT");
        view.addObject("contractInfo", contractService.getContract(contractId));
        view.addObject("options",   tariffService.getCompatibleOptions(contaractInfo.getTariffId()));
        view.addObject("tariffs", tariffService.getAll());
        return view;
    }

    @PutMapping("/block/{contractId}")
    @ResponseBody
    public HttpStatus blockContract(@PathVariable int contractId, @RequestBody boolean block) throws TMobileException {
        contractService.blockContract(contractId, block);
        return HttpStatus.OK;
    }

}

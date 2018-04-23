package com.tmobile.controller;

import com.tmobile.dto.ContractInfoDTO;
import com.tmobile.dto.ContractsListEntryDTO;
import com.tmobile.dto.OptionDTO;
import com.tmobile.dto.TariffDTO;
import com.tmobile.service.ContractService;
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
	
//	private UserService userService;
	private TariffService tariffService;
	private ContractService contractService;

	@Autowired
//	public ManagerController(TariffService tariffService, UserService userService, ContractService contractService) {
    public ManagerController(TariffService tariffService, UserService userService, ContractService contractService) {
        this.tariffService = tariffService;
//        this.userService = userService;
        this.contractService = contractService;
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

        if(contractInfo == null) {
            contractInfo = contractService.getDefaultContractInfo();
        }

        view.addObject("contractInfo", contractInfo);

		List<TariffDTO> tariffs = tariffService.getAll();

		List<OptionDTO> defaultTariffOptions = tariffService.getDefaultTariffOptions();

		view.addObject("tariffs", tariffService.getAll());
		view.addObject("options", defaultTariffOptions);
		view.addObject("page", "NEW_CONTRACT");
        view.addObject("user", "MANAGER");
		return view;
	}

	@GetMapping("/contract/{id}")
	public ModelAndView customerProfile(@PathVariable int id) {
		ModelAndView view = new ModelAndView("control_template");

		view.addObject("page", "EDIT_CONTRACT");
        view.addObject("user", "MANAGER");
		return view;
	}

    @PostMapping("/sync_new_contract_info")
    @ResponseBody
    public void syncContractInfo(@RequestBody ContractInfoDTO contractInfo, HttpSession session) {
        session.setAttribute("newContractInfo", contractInfo);
    }

    @PostMapping("/register_contract")
    public ModelAndView registerContract(@RequestBody ContractInfoDTO contractInfo) {
	    contractService.registerContract(contractInfo);
	    return controlPanel();
    }
}

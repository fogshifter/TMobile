package com.tmobile.controller;

import com.tmobile.dto.ContractInfoDTO;
import com.tmobile.dto.OptionDTO;
import com.tmobile.dto.TariffDTO;
import com.tmobile.service.ContractService;
import com.tmobile.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.tmobile.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/manager")
public class ManagerController {
	
	private UserService userService;
	private TariffService tariffService;
	private ContractService contractService;
    private ContractInfoDTO newContract;

	@Autowired
    public ManagerController(TariffService tariffService, UserService userService, ContractInfoDTO contractInfoDTO, ContractService contractService) {
        this.tariffService = tariffService;
        this.userService = userService;
        this.newContract = contractInfoDTO;
        this.contractService = contractService;
	}
	
	@GetMapping
	public ModelAndView controlPanel() {
		ModelAndView view = new ModelAndView("control_template");

		view.addObject("page", "CONTRACTS");
		view.addObject("customers", userService.getCustomersList());
		return view;
	}

	@GetMapping("/new_contract")
	public ModelAndView newCustomer() {
		ModelAndView view = new ModelAndView("control_template");

		List<TariffDTO> tariffs = tariffService.getAll();

		List<OptionDTO> defaultTariffOptions = tariffService.getDefaultTariffOptions();

		view.addObject("tariffs", tariffService.getAll());
		view.addObject("options", defaultTariffOptions);
		view.addObject("page", "NEW_CONTRACT");
		return view;
	}

	@GetMapping("/contract/{id}")
	public ModelAndView customerProfile(@PathVariable int id) {
		ModelAndView view = new ModelAndView("control_template");

		view.addObject("page", "EDIT_CONTRACT");
//		view.addObject("customer", );
		return view;
	}

    @PostMapping("/sync_new_contract_info/")
    public void syncContractInfo(ContractInfoDTO contractInfo) {
        newContract = contractInfo;
	    /*
        newContract.setAddress(contractInfo.getAddress());
        newContract.setBirthDate(contractInfo.getBirthDate());
        newContract.setEmail(contractInfo.getEmail());
        newContract.setFirstName(contractInfo.getFirstName());
        newContract.setLastName(contractInfo.getLastName());
        newContract.setOptionIds(contractInfo.getOptionIds());
        newContract.setPhone(contractInfo.getPhone());*/
    }

    @PostMapping("/register_contract")
    public ModelAndView registerContract(@RequestBody ContractInfoDTO contractInfo) {

	    contractService.registerContract(contractInfo);

	    return controlPanel();
    }
}

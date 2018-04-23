package com.tmobile.controller;


import com.tmobile.dto.ContractInfoDTO;
import com.tmobile.service.TariffService;
import com.tmobile.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contract")
public class ContractController {

    private UserService userService;
    private TariffService tariffService;

    private ContractInfoDTO newContract;
    private ContractInfoDTO editContract;

    @Autowired
    public ContractController(TariffService tariffService, UserService userService, ContractInfoDTO newContractDTO, ContractInfoDTO editContractDTO) {
        this.tariffService = tariffService;
        this.userService = userService;
        this.newContract = newContractDTO;
        this.editContract = editContractDTO;
    }

    @PostMapping("/sync_contract_info/{id}")
    public void syncContractInfo(ContractInfoDTO contractInfo, @PathVariable int id) {
        editContract = contractInfo;
        editContract.setCustomerId(id);
        /*editContract.setAddress(contractInfo.getAddress());
        editContract.setBirthDate(contractInfo.getBirthDate());
        editContract.setEmail(contractInfo.getEmail());
        editContract.setFirstName(contractInfo.getFirstName());
        editContract.setLastName(contractInfo.getLastName());
        editContract.setOptionIds(contractInfo.getOptionIds());
        editContract.setCustomerId(id);
        editContract.setPhone(contractInfo.getPhone());*/
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

    @GetMapping("/check_email_uniqueness")
    @ResponseBody
    public String isEmailAvailable(@RequestParam String email) {
        return String.join(":", "valid", userService.isEmailAvailable(email).toString());
    }

    @PostMapping("/save_contract")
    public void saveContractInfo(ContractInfoDTO contractInfo) {

    }
}

package com.tmobile.controller;


import com.tmobile.auth.ProviderUserDetails;
import com.tmobile.dto.ContractInfoDTO;
import com.tmobile.dto.OptionDTO;
import com.tmobile.exception.EntryNotFoundException;
import com.tmobile.service.ContractService;
import com.tmobile.service.TariffService;
import com.tmobile.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/contracts")
public class ContractsController {

    private UserService userService;
    private TariffService tariffService;
    private ContractService contractService;

//    private ContractInfoDTO newContract;
//    private ContractInfoDTO editContract;

    @Autowired
    public ContractsController(TariffService tariffService, UserService userService, ContractService contractService) {
        this.tariffService = tariffService;
        this.userService = userService;
        this.contractService = contractService;
    }

    @PutMapping("/sync_new_contract_info")
    @ResponseStatus(value = HttpStatus.OK)
    public List<OptionDTO> syncNewContractInfo(@RequestBody ContractInfoDTO contractInfoDTO, HttpSession session) throws EntryNotFoundException {
        session.setAttribute("newContractInfo", contractInfoDTO);

        return tariffService.getCompatibleOptions(contractInfoDTO.getTariffId());
    }

    @GetMapping("/check_email_uniqueness")
    @ResponseBody
    public String isEmailAvailable(@RequestParam String email) {
        return String.join(":", "valid", userService.isEmailAvailable(email).toString());
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public void createContract(@RequestBody ContractInfoDTO contractInfo, HttpSession session) {
        contractService.registerContract(contractInfo);
        session.removeAttribute("newContractInfo");
    }

    @PutMapping
    @ResponseStatus(value = HttpStatus.OK)
    public void editContract(@RequestBody ContractInfoDTO contractInfo) throws EntryNotFoundException {

        ProviderUserDetails userDetails = (ProviderUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

//        boolean customer = userDetails.getAuthorities().stream().noneMatch(r -> r.getAuthority().contains("MANAGER"));

        contractService.editContract(contractInfo);
    }
}

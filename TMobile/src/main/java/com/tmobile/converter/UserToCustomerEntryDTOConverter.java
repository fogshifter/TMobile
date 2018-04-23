package com.tmobile.converter;

import org.modelmapper.AbstractConverter;

import com.tmobile.dto.CustomersListEntryDTO;
import com.tmobile.entity.Contract;
import com.tmobile.entity.User;

public class UserToCustomerEntryDTOConverter extends AbstractConverter<User, CustomersListEntryDTO> {
	
	@Override
	protected CustomersListEntryDTO convert(User user) {
//		ProfileDTO profile = new ProfileDTO();
		CustomersListEntryDTO dto = new CustomersListEntryDTO();
		dto.setId(user.getId());
		dto.setFirstName(user.getFirstName());
		dto.setLastName(user.getLastName());
		dto.setEmail(user.getEmail());
		
		for(Contract contract : user.getContracts()) {
			dto.addPhone(contract.getPhone());
		}
		
//		dto.setPhones(user.getContracts().get);
		return dto;
		/*String name = customer.getFirstName();
		name = name.join(" ", customer.getLastName());
		profile.setCustomerName(name);
		
		for(Contract contract : customer.getContracts()) {
			Tariff tariff = contract.getTariff();
			profile.addTariff(tariff.getId(), tariff.getName());
			
			for(Option option : tariff.get)
		}
		
		for(customer.get) {
			
		}
		*/
		//return profile;
	}
}

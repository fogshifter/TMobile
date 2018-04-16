package com.tmobile.converter;

import org.modelmapper.AbstractConverter;

import com.tmobile.dto.ProfileDTO;
import com.tmobile.entity.Contract;
import com.tmobile.entity.Tariff;
import com.tmobile.entity.User;

public class CustomerToProfileDTOConverter extends AbstractConverter<User, ProfileDTO>{
	
	@Override
	protected ProfileDTO convert(User customer) {
		ProfileDTO profile = new ProfileDTO();
		
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
		return profile;
	}
}

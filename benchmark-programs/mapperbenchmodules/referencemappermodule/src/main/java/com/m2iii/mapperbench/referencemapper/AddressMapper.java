package com.m2iii.mapperbench.referencemapper;

import com.m2iii.mapperbench.Converter;
import com.m2iii.mapperbench.model.destination.AddressDTO;
import com.m2iii.mapperbench.model.source.Address;

public class AddressMapper implements Converter<Address, AddressDTO> {

    private static AddressMapper addressMapper = null;

    private AddressMapper() {

    }

    @Override
    public AddressDTO convert(Address address) {
        return address == null ? null : new AddressDTO(
                address.getStreet(),
                address.getCity(),
                address.getPostalCode(),
                address.getCountry()
        );
    }


    public static AddressMapper getInstance()
    {
        if (addressMapper == null)
            addressMapper = new AddressMapper();
        return addressMapper;
    }
}

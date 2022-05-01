package com.m2iii.mapperbench.referencemapper;

import com.m2iii.mapperbench.Converter;
import com.m2iii.mapperbench.model.destination.AddressDTO;
import com.m2iii.mapperbench.model.destination.DeliveryDataDTO;
import com.m2iii.mapperbench.model.source.DeliveryData;

public class DeliveryDataMapper implements Converter<DeliveryData, DeliveryDataDTO> {

    AddressMapper addressMapper = AddressMapper.getInstance();

    private static DeliveryDataMapper deliveryDataMapper = null;

    private DeliveryDataMapper(){}

    @Override
    public DeliveryDataDTO convert(DeliveryData deliveryData) {
        return deliveryData == null ? null : new DeliveryDataDTO(
            addressMapper.convert(deliveryData.getDeliveryAddress()),
                deliveryData.isPrePaid(),
                deliveryData.getTrackingCode(),
                deliveryData.getExpectedDeliveryTimeInDays()
        );
    }

    public static DeliveryDataMapper getInstance()
    {
        if (deliveryDataMapper == null)
            deliveryDataMapper = new DeliveryDataMapper();
        return deliveryDataMapper;
    }
}

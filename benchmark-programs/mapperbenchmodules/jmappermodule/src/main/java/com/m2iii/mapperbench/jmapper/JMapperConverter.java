package com.m2iii.mapperbench.jmapper;

import com.googlecode.jmapper.JMapper;
import com.googlecode.jmapper.api.JMapperAPI;
import com.m2iii.mapperbench.Converter;
import com.m2iii.mapperbench.model.destination.Order;
import com.m2iii.mapperbench.model.source.SourceOrder;

public class JMapperConverter implements Converter {
    JMapper realLifeMapper;

    public JMapperConverter() {
        JMapperAPI api = new JMapperAPI().add(JMapperAPI.mappedClass(Order.class));
        realLifeMapper = new JMapper(Order.class, SourceOrder.class, api);
    }

    @Override
    public Order convert(SourceOrder sourceOrder) {
        return (Order) realLifeMapper.getDestination(sourceOrder);
    }
}
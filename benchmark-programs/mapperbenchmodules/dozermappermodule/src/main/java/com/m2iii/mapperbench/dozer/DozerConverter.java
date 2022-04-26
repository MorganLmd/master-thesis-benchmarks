package com.m2iii.mapperbench.dozer;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.m2iii.mapperbench.Converter;
import com.m2iii.mapperbench.model.destination.Order;
import com.m2iii.mapperbench.model.source.SourceOrder;

public class DozerConverter implements Converter {
    private final Mapper mapper;

    public DozerConverter() {
        this.mapper = DozerBeanMapperBuilder.create()
                .withMappingFiles("dozer-mapping.xml")
                .build();
    }

    @Override
    public Order convert(SourceOrder sourceOrder) {
        return mapper.map(sourceOrder, Order.class);
    }
}
package com.m2iii.mapperbench.modelmapper;

import com.m2iii.mapperbench.Converter;
import com.m2iii.mapperbench.model.destination.Order;
import com.m2iii.mapperbench.model.source.SourceOrder;
import org.modelmapper.ModelMapper;

public class ModelMapperConverter implements Converter {
    private ModelMapper modelMapper;

    public ModelMapperConverter() {
        modelMapper = new ModelMapper();
    }

    @Override
    public Order convert(SourceOrder sourceOrder) {
        return modelMapper.map(sourceOrder, Order.class);
    }
}
package com.m2iii.mapperbench.mapstruct;

import com.m2iii.mapperbench.Converter;
import com.m2iii.mapperbench.model.destination.Order;
import com.m2iii.mapperbench.model.source.SourceOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MapStructConverter extends Converter {
    MapStructConverter MAPPER = Mappers.getMapper(MapStructConverter.class);

    @Mapping(source = "status", target = "orderStatus")
    @Override
    Order convert(SourceOrder sourceOrder);
}
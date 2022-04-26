package com.m2iii.mapperbench;

import com.m2iii.mapperbench.model.destination.Order;
import com.m2iii.mapperbench.model.source.SourceOrder;

public interface Converter {
    Order convert(SourceOrder sourceOrder);
}
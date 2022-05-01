package com.m2iii.mapperbench.referencemapper;

import com.m2iii.mapperbench.Converter;
import com.m2iii.mapperbench.model.destination.DiscountDTO;
import com.m2iii.mapperbench.model.source.Discount;

public class DiscountMapper implements Converter<Discount, DiscountDTO> {

    private static DiscountMapper discountMapper = null;

    @Override
    public DiscountDTO convert(Discount discount) {
        return discount == null ? null : new DiscountDTO(
                discount.getStartTime(),
                discount.getEndTime(),
                discount.getDiscountPrice()
        );
    }

    public static DiscountMapper getInstance() {
        if (discountMapper == null) {
            discountMapper = new DiscountMapper();
        }
        return discountMapper;
    }
}

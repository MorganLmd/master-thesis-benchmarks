package com.m2iii.mapperbench.referencemapper;

import com.m2iii.mapperbench.Converter;
import com.m2iii.mapperbench.model.destination.ShopDTO;
import com.m2iii.mapperbench.model.source.Shop;

import java.util.List;
import java.util.stream.Collectors;

public class ShopMapper implements Converter<Shop, ShopDTO> {

    private static ShopMapper shopMapper = null;
    private final ReviewMapper reviewMapper = ReviewMapper.getInstance();
    private final AddressMapper addressMapper = AddressMapper.getInstance();

    private ShopMapper() {}


    @Override
    public ShopDTO convert(Shop shop) {

        return shop == null ?  null : new ShopDTO(
                shop.getShopName(),
                addressMapper.convert(shop.getShopAddres()),
                shop.getShopUrl(),
                shop.getReviews()
                        .stream()
                        .map(reviewMapper::convert).collect(Collectors.toList())
        );
    }

    public static ShopMapper getInstance() {
        if (shopMapper == null) {
            shopMapper = new ShopMapper();
        }

        return shopMapper;
    }
}

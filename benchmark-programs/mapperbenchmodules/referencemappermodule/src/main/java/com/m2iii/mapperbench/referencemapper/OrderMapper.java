package com.m2iii.mapperbench.referencemapper;

import com.m2iii.mapperbench.Converter;
import com.m2iii.mapperbench.model.destination.OrderDTO;
import com.m2iii.mapperbench.model.destination.OrderStatus;
import com.m2iii.mapperbench.model.destination.PaymentType;
import com.m2iii.mapperbench.model.source.Discount;
import com.m2iii.mapperbench.model.source.SourceOrder;

import java.util.stream.Collectors;

public class OrderMapper implements Converter<SourceOrder, OrderDTO> {

    private static OrderMapper orderMapper = null;
    private final UserMapper userMapper = UserMapper.getInstance();
    private final ProductMapper productMapper = ProductMapper.getInstance();
    private final DiscountMapper discountMapper = DiscountMapper.getInstance();
    private final ShopMapper shopMapper = ShopMapper.getInstance();
    private final DeliveryDataMapper deliveryDataMapper = DeliveryDataMapper.getInstance();

    @Override
    public OrderDTO convert(SourceOrder sourceOrder) {
        return new OrderDTO(
                userMapper.convert(sourceOrder.getOrderingUser()),
                sourceOrder.getOrderedProducts()
                        .stream()
                        .map(productMapper::convert)
                        .collect(Collectors.toList()),
                OrderStatus.valueOf(sourceOrder.getStatus().name()),
                sourceOrder.getOrderDate(),
                sourceOrder.getOrderFinishDate(),
                PaymentType.valueOf(sourceOrder.getPaymentType().name()),
                discountMapper.convert(sourceOrder.getDiscount()),
                sourceOrder.getOrderId(),
                deliveryDataMapper.convert(sourceOrder.getDeliveryData()),
                shopMapper.convert(sourceOrder.getOfferingShop())
        );
    }

    public static OrderMapper getInstance() {
        if (orderMapper == null) {
            orderMapper = new OrderMapper();
        }
        return orderMapper;
    }
}

package com.m2iii.mapperbench.model.destination;

import com.google.common.base.Objects;
import com.googlecode.jmapper.annotations.JMap;
import com.googlecode.jmapper.annotations.JMapConversion;
import com.m2iii.mapperbench.model.source.SourceOrder;

import java.util.List;
public class OrderDTO {
    @JMap
    private UserDTO orderingUserDTO;
    @JMap
    private List<ProductDTO> orderedProductDTOS;
    @JMap("status")
    private OrderStatus orderStatus;
    @JMap
    private String orderDate;
    @JMap
    private String orderFinishDate;
    @JMap
    private PaymentType paymentType;
    @JMap
    private DiscountDTO discount;
    @JMap
    private int orderId;
    @JMap
    private DeliveryDataDTO deliveryDataDTO;
    @JMap
    private ShopDTO offeringShopDTO;

    public OrderDTO() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (o.getClass() == SourceOrder.class) {
            SourceOrder order =
                    (SourceOrder) o;
            return Objects.equal(orderingUserDTO, order.getOrderingUser()) &&
                    Objects.equal(orderedProductDTOS, order.getOrderedProducts()) &&
                    orderStatus.ordinal() == order.getStatus().ordinal() &&
                    Objects.equal(orderDate, order.getOrderDate()) &&
                    Objects.equal(orderFinishDate, order.getOrderFinishDate()) &&
                    paymentType.ordinal() == order.getPaymentType().ordinal() &&
                    Objects.equal(discount, order.getDiscount()) &&
                    Objects.equal(deliveryDataDTO, order.getDeliveryData());
        }
        if (o.getClass() != getClass()) return false;
        OrderDTO orderDTO = (OrderDTO) o;
        return Objects.equal(orderingUserDTO, orderDTO.orderingUserDTO) &&
                Objects.equal(orderedProductDTOS, orderDTO.orderedProductDTOS) &&
                orderStatus == orderDTO.orderStatus &&
                Objects.equal(orderDate, orderDTO.orderDate) &&
                Objects.equal(orderFinishDate, orderDTO.orderFinishDate) &&
                paymentType == orderDTO.paymentType &&
                Objects.equal(discount, orderDTO.discount) &&
                Objects.equal(deliveryDataDTO, orderDTO.deliveryDataDTO);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(orderingUserDTO, orderedProductDTOS, orderStatus, orderDate, orderFinishDate, paymentType, discount, deliveryDataDTO);
    }

    public UserDTO getOrderingUser() {
        return orderingUserDTO;
    }

    public void setOrderingUser(UserDTO orderingUserDTO) {
        this.orderingUserDTO = orderingUserDTO;
    }

    public List<ProductDTO> getOrderedProducts() {
        return orderedProductDTOS;
    }

    public void setOrderedProducts(List<ProductDTO> orderedProductDTOS) {
        this.orderedProductDTOS = orderedProductDTOS;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus status) {
        this.orderStatus = status;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderFinishDate() {
        return orderFinishDate;
    }

    public void setOrderFinishDate(String orderFinishDate) {
        this.orderFinishDate = orderFinishDate;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public DiscountDTO getDiscount() {
        return discount;
    }

    public void setDiscount(DiscountDTO discount) {
        this.discount = discount;
    }

    public DeliveryDataDTO getDeliveryData() {
        return deliveryDataDTO;
    }

    public void setDeliveryData(DeliveryDataDTO deliveryDataDTO) {
        this.deliveryDataDTO = deliveryDataDTO;
    }


    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public OrderDTO(UserDTO orderingUserDTO, List<ProductDTO> orderedProductDTOS, OrderStatus orderStatus, String orderDate, String orderFinishDate, PaymentType paymentType, DiscountDTO discount, int orderId, DeliveryDataDTO deliveryDataDTO, ShopDTO offeringShopDTO) {

        this.orderingUserDTO = orderingUserDTO;
        this.orderedProductDTOS = orderedProductDTOS;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
        this.orderFinishDate = orderFinishDate;
        this.paymentType = paymentType;
        this.discount = discount;
        this.orderId = orderId;
        this.deliveryDataDTO = deliveryDataDTO;
        this.offeringShopDTO = offeringShopDTO;
    }

    public ShopDTO getOfferingShop() {
        return offeringShopDTO;
    }

    public void setOfferingShop(ShopDTO offeringShopDTO) {
        this.offeringShopDTO = offeringShopDTO;
    }



    @JMapConversion(from = "status", to = "orderStatus")
    public OrderStatus conversion(com.m2iii.mapperbench.model.source.OrderStatus status) {
        OrderStatus orderStatus = null;
        switch(status) {
            case CREATED:
                orderStatus = OrderStatus.CREATED;
                break;
            case FINISHED:
                orderStatus = OrderStatus.FINISHED;
                break;

            case CONFIRMED:
                orderStatus = OrderStatus.CONFIRMED;
                break;

            case COLLECTING:
                orderStatus = OrderStatus.COLLECTING;
                break;

            case IN_TRANSPORT:
                orderStatus = OrderStatus.IN_TRANSPORT;
                break;
        }
        return orderStatus;
    }

    @JMapConversion(from = "paymentType", to = "paymentType")
    public PaymentType conversion(com.m2iii.mapperbench.model.source.PaymentType type) {
        PaymentType paymentType = null;
        switch(type) {
            case CARD:
                paymentType = PaymentType.CARD;
                break;

            case CASH:
                paymentType = PaymentType.CASH;
                break;

            case TRANSFER:
                paymentType = PaymentType.TRANSFER;
                break;
        }
        return paymentType;
    }


}
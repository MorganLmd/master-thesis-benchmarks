package com.m2iii.mapperbench.model.destination;

import com.google.common.base.Objects;
import com.googlecode.jmapper.annotations.JGlobalMap;

import java.math.BigDecimal;

@JGlobalMap
public class ProductDTO {
    private BigDecimal price;
    private int quantity;
    private String name;
    private String description;
    private boolean available;
    private RefundPolicyDTO refundPolicyDTO;


    public ProductDTO() {
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public RefundPolicyDTO getRefundPolicy() {
        return refundPolicyDTO;
    }

    public void setRefundPolicy(RefundPolicyDTO refundPolicyDTO) {
        this.refundPolicyDTO = refundPolicyDTO;
    }

    public ProductDTO(BigDecimal price, int quantity, String name, String description, boolean available, RefundPolicyDTO refundPolicyDTO) {
        this.price = price;
        this.quantity = quantity;
        this.name = name;
        this.description = description;
        this.available = available;
        this.refundPolicyDTO = refundPolicyDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (o.getClass() == com.m2iii.mapperbench.model.source.Product.class) {
            com.m2iii.mapperbench.model.source.Product product =
                    (com.m2iii.mapperbench.model.source.Product) o;
            return quantity == product.getQuantity() &&
                    available == product.isAvailable() &&
                    Objects.equal(price, product.getPrice()) &&
                    Objects.equal(name, product.getName()) &&
                    Objects.equal(description, product.getDescription()) &&
                    Objects.equal(refundPolicyDTO, product.getRefundPolicy());
        }
        if(o.getClass() != getClass()) return false;
        ProductDTO productDTO = (ProductDTO) o;
        return quantity == productDTO.quantity &&
                available == productDTO.available &&
                Objects.equal(price, productDTO.price) &&
                Objects.equal(name, productDTO.name) &&
                Objects.equal(description, productDTO.description) &&
                Objects.equal(refundPolicyDTO, productDTO.refundPolicyDTO);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(price, quantity, name, description, available, refundPolicyDTO);
    }
}
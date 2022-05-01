package com.m2iii.mapperbench.model.destination;

import com.googlecode.jmapper.annotations.JGlobalMap;

import java.util.List;

@JGlobalMap
public class ShopDTO {

    private String shopName;
    private AddressDTO shopAddres;
    private String shopUrl;
    private List<ReviewDTO> reviewDTOS;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public AddressDTO getShopAddres() {
        return shopAddres;
    }

    public void setShopAddres(AddressDTO shopAddres) {
        this.shopAddres = shopAddres;
    }

    public String  getShopUrl() {
        return shopUrl;
    }

    public void setShopUrl(String shopUrl) {
        this.shopUrl = shopUrl;
    }

    public ShopDTO() {
    }

    public List<ReviewDTO> getReviews() {
        return reviewDTOS;
    }

    public void setReviews(List<ReviewDTO> reviewDTOS) {
        this.reviewDTOS = reviewDTOS;
    }

    public ShopDTO(String shopName, AddressDTO shopAddres, String shopUrl, List<ReviewDTO> reviewDTOS) {

        this.shopName = shopName;
        this.shopAddres = shopAddres;
        this.shopUrl = shopUrl;
        this.reviewDTOS = reviewDTOS;
    }
}
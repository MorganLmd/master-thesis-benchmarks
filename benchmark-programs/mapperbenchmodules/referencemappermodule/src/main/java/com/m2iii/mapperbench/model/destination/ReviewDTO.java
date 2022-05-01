package com.m2iii.mapperbench.model.destination;

import com.googlecode.jmapper.annotations.JGlobalMap;

@JGlobalMap
public class ReviewDTO {

    int shippingGrade;
    int pricingGrade;
    int serviceGrade;
    UserDTO reviewingUserDTO;
    String note;

    public int getShippingGrade() {
        return shippingGrade;
    }

    public void setShippingGrade(int shippingGrade) {
        this.shippingGrade = shippingGrade;
    }

    public int getPricingGrade() {
        return pricingGrade;
    }

    public void setPricingGrade(int pricingGrade) {
        this.pricingGrade = pricingGrade;
    }

    public int getServiceGrade() {
        return serviceGrade;
    }

    public void setServiceGrade(int serviceGrade) {
        this.serviceGrade = serviceGrade;
    }

    public UserDTO getReviewingUser() {
        return reviewingUserDTO;
    }

    public void setReviewingUser(UserDTO reviewingUserDTO) {
        this.reviewingUserDTO = reviewingUserDTO;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public ReviewDTO() {

    }

    public ReviewDTO(int shippingGrade, int pricingGrade, int serviceGrade, UserDTO reviewingUserDTO, String note) {

        this.shippingGrade = shippingGrade;
        this.pricingGrade = pricingGrade;
        this.serviceGrade = serviceGrade;
        this.reviewingUserDTO = reviewingUserDTO;
        this.note = note;
    }
}
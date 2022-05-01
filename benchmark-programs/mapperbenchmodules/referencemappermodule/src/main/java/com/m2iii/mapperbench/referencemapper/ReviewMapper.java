package com.m2iii.mapperbench.referencemapper;

import com.m2iii.mapperbench.Converter;
import com.m2iii.mapperbench.model.destination.ReviewDTO;
import com.m2iii.mapperbench.model.source.Review;

public class ReviewMapper implements Converter<Review, ReviewDTO> {

    private static ReviewMapper reviewMapper = null;
    private final UserMapper userMapper = UserMapper.getInstance();

    private ReviewMapper() {}

    @Override
    public ReviewDTO convert(Review review) {
        return review == null ? null : new ReviewDTO(
                review.getShippingGrade(),
                review.getPricingGrade(),
                review.getServiceGrade(),
                userMapper.convert(review.getReviewingUser()),
                review.getNote()
        );
    }

    public static ReviewMapper getInstance() {
        if (reviewMapper == null) {
            reviewMapper = new ReviewMapper();
        }
        return reviewMapper;
    }

}

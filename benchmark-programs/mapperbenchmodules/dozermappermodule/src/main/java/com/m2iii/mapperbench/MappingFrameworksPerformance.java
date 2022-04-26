package com.m2iii.mapperbench;

import com.m2iii.mapperbench.dozer.DozerConverter;
import com.m2iii.mapperbench.model.destination.Order;
import com.m2iii.mapperbench.model.source.AccountStatus;
import com.m2iii.mapperbench.model.source.Address;
import com.m2iii.mapperbench.model.source.DeliveryData;
import com.m2iii.mapperbench.model.source.Discount;
import com.m2iii.mapperbench.model.source.OrderStatus;
import com.m2iii.mapperbench.model.source.PaymentType;
import com.m2iii.mapperbench.model.source.Product;
import com.m2iii.mapperbench.model.source.RefundPolicy;
import com.m2iii.mapperbench.model.source.Review;
import com.m2iii.mapperbench.model.source.Shop;
import com.m2iii.mapperbench.model.source.SourceOrder;
import com.m2iii.mapperbench.model.source.User;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
public class MappingFrameworksPerformance {

    private static final DozerConverter CONVERTER = new DozerConverter();

    public static void main(String[] args) {
        SourceOrder sourceOrder = null;
        User user = new User("John", "John@doe.com", AccountStatus.ACTIVE);
        RefundPolicy refundPolicy = new RefundPolicy(true, 30, Collections
                .singletonList("Refundable only if not used!"));

        Product product = new Product(BigDecimal.valueOf(10.99),
                100,
                "Sample Product",
                "Sample Product to be sold",
                true,
                refundPolicy
        );

        Discount discount = new Discount(Instant.now().toString(), Instant.now().toString(), BigDecimal.valueOf(5.99));
        Address deliveryAddress = new Address("Washington Street 5", "New York", "55045", "USA");
        DeliveryData deliveryData = new DeliveryData(deliveryAddress, true, "", 10);
        Address shopAddress = new Address("Roosvelt Street 9", "Boston", "55042", "USA");
        User reviewingUser = new User("John", "Johhny@John.com", AccountStatus.ACTIVE);
        User negativeReviewingUser = new User("Carl", "Carl@Coral.com", AccountStatus.ACTIVE);
        Review review = new Review(5, 5, 5, reviewingUser, "The best shop I've ever bought things in");

        Review negativeReview = new Review(1, 1, 1, negativeReviewingUser, "I will never buy anything again here!");

        List<Review> reviewList = new ArrayList<>();
        reviewList.add(review);
        reviewList.add(negativeReview);
        Shop shop = new Shop("Super Shop", shopAddress, "www.super-shop.com", reviewList);

        sourceOrder = new SourceOrder(OrderStatus.CONFIRMED,
                Instant.now().toString(),
                Instant.MAX.toString(),
                PaymentType.TRANSFER,
                discount,
                deliveryData,
                user,
                Collections.singletonList(product),
                shop,
                1
        );

        log.info("Start benchmarking");
        List<Order> orders = new ArrayList<>();
        Instant start = Instant.now();
        for (int i = 0 ; i < 100000 ; i++) {
            orders.add(CONVERTER.convert(sourceOrder));
        }
        Instant end = Instant.now();
        long totalTime = Duration.between(start, end).toMillis();
        log.info("benchmark finished with : " + totalTime + "ms");
    }
}
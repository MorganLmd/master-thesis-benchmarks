package com.m2iii.mapperbench.referencemapper;

import com.m2iii.mapperbench.Converter;
import com.m2iii.mapperbench.model.destination.ProductDTO;
import com.m2iii.mapperbench.model.source.Product;

public class ProductMapper implements Converter<Product, ProductDTO> {

    private static ProductMapper productMapper = null;
    private RefundPolicyMapper refundPolicyMapper = RefundPolicyMapper.getInstance();

    private ProductMapper() {}

    @Override
    public ProductDTO convert(Product product) {
        return product == null ? null : new ProductDTO(
                product.getPrice(),
                product.getQuantity(),
                product.getName(),
                product.getDescription(),
                product.isAvailable(),
                refundPolicyMapper.convert(product.getRefundPolicy())
        );
    }

    public static ProductMapper getInstance() {
        if (productMapper == null) {
            productMapper = new ProductMapper();
        }

        return productMapper;
    }
}

package com.m2iii.mapperbench.referencemapper;

import com.m2iii.mapperbench.Converter;
import com.m2iii.mapperbench.model.destination.RefundPolicyDTO;
import com.m2iii.mapperbench.model.source.RefundPolicy;

public class RefundPolicyMapper implements Converter<RefundPolicy, RefundPolicyDTO> {

    private static RefundPolicyMapper refundPolicyMapper = null;

    private RefundPolicyMapper() {}

    @Override
    public RefundPolicyDTO convert(RefundPolicy refundPolicy) {
        return refundPolicy == null ? null : new RefundPolicyDTO(
                refundPolicy.isRefundable(),
                refundPolicy.getRefundTimeInDays(),
                refundPolicy.getNotes()
        );
    }

    public static RefundPolicyMapper getInstance() {
        if (refundPolicyMapper == null) {
            refundPolicyMapper = new RefundPolicyMapper();
        }

        return refundPolicyMapper;
    }
}

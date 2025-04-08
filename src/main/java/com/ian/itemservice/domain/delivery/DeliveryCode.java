package com.ian.itemservice.domain.delivery;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * code: displayName
 * FAST: 빠른 배송
 * NORMAL: 일반 배송
 * SLOW: 느린 배송
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryCode {
    private String code;
    private String displayName;

}

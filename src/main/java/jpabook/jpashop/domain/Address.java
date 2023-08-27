package jpabook.jpashop.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

/**
 * 값 타입은 setter를 제공하지 않고 생성할 때만 값이 설정되도록.
 * 변경 불가능하도록 해야함.
 *
 * JPA 기본 스펙상 Entity나 Embeddable타입은 java 기본 생성자를 public이나 protected로 두어야함.
 */
@Embeddable//어딘가 내장될 수 있다.
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Address {
    private String city;
    private String street;
    private String zipcode;
}

package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "order_id")//DBA가 이 방식을 선호함.
    private Long id;

    /**
     * 양방향 연관관계의 주인은 Order임.
     * member쪽에 orders에 order를 추가한다고
     * order의 fk가 변경되지 않음.
     *
     * order의 member를 변경해야 fk가 다른 member로 변경됨.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")//조인 컬럼을 무엇으로 할 것인가?
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    /**
     * 1대1 연관관계에서는 FK를 어디든 둘 수 있지만,
     * Access가 많은 곳에 둔다.
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;//주문 상태 [ORDER, CANCEL]
}

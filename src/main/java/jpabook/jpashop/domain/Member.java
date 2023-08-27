package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    /**
     * Embeddable타입의의 값을 갖는다.
     * 해당 타입은 별도의 생명주기를 갖지 않고 속한 Entity의 생명주기를 따라간다.
     * Embaddable클래스의 속성은 속한 Entity의 Table에 저장됨.
     */
    @Embedded
    private Address address;

    /**
     *  JPA에서 mappedBy는 이 관계는 다른 편에서 매핑되었다는 의미를 가집니다.
     *
     * 예를 들어, User와 Order의 관계에서 User 엔티티에 mappedBy="user"라고 표기한다면,
     * User와 Order 사이의 관계는 Order 쪽의 user 필드에 의해 매핑되었다"라는 의미가 됩니다.
     * 그렇기 때문에 이 관계에서 User 쪽이 연관 관계의 주인이 아니라는 것을 알 수 있습니다.
     *
     * 다르게 표현하면, mappedBy는 해당 엔티티가 관계에서 주인이 아니며,
     * 대신 다른 엔티티에서 이미 관계가 매핑되었다는 것을 지정하는 역할을 합니다.
     * mappedBy가 지정된 엔티티 쪽에서는 데이터베이스 연관 관계의 CRUD를 수행하지 않습니다.
     */
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();


}

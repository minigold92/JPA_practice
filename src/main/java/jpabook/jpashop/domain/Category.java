package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.FetchType;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    /**
     * 객체는 컬렉션으로 서로 리스트를 갖는게 가능하지만
     * RDB는 관계를 나타낼 중간 테이블이 필요함
     * 중간 테이블에 있는 FK 조인키 설정.
     *
     * ManyToMany는 실무에서 쓰지 않음.
     * 중간 테이블에 FK를 두는 것 밖에 못함. 필드를 추가할 수 없음.
     * 중간에 등록 시간이나 수정 시간이라도 넣어주기 마련인데, 실무에서는 사용하기 부족함.
     */
    @ManyToMany
    @JoinTable(name = "category_item",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items = new ArrayList<>();


    /**
     * 셀프로 양방향 연관관계를 건 경우.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    public void addChildCategory(Category child) {
        this.child.add(child);
        child.setParent(this);
    }
}

package com.projects.shoppingcart.model.master;

import com.projects.shoppingcart.model.reference.ScRStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "SC_M_ORDER")
public class ScMOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SC_M_ORDER")
    @Column(name = "ORDER_ID", nullable = false)
    private Long orderId;

    @Column(name = "ORDER_NO", length = 20)
    private String orderNo;

    @Column(name = "ORDER_DATE")
    private LocalDate orderDate;

    @Column(name = "ORDER_TIME")
    private LocalTime orderTime;

    @Column(name = "TOTAL_ITEM")
    private Integer totalItem;

    @Column(name = "TOTAL_AMOUNT")
    private Float totalAmount;

    @Column(name = "TOTAL_DISCOUNT")
    private Float totalDiscount;

    @Column(name = "TOTAL_TAX")
    private Float totalTax;

    @Column(name = "TOTAL_PAYABLE")
    private Float totalPayable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    private ScMUser scMUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STATUS_ID", referencedColumnName = "STATUS_ID", nullable = false)
    private ScRStatus scRStatus;

    @OneToMany(mappedBy = "scMOrder", fetch = FetchType.LAZY)
    private List<ScMOrderProduct> scMOrderProducts;

}

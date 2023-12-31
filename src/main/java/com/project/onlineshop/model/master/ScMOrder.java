package com.project.onlineshop.model.master;

import com.project.onlineshop.model.reference.ScRStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
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
    private String orderTime;

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
    @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "CUSTOMER_ID")
    private ScMCustomer scMCustomer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STATUS_ID", referencedColumnName = "STATUS_ID", nullable = false)
    private ScRStatus scRStatus;

    @OneToMany(mappedBy = "scMOrder", fetch = FetchType.LAZY)
    private List<ScMOrderProduct> scMOrderProducts;

}

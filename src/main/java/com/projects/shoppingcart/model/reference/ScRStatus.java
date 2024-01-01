package com.projects.shoppingcart.model.reference;

import com.projects.shoppingcart.model.master.ScMCustomer;
import com.projects.shoppingcart.model.master.ScMOrder;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "SC_R_STATUS", indexes = {
        @Index(name = "SC_R_STATUS_CODE_UNQ_IDX", columnList = "STATUS_CODE", unique = true)
})
public class ScRStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SC_R_STATUS")
    @Column(name = "STATUS_ID", nullable = false)
    private Long statusId;

    @Column(name = "STATUS_NAME", length = 45)
    private String name;

    @Column(name = "STATUS_CODE", length = 5, unique = true)
    private String code;

    @Column(name = "STATUS_TYPE", length = 45)
    private String type;

    @OneToMany(mappedBy = "scRStatus", fetch = FetchType.LAZY)
    private List<ScMOrder> scMOrders;

    @OneToMany(mappedBy = "scRStatus", fetch = FetchType.LAZY)
    private List<ScMCustomer> scMCustomers;

}

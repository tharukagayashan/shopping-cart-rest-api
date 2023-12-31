package com.project.onlineshop.model.reference;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "SC_R_STATUS")
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

}

package com.tahauddin.syed.domain.tahauddin.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CARD_HOLDER")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardHolder {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID") private Long id;
    @Column(name = "CARD_ID") private Long cardId;
    @Column(name = "NAME") private String name;
    @Column(name = "CUSTOMER_ID") private String customerId;
    @Column(name = "PIN") private String pin;
    @Column(name = "CVV") private String cvv;

}

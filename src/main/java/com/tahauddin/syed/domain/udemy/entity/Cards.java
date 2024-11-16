package com.tahauddin.syed.domain.udemy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "CARDS_TABLE")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cards {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID") private Long id;
    @Column(name = "CARD_NAME") private String cardName;
    @Column(name = "CARD_TYPE") private String cardType;
    @Column(name = "AMOUNT") private String amount;
    @Column(name = "CREATED_BY") private String createdBy;
    @Column(name = "UPDATED_BY") private String updatedBy;
    @Column(name = "CREATED_DATE") private LocalDateTime createdDate;
    @Column(name = "UPDATED_DATE") private LocalDateTime updatedDate;

}

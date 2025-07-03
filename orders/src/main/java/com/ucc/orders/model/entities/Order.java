package com.ucc.orders.model.entities;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table (name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long productId;

    private Integer stock;


}

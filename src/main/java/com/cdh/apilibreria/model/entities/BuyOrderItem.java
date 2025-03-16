package com.cdh.apilibreria.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class BuyOrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "buy_order_id")
    private BuyOrder buyOrder;

    @ManyToOne
    @JoinColumn(name = "libro_id")
    private Libro libro;

    private int cantidad;

    public BuyOrderItem(BuyOrder buyOrder, Libro libro, int cantidad) {
        this.buyOrder = buyOrder;
        this.libro = libro;
        this.cantidad = cantidad;
    }
}
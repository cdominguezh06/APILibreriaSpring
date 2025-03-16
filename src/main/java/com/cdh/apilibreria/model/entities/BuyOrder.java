package com.cdh.apilibreria.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class BuyOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "buyOrder", cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    public List<BuyOrderItem> buyOrderItems = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.MERGE)
    private User user;

    public void addItem(Libro libro, int cantidad) {
        BuyOrderItem item = new BuyOrderItem(this, libro, cantidad);
        buyOrderItems.add(item);
    }
}

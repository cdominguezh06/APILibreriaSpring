    package com.cdh.apilibreria.model.entities;

    import com.cdh.apilibreria.model.POJO.DireccionUsuario;
    import com.cdh.apilibreria.model.POJO.UserRole;
    import com.fasterxml.jackson.annotation.JsonIgnore;
    import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
    import jakarta.persistence.*;
    import lombok.*;

    import java.util.Arrays;
    import java.util.List;
    import java.util.Set;

    @Data
    @Entity
    @NoArgsConstructor
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public class User {
        @Id
        @NonNull
        private String username;
        @NonNull
        @Column(unique = true)
        private String email;
        private String nombre;
        private String apellidos;
        @Embedded
        private DireccionUsuario direccion;
        @NonNull
        private String password;

        @ManyToMany(cascade = CascadeType.REMOVE)
        @JoinTable(
                name = "user_informacion_pago",
                joinColumns = @JoinColumn(name = "user_username"),
                inverseJoinColumns = @JoinColumn(name = "informacion_pago_numero_tarjeta")
        )
        @EqualsAndHashCode.Exclude
        @ToString.Exclude
        @JsonIgnore
        private Set<InformacionPago> informacionesPago;

        @Enumerated(EnumType.ORDINAL)
        private UserRole role;


        @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
        @EqualsAndHashCode.Exclude
        @ToString.Exclude
        @JsonIgnore
        private List<BuyOrder> orders;

        public User(@NonNull String username, @NonNull String email, @NonNull String password) {
            this.username = username;
            this.email = email;
            this.password = password;
            this.role = UserRole.CLIENT;
        }

        public void addInformacionPago(InformacionPago informacionPago) {
            informacionesPago.add(informacionPago);
        }

        public void addBuyOrder(BuyOrder buyOrder) {
            orders.add(buyOrder);
        }
    }

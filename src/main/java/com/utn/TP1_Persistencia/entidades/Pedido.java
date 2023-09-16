package com.utn.TP1_Persistencia.entidades;

import com.utn.TP1_Persistencia.Enumeraciones.Estado;
import com.utn.TP1_Persistencia.Enumeraciones.TipoEnvio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Estado estado;

    private Date fecha;

    private TipoEnvio tipoEnvio;

    private double total;


// RELACION ONE TO ONE UNIDIRECCIONAL
@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
@JoinColumn(name = "factura_id")
private Factura factura;

    // RELACION ONE TO MANY UNIDIRECCIONAL
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    @JoinColumn(name = "pedido_id")
    // OJO ES IMPORTANTE COLOCAR ESTA ANOTACIÓN SINO ME DA ERROR

    @Builder.Default
    private List<DetallePedido> detallePedidos = new ArrayList<>();

    public void agregarDetallePedido(DetallePedido dp){

        detallePedidos.add(dp);
    }
   public void mostrarDetallePedidos() {
        System.out.println("DetallePedidos de " + estado + " " + fecha + " " + tipoEnvio + " " + total + ":");
        for (DetallePedido detallePedido : detallePedidos) {
            System.out.println("Cantidad: " + detallePedido.getCantidad() + ", Subtotal: " + detallePedido.getSubtotal());
        }

    }

}

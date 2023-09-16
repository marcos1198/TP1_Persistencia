package com.utn.TP1_Persistencia.entidades;

import com.utn.TP1_Persistencia.Enumeraciones.Tipo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Producto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Tipo tipo;

    private int tiempoEstimadoCocina;

    private String denominacion;

    private double precioVenta;

    private double precioCompra;

    private int stockActual;

    private int stockMinimo;

    private String unidadMedida;

    private String receta;


/*
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    @Builder.Default
   private List<DetallePedido> detallePedidos= new ArrayList<>();

    public void agregarDetallePedido(DetallePedido detped){

        detallePedidos.add(detped);
    }


    public void mostrarDetallePedidos() {
        System.out.println("DetallePedido de " + tipo + " " + tiempoEstimadoCocina + " " + denominacion + " " + precioVenta + " " + precioCompra + " " + stockActual + " " + stockMinimo + " " + unidadVendida + " " + receta +":");
        for (DetallePedido detallePedido : detallePedidos) {
            System.out.println("Cantidad: " + detallePedido.getCantidad() + ",S: " + detallePedido.getSubtotal());
        }


    }

 */
}


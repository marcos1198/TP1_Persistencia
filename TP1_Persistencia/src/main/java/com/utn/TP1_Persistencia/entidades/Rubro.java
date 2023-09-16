package com.utn.TP1_Persistencia.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rubro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String denominacion;

    // RELACION ONE TO MANY UNIDIRECCIONAL
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    @JoinColumn(name = "rubro_id")
    // OJO ES IMPORTANTE COLOCAR ESTA ANOTACIÓN SINO ME DA ERROR

    @Builder.Default
    private List<Producto> productos = new ArrayList<>();

    public void agregarProducto(Producto p){

        productos.add(p);
    }
   public void mostrarProductos() {
        System.out.println("Productos de " +  denominacion + ":");
        for (Producto producto : productos) {
            System.out.println("Tipo: " + producto.getTipo() + ", TiempoEstimadoCocina: " + producto.getTiempoEstimadoCocina() + ", Denominacion: " + producto.getDenominacion() + ", PrecioVenta: "+ producto.getPrecioVenta() + ", PrecioCompra: " + producto.getPrecioCompra() +", StockActual: " + producto.getStockActual() +", StockMinimo: "+ producto.getStockMinimo() + ", UnidadVendida: " + producto.getUnidadMedida() + ", Receta: " + producto.getReceta() );
        }

    }
}

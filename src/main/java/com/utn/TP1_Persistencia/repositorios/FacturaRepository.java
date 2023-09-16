package com.utn.TP1_Persistencia.repositorios;

import com.utn.TP1_Persistencia.entidades.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaRepository extends JpaRepository<Factura,Long> {
}

package co.ucentral.ventaproductosmascotas.persistencia.repositorio;

import co.ucentral.ventaproductosmascotas.persistencia.entidades.Descuento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DescuentosPromocionesRepositorio extends JpaRepository<Descuento, Long> {
    // Métodos personalizados para descuentos si es necesario
}

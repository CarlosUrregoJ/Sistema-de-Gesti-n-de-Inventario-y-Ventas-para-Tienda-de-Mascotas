package co.ucentral.ventaproductosmascotas.persistencia.repositorio;

import co.ucentral.ventaproductosmascotas.persistencia.entidades.ventas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ventasRepositorio extends JpaRepository<ventas, Long> {

}

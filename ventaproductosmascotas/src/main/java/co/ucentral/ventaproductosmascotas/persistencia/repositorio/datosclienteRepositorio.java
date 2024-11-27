package co.ucentral.ventaproductosmascotas.persistencia.repositorio;

import co.ucentral.ventaproductosmascotas.persistencia.entidades.datoscliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface datosclienteRepositorio extends JpaRepository<datoscliente, Long> {
    // Los métodos básicos como guardar, buscar, eliminar ya están disponibles gracias a JpaRepository.
}

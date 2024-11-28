package co.ucentral.ventaproductosmascotas.persistencia.repositorio;

import co.ucentral.ventaproductosmascotas.persistencia.entidades.datoscliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface datosclienteRepositorio extends JpaRepository<datoscliente, Long> {
    // Los métodos básicos como guardar, buscar, eliminar ya están disponibles gracias a JpaRepository.

    // Método para buscar cliente por identificacionCliente
    Optional<datoscliente> findByIdentificacionCliente(String identificacion_Cliente);
}

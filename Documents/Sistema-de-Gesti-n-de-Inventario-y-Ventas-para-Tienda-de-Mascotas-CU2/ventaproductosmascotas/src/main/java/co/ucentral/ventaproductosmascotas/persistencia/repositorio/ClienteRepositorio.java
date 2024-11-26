package co.ucentral.ventaproductosmascotas.persistencia.repositorio;

import co.ucentral.ventaproductosmascotas.persistencia.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {
    // Métodos personalizados para clientes si es necesario
}

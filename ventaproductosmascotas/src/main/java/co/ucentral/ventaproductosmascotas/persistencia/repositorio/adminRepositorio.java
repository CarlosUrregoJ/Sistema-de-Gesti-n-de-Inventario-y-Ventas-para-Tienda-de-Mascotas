package co.ucentral.ventaproductosmascotas.persistencia.repositorio;

import co.ucentral.ventaproductosmascotas.persistencia.entidades.admins;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface adminRepositorio extends JpaRepository<admins, Long> {

    // Método para buscar un admin por username
    @Query("SELECT a FROM admins a WHERE a.username = :username")
    admins findByUsername(@Param("username") String username);
}



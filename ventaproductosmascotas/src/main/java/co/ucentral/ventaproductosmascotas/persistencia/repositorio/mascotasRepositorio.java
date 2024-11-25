package co.ucentral.ventaproductosmascotas.persistencia.repositorio;

import co.ucentral.ventaproductosmascotas.persistencia.entidades.mascotas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface mascotasRepositorio extends JpaRepository<mascotas, Long> {

    // Método para encontrar productos por categoría
    @Query("SELECT m FROM mascotas m WHERE m.categoria = :categoria")
    List<mascotas> findByCategoria(@Param("categoria") String categoria);

    // Método para búsqueda flexible por nombre
    @Query("SELECT m FROM mascotas m WHERE LOWER(m.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<mascotas> findByNombreContaining(@Param("nombre") String nombre);
}

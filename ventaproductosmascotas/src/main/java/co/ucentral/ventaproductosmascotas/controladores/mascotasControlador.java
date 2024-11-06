package co.ucentral.ventaproductosmascotas.controladores;

import co.ucentral.ventaproductosmascotas.persistencia.entidades.mascotas;
import co.ucentral.ventaproductosmascotas.persistencia.repositorio.mascotasRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mascotas")
public class mascotasControlador {

    @Autowired
    private mascotasRepositorio mascotasRepositorio;

    // Endpoint para obtener productos por categoría
    @GetMapping("/categoria")
    public ResponseEntity<List<mascotas>> obtenerMascotasPorCategoria(@RequestParam String categoria) {
        List<mascotas> productos = mascotasRepositorio.findByCategoria(categoria);
        if (productos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(productos);
        }
        return ResponseEntity.ok(productos);
    }

    // Endpoint para agregar un nuevo producto
    @PostMapping
    public ResponseEntity<?> agregarMascota(@RequestBody mascotas mascota) {
        try {
            mascotas nuevaMascota = mascotasRepositorio.save(mascota);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaMascota);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al agregar el producto: " + e.getMessage());
        }
    }
}

package co.ucentral.ventaproductosmascotas.controladores;

import co.ucentral.ventaproductosmascotas.persistencia.entidades.Descuento;
import co.ucentral.ventaproductosmascotas.persistencia.repositorio.DescuentosPromocionesRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/descuentos")
public class DescuentosPromocionesControlador {

    @Autowired
    private DescuentosPromocionesRepositorio descuentosRepositorio;

    @PostMapping
    public ResponseEntity<?> agregarDescuento(@RequestBody Descuento descuento) {
        try {
            Descuento nuevoDescuento = descuentosRepositorio.save(descuento);
            return ResponseEntity.status(HttpStatus.CREATED).body("Descuento agregado exitosamente con ID: " + nuevoDescuento.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al agregar el descuento: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Descuento>> obtenerTodosLosDescuentos() {
        List<Descuento> listaDescuentos = descuentosRepositorio.findAll();
        return ResponseEntity.ok(listaDescuentos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarDescuento(@PathVariable Long id) {
        Optional<Descuento> descuento = descuentosRepositorio.findById(id);
        if (descuento.isPresent()) {
            descuentosRepositorio.delete(descuento.get());
            return ResponseEntity.ok("Descuento eliminado exitosamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Descuento no encontrado");
        }
    }
}

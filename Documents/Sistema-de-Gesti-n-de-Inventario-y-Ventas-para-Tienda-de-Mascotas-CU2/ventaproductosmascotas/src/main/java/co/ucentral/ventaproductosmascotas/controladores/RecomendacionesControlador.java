package co.ucentral.ventaproductosmascotas.controladores;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/api/recomendaciones")
public class RecomendacionesControlador {

    @GetMapping("/{clienteId}")
    public ResponseEntity<?> generarRecomendaciones(@PathVariable Long clienteId) {
        // Lógica para generar recomendaciones (placeholder)
        List<String> recomendaciones = List.of("Producto 1", "Producto 2", "Producto 3");
        return ResponseEntity.ok(recomendaciones);
    }
}

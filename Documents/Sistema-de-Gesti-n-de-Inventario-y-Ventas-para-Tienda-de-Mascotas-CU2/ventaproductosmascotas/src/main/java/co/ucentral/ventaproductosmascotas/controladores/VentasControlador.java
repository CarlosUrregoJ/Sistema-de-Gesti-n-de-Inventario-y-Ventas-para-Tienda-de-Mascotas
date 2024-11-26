package co.ucentral.ventaproductosmascotas.controladores;

import co.ucentral.ventaproductosmascotas.persistencia.entidades.Venta;
import co.ucentral.ventaproductosmascotas.persistencia.repositorio.VentasRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentasControlador {

    @Autowired
    private VentasRepositorio ventasRepositorio;

    @PostMapping
    public ResponseEntity<?> registrarVenta(@RequestBody Venta venta) {
        try {
            Venta nuevaVenta = ventasRepositorio.save(venta);
            return ResponseEntity.ok("Venta registrada exitosamente con ID: " + nuevaVenta.getId());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al registrar la venta: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Venta>> obtenerTodasLasVentas() {
        return ResponseEntity.ok(ventasRepositorio.findAll());
    }
}

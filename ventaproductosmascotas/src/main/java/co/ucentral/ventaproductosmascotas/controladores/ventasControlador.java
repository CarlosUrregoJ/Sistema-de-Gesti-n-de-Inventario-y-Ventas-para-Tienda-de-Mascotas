package co.ucentral.ventaproductosmascotas.controladores;

import co.ucentral.ventaproductosmascotas.persistencia.entidades.ventas;
import co.ucentral.ventaproductosmascotas.persistencia.entidades.mascotas;
import co.ucentral.ventaproductosmascotas.persistencia.repositorio.mascotasRepositorio;
import co.ucentral.ventaproductosmascotas.persistencia.repositorio.ventasRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/ventas")
public class ventasControlador {

    @Autowired
    private mascotasRepositorio mascotasRepositorio;
    @Autowired
    private ventasRepositorio ventasRepositorio;

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarVenta(@RequestBody ventas nuevaVenta) {
        try {
            // Establecer la fecha y hora de la venta
            nuevaVenta.setFechaHoraVenta(LocalDateTime.now());

            // Guardar la venta en la base de datos
            ventas ventaGuardada = ventasRepositorio.save(nuevaVenta);

            return ResponseEntity.ok(ventaGuardada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar la venta: " + e.getMessage());
        }
    }

    @GetMapping("/todas")
    public ResponseEntity<?> obtenerTodasLasVentas() {
        try {
            List<ventas> todasLasVentas = ventasRepositorio.findAll();
            if (todasLasVentas.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay ventas registradas.");
            }
            return ResponseEntity.ok(todasLasVentas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener las ventas: " + e.getMessage());
        }
    }



    @PostMapping("/venta")
    @ResponseBody
    public ResponseEntity<?> registrarVenta(@RequestBody ventas ventaRequest) {
        try {
            // Buscar el producto por ID
            Optional<mascotas> productoOpt = mascotasRepositorio.findById(ventaRequest.getProductoId());
            if (!productoOpt.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Producto no encontrado con ID: " + ventaRequest.getProductoId());
            }

            mascotas producto = productoOpt.get();

            // Verificar si la cantidad solicitada es válida
            if (producto.getCantidad() < ventaRequest.getCantidad()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Cantidad insuficiente. Solo hay " + producto.getCantidad() + " disponibles.");
            }

            // Restar la cantidad vendida
            producto.setCantidad(producto.getCantidad() - ventaRequest.getCantidad());
            mascotasRepositorio.save(producto); // Guardar los cambios en la base de datos

            // Retornar una respuesta exitosa
            return ResponseEntity.ok("Venta registrada exitosamente. Cantidad actualizada: " + producto.getCantidad());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al registrar la venta: " + e.getMessage());
        }


    }
}

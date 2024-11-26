package co.ucentral.ventaproductosmascotas.controladores;

import co.ucentral.ventaproductosmascotas.persistencia.entidades.mascotas;
import co.ucentral.ventaproductosmascotas.persistencia.repositorio.mascotasRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/mascotas")
public class mascotasControlador {

    @Autowired
    private mascotasRepositorio mascotasRepositorio;

    // Método para devolver la página principal
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    // Método para mostrar la página de inicio de sesión
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // Método para redirigir a la página de administración de productos
    @GetMapping("/administrar_productos_admin")
    public String manejarprodcutos() {
        return "administrar_productos_admin";
    }

    @GetMapping("/administrar_productos_admins")
    public String manejarprodcutosadmins() {
        return "administrar_productos_admins";
    }

    // Método GET para obtener productos por categoría específica
    @GetMapping("/categoria")
    @ResponseBody
    public ResponseEntity<?> obtenerMascotasPorCategoria(@RequestParam String categoria) {
        try {
            List<mascotas> productos = mascotasRepositorio.findByCategoria(categoria);
            if (productos.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron productos en la categoría especificada.");
            }
            return ResponseEntity.ok(productos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener productos por categoría: " + e.getMessage());
        }
    }

    // Método POST para agregar un nuevo producto
    @PostMapping
    @ResponseBody
    public ResponseEntity<?> agregarMascota(@RequestBody mascotas mascota) {
        try {
            mascotas nuevaMascota = mascotasRepositorio.save(mascota);
            return ResponseEntity.status(HttpStatus.CREATED).body("Producto agregado exitosamente: " + nuevaMascota);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al agregar el producto: " + e.getMessage());
        }
    }



    // Método DELETE para eliminar un producto existente
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> eliminarMascota(@PathVariable Long id) {
        try {
            Optional<mascotas> mascotaExistente = mascotasRepositorio.findById(id);
            if (!mascotaExistente.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado con ID: " + id);
            }

            mascotasRepositorio.delete(mascotaExistente.get());
            return ResponseEntity.ok("Producto eliminado exitosamente con ID: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el producto: " + e.getMessage());
        }
    }

    // Método PUT para actualizar un producto existente
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> actualizarMascota(@PathVariable Long id, @RequestBody mascotas mascotaDetalles) {
        try {
            Optional<mascotas> mascotaExistente = mascotasRepositorio.findById(id);
            if (!mascotaExistente.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado con ID: " + id);
            }

            mascotas mascota = mascotaExistente.get();
            mascota.setNombre(mascotaDetalles.getNombre());
            mascota.setCategoria(mascotaDetalles.getCategoria());
            mascota.setCantidad(mascotaDetalles.getCantidad());
            mascota.setPrecio(mascotaDetalles.getPrecio());
            mascota.setDescripcion(mascotaDetalles.getDescripcion());

            mascotas mascotaActualizada = mascotasRepositorio.save(mascota);
            return ResponseEntity.ok("Producto actualizado exitosamente: " + mascotaActualizada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el producto: " + e.getMessage());
        }
    }

    // Método GET para buscar productos por nombre flexible
    @GetMapping("/buscar")
    @ResponseBody
    public ResponseEntity<?> buscarMascotasPorNombre(@RequestParam String nombre) {
        try {
            List<mascotas> productos = mascotasRepositorio.findByNombreContaining(nombre);
            if (productos.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron productos que coincidan con el término: " + nombre);
            }
            return ResponseEntity.ok(productos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al buscar productos: " + e.getMessage());
        }
    }
}

package co.ucentral.ventaproductosmascotas.controladores;

import co.ucentral.ventaproductosmascotas.persistencia.entidades.admins;
import co.ucentral.ventaproductosmascotas.persistencia.repositorio.adminRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/admins")
public class adminControlador {

    @Autowired
    private adminRepositorio adminRepositorio;

    // Método para agregar un nuevo admin
    @PostMapping
    public ResponseEntity<?> agregarAdmin(@RequestBody admins admin) {
        try {
            admins nuevoAdmin = adminRepositorio.save(admin);
            return ResponseEntity.status(HttpStatus.CREATED).body("Admin agregado exitosamente con ID: " + nuevoAdmin.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al agregar el admin: " + e.getMessage());
        }
    }

    // Método para validar las credenciales de un admin
    @PostMapping("/validar")
    public ResponseEntity<?> validarAdmin(@RequestBody admins admin) {
        admins adminEncontrado = adminRepositorio.findByUsername(admin.getUsername());
        if (adminEncontrado != null && adminEncontrado.getPassword().equals(admin.getPassword())) {
            return ResponseEntity.ok(Map.of("success", true));
        } else {
            return ResponseEntity.ok(Map.of("success", false));
        }
    }

    // Método para obtener un admin por su ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerAdminPorId(@PathVariable Long id) {
        Optional<admins> admin = adminRepositorio.findById(id);
        if (admin.isPresent()) {
            return ResponseEntity.ok(admin.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin no encontrado");
        }
    }

    // Método para obtener todos los admins
    @GetMapping
    public ResponseEntity<List<admins>> obtenerTodosLosAdmins() {
        List<admins> listaAdmins = adminRepositorio.findAll();
        return ResponseEntity.ok(listaAdmins);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarAdmin(@PathVariable Long id) {
        Optional<admins> admin = adminRepositorio.findById(id);
        if (admin.isPresent()) {
            admins adminToDelete = admin.get();
            // Formato del mensaje incluye solo ID y username
            String info = String.format("Admin ID-%d: %s eliminado exitosamente.", adminToDelete.getId(), adminToDelete.getUsername());
            adminRepositorio.delete(adminToDelete);
            return ResponseEntity.ok(info);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin no encontrado.");
        }
    }
}

package co.ucentral.ventaproductosmascotas.controladores;

import co.ucentral.ventaproductosmascotas.persistencia.entidades.Cliente;
import co.ucentral.ventaproductosmascotas.persistencia.repositorio.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class DatosClienteMascotaControlador {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @PostMapping
    public ResponseEntity<?> agregarCliente(@RequestBody Cliente cliente) {
        try {
            Cliente nuevoCliente = clienteRepositorio.save(cliente);
            return ResponseEntity.ok("Cliente agregado exitosamente con ID: " + nuevoCliente.getId());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al agregar el cliente: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> obtenerTodosLosClientes() {
        return ResponseEntity.ok(clienteRepositorio.findAll());
    }
}

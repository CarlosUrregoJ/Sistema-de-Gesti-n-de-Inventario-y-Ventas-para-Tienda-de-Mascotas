package co.ucentral.ventaproductosmascotas.controladores;

import co.ucentral.ventaproductosmascotas.persistencia.entidades.datoscliente;
import co.ucentral.ventaproductosmascotas.persistencia.repositorio.datosclienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/datoscliente") // Ruta base para este controlador
public class datosclienteControlador {

    @Autowired
    private datosclienteRepositorio datosclienteRepositorio;

    // Endpoint para guardar o actualizar cliente en la base de datos
    @PostMapping("/guardar")
    public ResponseEntity<datoscliente> guardarCliente(@RequestBody datoscliente cliente) {
        // Buscar si el cliente ya existe por identificacionCliente
        Optional<datoscliente> clienteExistente = datosclienteRepositorio.findByIdentificacionCliente(cliente.getIdentificacionCliente());

        if (clienteExistente.isPresent()) {
            // Si el cliente existe, obtenerlo y sumar 1 a la cantidadCompras
            datoscliente clienteGuardado = clienteExistente.get();
            clienteGuardado.setCantidadCompras(clienteGuardado.getCantidadCompras() + 1);
            datosclienteRepositorio.save(clienteGuardado); // Guardar los cambios
            return ResponseEntity.ok(clienteGuardado); // Retornar el cliente actualizado
        } else {
            // Si el cliente no existe, establecer cantidadCompras a 1 y guardar
            cliente.setCantidadCompras(1);
            datoscliente clienteGuardado = datosclienteRepositorio.save(cliente); // Guardar nuevo cliente
            return ResponseEntity.ok(clienteGuardado); // Retornar el cliente guardado
        }
    }

    @GetMapping("/todosclientesymas")
    public ResponseEntity<?> obtenerTodosLosClientesYMascotas() {
        try {
            List<datoscliente> todosLosClientes = datosclienteRepositorio.findAll(); // Obtiene todos los clientes con sus mascotas
            if (todosLosClientes.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay clientes registrados.");
            }
            return ResponseEntity.ok(todosLosClientes); // Verifica si se retorna una lista válida
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener los clientes: " + e.getMessage());
        }
    }
}

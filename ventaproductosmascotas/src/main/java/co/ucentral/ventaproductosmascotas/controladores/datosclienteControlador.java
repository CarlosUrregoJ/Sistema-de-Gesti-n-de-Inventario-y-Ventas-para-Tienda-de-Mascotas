package co.ucentral.ventaproductosmascotas.controladores;

import co.ucentral.ventaproductosmascotas.persistencia.entidades.datoscliente;
import co.ucentral.ventaproductosmascotas.persistencia.repositorio.datosclienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/datoscliente") // Ruta base para este controlador
public class datosclienteControlador {

    @Autowired
    private datosclienteRepositorio datosclienteRepositorio;

    // Endpoint para guardar un cliente en la base de datos
    @PostMapping("/guardar")
    public ResponseEntity<datoscliente> guardarCliente(@RequestBody datoscliente cliente) {
        datoscliente clienteGuardado = datosclienteRepositorio.save(cliente); // Guardar cliente en la tabla
        return ResponseEntity.ok(clienteGuardado); // Retornar el cliente guardado
    }
}

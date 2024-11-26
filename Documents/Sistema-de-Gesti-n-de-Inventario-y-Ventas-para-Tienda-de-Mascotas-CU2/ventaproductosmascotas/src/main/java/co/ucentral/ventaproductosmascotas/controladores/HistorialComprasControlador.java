package co.ucentral.ventaproductosmascotas.controladores;

import co.ucentral.ventaproductosmascotas.persistencia.entidades.Compra;
import co.ucentral.ventaproductosmascotas.persistencia.repositorio.HistorialComprasRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compras")
public class HistorialComprasControlador {

    @Autowired
    private HistorialComprasRepositorio comprasRepositorio;

    @GetMapping
    public ResponseEntity<List<Compra>> obtenerHistorialCompras() {
        List<Compra> compras = comprasRepositorio.findAll();
        return ResponseEntity.ok(compras);
    }
}

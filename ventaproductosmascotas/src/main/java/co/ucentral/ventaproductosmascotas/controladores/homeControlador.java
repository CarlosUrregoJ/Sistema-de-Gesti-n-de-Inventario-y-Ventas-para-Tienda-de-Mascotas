package co.ucentral.ventaproductosmascotas.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homeControlador {
    @GetMapping("/")
    public String home() {
        return "index"; // El archivo "index.html" en /templates/
    }
}
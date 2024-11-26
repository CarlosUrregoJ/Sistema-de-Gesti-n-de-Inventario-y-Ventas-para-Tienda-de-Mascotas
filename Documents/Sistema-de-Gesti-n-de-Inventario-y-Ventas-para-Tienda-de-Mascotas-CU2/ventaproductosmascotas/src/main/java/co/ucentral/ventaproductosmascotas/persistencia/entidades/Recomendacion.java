package co.ucentral.ventaproductosmascotas.persistencia.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Recomendacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long clienteId;
    private String productoRecomendado;

    // Constructor vacío
    public Recomendacion() {}

    // Constructor con parámetros
    public Recomendacion(Long clienteId, String productoRecomendado) {
        this.clienteId = clienteId;
        this.productoRecomendado = productoRecomendado;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getProductoRecomendado() {
        return productoRecomendado;
    }

    public void setProductoRecomendado(String productoRecomendado) {
        this.productoRecomendado = productoRecomendado;
    }
}

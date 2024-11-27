package co.ucentral.ventaproductosmascotas.persistencia.entidades;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ventas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Elimina la propiedad productoId
    private String nombre; // Nombre del producto vendido
    private Integer cantidad; // Cantidad vendida

    private LocalDateTime fechaHoraVenta; // Fecha y hora de la venta

    @Transient // Esto indica que este campo no se mapeará en la base de datos
    private Long productoId; // ID del producto vendido

    // Constructor vacío requerido por JPA
    public ventas() {}

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDateTime getFechaHoraVenta() {
        return fechaHoraVenta;
    }

    public void setFechaHoraVenta(LocalDateTime fechaHoraVenta) {
        this.fechaHoraVenta = fechaHoraVenta;
    }

    // Este getter ahora puede seguir siendo utilizado
    public Long getProductoId() {
        return productoId;
    }

    // Puedes agregar un setter si necesitas modificar el productoId
    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }
}

package co.ucentral.ventaproductosmascotas.persistencia.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
//Crear tala datoscliente de forma automatica gracias a Entity
public class datoscliente {

    @Id  // Marca el campo 'id' como la clave primaria de la tabla.
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Configura la generación automática del valor del 'id'.
    private Long id; // Se requiere un campo de clave primaria.

    private String nombreCliente;       // Nombre del cliente
    private String identificacionCliente; // Identificación del cliente
    private String nombreMascota;      // Nombre de la mascota
    private String razaMascota;        // Raza de la mascota
    private Integer cantidadCompras;

    // Constructor vacío requerido por JPA
    public datoscliente() {}

    // Getters y Setters
    // Getter y Setter para 'cantidadCompras'
    public Integer getCantidadCompras() {
        return cantidadCompras;
    }

    public void setCantidadCompras(Integer cantidadCompras) {
        this.cantidadCompras = cantidadCompras;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getIdentificacionCliente() {
        return identificacionCliente;
    }

    public void setIdentificacionCliente(String identificacionCliente) {
        this.identificacionCliente = identificacionCliente;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public String getRazaMascota() {
        return razaMascota;
    }

    public void setRazaMascota(String razaMascota) {
        this.razaMascota = razaMascota;
    }
}
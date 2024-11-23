package co.ucentral.ventaproductosmascotas.persistencia.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity  // Indica que esta clase es una entidad JPA que se mapeará a una tabla en la base de datos.
public class admins {
    @Id  // Marca el campo 'id' como la clave primaria de la tabla.
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Configura la generación automática del valor del 'id'.
    private Long id;
    private String username;
    private String password; // En un futuro deberías almacenar un hash en lugar del texto plano

    // Constructor vacío requerido por JPA
    public admins() {}

    // Constructor con parámetros
    public admins(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

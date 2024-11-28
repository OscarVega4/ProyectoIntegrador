package proyecto_integrador.Entidad;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "clientes")  
public class Clientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long clienteID;

    @Column(name = "nombreCompleto", nullable = false)  
    private String nombreCompleto;

    @Column(name = "correo", nullable = false, unique = true) 
    private String correo;

    @Column(name = "contraseña", nullable = false) 
    private String contraseña;

    // Getters y Setters
    public Long getClienteID() {
        return clienteID;
    }

    public void setClienteID(Long clienteID) {
        this.clienteID = clienteID;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}


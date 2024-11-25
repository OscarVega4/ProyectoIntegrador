package proyecto_integrador.Entidad;  

import javax.persistence.Entity;  
import javax.persistence.GeneratedValue;  
import javax.persistence.GenerationType;  
import javax.persistence.Id; // Cambiado AdminId por Id  
import javax.persistence.Table;  
import javax.persistence.Column;  

@Entity  
@Table(name = "administradores")  
public class Administradores { // Cambiado 'Administradores' a 'Administrador'  
    
    @Id // Cambiado AdminId por @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private Long adminid;  

    @Column(name = "nombreCompleto")  
    private String nombreCompleto;  

    @Column(name = "contraseña")  
    private int contraseña;  

    // Getters y Setters  
    public Long getAdminId() { // Cambiado a getAdminId  
        return adminid;  
    }  

    public void setAdminId(Long adminid) { // Cambiado a setAdminId  
        this.adminid = adminid;  
    }  

    public String getNombreCompleto() {  
        return nombreCompleto;  
    }  

    public void setNombreCompleto(String nombreCompleto) {  
        this.nombreCompleto = nombreCompleto;  
    }  

    public int getContraseña() { // Cambiado a getContraseña  
        return contraseña;  
    }  

    public void setContraseña(int contraseña) { // Cambiado a setContraseña  
        this.contraseña = contraseña;  
    }  
}
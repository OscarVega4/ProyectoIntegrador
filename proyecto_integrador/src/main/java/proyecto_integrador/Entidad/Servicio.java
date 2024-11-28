package proyecto_integrador.Entidad;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;

@Entity
@Table(name = "Servicios")
public class Servicio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ServiciosId;

    @Column(name = "nombreServicio")
    private String nombreServicio;

    @Column(name = "descripcion")
    private String Descripcion;


    // Getters y Setters
    public Long getServiciosId() {
        return ServiciosId;
    }

    public void setId(Long ServiciosId) {
        this.ServiciosId = getServiciosId();
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }
    
}




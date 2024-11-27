package proyecto_integrador.Entidad;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;

@Entity
@Table(name = "Productos")
public class Productos {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ProductosId;

    @Column(name = "nombreProducto")
    private String nombreProducto;

    @Column(name = "descripcion")
    private String Descripcion;

    @Column(name = "cantidad")
    private int cantidad;

    // Getters y Setters
    public Long ProductosId() {
        return ProductosId;
    }

    public void setId(Long ProductosId) {
        this.ProductosId = ProductosId();
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
}
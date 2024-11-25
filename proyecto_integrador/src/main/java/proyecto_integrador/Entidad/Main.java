package proyecto_integrador;

package proyecto_integrador.Entidad;
import com.example.Entidad.Administradores;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidadPersistencia");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Administradores administradores = new Administradores();
        administradores.setNombreCompleto("Juan");
        
        em.persist(administradores);

        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}
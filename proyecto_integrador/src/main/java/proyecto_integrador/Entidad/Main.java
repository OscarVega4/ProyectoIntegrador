package proyecto_integrador.Entidad;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidadPersistencia");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();

        crearProducto("Panel", 30);

        Productos producto = leerProducto(5L);
        if (producto != null) {
            System.out.println(
                    "Producto encontrado: " + producto.getNombreProducto() + ", Cantidad: " + producto.getCantidad());
        }

        actualizarProducto(5L, "Panel Solar", 35);

        eliminarProducto(5L);

        em.close();
        emf.close();
    }

    public static void crearProducto(String nombre, int cantidad) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Productos producto = new Productos();
        producto.setNombreProducto(nombre);
        producto.setCantidad(cantidad);

        em.persist(producto);

        em.getTransaction().commit();
        em.close();
    }

    public static Productos leerProducto(Long id) {
        EntityManager em = emf.createEntityManager();
        Productos producto = em.find(Productos.class, id);
        em.close();
        return producto;
    }

    public static void actualizarProducto(Long id, String nuevoNombre, int nuevaCantidad) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Productos producto = em.find(Productos.class, id);
        if (producto != null) {
            producto.setNombreProducto(nuevoNombre);
            producto.setCantidad(nuevaCantidad);
            em.merge(producto);
        }

        em.getTransaction().commit();
        em.close();
    }

    public static void eliminarProducto(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Productos producto = em.find(Productos.class, id);
        if (producto != null) {
            em.remove(producto);
        }

        em.getTransaction().commit();
        em.close();
    }
}
package proyecto_integrador.Entidad;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

public class ServiciosMain {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidadPersistencia");

    public static void main(String[] args) {
        EntityManager em = null;
        
        try {
            em = emf.createEntityManager();

            
            crearServicio("Mantenimiento de Paneles", "Servicio de mantenimiento preventivo para paneles solares");

            Servicio servicio = leerServicio(1L); // Asumimos que el ID 1 existe
            if (servicio != null) {
                System.out.println(
                        "Servicio encontrado: " + servicio.getNombreServicio() + ", Descripción: " + servicio.getDescripcion());
            }

            actualizarServicio(1L, "Mantenimiento y Reparación de Paneles", "Servicio de mantenimiento y reparación de paneles solares");

            eliminarServicio(1L); // Eliminar el servicio con ID 1

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
            emf.close();
        }
    }

    
    public static void crearServicio(String nombre, String descripcion) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            Servicio servicio = new Servicio();
            servicio.setNombreServicio(nombre);
            servicio.setDescripcion(descripcion);

            em.persist(servicio);

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Error al crear el servicio: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public static Servicio leerServicio(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Servicio.class, id);
        } catch (Exception e) {
            System.out.println("Error al leer el servicio: " + e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }

    public static void actualizarServicio(Long id, String nuevoNombre, String nuevaDescripcion) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            Servicio servicio = em.find(Servicio.class, id);
            if (servicio != null) {
                servicio.setNombreServicio(nuevoNombre);
                servicio.setDescripcion(nuevaDescripcion);
                em.merge(servicio);
                em.getTransaction().commit();
            } else {
                System.out.println("Servicio no encontrado con ID: " + id);
            }
        } catch (RollbackException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Error al actualizar el servicio: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public static void eliminarServicio(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            Servicio servicio = em.find(Servicio.class, id);
            if (servicio != null) {
                em.remove(servicio);
                em.getTransaction().commit();
            } else {
                System.out.println("Servicio no encontrado con ID: " + id);
            }
        } catch (RollbackException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Error al eliminar el servicio: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}

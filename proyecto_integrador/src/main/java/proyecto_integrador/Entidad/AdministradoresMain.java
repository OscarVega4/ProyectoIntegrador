package proyecto_integrador.Entidad;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

public class AdministradoresMain {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidadPersistencia");

    public static void main(String[] args) {
        EntityManager em = null;
        
        try {
            em = emf.createEntityManager();

        
            crearAdministrador("Oscar Vega", 456789);

            Administradores admin = leerAdministrador(1L);
            if (admin != null) {
                System.out.println(
                        "Administrador encontrado: " + admin.getNombreCompleto() + ", Contraseña: " + admin.getContraseña());
            }

            actualizarAdministrador(1L, "Oscar Acevedo", 654321);

            eliminarAdministrador(1L); 

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

    
    public static void crearAdministrador(String nombre, int contraseña) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            Administradores admin = new Administradores();
            admin.setNombreCompleto(nombre);
            admin.setContraseña(contraseña);

            em.persist(admin);

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Error al crear el administrador: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public static Administradores leerAdministrador(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Administradores.class, id);
        } catch (Exception e) {
            System.out.println("Error al leer el administrador: " + e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }

    public static void actualizarAdministrador(Long id, String nuevoNombre, int nuevaContraseña) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            Administradores admin = em.find(Administradores.class, id);
            if (admin != null) {
                admin.setNombreCompleto(nuevoNombre);
                admin.setContraseña(nuevaContraseña);
                em.merge(admin);
                em.getTransaction().commit();
            } else {
                System.out.println("Administrador no encontrado con ID: " + id);
            }
        } catch (RollbackException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Error al actualizar el administrador: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public static void eliminarAdministrador(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            Administradores admin = em.find(Administradores.class, id);
            if (admin != null) {
                em.remove(admin);
                em.getTransaction().commit();
            } else {
                System.out.println("Administrador no encontrado con ID: " + id);
            }
        } catch (RollbackException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Error al eliminar el administrador: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}


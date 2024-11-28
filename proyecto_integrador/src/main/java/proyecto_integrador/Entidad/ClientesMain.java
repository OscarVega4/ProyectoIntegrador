package proyecto_integrador.Entidad;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

public class ClientesMain {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidadPersistencia");

    public static void main(String[] args) {
        EntityManager em = null;

        try {
            em = emf.createEntityManager();

            
            crearCliente("Oscar Vega", "oscar.vega@cesde.com", "12345678");

            
            Clientes cliente = leerCliente(1L);
            if (cliente != null) {
                System.out.println(
                        "Cliente encontrado: " + cliente.getNombreCompleto() + ", Correo: " + cliente.getCorreo());
            }

            actualizarCliente(1L, "Oscar Acevedo", "Oscar.Acevedo@cesde.com", "987654");

            eliminarCliente(1L);

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

    public static void crearCliente(String nombre, String correo, String contraseña) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            Clientes cliente = new Clientes();
            cliente.setNombreCompleto(nombre);
            cliente.setCorreo(correo);
            cliente.setContraseña(contraseña);

            em.persist(cliente);  

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Error al crear el cliente: " + e.getMessage());
        } finally {
            em.close();
        }
    }


    public static Clientes leerCliente(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Clientes.class, id); 
        } catch (Exception e) {
            System.out.println("Error al leer el cliente: " + e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }

  
    public static void actualizarCliente(Long id, String nuevoNombre, String nuevoCorreo, String nuevaContraseña) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            Clientes cliente = em.find(Clientes.class, id); 
            if (cliente != null) {
                cliente.setNombreCompleto(nuevoNombre);
                cliente.setCorreo(nuevoCorreo);
                cliente.setContraseña(nuevaContraseña);
                em.merge(cliente);  
                em.getTransaction().commit();
            } else {
                System.out.println("Cliente no encontrado con ID: " + id);
            }
        } catch (RollbackException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Error al actualizar el cliente: " + e.getMessage());
        } finally {
            em.close();
        }
    }

  
    public static void eliminarCliente(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            Clientes cliente = em.find(Clientes.class, id);  
            if (cliente != null) {
                em.remove(cliente); 
                em.getTransaction().commit();
            } else {
                System.out.println("Cliente no encontrado con ID: " + id);
            }
        } catch (RollbackException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Error al eliminar el cliente: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}



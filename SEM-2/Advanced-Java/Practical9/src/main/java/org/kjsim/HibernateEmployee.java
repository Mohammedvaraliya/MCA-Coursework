package org.kjsim;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.util.List;
import java.util.Collections;

@WebListener
public class HibernateEmployee implements ServletContextListener {

    private static SessionFactory factory;

    // Initialize SessionFactory when class is loaded
    static {
        try {
            // Create the ServiceRegistry from hibernate-cfg.xml
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .configure("hibernate-cfg.xml")
                    .build();

            // Create MetadataSources
            MetadataSources sources = new MetadataSources(registry)
                    .addAnnotatedClass(Employee.class);

            // Create SessionFactory
            factory = sources.buildMetadata().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create SessionFactory: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        shutdown();
    }

    public void createEmployee(Employee employee) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.persist(employee);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error creating employee: " + e.getMessage());
            throw e;
        }
    }

    public void updateEmployee(long employeeId, String name, String email,
                               String hireDate, String department) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Employee employee = session.get(Employee.class, employeeId);

            if (employee != null) {
                employee.setName(name);
                employee.setEmail(email);
                employee.setHireDate(hireDate);
                employee.setDepartment(department);
                session.merge(employee);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error updating employee: " + e.getMessage());
            throw e;
        }
    }

    public Employee getEmployeeById(long employeeId) {
        try (Session session = factory.openSession()) {
            return session.get(Employee.class, employeeId);
        } catch (Exception e) {
            System.err.println("Error fetching employee: " + e.getMessage());
            throw e;
        }
    }

    public List<Employee> getAllEmployees() {
        try (Session session = factory.openSession()) {
            Query<Employee> query = session.createQuery("FROM Employee", Employee.class);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error fetching employees: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public void deleteEmployee(long employeeId) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Employee employee = session.get(Employee.class, employeeId);
            if (employee != null) {
                session.remove(employee);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error deleting employee: " + e.getMessage());
            throw e;
        }
    }

    public static void shutdown() {
        if (factory != null && !factory.isClosed()) {
            factory.close();
        }
    }
}
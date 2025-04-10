package org.kjsim.blogs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.util.Collections;
import java.util.List;

@WebListener
public class HibernateBlogPost implements ServletContextListener {

    private static SessionFactory factory;

    static {
        try {
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .configure("hibernate-cfg.xml")
                    .build();

            factory = new MetadataSources(registry)
                    .addAnnotatedClass(BlogPost.class)
                    .addAnnotatedClass(Comment.class)
                    .buildMetadata()
                    .buildSessionFactory();

        } catch (Throwable ex) {
            System.err.println("Failed to create SessionFactory: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        shutdown();
    }

    public void createBlogPost(BlogPost post) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.persist(post);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error creating blog post: " + e.getMessage());
        }
    }

    public BlogPost getBlogPostById(long id) {
        try (Session session = factory.openSession()) {
            return session.get(BlogPost.class, id);
        } catch (Exception e) {
            System.err.println("Error fetching blog post: " + e.getMessage());
            return null;
        }
    }

    public List<BlogPost> getAllBlogPosts() {
        try (Session session = factory.openSession()) {
            Query<BlogPost> query = session.createQuery("FROM BlogPost", BlogPost.class);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error fetching blog posts: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public void updateBlogPost(BlogPost updatedPost) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.merge(updatedPost);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error updating blog post: " + e.getMessage());
        }
    }

    public void deleteBlogPost(long id) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            BlogPost post = session.get(BlogPost.class, id);
            if (post != null) {
                session.remove(post);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error deleting blog post: " + e.getMessage());
        }
    }

    public static void shutdown() {
        if (factory != null && !factory.isClosed()) {
            factory.close();
        }
    }
}

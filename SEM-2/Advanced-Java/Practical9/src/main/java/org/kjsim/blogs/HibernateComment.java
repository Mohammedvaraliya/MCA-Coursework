package org.kjsim.blogs;

import jakarta.servlet.ServletContextEvent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class HibernateComment {

    private static final SessionFactory factory;

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

    public void addCommentToPost(long postId, Comment comment) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            BlogPost post = session.get(BlogPost.class, postId);
            if (post != null) {
                post.addComment(comment); // This sets both sides of the relation
                session.merge(post);      // Persist changes
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error adding comment: " + e.getMessage());
        }
    }

    public List<Comment> getCommentsForPost(long postId) {
        try (Session session = factory.openSession()) {
            BlogPost post = session.get(BlogPost.class, postId);
            return post != null ? post.getComments() : null;
        } catch (Exception e) {
            System.err.println("Error fetching comments: " + e.getMessage());
            return null;
        }
    }

    public void deleteComment(long commentId) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Comment comment = session.get(Comment.class, commentId);
            if (comment != null) {
                session.remove(comment);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error deleting comment: " + e.getMessage());
        }
    }

    public static void shutdown() {
        if (factory != null && !factory.isClosed()) {
            factory.close();
        }
    }
}

package edu.mum.cs.cs544.exercises;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class AppBook {

    private static final SessionFactory sessionFactory;
    private static final ServiceRegistry serviceRegistry;

    static {
        Configuration configuration = new Configuration();
        configuration.configure();
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    public static void main(String[] args) {
        // Hibernate placeholders
        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // Create new instance of Book and set values in it
            Book book1 = new Book("For the Roses", "Julie Garwood", "9780671046118", 8.99);
            // save the book
            session.persist(book1);
//            // Create new instance of Book and set values in it
            Book book2 = new Book("The Other Side of Midnight", "Sidney Sheldon","9785702008783", 10.99);
            // save the book
            session.persist(book2);
            	// Create new instance of Book and set values in it
            Book book3 = new Book("The Last Juror", "John Grisham", "9880440246022", 9.99);
           // save the book 
           session.persist(book3);

            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) {
                System.err.println("Rolling back: " + e.getMessage());
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        
        
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
           

            // retrieve all books after first time insertion to DB
            @SuppressWarnings("unchecked")
            List<Book> bookList = session.createQuery("from Book").list();
            for (Book book : bookList) {
                System.out.println("Title: " + book.getTitle() + ", Author: "
                        +book.getAuthor() +", ISBN: " +book.getISBN()  +", price: " + book.getPrice());
            }
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) {
                System.err.println("Rolling back: " + e.getMessage());
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        
        
        
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Book book3 = (Book) session.get(Book.class, 3L);
            book3.setAuthor("Danielle Steel");
//            book3.setISBN("9782266150187");
            book3.setTitle("The Gift");
            book3.setPrice(12.99);
            
            session.flush();
            
            Book book1 = (Book) session.get(Book.class, 1L);
            
            session.delete(book1);
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) {
                System.err.println("Rolling back: " + e.getMessage());
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        
        

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
           

            // retrieve all books after updating book3 and deleting book 1
            @SuppressWarnings("unchecked")
            List<Book> bookList = session.createQuery("from Book").list();
            for (Book book : bookList) {
                System.out.println("Title: " + book.getTitle() + ", Author: "
                        +book.getAuthor() +", ISBN: " +book.getISBN()  +", price: " + book.getPrice());
            }
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) {
                System.err.println("Rolling back: " + e.getMessage());
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }

        // Close the SessionFactory (not mandatory)
        sessionFactory.close();
        System.exit(0);
    }
}

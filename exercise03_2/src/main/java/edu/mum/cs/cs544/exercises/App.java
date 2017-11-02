package edu.mum.cs.cs544.exercises;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class App {

    private static final SessionFactory sessionFactory;
    private static final ServiceRegistry serviceRegistry;

    static {
        Configuration configuration = new Configuration();
        configuration.configure();
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    public static void main(String[] args) throws ParseException {
        // Hibernate placeholders
        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            
           Department marketing = new Department("Marketing");
            session.persist(marketing);
            
           Department sales = new Department("Sales");
            session.persist(sales);

            // Create new instance of Employee and set values in it
            Employee employee1 = new Employee("Bereket", "D101");
           
            // save the employee
            session.persist(employee1);
         // Create new instance of Employee and set values in it
            Employee employee2 = new Employee("Boby", "M210");
            // save the employee
            session.persist(employee2);
            
         // Create new instance of Employee and set values in it
            Employee employee3 = new Employee("Fredy", "T300");
           
            // save the employee
            session.persist(employee3);
         // Create new instance of Employee and set values in it
            Employee employee4 = new Employee("Alex", "U299");
            // save the employee
            session.persist(employee4);
            
            marketing.addEmployee(employee4);
            marketing.addEmployee(employee3);
            sales.addEmployee(employee1);
            sales.addEmployee(employee2);
            
            // Book-Publisher
            
            Publisher publisher1 = new Publisher("Springer");
            session.persist(publisher1);
            Publisher publisher2 = new Publisher("Manning");
            session.persist(publisher2);
            
            Book book1 = new Book("The last juror", "John Grisham");
            book1.setPublisher(publisher2);
            Book book2 = new Book("The gift", "Danielle Steel");
            book2.setPublisher(publisher2);
            Book book3 = new Book("Memories of midnight", "Sidney Sheldon");
            book3.setPublisher(publisher1);
            Book book4 = new Book("The Bourne Identity", "Rebert Lodlum");
            book4.setPublisher(publisher1);
            
            session.persist(book1);
            session.persist(book2);
            session.persist(book3);
            session.persist(book4);
            
            // Student- Course
            
            Student bereket = new Student("Bereket", "Haile");
            Student mana = new Student("Mana", "Mebrahtu");
            Student fre = new Student("Fre", "Mariam");
            Student yosias = new Student("Yosias", "Nebiy");
            
            
            session.persist(bereket);
            session.persist(mana);
            session.persist(fre);
            session.persist(yosias);
            
            Course ea = new Course("CS544", "Enterprise Architecture");
            Course mpp = new Course("CS401", "Model Programming Practices");
            Course wap= new Course("CS472", "Web Appricaltion programming");
            Course waa= new Course("CS545", " Web Appricaltion Architecture");
            
//            This is when the navigation is from courses to student table.
//            ea.addStudent(bereket);
//            ea.addStudent(yosias);
//            ea.addStudent(fre);
//            ea.addStudent(mana);
//            waa.addStudent(fre);
//            wap.addStudent(yosias);
//            mpp.addStudent(mana);
            
            bereket.addCourse(mpp);
            bereket.addCourse(waa);
            bereket.addCourse(wap);
            bereket.addCourse(ea);
            
            fre.addCourse(mpp);
            fre.addCourse(waa);
            fre.addCourse(wap);
            fre.addCourse(ea);
            
            mana.addCourse(mpp);
            mana.addCourse(waa);
            mana.addCourse(wap);
            mana.addCourse(ea);
            
            yosias.addCourse(mpp);
            yosias.addCourse(waa);
            yosias.addCourse(wap);
            yosias.addCourse(ea);
            
            session.persist(ea);
            session.persist(waa);
            session.persist(mpp);
            
            // Customer- Reservation
            
            Customer customer1 = new Customer("Bereket Haile");
            Customer customer2 = new Customer("Tesfay Merhatsion");
            
            session.persist(customer1);
            session.persist(customer2);
            session.persist(fre);
            session.persist(yosias);
            
            DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
            Date d1 = df.parse("12-10-2017"); 
            Date d2 = df.parse("01-01-2018");
            Date d3 = df.parse("10-10-2018"); 
            Date d4 = df.parse("11-15-2018"); 
            
            Reservation reservation1 = new Reservation(d1);
            Reservation reservation2 = new Reservation(d2);
            Reservation reservation3 = new Reservation(d3);
            Reservation reservation4 = new Reservation(d4);
            
            session.persist(reservation1);
            session.persist(reservation2);
            session.persist(reservation3);
            session.persist(reservation4);
            
            customer1.addReservations(reservation4);
            customer1.addReservations(reservation3);
            customer1.addReservations(reservation2);
            customer2.addReservations(reservation1);
//            customer2.addReservations(reservation3);
            
            //Customer-Reservation-Book
            
            reservation1.setBook(book4);
            reservation2.setBook(book4);
            reservation3.setBook(book4);
            reservation4.setBook(book4);
            
            //Employee Office
            
            Office office1 = new Office("Greenedge", "102-A");
            Office office2 = new Office("Fowler park", "290");
            
            session.persist(office1);
            session.persist(office2);
            
            employee1.setOffice(office1);
            employee2.setOffice(office2);
            employee3.setOffice(office1);
            employee4.setOffice(office2);
            
            
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

            // retrieve all Departments
            @SuppressWarnings("unchecked")
            List<Department> departmentList = session.createQuery("from Department").list();
            for (Department department : departmentList) {
                System.out.println(department);
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

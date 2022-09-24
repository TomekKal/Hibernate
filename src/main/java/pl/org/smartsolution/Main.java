package pl.org.smartsolution;

import jakarta.persistence.NoResultException;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.spi.SessionFactoryDelegatingImpl;
import org.hibernate.query.Query;

import java.util.Optional;

public class Main {

    public static SessionFactory sessionFactory;
    //sessionFactory - zwraca pojedyncze połączenie

    public static void main(String[] args) {

        sessionFactory = new Configuration().configure().buildSessionFactory();

        User user = new User();
        //user.setId(1);
        user.setName("Mateusz");
        user.setSurename("Adamek");
        user.setSex(User.Sex.MEN);

        //persistUser(user);
        //updateUser(user);

        Optional<User> userBox = getUserById(2);
        if(userBox.isPresent()){
            System.out.println(userBox.get());
        }else {
            System.out.println("Takiego usera nie ma!");
        }
    }

    public static void persistUser(User user){

        Session session = Main.sessionFactory.openSession();
        // mam sesję, chce stworzyć transakcje
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.persist(user);
            tx.commit();

        } catch (Exception e) {
            if(tx !=null)
            tx.rollback();
        }finally {
            session.close();
        }
    }


    public static void updateUser(User user){

        Session session = Main.sessionFactory.openSession();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            session.update(user);
            tx.commit();
        } catch (Exception e) {
            if(tx !=null)
                tx.rollback();
        }finally {
            session.close();
        }

    }


    public static Optional<User> getUserById(int id){

        Session session = Main.sessionFactory.openSession();
        Query<User> query = session.createQuery("FROM pl.org.smartsolution.User WHERE id = :param_id");
        query.setParameter("param_id", id);
        // może się pojawić wyjątek noResultExeption



        try {
            User user = query.getSingleResult();
            session.close();
            return Optional.of(user);
        }catch (NoResultException e){
            session.close();
            return Optional.empty();
        }
    }



}

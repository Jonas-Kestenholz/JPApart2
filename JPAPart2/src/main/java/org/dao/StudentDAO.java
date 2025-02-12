package org.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.config.HibernateConfig;
import org.entities.Course;

import java.util.List;

public class StudentDAO implements IDAO<Course,Long>{
    private static final EntityManagerFactory emf= HibernateConfig.getEntityManagerFactory();
    private static StudentDAO instance;

    private StudentDAO(){
    }
    public static StudentDAO getInstance(){
        if(instance==null){
            instance=new StudentDAO();
        }
        return instance;
    }


    @Override
    public Course find(Long aLong) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Course course = em.find(Course.class, aLong);
        em.getTransaction().commit();
        return course;
    }

    @Override
    public List<Course> findAll() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Course> courses = em.createQuery("from Course").getResultList();
        em.getTransaction().commit();
        return courses;
    }

    @Override
    public Course save(Course course) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(course);
        em.getTransaction().commit();
        return course;
    }

    @Override
    public void delete(Long aLong) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Course course = em.find(Course.class, aLong);
        em.remove(course);
        em.getTransaction().commit();


    }

    @Override
    public Course update(Course course) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(course);
        em.getTransaction().commit();
        return course;

    }
}

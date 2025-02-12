package org.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.config.HibernateConfig;
import org.entities.Course;

import java.util.List;

public class TeacherDAO implements IDAO<Course,Long> {


    private static final EntityManagerFactory emf= HibernateConfig.getEntityManagerFactory();
    private static TeacherDAO instance;

    private TeacherDAO(){
    }
    public static TeacherDAO getInstance(){
        if(instance==null){
            instance=new TeacherDAO();
        }
        return instance;
    }

    @Override
    public Course find(Long aLong) {
        EntityManager em = emf.createEntityManager();
        Course course = em.find(Course.class, aLong);
        em.close();
        return course;

    }

    @Override
    public List<Course> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Course> courses = em.createQuery("from Course").getResultList();
        em.close();
        return courses;
    }

    @Override
    public Course save(Course course){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(course);
        em.getTransaction().commit();
        em.close();
        return course;

    }

    @Override
    public void delete(Long aLong) {
        EntityManager em = emf.createEntityManager();
        Course course = em.find(Course.class, aLong);
        em.getTransaction().begin();
        em.remove(course);
        em.getTransaction().commit();
        em.close();

    }

    @Override
    public Course update(Course course) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(course);
        em.getTransaction().commit();
        em.close();
        return course;
    }
}

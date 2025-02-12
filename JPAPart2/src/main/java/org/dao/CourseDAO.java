package org.dao;

import jakarta.persistence.EntityManager;
import org.ApiException.ApiException;
import org.config.HibernateConfig;
import org.entities.Student;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class CourseDAO implements IDAO<Student,Long>{

    private static final EntityManagerFactory emf= HibernateConfig.getEntityManagerFactory();
    private static CourseDAO instance;

    private CourseDAO(){
    }
public static CourseDAO getInstance(){
        if(instance==null){
            instance=new CourseDAO();
        }
        return instance;
}

    @Override
    public Student find(Long ID) {
        EntityManager em = emf.createEntityManager();
        return em.find(Student.class, ID);

    }

    @Override
    public List<Student> findAll() {
        try(EntityManager em = emf.createEntityManager()){;
        return em.createQuery("from Student", Student.class).getResultList();
    }catch(Exception e){
        throw new ApiException(400,"Cant find all students");
        }
    }

    @Override
    public Student save(Student student) {
        try(EntityManager em = emf.createEntityManager()){;
        em.getTransaction().begin();
        em.persist(student);
        em.getTransaction().commit();
        return student;
    }catch(Exception e){
        throw new ApiException(400,"STUDENT SAVE FAILED");
        }
    }

    @Override
    public void delete(Long ID) {
        try(EntityManager em = emf.createEntityManager()){;
        em.getTransaction().begin();
        Student student = em.find(Student.class, ID);
        em.remove(student);
        em.getTransaction().commit();

    }catch(Exception ex){
        throw new ApiException(400,"STUDENT DELETE FAILED");
        }
    }

    @Override
    public Student update(Student student) {
        try(EntityManager em = emf.createEntityManager()){;
        em.getTransaction().begin();
        Student student1 = em.find(Student.class, student.getId());
        em.merge(student1);
        em.getTransaction().commit();
        return student;
    }catch (Exception e){
        throw new ApiException(400,"STUDENT UPDATE FAILED");
        }
    }
}

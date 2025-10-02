package vn.codegym.bt_qlsinhvien2_thymleaf.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import vn.codegym.bt_qlsinhvien2_thymleaf.model.Student;

import java.util.List;

@Service
@Primary
public class HibernateStudentService implements StudentService {
    private static SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .buildSessionFactory();
        } catch (HibernateException e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("Khởi tạo SessionFactory thất bại: " + e);
        }
    }
    @Override
    public List<Student> findAll(String q, String sort, String dir, int page, int size) {
        try (var session = sessionFactory.openSession()) {
            return session.createQuery("FROM Student", Student.class).list();
        }
    }

    @Override
    public Student findById(String id) {
        try (var session = sessionFactory.openSession()) {
            return session.find(Student.class, id);
        }
    }

    @Override
    public void save(Student student) {
        try (var session = sessionFactory.openSession()) {
            var tx = session.beginTransaction();
            session.persist(student);
            tx.commit();
        }
    }

    @Override
    public void update(Student student) {
        try (var session = sessionFactory.openSession()) {
            var tx = session.beginTransaction();
            session.merge(student);
            tx.commit();
        }
    }

    @Override
    public void delete(String id) {
        try (var session = sessionFactory.openSession()) {
            var tx = session.beginTransaction();
            Student student = session.find(Student.class, id);
            if (student != null) session.remove(student);
            tx.commit();
        }
    }
}


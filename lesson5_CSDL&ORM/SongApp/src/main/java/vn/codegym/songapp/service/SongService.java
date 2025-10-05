package vn.codegym.songapp.service;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;
import vn.codegym.songapp.models.Song;

import java.util.List;

@Service // để string tạo bean(Object) trong Ioc contener
public class SongService implements ISongService {
    private static final EntityManager entityManager;
    private static final SessionFactory sessionFactory;
    //khởi tạo --> 1 lần duy nhất SongService
    static {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        entityManager = sessionFactory.createEntityManager();
    }

    @Override
    public List<Song> findAll() {
        String query = "select s from Song s ORDER BY s.id ASC";
        return entityManager.createQuery(query, Song.class).getResultList();
    }


    public Song create(Song song) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            session.persist(song); // chỉ cần persist trực tiếp
            tx.commit();
            entityManager.clear();

            return song; // Hibernate sẽ tự gán ID sau khi commit
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new RuntimeException("Lỗi khi lưu bài hát: " + e.getMessage(), e);
        }
    }

    @Override
    public Song getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(Song.class, id);
        }
    }

    @Override
    public void update(Song song) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            // merge = cập nhật entity nếu có trong DB, hoặc thêm mới nếu chưa có
            session.merge(song);

            tx.commit();
            entityManager.clear();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new RuntimeException("Lỗi khi cập nhật bài hát: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(Long id) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            Song song = session.find(Song.class, id);
            if (song != null) {
                session.remove(song);
            } else {
                throw new RuntimeException("Không tìm thấy bài hát có id = " + id);
            }

            tx.commit();
            entityManager.clear();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new RuntimeException("Lỗi khi xóa bài hát: " + e.getMessage(), e);
        }
    }
}

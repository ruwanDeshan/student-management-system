package lk.developpersstack.lms.dao.custom.impl;

import lk.developpersstack.lms.dao.custom.StudentDao;
import lk.developpersstack.lms.entity.Student;
import lk.developpersstack.lms.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    @Override
    public void save(Student student) throws SQLException, ClassNotFoundException {
        try(Session session= HibernateUtil.getInstance().getSession()){
            session.beginTransaction();
            session.save(student);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Student student) throws SQLException, ClassNotFoundException {

    }

    @Override
    public Student find(Long aLong) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public void delete(Long aLong) throws SQLException, ClassNotFoundException {
        try(Session session= HibernateUtil.getInstance().getSession()){
            session.beginTransaction();
            Query query=session.createQuery("DELETE FROM Student WHERE id=:selectedId");
            query.setParameter("selectedId",aLong);
            query.executeUpdate();
        }
    }

    @Override
    public List<Student> findAll() throws SQLException, ClassNotFoundException {
        try(Session session= HibernateUtil.getInstance().getSession()){
           return session.createQuery("FROM Student", Student.class).list();
        }
    }
}

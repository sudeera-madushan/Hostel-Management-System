package lk.ijse.hostel.dao.custom.impl;

import lk.ijse.hostel.dao.custom.StudentDAO;
import lk.ijse.hostel.entity.Student;
import lk.ijse.hostel.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    Session session;
    Transaction transaction;
    @Override
    public boolean save(Student entity) {
        try {
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean delete(Student entity){
        try {
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean update(Student entity) {
        try {
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Student> getAll() {
        List<Student> rooms;
        try {
            session = FactoryConfiguration.getInstance().getSession();
            Query query = session.createQuery("from Student");
            rooms=query.list();
            return rooms;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public String getLastId() {
        List<Student> rooms;
        try {
            session = FactoryConfiguration.getInstance().getSession();
            Query query = session.createQuery("from Student order by student_id desc ");
            rooms=query.list();
            return rooms.get(0).getStudent_id();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public Student find(String s) {
        try {
            session = FactoryConfiguration.getInstance().getSession();
            return session.get(Student.class, s);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public Long getCount() {
        try {
            session = FactoryConfiguration.getInstance().getSession();
            Query query = session.createQuery("select count(s) from Student s");
            List<Long> list = query.list();
            return list.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return new Long(0);
    }
}

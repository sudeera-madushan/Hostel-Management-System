package lk.ijse.hostel.dao.custom.impl;

import lk.ijse.hostel.dao.custom.RoomDAO;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class RoomDAOImpl implements RoomDAO {
    private Session session;
    private Transaction transaction;

    @Override
    public boolean save(Room entity){
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
    public boolean delete(Room entity){
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
    public boolean update(Room entity)  {
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
    public List<Room> getAll()  {
        List<Room> rooms;
        try {
            session = FactoryConfiguration.getInstance().getSession();
            Query query = session.createQuery("from Room");
            rooms=query.list();
            return rooms;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
}

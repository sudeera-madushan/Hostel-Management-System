package lk.ijse.hostel.dao.custom.impl;

import lk.ijse.hostel.dao.custom.ReservationDAO;
import lk.ijse.hostel.entity.Reservation;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {
    private Session session;
    private Transaction transaction;

    @Override
    public boolean save(Reservation entity){
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
    public boolean delete(Reservation entity) {
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
    public boolean update(Reservation entity) {
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
    public List<Reservation> getAll(){
        List<Reservation> reservations;
        try {
            session = FactoryConfiguration.getInstance().getSession();
            Query query = session.createQuery("from Reservation");
            reservations=query.list();
            return reservations;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public String getLastId() {
        List<Reservation> rooms;
        try {
            session = FactoryConfiguration.getInstance().getSession();
            Query query = session.createQuery("from Reservation order by res_id desc ");
            rooms=query.list();
            return rooms.get(0).getRes_id();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
}

package lk.ijse.hostel.dao.util;

import lk.ijse.hostel.dao.custom.impl.ReservationDAOImpl;
import lk.ijse.hostel.dao.custom.impl.RoomDAOImpl;
import lk.ijse.hostel.dao.custom.impl.StudentDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;


    private DAOFactory() {

    }

    public static DAOFactory getDaoFactory() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public SuperDAO getDAO(DAOType type) {
        switch (type) {
            case STUDENT:
                return new StudentDAOImpl();
            case ROOM:
                return new RoomDAOImpl();
            case RESERVATION:
                return new ReservationDAOImpl();
            default:
                return null;
        }

    }

    public enum DAOType {
        STUDENT, RESERVATION, ROOM
    }
}

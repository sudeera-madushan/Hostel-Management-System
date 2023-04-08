package lk.ijse.hostel.dao.util;

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
            default:
                return null;
        }

    }

    public enum DAOType {
        STUDENT, RESERVATION, ROOM
    }
}

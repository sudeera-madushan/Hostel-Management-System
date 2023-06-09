package lk.ijse.hostel.bo;

import lk.ijse.hostel.bo.custom.impl.ReservationBOImpl;
import lk.ijse.hostel.bo.custom.impl.RoomBOImpl;
import lk.ijse.hostel.bo.custom.impl.StudentBOImpl;
import lk.ijse.hostel.bo.custom.impl.UserBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBoFactory() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public SuperBo getBo(BOType boType) {
        switch (boType) {
            case STUDENT:
                return new StudentBOImpl();

            case ROOM:
                return new RoomBOImpl();

            case RESERVATION:
                return new ReservationBOImpl();

            case USER:
                return new UserBOImpl();

            default:
                return null;


        }
    }

    public enum BOType {
        ROOM,RESERVATION,STUDENT,USER
    }
}

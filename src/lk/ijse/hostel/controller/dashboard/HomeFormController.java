package lk.ijse.hostel.controller.dashboard;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hostel.bo.BOFactory;
import lk.ijse.hostel.bo.custom.ReservationBO;
import lk.ijse.hostel.bo.custom.RoomBO;
import lk.ijse.hostel.bo.custom.StudentBO;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class HomeFormController {
    public AnchorPane workingContext;
    public Label lblStudentCount;
    public Label lblRoomCount;
    public Label lblReservationCount;
    public Label lblPendingPayment;
    public BarChart barChart;
    public CategoryAxis cAxis;
    public NumberAxis yAxis;
    public Label lblAvailableRooms;
    public Label lblReservedRooms;
    private StudentBO studentBO;
    private RoomBO roomBO;
    private ReservationBO reservationBO;

    public void initialize() {
        studentBO = (StudentBO) BOFactory.getBoFactory().getBo(BOFactory.BOType.STUDENT);
        roomBO = (RoomBO) BOFactory.getBoFactory().getBo(BOFactory.BOType.ROOM);
        reservationBO = (ReservationBO) BOFactory.getBoFactory().getBo(BOFactory.BOType.RESERVATION);
        setBarChart();
        setDataRowCount();
    }

    private void setBarChart() {
        ObservableList<XYChart.Series<String, Long>> seriesList = FXCollections.observableArrayList();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate currentDate = LocalDate.parse(new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime()), formatter);
        for (int i = 0; i < 3; i++) {
            XYChart.Series<String, Long> series = new XYChart.Series<>();
            String year = i == 0 ? String.valueOf(currentDate.getYear() - 2) : i == 1 ? String.valueOf(currentDate.getYear() - 1) : String.valueOf(currentDate.getYear());
            series.setName(year);
            for (int n = 1; n < 13; n++) {
                series.getData().add(new XYChart.Data<String, Long>(
                        (n == 1 ? "January" : n == 2 ? "February" : n == 3 ? "March" :
                                n == 4 ? "April" : n == 5 ? "May" : n == 6 ? "Jun" : n == 7 ? "July" : n == 8 ? "August" :
                                        n == 9 ? "September" : n == 10 ? "October" : n == 11 ? "November" : "Desember")
                        , reservationBO.getReservationCountByDate(year + "-" + (n>=10 ? n : "0"+n))
                ));

            }
            seriesList.add(series);
        }
        barChart.getData().addAll(seriesList);
        System.out.println(reservationBO.getReservationCountByDate("2023-04"));
    }

    private void setDataRowCount() {
        lblStudentCount.setText(String.valueOf(studentBO.getStudentCount()));
        lblRoomCount.setText("125");
        lblReservationCount.setText(String.valueOf(reservationBO.getReservationCount()));
        lblPendingPayment.setText(String.valueOf(reservationBO.getReservationCountPending()));

        lblAvailableRooms.setText(String.valueOf("Available : "+roomBO.getRoomCount()));
        lblReservedRooms.setText(String.valueOf("Reserved : " + (125-roomBO.getRoomCount())));
    }
}

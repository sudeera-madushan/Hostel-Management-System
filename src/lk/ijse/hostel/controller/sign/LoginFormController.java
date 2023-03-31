package lk.ijse.hostel.controller.sign;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hostel.controller.util.UiNavigateUtil;

public class LoginFormController {
    public JFXPasswordField pxtPassword;
    public JFXTextField txtUserName;
    public JFXButton btnCreate;
    public JFXTextField txtPassword;
    public ImageView imgShowPassword;
    public ImageView imgHidePassword;
    public JFXButton btnLogin;
    public AnchorPane rootContext;

    public void initialize(){
        txtPassword.setVisible(false);
        imgHidePassword.setVisible(false);
        pxtPassword.setOnKeyReleased(event -> txtPassword.setText(pxtPassword.getText()));
        txtPassword.setOnKeyReleased(event -> pxtPassword.setText(txtPassword.getText()));
    }

    public void btnCreateAccountOnAction(ActionEvent actionEvent) {
    }

    public void btnLoginAccountOnAction(ActionEvent actionEvent) {
        UiNavigateUtil.newUi(rootContext,"dashboard/DashboardFrom");
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void imgShowPasswordOnAction(MouseEvent mouseEvent) {
        pxtPassword.setVisible(false);
        imgShowPassword.setVisible(false);
        txtPassword.setVisible(true);
        imgHidePassword.setVisible(true);
    }

    public void imgHidePasswordOnAction(MouseEvent mouseEvent) {
        txtPassword.setVisible(false);
        imgHidePassword.setVisible(false);
        pxtPassword.setVisible(true);
        imgShowPassword.setVisible(true);

    }
}

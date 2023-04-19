package lk.ijse.hostel.controller.sign;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import lk.ijse.hostel.bo.BOFactory;
import lk.ijse.hostel.bo.custom.UserBO;
import lk.ijse.hostel.controller.util.UiNavigateUtil;
import lk.ijse.hostel.dto.UserDTO;

public class LoginFormController {
    public JFXPasswordField pxtPassword;
    public JFXTextField txtUserName;
    public JFXButton btnCreate;
    public JFXTextField txtPassword;
    public ImageView imgShowPassword;
    public ImageView imgHidePassword;
    public JFXButton btnLogin;
    public AnchorPane rootContext;
    private UserBO userBO;

    public void initialize(){
        userBO= (UserBO) BOFactory.getBoFactory().getBo(BOFactory.BOType.USER);
        txtPassword.setVisible(false);
        imgHidePassword.setVisible(false);
        pxtPassword.setOnKeyReleased(event -> txtPassword.setText(pxtPassword.getText()));
        txtPassword.setOnKeyReleased(event -> pxtPassword.setText(txtPassword.getText()));
        boolean isEmpty = userBO.getAll().isEmpty();
        btnCreate.setVisible(isEmpty);
        btnLogin.setVisible(!isEmpty);
    }

    public void btnCreateAccountOnAction(ActionEvent actionEvent) {
        if (userBO.saveUser(new UserDTO(0,txtUserName.getText(),pxtPassword.getText()))){
            new Alert(Alert.AlertType.CONFIRMATION, "Want Create Account !!!").show();
            UiNavigateUtil.newUi(rootContext,"dashboard/DashboardFrom");
        }else new Alert(Alert.AlertType.ERROR, "Account Cannot Create !!!").show();
    }

    public void btnLoginAccountOnAction(ActionEvent actionEvent) {
        UserDTO userDTO = userBO.getAll().get(0);
        if (userDTO.getUser_name().equals(txtUserName.getText())){
            txtUserName.setFocusColor(Color.GREEN);
            txtUserName.setUnFocusColor(Color.GREEN);
            if (userDTO.getPassword().equals(pxtPassword.getText())){
                UiNavigateUtil.newUi(rootContext,"dashboard/DashboardFrom");
            }else {
                pxtPassword.setFocusColor(Color.RED);
                pxtPassword.setUnFocusColor(Color.RED);
                txtPassword.setFocusColor(Color.RED);
                txtPassword.setUnFocusColor(Color.RED);
            }
        }else {
            txtUserName.setFocusColor(Color.RED);
            txtUserName.setUnFocusColor(Color.RED);

        }
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

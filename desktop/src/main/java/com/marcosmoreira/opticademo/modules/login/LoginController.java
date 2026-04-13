package com.marcosmoreira.opticademo.modules.login;

import com.marcosmoreira.opticademo.app.session.CurrentSucursalContext;
import com.marcosmoreira.opticademo.app.session.CurrentUserContext;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtPassword;
    @FXML private Button btnLogin;

    @FXML
    public void handleLogin() {
        String user = txtUsuario.getText();
        if (user == null || user.isBlank()) {
            return;
        }

        CurrentUserContext.setCurrentUser(user);

        try {
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(
                    getClass().getResource("/fxml/app/MainLayout.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) btnLogin.getScene().getWindow();
            Scene scene = new Scene(root, 1280, 800);
            scene.getStylesheets().addAll(
                    getClass().getResource("/css/tokens.css").toExternalForm(),
                    getClass().getResource("/css/app.css").toExternalForm()
            );
            stage.setScene(scene);
            stage.setMaximized(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

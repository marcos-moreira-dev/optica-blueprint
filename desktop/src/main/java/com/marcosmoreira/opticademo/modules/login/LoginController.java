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

/**
 * Controlador para la pantalla de inicio de sesion del sistema Optica.
 * <p>
 * Gestiona la autenticacion del usuario mediante la validacion del nombre de usuario
 * y la transicion hacia la interfaz principal ({@code MainLayout}) una vez verificadas
 * las credenciales. Este controlador actua como punto de entrada al flujo de trabajo
 * del sistema, inicializando el contexto de usuario ({@link CurrentUserContext})
 * antes de cargar el layout principal de la aplicacion.
 * </p>
 * <p>
 * El flujo de navegacion consiste en reemplazar la escena actual del {@link Stage}
 * por una nueva instancia cargada desde {@code /fxml/app/MainLayout.fxml}, aplicando
 * las hojas de estilo definidas en {@code tokens.css} y {@code app.css}.
 * </p>
 *
 * @author Marcos Moreira
 * @version 1.0.0
 * @see CurrentUserContext
 */
public class LoginController {

    /**
     * Campo de texto para ingresar el nombre de usuario.
     * Vinculado al elemento FXML {@code txtUsuario}.
     */
    @FXML private TextField txtUsuario;

    /**
     * Campo de contraseña para ingresar la clave de acceso.
     * Vinculado al elemento FXML {@code txtPassword}.
     */
    @FXML private PasswordField txtPassword;

    /**
     * Boton que dispara la accion de inicio de sesion.
     * Vinculado al elemento FXML {@code btnLogin}.
     */
    @FXML private Button btnLogin;

    /**
     * Inicializa el controlador y configura los listeners necesarios.
     * <p>
     * Este metodo es invocado automaticamente por el {@link javafx.fxml.FXMLLoader}
     * tras cargar el archivo FXML asociado. En esta implementacion, el controlador
     * no requiere configuracion inicial ya que toda la logica reside en el manejador
     * de eventos {@link #handleLogin()}.
     * </p>
     */
    public void initialize() {
    }

    /**
     * Maneja la accion de inicio de sesion al presionar el boton de login.
     * <p>
     * Este metodo valida que el campo de usuario no este vacio, registra el usuario
     * en el contexto global de la aplicacion mediante {@link CurrentUserContext#setCurrentUser(String)},
     * y realiza la transicion de escena hacia el layout principal ({@code MainLayout.fxml}).
     * La nueva escena se configura con dimensiones de 1280x800 pixeles, se maximiza
     * y se le aplican las hojas de estilo {@code tokens.css} y {@code app.css}.
     * </p>
     * <p>
     * En caso de error durante la carga del FXML, se imprime el stack trace
     * para facilitar la depuracion.
     * </p>
     *
     * @see CurrentUserContext#setCurrentUser(String)
     * @see CurrentSucursalContext
     */
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

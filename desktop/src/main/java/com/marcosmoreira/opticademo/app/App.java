package com.marcosmoreira.opticademo.app;

import com.marcosmoreira.opticademo.demo.DemoDataInitializer;
import com.marcosmoreira.opticademo.demo.DemoStore;
import com.marcosmoreira.opticademo.app.session.DemoSession;
import com.marcosmoreira.opticademo.app.session.CurrentUserContext;
import com.marcosmoreira.opticademo.app.session.CurrentSucursalContext;
import com.marcosmoreira.opticademo.app.theme.ThemeManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

    private DemoStore demoStore;
    private DemoSession demoSession;

    @Override
    public void init() {
        this.demoStore = new DemoStore();
        this.demoSession = new DemoSession();
        AppSessionHolder.store = this.demoStore;

        DemoDataInitializer initializer = new DemoDataInitializer(this.demoStore);
        initializer.initialize();

        CurrentUserContext.setCurrentUser(this.demoSession.getDefaultUser());
        CurrentSucursalContext.setSucursal(this.demoSession.getDefaultSucursal());
        ThemeManager.setThemeMode(com.marcosmoreira.opticademo.app.theme.ThemeMode.LIGHT);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login/LoginView.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root, 900, 560);
        scene.getStylesheets().addAll(
                getClass().getResource("/css/tokens.css").toExternalForm(),
                getClass().getResource("/css/app.css").toExternalForm(),
                getClass().getResource("/css/custom-controls.css").toExternalForm(),
                getClass().getResource("/css/login.css").toExternalForm()
        );

        primaryStage.setTitle("Optica Manager - Login");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(500);
        primaryStage.centerOnScreen();

        // Set application icon (logo.png in resources)
        try {
            Image icon = new Image(getClass().getResourceAsStream("/logo.png"));
            primaryStage.getIcons().add(icon);
        } catch (Exception e) {
            // Icon not available, continuing without it
        }

        primaryStage.show();
    }

    public static DemoStore getDemoStore() {
        return AppSessionHolder.store;
    }

    private static class AppSessionHolder {
        static DemoStore store;
    }
}

package com.marcosmoreira.opticademo.app.navigation;

import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public class ModuleNavigator {

    private final StackPane contentHost;
    private final ModuleViewLoader viewLoader;
    private ModuleId currentModule;

    public ModuleNavigator(StackPane contentHost) {
        this.contentHost = contentHost;
        this.viewLoader = new ModuleViewLoader();
    }

    public void navigateTo(ModuleId moduleId) {
        if (currentModule == moduleId) {
            return;
        }
        currentModule = moduleId;
        Node view = viewLoader.loadView(moduleId);
        contentHost.getChildren().setAll(view);
    }

    public ModuleId getCurrentModule() {
        return currentModule;
    }
}

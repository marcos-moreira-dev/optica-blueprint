package com.marcosmoreira.opticademo.app.navigation;

public record NavigationItem(ModuleId moduleId, String icon) {

    public String getDisplayName() {
        return moduleId.getDisplayName();
    }
}

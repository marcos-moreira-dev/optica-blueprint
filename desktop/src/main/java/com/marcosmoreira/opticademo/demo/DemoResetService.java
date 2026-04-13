package com.marcosmoreira.opticademo.demo;

/**
 * Service responsible for resetting the DemoStore to its initial seed state.
 * Clears all collections and re-runs the DemoDataInitializer.
 */
public class DemoResetService {

    private final DemoStore store;
    private final DemoDataInitializer initializer;

    public DemoResetService(DemoStore store, DemoDataInitializer initializer) {
        this.store = store;
        this.initializer = initializer;
    }

    /**
     * Clears all data and re-populates the store with seed data.
     */
    public void reset() {
        store.clear();
        initializer.initialize();
    }
}

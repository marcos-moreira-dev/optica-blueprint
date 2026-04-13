package com.marcosmoreira.opticademo.shared.ui.components;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * Controller for a pagination bar showing page info and navigation buttons.
 */
public class PaginationBarController {

    @FXML
    private HBox rootPane;

    @FXML
    private Label recordsLabel;

    @FXML
    private Label pageLabel;

    @FXML
    private Button prevButton;

    @FXML
    private Button nextButton;

    private int currentPage = 1;
    private int totalPages = 1;
    private int totalItems = 0;
    private int pageSize = 10;

    private Runnable onPrev;
    private Runnable onNext;

    public void initialize() {
        if (rootPane != null) {
            rootPane.getStyleClass().add("pagination-bar");
        }
        if (prevButton != null) {
            prevButton.setOnAction(e -> triggerPrev());
        }
        if (nextButton != null) {
            nextButton.setOnAction(e -> triggerNext());
        }
        updateDisplay();
    }

    /**
     * Sets the current page number (1-based).
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = Math.max(1, currentPage);
        updateDisplay();
    }

    /**
     * Sets the total number of pages.
     */
    public void setTotalPages(int totalPages) {
        this.totalPages = Math.max(1, totalPages);
        updateDisplay();
    }

    /**
     * Sets the total number of items.
     */
    public void setTotalItems(int totalItems) {
        this.totalItems = Math.max(0, totalItems);
        updateDisplay();
    }

    /**
     * Sets the page size used for calculating display info.
     */
    public void setPageSize(int pageSize) {
        this.pageSize = Math.max(1, pageSize);
        updateDisplay();
    }

    /**
     * Sets the callback for the previous page button.
     */
    public void setOnPrev(Runnable onPrev) {
        this.onPrev = onPrev;
    }

    /**
     * Sets the callback for the next page button.
     */
    public void setOnNext(Runnable onNext) {
        this.onNext = onNext;
    }

    private void updateDisplay() {
        if (recordsLabel != null) {
            int start = totalItems == 0 ? 0 : (currentPage - 1) * pageSize + 1;
            int end = Math.min(currentPage * pageSize, totalItems);
            recordsLabel.setText(String.format("Mostrando %d de %d registros", start, totalItems));
        }
        if (pageLabel != null) {
            pageLabel.setText(String.format("Pagina %d de %d", currentPage, totalPages));
        }
        if (prevButton != null) {
            prevButton.setDisable(currentPage <= 1);
        }
        if (nextButton != null) {
            nextButton.setDisable(currentPage >= totalPages);
        }
    }

    private void triggerPrev() {
        if (currentPage > 1) {
            currentPage--;
            updateDisplay();
            if (onPrev != null) {
                onPrev.run();
            }
        }
    }

    private void triggerNext() {
        if (currentPage < totalPages) {
            currentPage++;
            updateDisplay();
            if (onNext != null) {
                onNext.run();
            }
        }
    }
}

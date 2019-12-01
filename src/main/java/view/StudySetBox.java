package main.java.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.VBox;

class StudySetBox extends VBox implements EventHandler<ActionEvent> {

    /**
     * Torli azt a szett elemet amibol hivas tortent.
     * @param actionEvent gomb esemény
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        SetElement e = ((ElementDeleteButton)actionEvent.getSource()).getButtonHolder();
        this.getChildren().remove(e);
    }
}

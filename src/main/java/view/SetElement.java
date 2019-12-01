package main.java.view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.java.model.TermAndDefinition;

class SetElement extends HBox {
    private TextArea term;
    private TextArea definition;

    /**
     * Létrehoz egy szett elemet a megadott kifejezes-definicio parral a megadott kontenerben.
     * @param tad kifejezes-definicio par
     * @param studySetBox kontener
     */
    SetElement(TermAndDefinition tad, StudySetBox studySetBox) {
        Label l1 = new Label("Term");
        Label l2 = new Label("Definition");
        term = new TextArea(tad.term);
        definition = new TextArea(tad.definition);
        term.setPrefHeight(50);
        definition.setPrefHeight(50);
        ElementDeleteButton delete = new ElementDeleteButton("X", this);
        delete.setOnAction(studySetBox);

        VBox first = new VBox();
        VBox second = new VBox();
        VBox third = new VBox();
        Insets insets = new Insets(10, 10, 10, 10);
        first.setPadding(insets);
        second.setPadding(insets);
        third.setPadding(insets);

        first.getChildren().addAll(l1, term);
        second.getChildren().addAll(l2, definition);
        third.getChildren().addAll(delete);

        this.getChildren().addAll(first, second, third);
    }

    /**
     * Getter a kifejezesre.
     * @return kifejezes
     */
    String getTerm() {
        return term.getText();
    }

    /**
     * Getter a definiciora.
     * @return definicio
     */
    String getDefinition() {
        return definition.getText();
    }
}

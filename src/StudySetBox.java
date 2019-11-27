import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.VBox;

public class StudySetBox extends VBox implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent actionEvent) {
        SetElement e = ((ElementDeleteButton)actionEvent.getSource()).getButtonHolder();
        this.getChildren().remove(e);
    }
}

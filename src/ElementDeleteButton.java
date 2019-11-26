import javafx.scene.control.Button;

public class ElementDeleteButton extends Button {
    private SetElement setElement;

    ElementDeleteButton(String text, SetElement setElement) {
        super(text);
        this.setElement = setElement;
    }

    SetElement getButtonHolder() {
        return setElement;
    }
}

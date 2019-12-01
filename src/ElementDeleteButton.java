import javafx.scene.control.Button;

class ElementDeleteButton extends Button {
    private SetElement setElement;

    /**
     * Beallitja a gomb szoveget, es, hogy melyik szett elemben van benne.
     * @param text szoveg
     * @param setElement szett elem
     */
    ElementDeleteButton(String text, SetElement setElement) {
        super(text);
        this.setElement = setElement;
    }

    /**
     * Visszater azzal a szett elemmel amelyikben a gomb elhelyezkedik.
     * @return szett elem
     */
    SetElement getButtonHolder() {
        return setElement;
    }
}

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    /**
     * Beallitja a Controller, Model, View harmast.
     * @param primaryStage szinter
     * @throws Exception kivetel (Sosem dob kivetelt, de benne hagyjuk, a szokas kedveert)
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        View view = new View(primaryStage);

        Model model = new Model(view);
        model.loadSavedSets();

        Controller controller = new Controller(model, view);

        view.setupMainMenu(controller, model.getHierarchyList());
        view.setupStudySections();

        view.showMainMenu();
        view.showView();

        primaryStage.setOnCloseRequest(actionEvent -> {
            controller.saveDisplayedSet();
            model.saveSets();
        });
    }


    /**
     * Main fuggveny. Inicializalja a futtatasi kornyezetet.
     * @param args futtatasi argumentumok
     */
    public static void main(String[] args) {
        launch(args);
    }
}
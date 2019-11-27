import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        View view = new View(primaryStage);

        Model model = new Model(view);
        model.loadSavedSets();

        Controller controller = new Controller(model, view);

        view.setupMainMenu(controller, model.getHierarchyList());
        view.setupStudyFlashcard();

        view.showMainMenu();
        //view.showStudyFlashcard();

        view.showView();

        primaryStage.setOnCloseRequest(actionEvent -> {
            controller.saveDisplayedSet();
            model.saveSets();
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
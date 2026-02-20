package bsu.edu.cs;

import javafx.scene.control.*;
import javafx.scene.layout.Priority;
import org.json.JSONException;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


//START GUI
//____________________________________________________________________________________________________


public class Main extends Application{
    public static void main(String[] args){ launch(args);}
    private final Button getRevisionsButton = new Button("Get Latest Revisions");
    private final TextField inputField = new TextField();
    private final TextArea outputField = new TextArea();
    private final Button runConsoleButton = new Button("Run Console Instead");

    @Override
    public void start(Stage primaryStage) throws JSONException, IOException {
        outputField.setEditable(false);
        outputField.setWrapText(true);
        configure(primaryStage);
        configureGetRevisionsButton();
    }
    private void configure(Stage stage){
        stage.setTitle("RevisionFinder");
        stage.setScene(new Scene(createRoot()));
        stage.sizeToScene();
        stage.show();
    }
    private Pane createRoot(){
        VBox root = new VBox();
        root.getChildren().addAll(
                new Label("Search with article title here: "),
                inputField,
                getRevisionsButton,
                new Label("Revisions:"),
                outputField,
                runConsoleButton);
        VBox.setVgrow(outputField, Priority.ALWAYS);
        return root;
    }
    private void configureGetRevisionsButton(){
        getRevisionsButton.setOnAction(event -> {
            try {
                getRevisionsFromInput();
            } catch (JSONException | IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    private void getRevisionsFromInput() throws JSONException, IOException {
        if((inputField.getText()).isEmpty()){
            outputField.setText("Please enter something atleast");
        }else {
            String JsonString = MediaWikiReader.runReader(inputField.getText());
            String FormattedRevisions = RevisionFormatter.formatRevisions(JsonString);
            outputField.setText(FormattedRevisions);
        }
    }
//____________________________________________________________________________________________________
//END GUI

}

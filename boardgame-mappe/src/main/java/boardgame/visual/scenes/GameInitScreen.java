package boardgame.visual.scenes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GameInitScreen {
    public Stage primaryStage;
    private static final int playerCount = 4;
    private String[] playerNames = new String[playerCount];
    List<String> imagePaths = new ArrayList<>();


    public GameInitScreen(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void init() {

        primaryStage.setTitle("Snakes & Ladders config");

        VBox menuWrapper = new VBox();
        HBox menuPane = new HBox();
        menuWrapper.getChildren().add(menuPane);
        menuPane.setAlignment(Pos.CENTER);
        menuWrapper.setMargin(menuPane, new Insets(50,50,50,50));

        Button b1 = new Button("Preset1");
        Button b2 = new Button("Preset2");
        Button b3 = new Button("Preset3");



        String buttonStyle = "\"-fx-start-margin: 0; -fx-end-margin: 20; -fx-alignment: center; -fx-pref-height: 30\"";

        b1.setStyle(buttonStyle);
        b2.setStyle(buttonStyle);
        b3.setStyle(buttonStyle);
        menuPane.getChildren().addAll(b1,b2,b3);

        Button b4 = new Button("Start game!");
        b4.setStyle(buttonStyle);

        HBox startButtonWrapper = new HBox();
        startButtonWrapper.setAlignment(Pos.CENTER);
        startButtonWrapper.getChildren().add(b4);
        menuWrapper.getChildren().add(startButtonWrapper);

        VBox playerWrapper = new VBox();
        TextField playerNameField = new TextField("playername");
        playerWrapper.getChildren().add(playerNameField);
        menuWrapper.getChildren().add(playerWrapper);

        //button action
        b4.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                GameInitScreen.this.startGame(b4);
            }
        });

        Scene menu = new Scene(menuWrapper, 600, 600);
        primaryStage.setScene(menu);
    }

    public void startGame(Button button){
         button.setText("Womp womp gora");
    }

    public void start() {
        this.init();
        primaryStage.show();
    }

    private List<String> loadImages(String path) {
        imagePaths = new ArrayList<>();
        File dir = new File(path);
        if (dir.isDirectory()) {
            for (File file : dir.listFiles((d, name) -> name.endsWith(".png") || name.endsWith(".jpg") || name.endsWith(".webp") || name.endsWith(".gif"))) {
                imagePaths.add(file.toURI().toString());
            }
        }
        return imagePaths;
    }

    private void changePlayerImage(int playerIndex){

    }

}


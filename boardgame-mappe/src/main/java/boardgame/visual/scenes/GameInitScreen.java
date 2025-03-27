package boardgame.visual.scenes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.control.Button;
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
    List<Button> playerButtons = new ArrayList<>();
    List<TextField> playerTextFields = new ArrayList<>();
    List<HBox> playerFieldWrappers = new ArrayList<>();


    public GameInitScreen(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void init() {

        primaryStage.setTitle("Snakes & Ladders config");
        loadImages("/PlayerIcons/");

        VBox menuWrapper = new VBox();
        HBox menuPane = new HBox();
        menuWrapper.getChildren().add(menuPane);
        menuPane.setAlignment(Pos.CENTER);
        menuWrapper.setMargin(menuPane, new Insets(50,50,50,50));

        Button presetBtn1 = new Button("Preset1");
        Button presetBtn2 = new Button("Preset2");
        Button presetBtn3 = new Button("Preset3");

        String buttonStyle = "-fx-start-margin: 0; -fx-end-margin: 20; -fx-alignment: center; -fx-pref-height: 30; -fx-background-color: rgba(200,200,200);";

        presetBtn1.setStyle(buttonStyle);
        presetBtn2.setStyle(buttonStyle);
        presetBtn3.setStyle(buttonStyle);

        menuPane.getChildren().addAll(presetBtn1,presetBtn2,presetBtn3);

        VBox playerWrapper = new VBox();
        TextField playerNameField = new TextField("playername");
        for (int i = 0; i < playerCount; i++) {
            playerTextFields.add(new TextField());
            playerTextFields.get(i).setPromptText("Player " + (i + 1));
            playerButtons.add(new Button());
            playerFieldWrappers.add(new HBox());
            playerFieldWrappers.get(i).getChildren().addAll(playerButtons.get(i), playerTextFields.get(i));
            playerFieldWrappers.get(i).setAlignment(Pos.CENTER);

            int finalI = i;
            playerButtons.get(i).setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    ImageView buttonIMG = new ImageView(new Image("/PlayerIcons/xd.png"));
                    buttonIMG.setFitHeight(40);
                    buttonIMG.setFitWidth(40);
                    playerButtons.get(finalI).setGraphic(buttonIMG);
                }
            });
        }


        playerWrapper.getChildren().addAll(playerFieldWrappers);
        VBox.setMargin(playerWrapper, new Insets(0,0,50,0));
        menuWrapper.getChildren().add(playerWrapper);


        //start game button
        Button startGameBtn = new Button("Start game!");
        startGameBtn.setStyle(buttonStyle);
        HBox startButtonWrapper = new HBox();
        startButtonWrapper.setAlignment(Pos.CENTER);
        startButtonWrapper.getChildren().add(startGameBtn);
        menuWrapper.getChildren().add(startButtonWrapper);

        //button action
        startGameBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                GameInitScreen.this.startGame(startGameBtn);
            }
        });

        Scene menu = new Scene(menuWrapper);
        primaryStage.setScene(menu);
    }

    public void startGame(Button button){
         button.setText("Womp womp gora");
    }

    public void start() {
        this.init();
        primaryStage.show();
    }

    private void loadImages(String path) {
        imagePaths = new ArrayList<>();
        File dir = new File(path);
        if (dir.isDirectory()) {
            for (File file : dir.listFiles((d, name) -> name.endsWith(".png") || name.endsWith(".jpg") || name.endsWith(".webp") || name.endsWith(".gif"))) {
                imagePaths.add(file.toURI().toString());
            }
        }
    }



    private void changePlayerImage(int playerIndex){

    }

}


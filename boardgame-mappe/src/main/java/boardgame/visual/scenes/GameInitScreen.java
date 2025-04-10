package boardgame.visual.scenes;

import boardgame.controller.PlayerCSV;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameInitScreen {
    public Stage primaryStage;
    List<String> imagePaths = new ArrayList<>();
    List<Button> playerButtons = new ArrayList<>();
    List<HBox> playerFieldWrappers = new ArrayList<>();
    List<Button> saveButtons = new ArrayList<>();
    List<ComboBox> playerDropdowns = new ArrayList<>();
    ArrayList<Integer> playerIconIndexes = new ArrayList<>();
    private VBox playerWrapper;
    private PlayerCSV playerCSV = new PlayerCSV();

    public GameInitScreen(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void init() {
        primaryStage.setTitle("Snakes & Ladders config");
        loadImages();

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

        Button addPlayerBtn = new Button("Add Player");
        addPlayerBtn.setStyle(buttonStyle);
        HBox addPlayerBtnWrapper = new HBox();
        addPlayerBtnWrapper.getChildren().add(addPlayerBtn);
        addPlayerBtnWrapper.setAlignment(Pos.CENTER);
        menuWrapper.getChildren().add(addPlayerBtnWrapper);


        playerWrapper = new VBox();
        playerWrapper.setSpacing(10);
        addPlayer();
        addPlayerBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                addPlayer();
            }
        });


        VBox.setMargin(playerWrapper, new Insets(0,0,50,0));
        menuWrapper.getChildren().add(playerWrapper);


        Button startGameBtn = new Button("Start game!");
        startGameBtn.setStyle(buttonStyle);
        HBox startButtonWrapper = new HBox();
        startButtonWrapper.setAlignment(Pos.CENTER);
        startButtonWrapper.getChildren().add(startGameBtn);
        menuWrapper.getChildren().add(startButtonWrapper);


        startGameBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                GameInitScreen.this.startGame(startGameBtn);
            }
        });

        Scene menu = new Scene(menuWrapper);
        primaryStage.setScene(menu);
    }

    private void addPlayer(){
        int index = playerFieldWrappers.size();
        String[] playerNames = playerCSV.getPlayerNames();
        ComboBox<String> playerDropdown = new ComboBox<>();
        playerDropdown.setPromptText("Player " + (index + 1));
        playerDropdown.setEditable(true);
        playerDropdown.setPrefHeight(40);
        Arrays.stream(playerNames).toList().forEach(playername->{playerDropdown.getItems().add(playername);});

        playerDropdowns.add(playerDropdown);

        Button playerButton = new Button();
        playerButtons.add(playerButton);

        playerIconIndexes.add(0);

        ImageView buttonIMG = new ImageView(new Image(imagePaths.get(0)));
        buttonIMG.setFitHeight(40);
        buttonIMG.setFitWidth(40);
        playerButton.setGraphic(buttonIMG);

        Button saveButton = new Button("Save new");
        saveButtons.add(saveButton);


        HBox playerFieldWrapper = new HBox(10); // 10px spacing
        playerFieldWrapper.getChildren().addAll(playerButton, playerDropdown, saveButton);
        playerFieldWrapper.setAlignment(Pos.CENTER);
        playerFieldWrappers.add(playerFieldWrapper);

        playerWrapper.getChildren().add(playerFieldWrapper);

        final int finalIndex = index;
        playerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                int currentIndex = playerIconIndexes.get(finalIndex);
                if (currentIndex < imagePaths.size() - 1) {
                    currentIndex++;
                } else {
                    currentIndex = 0;
                }

                playerIconIndexes.set(finalIndex, currentIndex);

                ImageView buttonIMG = new ImageView(new Image(imagePaths.get(currentIndex)));
                buttonIMG.setFitHeight(40);
                buttonIMG.setFitWidth(40);
                playerButton.setGraphic(buttonIMG);
            }
        });

        playerDropdown.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String selectedPlayer = playerDropdown.getValue();
                if (Arrays.stream(playerNames).anyMatch(selectedPlayer::equals)) {
                    String iconPath = playerCSV.getPlayerIconByPlayerName(selectedPlayer);
                    File iconFile = new File(iconPath);

                    String iconURI = iconFile.toURI().toString();

                    int iconIndex = imagePaths.indexOf(iconURI);
                    if (iconIndex == -1) {
                        System.out.println("Icon not found in loaded images");
                        return;
                    }

                    playerIconIndexes.set(finalIndex, iconIndex);

                    ImageView buttonIMG = new ImageView(new Image(iconURI));
                    buttonIMG.setFitHeight(40);
                    buttonIMG.setFitWidth(40);
                    playerButtons.get(finalIndex).setGraphic(buttonIMG);
                }
            }
        });

        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String playerName = playerDropdown.getValue();
                if (playerName == null || playerName.trim().isEmpty()) {
                    System.out.println("Player name cannot be empty.");
                    return;
                }

                int selectedIconIndex = playerIconIndexes.get(finalIndex);
                String selectedIconPath = imagePaths.get(selectedIconIndex);

                File selectedIconFile = new File(selectedIconPath.replace("file:/", ""));
                String relativeIconPath = "src/main/resources/PlayerIcons/" + selectedIconFile.getName();

                String[] playerNames = playerCSV.getPlayerNames();
                if (Arrays.stream(playerNames).anyMatch(playerName::equals)) {
                    playerCSV.changeIcon(playerName, relativeIconPath);
                    saveButton.setText("Updated");
                } else {
                    playerCSV.registerNewPlayer(playerName, relativeIconPath);
                    saveButton.setText("Saved");
                }
            }
        });
    }

    public void startGame(Button button){
         button.setText("Womp womp gora");
    }

    public void start() {
        this.init();
        primaryStage.show();
    }

    private void loadImages() {
        String path = "src/main/resources/PlayerIcons/";
        imagePaths = new ArrayList<>();
        File dir = new File(path);
        if (dir.isDirectory()) {
            for (File file : dir.listFiles()) {
                imagePaths.add(file.toURI().toString());
            }
        }
        else{
            System.out.println("Not a directory");
        }
    }
}
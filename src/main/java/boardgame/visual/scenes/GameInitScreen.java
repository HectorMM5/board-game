package boardgame.visual.scenes;

import boardgame.controller.PlayerCSV;
import boardgame.model.boardFiles.Player;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameInitScreen {
    public Stage primaryStage;
    private final String ICON_RELATIVE_PATH = "/PlayerIcons/";
    List<String> iconFileNames = new ArrayList<>();
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

        Scene menu = new Scene(menuWrapper, 600, 600);
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

        Image iconImage = getImageFromFileName(iconFileNames.get(0));
        ImageView buttonIMG = new ImageView(iconImage);
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
                if (currentIndex < iconFileNames.size() - 1) {
                    currentIndex++;
                } else {
                    currentIndex = 0;
                }

                playerIconIndexes.set(finalIndex, currentIndex);

                Image iconImage = getImageFromFileName(iconFileNames.get(currentIndex));
                ImageView buttonIMG = new ImageView(iconImage);
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
                    String relativeIconPath = playerCSV.getPlayerIconByPlayerName(selectedPlayer);
                    
                    String fileName = new File(relativeIconPath).getName();

                    int iconIndex = iconFileNames.indexOf(fileName);
                    if (iconIndex == -1) {
                        System.out.println("Icon not found in loaded images");
                        return;
                    }

                    playerIconIndexes.set(finalIndex, iconIndex);

                    Image iconImage = getImageFromFileName(fileName);
                    ImageView buttonIMG = new ImageView(iconImage);
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
                String iconFileName = iconFileNames.get(selectedIconIndex);
                
                String relativeIconPath = ICON_RELATIVE_PATH + iconFileName;

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
        iconFileNames = new ArrayList<>();
        String directoryPath = "src/main/resources/PlayerIcons/";
        File dir = new File(directoryPath);
        
        if (dir.isDirectory()) {
            for (File file : dir.listFiles()) {
                iconFileNames.add(file.getName());
            }
        } else {
            System.out.println("Not a directory: " + directoryPath);
        }
    }
    

    private Image getImageFromFileName(String fileName) {
        String resourcePath = ICON_RELATIVE_PATH + fileName;
        return new Image(getClass().getResourceAsStream(resourcePath));
    }

    public ArrayList<Player> getCurrentPlayers() {
    ArrayList<Player> playerList = new ArrayList<>();

    for (int i = 0; i < playerDropdowns.size(); i++) {
        ComboBox<String> dropdown = playerDropdowns.get(i);
        String playerName = dropdown.getValue();

        if (playerName != null && !playerName.trim().isEmpty()) {
            String relativeIconPath = playerCSV.getPlayerIconByPlayerName(playerName);
            
            if (!relativeIconPath.startsWith(ICON_RELATIVE_PATH)) {
                String iconFileName = new File(relativeIconPath).getName();
                relativeIconPath = ICON_RELATIVE_PATH + iconFileName;
            }
            
            Player player = new Player(relativeIconPath, playerName);
            playerList.add(player);
        }
    }

    if (playerList.isEmpty()) {
        System.out.println("No valid players selected");
    }
    for (Player player : playerList) {
        System.out.println("Player: " + player.getName() + ", Icon: " + player.getIcon());
    }
    return playerList;
}
}
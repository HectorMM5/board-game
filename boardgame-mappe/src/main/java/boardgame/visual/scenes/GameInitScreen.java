package boardgame.visual.scenes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.text.Text;

public class GameInitScreen {
    public Stage primaryStage;

    public GameInitScreen(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void init() {

        primaryStage.setTitle("Snakes & Ladders config");


        GridPane menuPane = new GridPane();
        Button b1 = new Button("Preset1");
        Button b2 = new Button("Preset2");
        Button b3 = new Button("Preset3");

        Button b4 = new Button("Start game!");

        Slider s1 = new Slider();
        TextField t1 = new TextField("Slider for number of tiles. will change obviously");

        String buttonStyle = "\"-fx-start-margin: 0; -fx-end-margin: 20; -fx-alignment: center; -fx-pref-height: 30\"";

        b1.setStyle(buttonStyle);
        b2.setStyle(buttonStyle);
        b3.setStyle(buttonStyle);
        b4.setStyle(buttonStyle);

        Scene menu = new Scene(menuPane, 600, 600);
        menuPane.add(b1,0,0);
        menuPane.add(b2,1,0);
        menuPane.add(b3,3,0);
        menuPane.add(t1,0,1);
        menuPane.add(s1,0,2);
        menuPane.add(b4,0,3);

        GridPane.setColumnSpan(t1,2);
        GridPane.setColumnSpan(s1,2);

        //button action
        b4.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                GameInitScreen.this.startGame(b4);
            }
        });


        primaryStage.setScene(menu);
    }

    public void startGame(Button button){
         button.setText("Womp womp bitch");
    }

    public void start() {
        this.init();
        primaryStage.show();
    }
}


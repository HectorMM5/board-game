package boardgame.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.IntStream;

import org.json.JSONArray;
import org.json.JSONObject;

import boardgame.model.boardFiles.Board;
import boardgame.model.effectFiles.BackToStartEffect;
import boardgame.model.effectFiles.Effect;
import boardgame.model.effectFiles.LadderEffect;
import boardgame.model.effectFiles.PlaceholderEffect;
import boardgame.model.effectFiles.SkipTurnEffect;
import boardgame.model.effectFiles.SnakeEffect;

public class BoardJSON {

    GameController gameController;
    public static Board constructSnLBoardFromJSON(int choice, GameController gameController) {
        Board board = new Board();

        try {
            String jsonText = Files.readString(Paths.get("boards.json"));
            JSONObject SnLBoard = new JSONObject(jsonText).getJSONArray("games").getJSONObject(0).getJSONArray("SnL").getJSONObject(choice);

            JSONArray tilesWithEffects = SnLBoard.getJSONArray("tiles"); 

            IntStream.rangeClosed(0, tilesWithEffects.length() - 1)
            .forEach(i -> modifyEffectTileFromJSON(tilesWithEffects.getJSONObject(i), board, gameController));

            
        } catch (IOException e) {
            e.printStackTrace();
        }

        return board;
    }

    public static void modifyEffectTileFromJSON(JSONObject tileWithEffect, Board board, GameController gameController) {
        int tileNumber = tileWithEffect.getInt("tile");
        String effectType = tileWithEffect.getString("effect");
        
        Effect effect;
        int target;
        switch (effectType) {
            case "Ladder":
                target = tileWithEffect.getInt("target");
                board.getTiles().get(tileNumber - 1).setEffect(new PlaceholderEffect());

                effect = new LadderEffect(gameController, tileNumber, target);

                break;

            case "Snake":
                target = tileWithEffect.getInt("target");
                board.getTiles().get(tileNumber - 1).setEffect(new PlaceholderEffect());

                effect = new SnakeEffect(gameController, tileNumber, target);

                break;

            case "LoseTurn":
                effect = new SkipTurnEffect(gameController);
                break;

            case "Back":
                effect = new BackToStartEffect(gameController);
                break;

            default:
                throw new AssertionError();
        }

        board.getTiles().get(tileNumber - 1).setEffect(effect);

    }

}

package boardgame.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
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

    public static Board constructSnLBoardFromJSON(int choice, GameController gameController) {
        Board board = new Board();

        try (InputStream is = BoardJSON.class.getClassLoader().getResourceAsStream("boards.json")) {
            if (is == null) {
                throw new IOException("boards.json not found in resources!");
            }

            String jsonText = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            JSONObject SnLBoard = new JSONObject(jsonText)
                .getJSONArray("games")
                .getJSONObject(0)
                .getJSONArray("SnL")
                .getJSONObject(choice);

            JSONArray tilesWithEffects = SnLBoard.getJSONArray("tiles");

            IntStream.range(0, tilesWithEffects.length())
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
                board.getTiles().get(target - 1).setEffect(new PlaceholderEffect());
                effect = new LadderEffect(tileNumber, target);
                break;

            case "Snake":
                target = tileWithEffect.getInt("target");
                board.getTiles().get(target - 1).setEffect(new PlaceholderEffect());
                effect = new SnakeEffect(tileNumber, target);
                break;

            case "LoseTurn":
                effect = new SkipTurnEffect();
                break;

            case "Back":
                effect = new BackToStartEffect();
                break;

            default:
                throw new AssertionError("Unknown effect type: " + effectType);
        }

        board.getTiles().get(tileNumber - 1).setEffect(effect);
    }
}

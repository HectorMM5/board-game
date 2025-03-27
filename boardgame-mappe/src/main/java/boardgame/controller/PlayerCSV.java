package boardgame.controller;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class PlayerCSV {

    // Defines player CSV file
    private static final File file = new File("playerProfiles.csv");

    private static ArrayList<String[]> getCSVContent() {
        ArrayList<String[]> allPlayers = new ArrayList<>();

        try {
            // create FileWriter object with file as parameter 
            CSVReader reader = new CSVReader(new FileReader(file));

            String[] row;
            while ((row = reader.readNext()) != null) {
                //Saves the CSV content locally
                allPlayers.add(row);
            }

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return allPlayers;

    }

    private static void rewriteFile(ArrayList<String[]> allPlayers) {
        try {
            CSVWriter writer = new CSVWriter(new FileWriter(file));

            writer.writeAll(allPlayers);

            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //Taken and adapted from GeeksForGeeks
    public static void registerNewPlayer(String name, String icon) {

        ArrayList<String[]> allPlayers = getCSVContent();

        Iterator<String[]> iterator = allPlayers.iterator();
        
        while (iterator.hasNext()) {
            if (iterator.next()[0].equals(name)) {
                throw new IllegalArgumentException("A player profile with the given name already exists.");
            }
        }
        
        allPlayers.add(new String[] {name, icon, "0"});

        rewriteFile(allPlayers);

    }

    public static void incrementPlayerWins(String name) {

        ArrayList<String[]> allPlayers = getCSVContent();

        Iterator<String[]> iterator = allPlayers.iterator();

        String[] row;

        while (iterator.hasNext()) {
            row = iterator.next();

            if (row[0].equals(name)) {
                row[2] += Integer.toString(Integer.parseInt(row[2]) + 1);
                break;
            }
        }

        rewriteFile(allPlayers);

    }

    public static void changeIcon(String name, String icon) {
        ArrayList<String[]> allPlayers = getCSVContent();

        Iterator<String[]> iterator = allPlayers.iterator();

        String[] row;

        while (iterator.hasNext()) {
            row = iterator.next();

            if (row[0].equals(name)) {
                row[1] = icon;
                break;
            }
        }

        rewriteFile(allPlayers);

    }

}

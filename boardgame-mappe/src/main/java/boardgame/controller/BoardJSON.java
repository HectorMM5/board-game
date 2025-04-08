package boardgame.controller;

import boardgame.model.boardFiles.BoardCollection;
import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class BoardJSON {

    private final ObjectMapper objectMapper;
    private final String filePath;

    public BoardJSON(String filePath) {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        this.filePath = filePath;
    }

    public void save(BoardCollection boardCollection) throws IOException {
            objectMapper.writeValue(new File(filePath), boardCollection);
    }

    public BoardCollection read() throws IOException {

            return objectMapper.readValue(new File(filePath), BoardCollection.class);
    }
}
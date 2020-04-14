package mower.io;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mower.model.CommandType;
import mower.model.Lawn;
import mower.model.Mower;
import mower.model.Orientation;
import mower.model.Position;

public class InputFileParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(InputFileParser.class);

    private String fileName;

    public InputFileParser(String fileName) {
        this.fileName = fileName;
    }

    public Lawn parse() throws Exception {
        BufferedReader reader = createReader();

        Lawn lawn = createLawnFromLine(reader.readLine());

        boolean needToContinue = true;

        while (needToContinue) {
            Mower mower = parseMowerInformations(reader);

            if (mower == null) {
                needToContinue = false;
            } else {
                lawn.addMower(mower);
            }
        }

        return lawn;
    }

    private BufferedReader createReader() throws Exception {
        Path path = Paths.get(fileName);

        return Files.newBufferedReader(path);
    }

    private Mower parseMowerInformations(BufferedReader reader) throws Exception {
        String mowerAsString = reader.readLine();
        String commandsAsString = reader.readLine();

        if (mowerAsString == null || commandsAsString == null) {
            return null;
        }

        Mower mower = createMowerFromLine(mowerAsString);
        mower.setCommands(createCommandsFromLine(commandsAsString));

        LOGGER.debug("New mower {}", mower);

        return mower;
    }

    private Mower createMowerFromLine(String line) {
        String[] split = line.split(" ");

        return new Mower(
                new Position(
                        Integer.valueOf(split[0]),
                        Integer.valueOf(split[1]),
                        Orientation.fromValue(split[2])));
    }

    private Lawn createLawnFromLine(String line) {
        String[] split = line.split(" ");

        Lawn lawn = new Lawn(
                Integer.valueOf(split[0]),
                Integer.valueOf(split[1]));

        LOGGER.debug("Lawn size : {}x{}", lawn.getWidth(), lawn.getHeight());

        return lawn;
    }

    private List<CommandType> createCommandsFromLine(String line) {
        return line
            .chars()
            .mapToObj(charAsInt -> Character.valueOf((char) charAsInt))
            .map(CommandType::retrieveByLetter)
            .collect(toList());
    }

}

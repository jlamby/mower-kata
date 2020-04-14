package mower.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mower.model.Lawn;
import mower.model.Mower;

public class OutputFileWriter {

    private static final Logger LOGGER = LoggerFactory.getLogger(OutputFileWriter.class);

    private String fileName;

    public OutputFileWriter(String fileName) {
        this.fileName = fileName;
    }

    public void write(Lawn lawn) throws IOException {
        BufferedWriter writer = createWriter();

        for (Mower mower : lawn.getMowers()) {
            writer.write(mower.getPosition().toString());
            writer.newLine();
        }

        writer.close();

        LOGGER.info("Output file written !");
    }

    private BufferedWriter createWriter() throws IOException {
        return new BufferedWriter(new FileWriter(fileName));
    }
}

package mower;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mower.io.InputFileParser;
import mower.io.OutputFileWriter;
import mower.model.Lawn;
import mower.model.Mower;
import mower.service.MowingProcessor;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        InputFileParser inputFileParser = new InputFileParser("src/main/resources/input.txt");

        Lawn lawn = inputFileParser.parse();

        MowingProcessor mowingProcessor = new MowingProcessor();
        mowingProcessor.start(lawn);

        while (mowingProcessor.isRunning()) {
            LOGGER.debug("Running ....");
        }

        for (Mower m : lawn.getMowers()) {
            LOGGER.info("Final position of {}", m);
        }

        OutputFileWriter outputFileWriter = new OutputFileWriter("target/output.txt");
        outputFileWriter.write(lawn);
    }

}

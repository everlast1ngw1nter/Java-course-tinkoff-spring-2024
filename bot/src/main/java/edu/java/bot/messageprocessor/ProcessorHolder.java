package edu.java.bot.messageprocessor;

public class ProcessorHolder {

    private ProcessorHolder() {
    }

    public static final AbstractProcessor PROCESSOR = new StartProcessor(
            new HelpProcessor(
                    new TrackProcessor(
                            new UntrackProcessor(
                                    new ListProcessor(
                                            new TrackingSiteProcessor(
                                                    new UntrackingSiteProcessor(
                                                            new UnknownProcessor(null)
                                                    )))))));
}

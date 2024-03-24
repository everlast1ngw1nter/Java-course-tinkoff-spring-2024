package edu.java.bot.messageprocessor;

import edu.java.bot.clients.ScrapperWebClient;

public class ProcessorHolder {

    private ProcessorHolder() {
    }

    public static AbstractProcessor getProcessor(ScrapperWebClient scrapperWebClient) {
        return new StartProcessor(
                new HelpProcessor(
                        new TrackProcessor(
                                new UntrackProcessor(
                                        new ListProcessor(
                                                new TrackingSiteProcessor(
                                                        new UntrackingSiteProcessor(
                                                                new UnknownProcessor(null),
                                                                scrapperWebClient
                                                        ), scrapperWebClient
                                                ), scrapperWebClient
                                        )
                                )
                        )
                ), scrapperWebClient);
    }
}

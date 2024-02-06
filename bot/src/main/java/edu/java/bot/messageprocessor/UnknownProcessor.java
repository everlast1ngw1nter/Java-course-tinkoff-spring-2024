package edu.java.bot.messageprocessor;

public class UnknownProcessor extends AbstractProcessor{
    @Override
    public String process() {
        return "Получена неизвестная команда";
    }
}

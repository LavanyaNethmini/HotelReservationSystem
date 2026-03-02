package com.hotel.reservation.help;

public class HelpContext {

    private HelpStrategy strategy;

    public void setStrategy(HelpStrategy strategy) {
        this.strategy = strategy;
    }

    public String showHelp() {
        return strategy.getHelpContent();
    }
}

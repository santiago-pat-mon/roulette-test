package com.santiagopatino.roulette.roulettetest.domain;

import com.santiagopatino.roulette.roulettetest.Bet;

import java.io.Serializable;
import java.util.ArrayList;

public class Roulette implements Serializable {
    ArrayList<Bet> bets = new ArrayList<Bet>();
    private boolean status;

    public ArrayList<Bet> getBets() {
        return bets;
    }

    public void setBets(ArrayList<Bet> bets) {
        this.bets = bets;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

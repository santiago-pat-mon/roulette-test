package com.santiagopatino.roulette.roulettetest.repository;
import com.santiagopatino.roulette.roulettetest.Bet;
import com.santiagopatino.roulette.roulettetest.domain.Roulette;
import java.util.ArrayList;
import java.util.Map;
public interface RouletteRepositoryInterface {
    Map<String, Roulette> findAllRoulette();
    String createRoulette(Roulette roulette);
    void openRoulette (String id);
    boolean createBet (String id, Bet bet);
    ArrayList<Bet> closeRoulette(String id);
}

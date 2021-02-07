package com.santiagopatino.roulette.roulettetest.repository;


import com.santiagopatino.roulette.roulettetest.Bet;
import com.santiagopatino.roulette.roulettetest.domain.Roulette;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

@Repository
public class RouletteRepository implements RouletteRepositoryInterface {
    private static final String KEY="Roulette";
    private RedisTemplate<String, Roulette> redisTemplateRoulette;
    private HashOperations hashOperations;
    public RouletteRepository(RedisTemplate<String, Roulette> redisTemplateRoulette) {
        this.redisTemplateRoulette = redisTemplateRoulette;
    }
    @PostConstruct
    private void init() {
        hashOperations = redisTemplateRoulette.opsForHash();
    }
    @Override
    public Map<String, Roulette> findAllRoulette() {
        return hashOperations.entries(KEY);
    }
    @Override
    public String createRoulette(Roulette roulette) {
        String id = UUID.randomUUID().toString();
        hashOperations.put(KEY, id, roulette);
        return id;
    }
    @Override
    public void openRoulette(String id) {
        Roulette roulette=findRouletteById(id);
        roulette.setStatus(true);
        updateRoulette(id,roulette);
    }
    @Override
    public boolean createBet(String idRoulette, Bet bet) {
        Roulette roulette = findRouletteById(idRoulette);
        if(bet.getValue()>0 && bet.getValue()<10001 &&
                roulette.isStatus() &&
                bet.getNumber()>=-1 && bet.getNumber()<37 &&
                (bet.getColor().equals("rojo") || bet.getColor().equals("negro") || bet.getColor().equals("")))
        {
            roulette.getBets().add(bet);
            updateRoulette(idRoulette,roulette);
            
            return true;
        }
        else{

            return false;
        }
    }
    @Override
    public ArrayList<Bet> closeRoulette(String id) {
        Roulette roulette=findRouletteById(id);
        ArrayList<Bet> betResult = new ArrayList<Bet>();
        Integer winnerNumber = (int) (Math.random() * (37 - 0)+0);
        Double giveValue = 0.0;
        String winnerColor;
        if(winnerNumber % 2 == 0){
            winnerColor = "rojo";
        }
        else{
            winnerColor = "negro";
        }
        for(Bet bet : roulette.getBets()) {
            if (bet.getNumber()/winnerNumber == 1) {
                giveValue += bet.getValue() * 5;
            }
            if (bet.getColor().equals(winnerColor)) {
                giveValue += bet.getValue() * 1.8;
            }
            bet.setValue(giveValue);
            betResult.add(bet);
            giveValue = 0.0;
        }
        roulette.setStatus(false);
        updateRoulette(id,roulette);
        
        return betResult;
    }
    public void updateRoulette (String id, Roulette roulette){
        hashOperations.put(KEY,id,roulette);
    }
    public Roulette findRouletteById (String id){
        
        return (Roulette)hashOperations.get(KEY,id);
    }
}

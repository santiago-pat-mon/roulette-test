package com.santiagopatino.roulette.roulettetest.controller;


import com.santiagopatino.roulette.roulettetest.Bet;
import com.santiagopatino.roulette.roulettetest.domain.Roulette;
import com.santiagopatino.roulette.roulettetest.repository.RouletteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
public class RouletteController {
    private RouletteRepository rouletteRepository;

    public RouletteController(RouletteRepository rouletteRepository) {
        this.rouletteRepository = rouletteRepository;
    }

    @PostMapping("/roulette/{idRoulette}/createBet")
    public boolean createBet(@RequestBody Bet bet, @RequestHeader ("user") String user, @PathVariable String idRoulette){
        bet.setUser(user);
        return rouletteRepository.createBet(idRoulette,bet);
    }
    @GetMapping("roulette/close/{id}")
    public ArrayList<Bet> closeRoulette(@PathVariable String id){
        return rouletteRepository.closeRoulette(id);
    }
    @GetMapping("/roulette")
    public Map<String, Roulette> findAllRoulette(){
        return rouletteRepository.findAllRoulette();
    }

    @PostMapping("/roulette/create")
    public String createRoulette(@RequestBody Roulette roulette){
        return rouletteRepository.createRoulette(roulette);
    }

    @PostMapping("/roulette/open/{id}")
    public void openRoulette(@PathVariable String id){
        rouletteRepository.openRoulette(id);
    }

}

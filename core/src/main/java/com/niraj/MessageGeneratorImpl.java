package com.niraj;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class MessageGeneratorImpl implements MessageGenerator {

    private final Game game;

    public MessageGeneratorImpl(Game game) {
        this.game = game;
    }

    @PostConstruct
    public void init(){
        log.info("game = {}",game);
    }

    // == bean method ==
    @Override
    public String getMainMessage() {
        return "Number is between " + game.getSmallest() + " and " + game.getBiggest() + ". Guess it: ";
    }

    @Override
    public String getResultMessage() {
        if(game.isGameWon())
        {
            return "You guessed it right! : "+ game.getNumber();
        }else if(game.isGameLost()){
            return "Wasted: "+ game.getNumber();
        }else if(!game.isValidNumberRange()){
            return "Not legal guess";
        }else if(game.getRemainingGuesses() == game.getGuessCount()){
            return "Whats your first guess?";
        }else{
            String direction = "Lower";

            if(game.getGuess() < game.getNumber()){
                direction = "Higher";
            }

            return direction + " than that. Remaining guesses "+ game.getRemainingGuesses();
        }
    }

}

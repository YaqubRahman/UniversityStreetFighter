package game;

import city.cs.engine.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Manages a countdown timer for the game level.
 * <p>
 * Displays the remaining time, updates every second, and triggers level progression
 * when the timer reaches zero. Integrates directly with the game's UI and flow
 * by updating a visible timer label and communicating with {@link GameWorld} and {@link Game}.
 * </p>
 */

public class ClockTimer {
    private int remainingTime = 100;
    private JLabel timerLabel;
    private Timer countdownTimer;
    private World world;
    private GameWorld level;
    private Game game;

    public ClockTimer(GameWorld world, Game game) {
        this.level = world;
        this.game = game;
        timerLabel = setupTimerUI();
        startCountdownTimer();
    }

    private JLabel setupTimerUI(){
        timerLabel = new JLabel("100s");
        timerLabel.setFont(new Font("Arial", Font.BOLD, 30));
        timerLabel.setForeground(Color.RED);
        return timerLabel;
    }

    public JLabel getTimerLabel() {
        return timerLabel;
    }

    private void startCountdownTimer(){
        countdownTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remainingTime--;
                timerLabel.setText(String.valueOf(remainingTime));

                if(remainingTime <= 0){
                    System.out.println("Game Over");
                    game.goToNextLevel();
                    remainingTime = 100;
                }

            }
        });
        countdownTimer.start();
    }

    public void resetTimer() {
        remainingTime = 100;
        timerLabel.setText(String.valueOf(remainingTime));
    }



}

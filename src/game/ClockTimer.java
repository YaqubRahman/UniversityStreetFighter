package game;

import city.cs.engine.UserView;
import city.cs.engine.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ClockTimer {
    private int remainingTime = 60;
    private JLabel timerLabel;
    private Timer countdownTimer;
    private World world;

    public ClockTimer(World world) {
        this.world = world;
        timerLabel = setupTimerUI();
        startCountdownTimer();
    }

    private JLabel setupTimerUI(){
        timerLabel = new JLabel("60s");
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
                timerLabel.setText(remainingTime + "s");

                if(remainingTime <= 0){
                    System.out.println("Game Over would be here");
                }
            }
        });
        countdownTimer.start();
    }



}

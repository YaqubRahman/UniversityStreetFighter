package game;

import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import javax.swing.*;

import java.awt.*;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Your main game entry point
 */
public class Game {
    private JFrame frame;
    private GameWorld level;
    private GameView view;
    private StudentController controller;

    private static final String background_music = "data/GameMusic.wav";

    /** Initialise a new Game. */
    public Game() {
        level = new Level1(this);



        //3. make a view to look into the game world
        // UserView view = new UserView(world, 500, 500);
        view = new GameView(level, 700, 300, this);

        UserView wideview = new UserView(level, 700, 300);
        level.addStepListener(new CameraTracker(view, level.getStudent(), level.getStudent2() ));

        wideview.setZoom(3);


        // Calls an instance of the SoundHandler class and passes in teh background_music and sets the game loop boolean to true
        SoundHandler.playSound(background_music, true);
        controller = new StudentController(level.getStudent(), level.getStudent2());




        view.addKeyListener(controller);


        //optional: draw a 1-metre grid over the view
        //view.setGridResolution(1);


        //4. create a Java window (frame) and add the game
        //   view to it
        final JFrame frame = new JFrame("City Game");
        frame.add(view);


        // enable the frame to quit the application
        // when the x button is pressed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        // don't let the frame be resized
        frame.setResizable(false);
        // size the frame to fit the world view
        frame.pack();
        // finally, make the frame visible
        frame.setVisible(true);



        //optional: uncomment this to make a debugging view
        JFrame debugView = new DebugViewer(level, 700, 300);

        // start our game world simulation!
        level.start();

        view.requestFocus();
        view.setFocusable(true);
        view.requestFocusInWindow();

    }

    public void goToNextLevel() {
        SoundHandler.stopCurrentMusic();
        int previousTotalCoinsStudent = level.getStudent().getTotalCoins();
        int previousTotalCoinsStudent2 = level.getStudent2().getTotalCoins();
        if (level instanceof Level1) {
            level.stop();
            JOptionPane.showMessageDialog(frame, "End of first level!\n Winner is:" + ((Level1) level).getWinner());
            level = new Level2(this);
            // level now refer to the new level
            view.setWorld(level);
            controller.updateStudent(level.getStudent(), level.getStudent2());
            level.addStepListener(new CameraTracker(view, level.getStudent(), level.getStudent2()));
            level.getStudent().addToTotalCoins(previousTotalCoinsStudent);
            level.getStudent2().addToTotalCoins(previousTotalCoinsStudent2);
            level.start();
            ((GameView) view).getClockTimer().resetTimer();
            System.out.println("Second Level!!");
        } else if (level instanceof Level2) {
            level.stop();
            JOptionPane.showMessageDialog(frame, "End of second level!\n Winner is:" + ((Level2) level).getWinner());
            level = new Level3(this);
            // level now refer to the new level
            view.setWorld(level);
            controller.updateStudent(level.getStudent(), level.getStudent2());
            level.addStepListener(new CameraTracker(view, level.getStudent(), level.getStudent2()));
            level.getStudent().addToTotalCoins(previousTotalCoinsStudent);
            level.getStudent2().addToTotalCoins(previousTotalCoinsStudent2);
            level.start();
            ((GameView) view).getClockTimer().resetTimer();
            System.out.println("Third Level!!");
        } else if (level instanceof Level3) {
            level.getStudent().addToTotalCoins(previousTotalCoinsStudent);
            level.getStudent2().addToTotalCoins(previousTotalCoinsStudent2);
            System.out.println("Well done! Game complete.");
            JOptionPane.showMessageDialog(frame, "End of third level!\n Winner is:" + ((Level3) level).getWinner());
            JOptionPane.showMessageDialog(frame, "End of game! \nRight Player Total Coins:" + level.getStudentTotalCoins() + "\nLeft Player Total Coins:" + level.getStudent2TotalCoins());
            System.exit(0);
        }
    }

    /** Run the game. */
    public static void main(String[] args) {

        new Game();
    }

}
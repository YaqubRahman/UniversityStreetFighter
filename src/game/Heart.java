package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Heart extends Walker {
    private static final Shape heartShape = new BoxShape(0.5F,0.5F );
    private static BodyImage image = new BodyImage("data/Heart.gif");

    public Heart(World world){
        super(world, heartShape);
        addImage(image);
    }

    @Override
    public void destroy(){
        super.destroy();
    }





}

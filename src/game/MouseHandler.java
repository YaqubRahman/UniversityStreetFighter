package game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {

    private GameWorld world;
    private GameView view;

    public MouseHandler(GameWorld w, GameView v) {
        world = w;
        view = v;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //create a round ball object
        Shape circleShape = new CircleShape(1f);
        DynamicBody ball = new DynamicBody(world, circleShape);

        //get the coordinates of the mouse click - these are in
        //pixels (the location in the window where the click happened)
        Point mousePoint = e.getPoint();

        //the ball though needs to be added at *world* coordinates which
        //are in meters. So, we transform mouse coordinates into
        //world coordinates using a method provided by the view class:
        Vec2 worldPoint = view.viewToWorld(mousePoint);
        ball.setPosition(worldPoint);


    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
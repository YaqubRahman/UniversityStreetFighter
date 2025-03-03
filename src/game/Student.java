package game;

import city.cs.engine.*;

public class Student extends Walker {
    private static final Shape studentShape1 = new PolygonShape(0.0f,1.06f, 0.46f,0.63f, 0.5f,-0.59f, 0.54f,-1.9f, -0.56f,-1.91f, -0.44f,0.78f);
    private static final BodyImage image1 = new BodyImage("data/Ahmad1.png", 7f);


    private int credits;

    public Student(World world) {
        super(world, studentShape1);
        addImage(image1);
        credits = 0;
    }




    public int getCredits(){
        return credits;
    }

    public void setCredits(int credits){
        this.credits = credits;
    }
}

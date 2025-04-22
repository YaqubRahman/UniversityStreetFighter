package game;

import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import org.jbox2d.common.Vec2;

public class CameraTracker implements StepListener {
    private GameView gameView;
    private Student student;
    private Student2 student2;

    public CameraTracker(GameView gameView, Student student, Student2 student2) {
        this.gameView = gameView;
        this.student = student;
        this.student2 = student2;
    }

    @Override
    public void preStep(StepEvent e) {
        // Get both players' positions
        Vec2 pos1 = student.getPosition();
        Vec2 pos2 = student2.getPosition();

        // Calculate the midpoint between the two players
        float midX = (pos1.x + pos2.x) / 2;
        float midY = (pos1.y + pos2.y) / 2;

        Vec2 midpoint = new Vec2(midX, 0);

        // Set the camera center to the midpoint
        gameView.setCentre(midpoint);
    }

    @Override
    public void postStep(StepEvent e) {}


}

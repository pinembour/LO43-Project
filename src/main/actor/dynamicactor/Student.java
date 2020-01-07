package main.actor.dynamicactor;

import main.actor.staticactor.Chair;
import main.actor.staticactor.Computer;
import main.maps.TiledMap;
import main.math.Vector2;
import main.graphics.Color;
import main.utiles.Constants;

public class Student extends Character {

    private boolean isRegistered;
    private boolean isCounted;

    // le student a un objectif des son apparition

    public Student(int x, int y, Computer computer, TiledMap map) {
        super(x, y, map);
        hasAGoal = true;
        this.chair = computer.getStudentChair();
        goalPoint = new Vector2<Integer>(chair.getCurrentTile().getX() - 1, chair.getCurrentTile().getY());
        chair.setChairState(Chair.ChairState.RESERVED);
        computer.setStudent(this);
    }


    public void update() {

        // est inscrit et devant la sortie
        if (isRegistered &&
                this.currentTile.getY() + 1 == Constants.STUDENT_DESPAWN_Y) {
            removed = true;
        }

        goalManagement();

        if (!hasAGoal && !isRegistered) {
            setSit(true);
            position.setX(chair.getX());
            position.setY(chair.getY());
            chair.setChairState(Chair.ChairState.OCCUPIED);
        }

        // a fini son inscription ?
        if (isRegistered && !hasAGoal) {
            setSit(false);
            position.setX(chair.getX() - Constants.TILE_SIZE);
            position.setY(chair.getY());
            goalPoint = new Vector2<Integer>(Constants.STUDENT_DESPAWN_X, Constants.STUDENT_DESPAWN_Y);
            hasAGoal = true;
        }
    }

    public void render() {
        renderCharacter(Color.GREEN);
    }

    public boolean isRegistered() {
        return isRegistered;
    }

    public void setRegistered(boolean registered) {
        isRegistered = registered;
    }

    public boolean isCounted() {
        return isCounted;
    }

    public void setCounted(boolean counted) {
        isCounted = counted;
    }
}
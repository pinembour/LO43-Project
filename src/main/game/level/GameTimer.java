package main.game.level;

import main.graphics.Color;
import main.graphics.Renderer;
import main.utiles.Constants;

import java.util.concurrent.TimeUnit;

public class GameTimer extends Timer{
    private long timeSinceStart;


    public GameTimer(){
        super();
        timeSinceStart=0l;
    }

    public long getTimeSinceStart() {
        timeSinceStart = this.getCurrent()-start;
        return timeSinceStart;
    }

    public void render(){
        String time = this.toString();
        int length = time.length();
        Renderer.drawText(this.toString(), Constants.WINDOW_WIDTH/2-(length*Constants.HUD_FONT_SIZE),25,Constants.HUD_FONT_SIZE, Color.BLACK);
    }

    public String toString(){
        return String.valueOf(TimeUnit.MILLISECONDS.toSeconds(getTimeSinceStart()));
    }
}

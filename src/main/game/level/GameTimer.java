package main.game.level;

import main.graphics.Color;
import main.graphics.Renderer;
import main.utiles.Constants;

import java.util.concurrent.TimeUnit;

public class GameTimer extends Timer{
    private long timeSinceStart;
    private int timeLimit = Constants.TIME_LIMIT;


    public GameTimer(){
        super();
        timeSinceStart=0l;
    }

    public long getTimeSinceStart() {
        timeSinceStart = this.getCurrent()-start;
        return timeSinceStart;
    }

    @Override
    public void stopPause() {
        super.stopPause();
        start = start + (pauseTime);
    }

    public void render(){
        String time = ""+ (timeLimit-this.toSeconds());
        int length = time.length();


        if ((timeLimit-this.toSeconds()) > 20 ){
            if ( (timeLimit-this.toSeconds()) %  20 == 0 ){
                Renderer.drawText(time, Constants.WINDOW_WIDTH / 2 - (length * Constants.HUD_FONT_SIZE), Constants.HUD_LIGNE_TOP,
                        Constants.HUD_FONT_SIZE, Color.RED);
            }else {
                Renderer.drawText(time, Constants.WINDOW_WIDTH / 2 - (length * Constants.HUD_FONT_SIZE), Constants.HUD_LIGNE_TOP,
                        Constants.HUD_FONT_SIZE, Color.BLACK);
            }
        }else {
            if ( (timeLimit-this.toSeconds()) %  2 == 0 ){
                Renderer.drawText(time, Constants.WINDOW_WIDTH / 2 - (length * Constants.HUD_FONT_SIZE), Constants.HUD_LIGNE_TOP,
                        Constants.HUD_FONT_SIZE, Color.RED);
            }else {
                Renderer.drawText(time, Constants.WINDOW_WIDTH / 2 - (length * Constants.HUD_FONT_SIZE), Constants.HUD_LIGNE_TOP,
                        Constants.HUD_FONT_SIZE, Color.BLACK);
            }
        }

    }
    public boolean isOver(){
        return toSeconds() >= timeLimit;
    }

    public long toSeconds(){
        return TimeUnit.MILLISECONDS.toSeconds(getTimeSinceStart());
    }
    public String toString(){
        return String.valueOf(toSeconds());
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }
}

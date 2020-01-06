package main.game.level;
import main.utiles.Constants;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Timer {
    private long start;
    private long current;
    private int eventPeriod = Constants.EVENT_PERIOD;
    private Random rand;
    private long pauseTime;

    public Timer() {
        current = 0;
        pauseTime = 0;
        rand = new Random();
        this.start = System.currentTimeMillis();
        eventPeriod = (int) (eventPeriod*rand.nextDouble())+eventPeriod;
    }
    public void startPause(){
        pauseTime = System.currentTimeMillis();
    }
    public void stopPause(){
        pauseTime = System.currentTimeMillis()/1000 - pauseTime;
    }

    public boolean IsEventNow(){
        current = System.currentTimeMillis();
        long duration = TimeUnit.MILLISECONDS.toSeconds(current-start-pauseTime);
        if (duration > eventPeriod) {start = System.currentTimeMillis(); return true;}
        else return false;
    }
    public long getStart() {
        return start;
    }

    public long getCurrent() {
        return current;
    }

}

package main.game.level;
import main.utiles.Constants;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class EventTimer extends Timer {
    private int eventPeriod = Constants.EVENT_PERIOD;
    private Random rand;


    public EventTimer() {
        super();
        rand = new Random();
        eventPeriod = (int) (eventPeriod*rand.nextDouble())+eventPeriod;
    }

    public boolean IsEventNow(){
        current = System.currentTimeMillis();
        long duration = TimeUnit.MILLISECONDS.toSeconds(current-start-pauseTime);
        if (duration > eventPeriod) {start = System.currentTimeMillis(); return true;}
        else return false;
    }

}

package main.game.level;

public class Timer {
    protected long start;
    protected long current;
    protected long pauseTime;

    public Timer() {
        current = 0;
        pauseTime = 0;
        this.start = System.currentTimeMillis();
    }
    public void startPause(){
        pauseTime = System.currentTimeMillis();
    }
    public void stopPause(){
        pauseTime = System.currentTimeMillis()/1000 - pauseTime;
    }


    public long getStart() {
        return start;
    }

    public long getCurrent() {
        current = System.currentTimeMillis();
        return current;
    }

}

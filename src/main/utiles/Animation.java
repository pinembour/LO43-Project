package main.utiles;

public class Animation {

    private int frame = 0;
    private int length;
    private int speed;
    private boolean loop = false;

    private boolean playing = false;
    private int time = 0;

    public Animation(int length, int speed, boolean loop){
        this.length = length;
        this.speed = speed;
        this.loop = loop;
    }

    public void update(){
        if (playing){
            time++;
            if (time > speed){
                time = 0;
                frame++;
                if(frame >= length){
                    if (loop){
                        frame = 0 ;
                    }else {
                        frame = length - 1;
                    }
                }
            }
        }
    }

    public void play(){
        playing= true;
    }

    public void pause(){
        playing = false;
        frame = 0 ; // On remet a zero
    }

    public int getCurrentFrame(){
        return frame;
    }
}

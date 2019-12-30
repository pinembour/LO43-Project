package main.game;

public class Player {

    int gold;

    public Player(){
        gold = 0;
    }

    public void update(){
    }

    public void render(){

    }

    public void addGold(int gold){
        this.gold += gold;
    }

    public void removeGold(int gold){
        this.gold -= gold;
    }

    @Override
    public String toString() {
        return "Player{" +
                "gold=" + gold +
                '}';
    }
}

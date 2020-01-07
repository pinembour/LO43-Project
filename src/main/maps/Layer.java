package main.maps;

import main.utiles.Constants;

import java.util.ArrayList;
import java.util.List;

public class Layer {
    String name;
    int id;
    int width;
    int height;
    List<Integer> gids = new ArrayList<>();


    public int getGid(int index) {
        return this.gids.get(index);
    }

    public List<Integer> getGids() {
        return gids;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void printLayer(){
        System.out.println("------Affichage Layer ---------");
        for (int i = 0 ; i<gids.size() ;i++){
            if (gids.get(i) == 0 ){
                System.out.print(" " + gids.get(i) + " ");
            }else {
                System.out.print(gids.get(i) );
            }
            System.out.print(" ");


            if ((i+1 )% (Constants.HORIZONTAL_TILES) == 0 ){
                System.out.println("  ");
            }
        }
        System.out.println("--------------");

    }

}

package main.maps;

import java.util.ArrayList;
import java.util.List;

public class Layer {
    String name;
    int id;
    int width;
    int height;
    List<Integer> gids = new ArrayList<>();

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

}

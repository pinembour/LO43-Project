package main.maps;

import java.util.ArrayList;
import java.util.List;

public class TiledMap {
    int width;
    int height;
    int tileWidth;
    int tileHeight;
    List<TileSet> sets = new ArrayList<>();
    List<Layer> layers = new ArrayList<>();

    public TileSet getSet(int index){
        return this.sets.get(index);
    }

    public TileSet getGidsSet(int gid){
        TileSet set = this.sets.get(0);
        int i=1;
        while(set.lastGid< gid){
            set = this.sets.get(i);
            i++;
        }
        return set;
    }

    public Layer getLayer(int index){
        return this.layers.get(index);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public int getLayersSize(){
        return layers.size();
    }
}

package main.maps;

import main.math.Vector2;

public class TileSet {
    int firstGid;
    int lastGid;
    String name;
    int tileWidth;
    int tileHeight;
    Image image;
    int tileAmountWidth;

    public Vector2 getPosition(int index){
        index = index-firstGid;
        int nbTilesHorizontal = this.image.getImageWidth()/this.tileWidth;
        int x,y;
        x = index%nbTilesHorizontal;
        y = index/nbTilesHorizontal;
        Vector2 position = new Vector2(x,y);
        return position;
    }

    public int getFirstGid() {
        return firstGid;
    }

    public int getLastGid() {
        return lastGid;
    }

    public String getName() {
        return name;
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public Image getImage() {
        return image;
    }

    public int getTileAmountWidth() {
        return tileAmountWidth;
    }
}

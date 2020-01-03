package main.maps;

import main.math.Vector2f;

public class TileSet {
    int firstGid;
    int lastGid;
    String name;
    int tileWidth;
    int tileHeight;
    Image image;
    int tileAmountWidth;

    public Vector2f getPosition(int index){
        int nbTilesHorizontal = this.image.getImageHeight()/this.tileHeight;
        int x,y;
        x = index%nbTilesHorizontal;
        y = index/nbTilesHorizontal;
        Vector2f position = new Vector2f(x,y);
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

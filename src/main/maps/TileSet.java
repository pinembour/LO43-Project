package main.maps;

public class TileSet {
    int firstGid;
    int lastGid;
    String name;
    int tileWidth;
    int tileHeight;
    Image image;
    int tileAmountWidth;

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

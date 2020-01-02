package main.maps;

public class TileSet {
    int firstGid;
    int lastGid;
    String name;
    int tileWidth;
    int tileHeight;
    Image image;
    int tileAmountWidth;

    public TileSet(int firstGid, String name, int tileWidth, int tileHeight, Image image)
    {
        this.firstGid = firstGid;
        this.name = name;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.image = image;
        this.tileAmountWidth = (int) Math.floor(image.imageWidth / tileWidth);
        this.lastGid = (int) (tileAmountWidth * Math.floor(image.imageHeight / tileHeight) + firstGid - 1);
    }

    public TileSet() {

    }
}

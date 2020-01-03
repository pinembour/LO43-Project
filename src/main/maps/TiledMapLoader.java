package main.maps;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Java DOM Parser to Read XML File in Java, from https://www.javaguides.net/2018/10/how-to-read-xml-file-in-java-dom-parser.html
 * @author pinembour
 *
 */

public class TiledMapLoader {
    private String filePath;

    public TiledMapLoader(String filePath) {
        this.filePath = filePath;
    }

    public TiledMap load() {
        TiledMap map = new TiledMap();
        File xmlFile = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            Element root = doc.getDocumentElement();
            // now XML is loaded as Document in memory, lets convert it to Object List
            map = getMap(root);
            NodeList nodeList = doc.getElementsByTagName("tileset");
            for (int i = 0; i < nodeList.getLength(); i++) {
                map.sets.add(getSet(nodeList.item(i)));
            }
            nodeList = doc.getElementsByTagName("layer");
            for (int i = 0; i < nodeList.getLength(); i++) {
                map.layers.add(getLayer(nodeList.item(i)));
            }
        } catch (SAXException | ParserConfigurationException | IOException e1) {
            e1.printStackTrace();
        }
        System.out.println("map imported <3");
        return map;
    }

    private static TiledMap getMap(Element root) {
        TiledMap map = new TiledMap();
        //if (mapNode.getNodeType() == Node.ELEMENT_NODE) {
            map.width = Integer.parseInt(root.getAttribute("width"));
            map.height = Integer.parseInt(root.getAttribute("height"));
            map.tileHeight = Integer.parseInt(root.getAttribute("tileheight"));
            map.tileWidth = Integer.parseInt(root.getAttribute("tilewidth"));
        //}
        return map;
    }

    private static TileSet getSet(Node tilesetNode) {
        TileSet set = new TileSet();
        if (tilesetNode.getNodeType() == Node.ELEMENT_NODE) {
            Element tilesetElement = (Element) tilesetNode;
            set.firstGid =  Integer.parseInt(tilesetElement.getAttribute("firstgid"));
            set.name = tilesetElement.getAttribute("name");
            set.tileHeight = Integer.parseInt(tilesetElement.getAttribute("tileheight"));
            set.tileWidth = Integer.parseInt(tilesetElement.getAttribute("tilewidth"));
            NodeList imageList = tilesetElement.getElementsByTagName("image");
            Node imageNode = imageList.item(0);
            Element image = (Element) imageNode;
            set.image = new Image();
            set.image.source = image.getAttribute("source");
            set.image.imageHeight = Integer.parseInt(image.getAttribute("height"));
            set.image.imageWidth = Integer.parseInt(image.getAttribute("width"));
            set.tileAmountWidth = (int) Math.floor(set.image.imageWidth / set.tileWidth);
            set.lastGid = (int) (set.tileAmountWidth * Math.floor(set.image.imageHeight / set.tileHeight) + set.firstGid - 1);
        }
        return set;
    }


    private static Layer getLayer(Node layerNode) {
        Layer layer = new Layer();
        if (layerNode.getNodeType() == Node.ELEMENT_NODE) {
            Element layerElement = (Element) layerNode;
            layer.id =  Integer.parseInt(layerElement.getAttribute("id"));
            layer.name = layerElement.getAttribute("name");
            layer.width =  Integer.parseInt(layerElement.getAttribute("width"));
            layer.height =  Integer.parseInt(layerElement.getAttribute("height"));
            NodeList dataList = layerElement.getElementsByTagName("data");
            Node dataNode = dataList.item(0);
            Element data = (Element) dataNode;
            NodeList tileList = data.getElementsByTagName("tile");
            for (int i=0; i<tileList.getLength();i++){
                Node tileNode = tileList.item(i);
                Element tile = (Element) tileNode;
                if (tile.hasAttribute("gid")){
                    layer.gids.add(Integer.parseInt(tile.getAttribute("gid")));
                } else { layer.gids.add(0);}
            }
        }
        return layer;
    }

}

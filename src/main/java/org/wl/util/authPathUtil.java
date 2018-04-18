package org.wl.util;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class authPathUtil {
    private static DocumentBuilderFactory dbFactory = null;
    private static DocumentBuilder db = null;
    static{
        try {
            dbFactory = DocumentBuilderFactory.newInstance();
            db = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static Map<String,String> getAuthConfig(InputStream in) throws IOException, SAXException {
        Map<String,String> map = new HashMap<String,String>();
        Document document = db.parse(in);
        NodeList list = document.getElementsByTagName("http");
        if(list.getLength()>0){
            for(int i =0;i<list.getLength();i++){
                Map<String,String> cMap = new HashMap<String,String>();
                Node node = list.item(i);
                if(node.hasChildNodes()){
                    NodeList cNodeList = node.getChildNodes();
                    //子节点集合中包含了换行，所以循环时要加2
                    for(int j=1;j<cNodeList.getLength();j+=2){
                        Node cNode = cNodeList.item(j);
                        if(cNode.hasChildNodes()){
                            cMap.put(cNode.getNodeName(),cNode.getFirstChild().getTextContent());
                        }
                    }
                }
                System.out.println(cMap.size());
                map.put(cMap.get("pattern"),cMap.get("access"));
            }
        }
        return map;
    }
}

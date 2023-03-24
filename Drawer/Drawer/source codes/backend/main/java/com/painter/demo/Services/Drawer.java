package com.painter.demo.Services;
import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import com.painter.demo.shapes.*;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

@Component
public class Drawer
{
    int currentLevel = 0;
    int undo=0;
    ArrayList<ArrayList<Ishape>> History = new ArrayList<>();

    Factory factory = new Factory();

    public boolean areTheSame(Ishape currentShape, Ishape addedShape){

          if (!currentShape.getFirst().equals(addedShape.getFirst())){return false;}
          else if (!currentShape.getSecond().equals(addedShape.getSecond())) {return false;}
          else if (!currentShape.getThird().equals(addedShape.getThird())) {return false;}
          else if (!currentShape.getFourth().equals(addedShape.getFourth())) {return false;}
          else if (!currentShape.getFifth().equals(addedShape.getFifth())) {return false;}
          else if (!currentShape.getSixth().equals(addedShape.getSixth())) {return false;}
          else if (!currentShape.getSeventh().equals(addedShape.getSeventh())) {return false;}
          else if (!currentShape.getEight().equals(addedShape.getEight())) {return false;}
          else if (!currentShape.getNinth().equals(addedShape.getNinth())) {return false;}
          else if (!currentShape.getTenth().equals(addedShape.getTenth())) {return false;}
          else if (!currentShape.getEleventh().equals(addedShape.getEleventh())) {return false;}
          else if (!currentShape.getTwelfth().equals(addedShape.getTwelfth())) {return false;}
          else if (!currentShape.getThrteen().equals(addedShape.getThrteen())) {return false;}
          return true;


    }
    public void test(){
        Ishape square= factory.createShape("Square");
        square.setFirst("Square");
        square.setSecond("2");
        square.setThird("3");
        square.setFourth("nagui");
        square.setFifth("5");
        square.setSixth("6");
        square.setSeventh("7");
        square.setEight("8");
        square.setNinth("9");

            Ishape square1= factory.createShape("Square");
            square.setFirst("Square");
            square.setSecond("2");
            square.setThird("3");
            square.setFourth("nagui");
            square.setFifth("5");
            square.setSixth("6");
            square.setSeventh("7");
            square.setEight("8");
            square.setNinth("9");

        ArrayList<Ishape> e=new ArrayList<>();
        e.add(square);
        e.add(square1);
        History.add(e);
        if (!square.equals(square1)){
            System.out.println("works");
        }


    }
    public void addShape(String type, jsonShape data)
    {
        Ishape sentShape = factory.createShape(type); // FactorJob is done
        sentShape.handle(data); // Done
        storeInContainer(sentShape);
    }
    ArrayList<Ishape> formatter(){
        ArrayList<Ishape> empty=new ArrayList<>();
        if(currentLevel==0){return empty;}
            System.out.println(History.get(currentLevel-1));

             return History.get(currentLevel-1);

    }
    public ArrayList<Ishape> undo()
    {
        ArrayList<Ishape> empty=new ArrayList<>();
        if(currentLevel==0){return empty;}
        undo++;

        currentLevel--;
        System.out.println(History);
        return formatter();
    }
    public ArrayList<Ishape> redo()
    {   if (undo>0){
        currentLevel++;
        undo--;
        return formatter();
    }
    else {
        return formatter();
    }


    }
    public ArrayList<Ishape> copy(IDs ID)
    {
        String oldID, newID;
        oldID = ID.ID; newID = ID.newID;
        jsonShape toBeReturned;
        for(Ishape iterator: History.get(currentLevel-1))
        {
            if(iterator.getSecond().equals(oldID))
            {
                Ishape sentShape = iterator.clone(iterator);
                sentShape.setSecond(newID);
                ArrayList state = new ArrayList<Ishape>() ;
                ArrayList buffer = new ArrayList<Ishape>() ;
                currentLevel++ ;
                if(currentLevel!=1)
                    buffer = History.get(currentLevel-2);
                for(Object it: buffer){
                    state.add(it);
                }
                state.add(sentShape);
                History.add(currentLevel-1, state);
                System.out.println(sentShape.getSecond());
                System.out.println(History);
                return state;
            }
        }
        return null;
    }


    public void storeInContainer(Ishape toBeSaved)
    {
        ArrayList state = new ArrayList<Ishape>() ;
        ArrayList buffer = new ArrayList<Ishape>() ;
        currentLevel++ ;
        if(currentLevel!=1)
            buffer = History.get(currentLevel-2);
        for(Object iterator: buffer){

            Ishape currentShape=(Ishape) iterator;
            if (areTheSame(currentShape,toBeSaved)){currentLevel--; return;}



        }

        for(Object iterator: buffer){

            Ishape checker=(Ishape) iterator;
            if(toBeSaved.getSecond().equals(checker.getSecond())){continue;}//same ID with different attributes
            state.add(iterator);

        }



        state.add(toBeSaved);
        History.add(currentLevel-1, state);
        //System.out.println(History);
    }



    public void eraseAll()
    {
        ArrayList<Ishape> temp = new ArrayList<>();
        Ishape zero_array=new Rectangle();
        zero_array.setFirst("error");
        temp.add(zero_array);
        currentLevel++;
        History.add(currentLevel-1,temp);
        return ;
    }


    public void eraseOne(String ID)
    {
        ArrayList state = new ArrayList<Ishape>() ;

        for(Ishape iterator: History.get(currentLevel-1))
        {
            if(iterator.getSecond().equals(ID)) {}
            else state.add(iterator);
        }
        currentLevel++ ;
        History.add(currentLevel-1, state);
        return;
    }
    public ArrayList<Ishape> Error(){
        Ishape Error= factory.createShape("Square");
        Error.setFirst("Error");
        ArrayList<Ishape> Errors=new ArrayList<>();
        Errors.add(Error);
        return Errors;
    }
    public ArrayList<Ishape> Empty(){
        ArrayList<Ishape> empty=new ArrayList<>();
        return empty;
    }
    public ArrayList<Ishape> save(String path){
        String[] decoder=path.split("\\+");
        String filePath=decoder[0];
        String fileName=decoder[1];
        String fileType=decoder[2];
        switch (fileType){
            case "json":return save_json(filePath,fileName);
            case "xml":return save_xml(filePath,fileName);

        }

        return Empty();
    }
    public  ArrayList<Ishape> save_json(String jsonFilePath,String fileName){
        String[] splitter=jsonFilePath.split("/");
        String file_name=splitter[splitter.length-1];

        if(!file_name.contains(".json")){
            jsonFilePath=jsonFilePath+"/"+fileName+".json";
        }
        Path JsonFilePath= Paths.get(jsonFilePath);
        if (History.isEmpty()){ File jsonData= new File(jsonFilePath);
            try {
                FileOutputStream outputStream=new FileOutputStream(jsonData);
                return Empty();

            }
            catch (FileNotFoundException e){return Error();}



        }
        ArrayList<Ishape> shapes=History.get(History.size()-1);
        File jsonData =new File(jsonFilePath);
        JsonArray jsonArray=new JsonArray();
        for (Ishape shape : shapes){
            jsonArray.add(shape.tojsonobject());
        }
        String jsonText= Jsoner.serialize(jsonArray);
        try {
            Files.write(JsonFilePath,jsonText.getBytes(), StandardOpenOption.CREATE);

        }
        catch (IOException e){
            return Error();
        }
        System.out.println(shapes.get(0));
        return Empty();
    }
    public ArrayList<Ishape> load_json(String jsonFilePath){
        Path JsonFilePath= Paths.get(jsonFilePath);
        ArrayList<Ishape> shapes=new ArrayList<>();
        String jsonText=null;
        JsonArray jsonarray=null;
        File jsonFile=new File(jsonFilePath);
        if (jsonFile.length()==0){History.clear(); currentLevel=0;return shapes;}
        try {
            jsonText = new String(Files.readAllBytes(JsonFilePath));
        }
        catch (IOException e){
            return Error();
        }
        try {
            jsonarray = (JsonArray) Jsoner.deserialize(jsonText);
        }
        catch (JsonException e){
            return Error();
        }
        for (Object object : jsonarray){
            JsonObject jsonObject=(JsonObject) object;
            String name=(String) jsonObject.get("first");
            Ishape shape= factory.createShape(name);
            shapes.add(shape.fromjsonObject(jsonObject));
        }
        System.out.println(shapes.get(0).getSecond());
        System.out.println(shapes.get(0).getFirst());
        History.clear();History.add(shapes);
        currentLevel=1;
        return shapes;
    }
    public ArrayList<Ishape> save_xml(String xmlFilePath,String fileName)  {
        String[] splitter=xmlFilePath.split("/");
        String file_name=splitter[splitter.length-1];
        if(!file_name.contains(".xml")){
            xmlFilePath=xmlFilePath+"/"+fileName+".xml";
        }
        System.out.println(xmlFilePath);
        if (History.isEmpty()){ File xmlData= new File(xmlFilePath);
            try {
                FileOutputStream outputStream=new FileOutputStream(xmlData);
                return Empty();

            }
            catch (FileNotFoundException e){return Error();}



        }
        ArrayList<Ishape> shapes=History.get(History.size()-1);

        DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder=documentBuilderFactory.newDocumentBuilder();
            Document xmlDoc=documentBuilder.newDocument();
            Element rootElement=xmlDoc.createElement("Shapes");
            xmlDoc.appendChild(rootElement);
            for (int i=0;i<shapes.size();i++){
                Element mainElement=xmlDoc.createElement("Shape");
                rootElement.appendChild(mainElement);
                Ishape shape= shapes.get(i);
                shape.ObjectToXml(xmlDoc,mainElement);
            }
            OutputFormat outputFormat=new OutputFormat(xmlDoc);
            outputFormat.setIndenting(true);
            File xmlData= new File(xmlFilePath);
            try {
                FileOutputStream outputStream=new FileOutputStream(xmlData);
                XMLSerializer serializer=new XMLSerializer(outputStream,outputFormat);
                try {
                    serializer.serialize(xmlDoc);
                    return Empty();
                }
                catch (IOException e){return Error();}
            }
            catch (FileNotFoundException e){return Error();}
        }
        catch (ParserConfigurationException e){return Error();}
    }
    public  ArrayList<Ishape> load_xml(String xmlFilePath){
        ArrayList<Ishape> shapes=new ArrayList<>();
        Ishape shape;
        File xmlFile=new File(xmlFilePath);
        if (xmlFile.length()==0){History.clear(); currentLevel=0;return shapes;}
        DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder=documentBuilderFactory.newDocumentBuilder();
            try {
                Document document=documentBuilder.parse(new File(xmlFilePath));
                document.getDocumentElement().normalize();
                NodeList shapes_nodes=document.getElementsByTagName("Shape");
                NodeList names_nodes=document.getElementsByTagName("first");
                for (int i=0;i<shapes_nodes.getLength();i++){
                    Node shape_node=shapes_nodes.item(i);
                    Node name_node=names_nodes.item(i);
                    Element name_element=(Element) name_node;
                    String name=name_element.getAttribute("value");

                    if (shape_node.getNodeType()==Node.ELEMENT_NODE){
                        if (name_node.getNodeType()==Node.ELEMENT_NODE){
                            shape= factory.createShape(name);
                            shapes.add(shape.xmlToObject(shape_node));
                        }
                    }
                }
                System.out.println(shapes.get(0).getSecond());
                System.out.println(shapes.get(0).getFirst());
                History.clear();History.add(shapes);
                currentLevel=1;
                return shapes;
            }
            catch (IOException e){return Error();}
            catch (SAXException e){return Error();}
        }
        catch (ParserConfigurationException e){return Error();}
    }

}

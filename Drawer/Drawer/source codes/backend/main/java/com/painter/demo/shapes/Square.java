package com.painter.demo.shapes;

import com.github.cliftonlabs.json_simple.JsonObject;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@Component
 public class Square implements Ishape
{
    private String first;
    private String second;
    private String third;
    private String fourth;
    private String fifth;
    private String sixth;
    private String seventh;
    private String eight;
    private String ninth;
    private String tenth;
    private String eleventh;
    private String twelfth;
    private String thrteen;
    private String fourteen;



    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    public String getThird() {
        return third;
    }

    public void setThird(String third) {
        this.third = third;
    }

    public String getFourth() {
        return fourth;
    }

    public void setFourth(String fourth) {
        this.fourth = fourth;
    }

    public String getFifth() {
        return fifth;
    }

    public void setFifth(String fifth) {
        this.fifth = fifth;
    }

    public String getSixth() {
        return sixth;
    }

    public void setSixth(String sixth) {
        this.sixth = sixth;
    }

    public String getSeventh() {
        return seventh;
    }

    public void setSeventh(String seventh) {
        this.seventh = seventh;
    }

    public String getEight() {
        return eight;
    }

    public void setEight(String eight) {
        this.eight = eight;
    }

    public String getNinth() {
        return ninth;
    }

    public void setNinth(String ninth) {
        this.ninth = ninth;
    }

    public String getTenth() {
        return tenth;
    }

    public void setTenth(String tenth) {
        this.tenth = tenth;
    }

    public String getEleventh() {
        return eleventh;
    }

    public void setEleventh(String eleventh) {
        this.eleventh = eleventh;
    }

    public String getTwelfth() {
        return twelfth;
    }

    public void setTwelfth(String twelfth) {
        this.twelfth = twelfth;
    }

    public String getThrteen() {return thrteen;}

    public void setThrteen(String thrteen){ this.thrteen = thrteen;}

    public String getFourteen() {return fourteen;}

    public void setFourteen(String fourteen){ this.fourteen = fourteen;}

    @Override
    public String toString() {
        return "{" +
                "\"first\":\"" + first + "\""+
                ", \"second\":\"" + second + "\"" +
                ", \"third\":\"" + third + "\"" +
                ", \"fourth\":\"" + fourth + "\"" +
                ", \"fifth\":\"" + fifth + "\"" +
                ", \"sixth\":\"" + sixth + "\"" +
                ", \"seventh\":\"" + seventh + "\"" +
                ", \"eight\":\"" + eight + "\"" +
                ", \"ninth\":\"" + ninth + "\"" +
                ", \"tenth\":\"" + tenth + "\"" +
                ", \"eleventh\":\"" + eleventh + "\"" +
                ", \"twelfth\":\"" + twelfth + "\"" +
                ", \"thrteen\":\"" + thrteen + "\"" +
                ", \"fourteen\":\"" + fourteen + "\"" +
                '}';
    }
    public void handle(jsonShape data)
    {
        this.setFirst(data.first);
        this.setSecond(data.second);
        this.setThird(data.third);
        this.setFourth(data.fourth);
        this.setFifth(data.fifth);
        this.setSixth(data.sixth);
        this.setSeventh(data.seventh);
        this.setEight(data.eight);
        this.setNinth(data.ninth);
        this.setTenth(data.tenth);
        this.setEleventh("0");
        this.setTwelfth("0");
        this.setThrteen("0");
        this.setFourteen("0");

    }
    public Ishape clone(Ishape toBeCloned) {
        Ishape cloned = new Square();
        jsonShape shadow = new jsonShape();
        shadow.first = toBeCloned.getFirst();
        shadow.second = toBeCloned.getSecond();
        shadow.third = String.valueOf(Double.parseDouble(toBeCloned.getThird())+40);
        shadow.fourth = String.valueOf(Double.parseDouble(toBeCloned.getFourth())+40);
        shadow.fifth = toBeCloned.getFifth();
        shadow.sixth = toBeCloned.getSixth();
        shadow.seventh = toBeCloned.getSeventh();
        shadow.eight = toBeCloned.getEight();
        shadow.ninth = toBeCloned.getNinth();
        shadow.tenth = toBeCloned.getTenth();
        shadow.eleventh = toBeCloned.getEleventh();
        shadow.twelfth = toBeCloned.getTwelfth();
        shadow.thrteen = toBeCloned.getThrteen();
        shadow.fourteen = toBeCloned.getFourteen();
        cloned.handle(shadow);
        return cloned;
    }
    public JsonObject tojsonobject(){
        JsonObject jsonObject=new JsonObject();
        jsonObject.put("first",this.getFirst());
        jsonObject.put("second",this.getSecond());
        jsonObject.put("third",this.getThird());
        jsonObject.put("fourth",this.getFourth());
        jsonObject.put("fifth",this.getFifth());
        jsonObject.put("sixth",this.getSixth());
        jsonObject.put("seventh",this.getSeventh());
        jsonObject.put("eight",this.getEight());
        jsonObject.put("ninth",this.getNinth());
        jsonObject.put("tenth",this.getTenth());
        jsonObject.put("eleventh",this.getEleventh());
        jsonObject.put("twelfth",this.getTwelfth());
        jsonObject.put("thrteen",this.getThrteen());
        jsonObject.put("fourteen",this.getFourteen());

        return jsonObject;
    }
    public Ishape fromjsonObject(JsonObject jsonObject){
        this.setFirst((String) jsonObject.get("first"));
        this.setSecond((String) jsonObject.get("second"));
        this.setThird((String) jsonObject.get("third"));
        this.setFourth((String) jsonObject.get("fourth"));
        this.setFifth((String) jsonObject.get("fifth"));
        this.setSixth((String) jsonObject.get("sixth"));
        this.setSeventh((String) jsonObject.get("seventh"));
        this.setEight((String) jsonObject.get("eight"));
        this.setNinth((String) jsonObject.get("ninth"));
        this.setTenth((String) jsonObject.get("tenth"));
        this.setEleventh((String) jsonObject.get("eleventh"));
        this.setTwelfth((String) jsonObject.get("twelfth"));
        this.setThrteen((String) jsonObject.get("thrteen"));
        this.setFourteen((String) jsonObject.get("fourteen"));

        return this;
    }
    public void ObjectToXml (Document xmlDoc, Element mainElement){
        Element first=xmlDoc.createElement("first");
        Element second=xmlDoc.createElement("second");
        Element third=xmlDoc.createElement("third");
        Element fourth=xmlDoc.createElement("fourth");
        Element fifth=xmlDoc.createElement("fifth");
        Element sixth=xmlDoc.createElement("sixth");
        Element seventh=xmlDoc.createElement("seventh");
        Element eight=xmlDoc.createElement("eight");
        Element ninth=xmlDoc.createElement("ninth");
        Element tenth=xmlDoc.createElement("tenth");
        Element eleventh=xmlDoc.createElement("eleventh");
        Element twelfth=xmlDoc.createElement("twelfth");
        Element thrteen=xmlDoc.createElement("thrteen");
        Element fourteen=xmlDoc.createElement("fourteen");
        first.setAttribute("value",this.getFirst());
        second.setAttribute("value",this.getSecond());
        third.setAttribute("value",this.getThird());
        fourth.setAttribute("value",this.getFourth());
        fifth.setAttribute("value",this.getFifth());
        sixth.setAttribute("value",this.getSixth());
        seventh.setAttribute("value",this.getSeventh());
        eight.setAttribute("value",this.getEight());
        ninth.setAttribute("value",this.getNinth());
        tenth.setAttribute("value",this.getTenth());
        eleventh.setAttribute("value",this.getEleventh());
        twelfth.setAttribute("value",this.getTwelfth());
        thrteen.setAttribute("value",this.getThrteen());
        fourteen.setAttribute("value",this.getFourteen());

        mainElement.appendChild(first);
        mainElement.appendChild(second);
        mainElement.appendChild(third);
        mainElement.appendChild(fourth);
        mainElement.appendChild(fifth);
        mainElement.appendChild(sixth);
        mainElement.appendChild(seventh);
        mainElement.appendChild(eight);
        mainElement.appendChild(ninth);
        mainElement.appendChild(tenth);
        mainElement.appendChild(eleventh);
        mainElement.appendChild(twelfth);
        mainElement.appendChild(thrteen);
        mainElement.appendChild(fourteen);


    }
    public Ishape  xmlToObject(Node shape_node){
        NodeList attributes_nodes=shape_node.getChildNodes();
        for (int i=0;i<attributes_nodes.getLength();i++){
            Node attribute_node=attributes_nodes.item(i);
            if (attribute_node.getNodeType()==Node.ELEMENT_NODE){
                Element attribute=(Element) attribute_node;
                String attribute_name=attribute.getTagName();
                switch (attribute_name){
                    case "first":this.setFirst(attribute.getAttribute("value"));break;
                    case "second":this.setSecond(attribute.getAttribute("value"));break;
                    case "third":this.setThird(attribute.getAttribute("value"));break;
                    case "fourth":this.setFourth(attribute.getAttribute("value"));break;
                    case "fifth":this.setFifth(attribute.getAttribute("value"));break;
                    case "sixth":this.setSixth(attribute.getAttribute("value"));break;
                    case "seventh":this.setSeventh(attribute.getAttribute("value"));break;
                    case "eight":this.setEight(attribute.getAttribute("value"));break;
                    case "ninth":this.setNinth(attribute.getAttribute("value"));break;
                    case "tenth":this.setTenth(attribute.getAttribute("value"));break;
                    case "eleventh":this.setEleventh(attribute.getAttribute("value"));break;
                    case "twelfth":this.setTwelfth(attribute.getAttribute("value"));break;
                    case "thrteen":this.setThrteen(attribute.getAttribute("value"));break;
                    case "fourteen":this.setFourteen(attribute.getAttribute("value"));break;


                }
            }
        }

        return this;
    }
}

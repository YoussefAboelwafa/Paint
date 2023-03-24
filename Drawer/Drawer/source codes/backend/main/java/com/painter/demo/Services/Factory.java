package com.painter.demo.Services;
import com.painter.demo.shapes.*;
import org.springframework.stereotype.Component;

@Component
public class Factory
{
    public Ishape createShape(String type)
    {
        if(type.equals("Square"))
            return new Square();
        else if(type.equals("Circle"))
            return new Circle();
        else if(type.equals("Line"))
            return new Line();
        else if(type.equals("Rectangle"))
            return new Rectangle();
        else if(type.equals("Triangle"))
            return new Triangle();
        else if(type.equals("Ellipse"))
            return new Ellipse();
        else
            return null;
    }
}

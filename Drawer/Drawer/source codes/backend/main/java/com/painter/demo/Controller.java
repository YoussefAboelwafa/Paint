package com.painter.demo;

import com.painter.demo.Services.Drawer;
import com.painter.demo.Services.Factory;
import com.painter.demo.Services.IDs;
import com.painter.demo.shapes.Ishape;
import com.painter.demo.shapes.jsonShape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Component
@RestController
@RequestMapping
@CrossOrigin
public class Controller{

    @Autowired
    Drawer drawer = new Drawer();
    @CrossOrigin

    // @GetMapping(value="/ans")
    @ResponseBody
    @RequestMapping(value = "/addshape",method = RequestMethod.POST)
    public void addShape(@RequestBody jsonShape shape) {

        drawer.addShape(shape.first,shape);


    }
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/undo",method = RequestMethod.POST)
    public ArrayList<Ishape> undo(@RequestBody String s) {
        return drawer.undo();


    }

    //@GetMapping(value="/redo")
    @CrossOrigin
    @RequestMapping(value = "/redo",method = RequestMethod.POST)
    @ResponseBody
    public ArrayList<Ishape> redo(@RequestBody String s) {return drawer.redo();
    }
    @CrossOrigin
    @RequestMapping(value = "/copy",method = RequestMethod.POST)
    @ResponseBody
    public ArrayList<Ishape> copy(@RequestBody IDs ID) {
        System.out.println(ID.ID+"idddddds"+ID.newID);

        return  drawer.copy(ID);
    }


    @CrossOrigin
    @RequestMapping(value = "/save",method =RequestMethod.POST)
    @ResponseBody
    public ArrayList<Ishape> save(@RequestBody String path) {
        return drawer.save(path);

    }
    @CrossOrigin
    @RequestMapping(value = "/load",method =RequestMethod.POST)
    @ResponseBody
    public ArrayList<Ishape>  load(@RequestBody String path) {
        System.out.println(path);
        if (path.charAt(path.length()-1)=='l'){
            return drawer.load_xml(path);
        } else {
            return drawer.load_json(path);

        }


    }

    @CrossOrigin
    @RequestMapping(value = "/eraseAll",method =RequestMethod.POST)
    @ResponseBody
    public void eraseAll(){drawer.eraseAll();}

    @RequestMapping(value = "/erase",method =RequestMethod.POST)
    @ResponseBody
    public void eraseOne(@RequestBody IDs ID){drawer.eraseOne(ID.ID);}

}
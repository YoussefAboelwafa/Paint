import { group } from '@angular/animations';
import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import konva from "konva"
import { Layer } from 'konva/lib/Layer';
import { Rect } from 'konva/lib/shapes/Rect';
import { Stage } from 'konva/lib/Stage';
import { bindCallback, isEmpty, withLatestFrom } from 'rxjs';
import { __values } from 'tslib';
import { copy_shape } from './copy';
import { object_shape } from './object_shape';
import { service } from './service';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {
  title = 'paint';
  color:any="#4a98f7";

  all_myshape:any[]=[];
 // temp_all_myshape:any[]=[];

  stage:any;
  layer:any;
  rect:any; 
  triangle:any;    //all for draw in konva
  circle:any;
  line:any;
  ellipse:any;
  square:any;
  transform:any;
  lastline:any;
  copy_shape:any;
  max_id:any=0;
  id_brach:number=1;
  id_shape:number=1;
  pre_id:any=0;
  pre_pre_id:any=0;

  isdraw:boolean=false;
  isdraw1:boolean=false;    //all are flages which are indicate to draw or not 
  isdraw2:boolean=false;
  isdraw3:boolean=false;


  side:any=3;
  flag_triangle:any;
  flag_rect:any;
  flage_circle:any;
  flag_line:any;
  flag_ellipse:any;
  flag_polygon:any;
  flage_square:any;
  flag_remove:any;

  temp_brach:any;
  temp_copy:any

  x:any; //for shape
  y:any;//for shape
  s:any;
  event_id:any;

  
  moves:any=0;
  undo_count:any=0;
  clear:any=0;
  file_ex:any="xml";
  file_name:any="File1";
  

  

  make_object:object_shape=new object_shape();

  constructor(private ser: service){}

  get_file_ex(value:any){

    this.file_ex=value;
  }
  get_file_name(value:any){
    this.file_name=value;
  }
  loadFile(value:any){
    
    if(value[0]=="\""&&value[value.length-1]=="\"")
    { 
      value=value.substring(1,value.length-1);
    }
      if(value==""){
        return;
      }
       this.ser.load(value).subscribe
           (
            
              
             (x)=> {
                this.moves=0;
                this.undo_count=0;
                this.clear=1;
                console.log(x);
                if(x[0].first!="Error"){
                  this.drawall(x,true);
                 }
                else{
                  alert("the path =>"+value+" is invalid path");  


                }
    
    
              
              error:(error: HttpErrorResponse) =>alert(error.message);
              }
            
           
          )
    
  
 

  }
  saveFile(value:any){

      if(value==""){
        return;
      }
    if(value[0]=="\""&&value[value.length-1]=="\"")
    { 
      value=value.substring(1,value.length-1);
    }

      var temp=value;
      
      value=value+"+"+this.file_name+"+"+this.file_ex;
      console.log(value);

      this.ser.save(value).subscribe
          (
            x=>{
              { 
                if(x.length==0){

                }
                else if(x[0].first=="Error"){
                  
                  alert("the path =>"+temp+" is invalid path");  
                  
                }   
              }
              error:(error: HttpErrorResponse) =>
              {
                alert(error.message);
              }
      
           }
          )
    
  }

  ngOnInit(){
   
 this.stage=new konva.Stage({
      container:"board",
      width:1500,
      height:850,
     });

   this.layer= new konva.Layer();
   this.stage.add(this.layer);

    this.transform = new konva.Transformer();
   this.layer.add(this.transform);


   this.stage.on("click",(e:any)=>{
    this.event_id=e.target.attrs.id;
    var shape=this.stage.findOne("#"+this.event_id);

  if(this.event_id==undefined){
     this.transform.nodes([]);
    }
    else{
    
    this.transform.nodes([shape]);
    shape.draggable(true);
    
    }    
     
     
     })




     this.stage.on("click",(e:any)=>{
      this.event_id=e.target.attrs.id;
      var shape=this.stage.findOne("#"+this.event_id);
         var pre_shape=this.stage.findOne("#"+this.pre_id);
    if(this.event_id==undefined&&this.pre_id!=this.event_id){
      if(this.pre_id!=undefined &&shape!=pre_shape){
      this.s=pre_shape.name();
        console.log(pre_shape);
        pre_shape.draggable(false);
        this.moves++;
        this.clear++;
        this.undo_count=0;
        if(pre_shape.name()!="brush"){
        this.ser.add_shape(this.make_object.get_object(pre_shape)).subscribe
        (
          {
            next: () =>
            {
              // Nothing to be returned
            },
            error:(error: HttpErrorResponse) =>
            {
              alert(error.message);
            }
    
         }
        )
              }
                  }
      }
      
      this.pre_id=this.event_id;
       })

     this.stage.on("dblclick",(e:any)=>{
      this.event_id=e.target.attrs.id;
    if(this.event_id!=undefined){

      var shape=this.stage.findOne("#"+this.event_id);
      
      if(shape.name()=="brush"){
        shape.stroke(this.color);

      }
     else if (shape.name()=="Line"){
        shape.stroke(this.color);

        this.moves++;
        this.clear++;
        this.undo_count=0;

        this.ser.add_shape(this.make_object.get_object(shape)).subscribe
      (

        res=>{
          
          {
            // Nothing to be returned
          }
          error:(error: HttpErrorResponse) =>
          {
            alert(error.message);
          }
  
       }
      )



      }

      else{
      shape.fill(this.color);
      this.moves++;
      this.clear++;
      this.undo_count=0;
      this.ser.add_shape(this.make_object.get_object(shape)).subscribe
      (
        res=>{
          
          error:(error: HttpErrorResponse) =>
          {
            alert(error.message);
          }
  
       }
      )

      //send line
      }
      }          
       })
       

}

clear_flags(){
  this.flag_ellipse=false;
  this.flag_line=false;
  this.flag_polygon=false;
  this.flag_rect=false;
  this.flage_circle=false;
  this.flage_square=false;
  this.temp_brach=false;
  this.temp_copy=false;
 this.flag_remove=false;

}


brush(){

  var isPaint = false;
  this.clear_flags();
  this.temp_brach=true;
  this.stage.on('mousedown touchstart',  (e:any)=> {
    if(this.temp_brach==true){
    isPaint = true;
    var pos = this.stage.getPointerPosition();
    this.lastline = new konva.Line({
      id:""+this.id_brach+this.id_shape,
      stroke: 'black',
      strokeWidth: 5,
      name:'brush',
      globalCompositeOperation:'source-over',
      draggable:true,
      lineCap: 'round',
      lineJoin: 'round',
      points: [pos.x, pos.y, pos.x, pos.y],
    });
    }
  });


  this.stage.on('mouseup touchend',  (e:any)=> {
    isPaint = false;
    this.id_brach++;
    
    this.temp_brach=false;
    this.all_myshape.push(this.lastline); 
  });

  // and core function - drawing
  this.stage.on('mousemove touchmove',  (e:any)=> {
    if (!isPaint) {
      return;
    }

    // prevent scrolling on touch devices
    e.evt.preventDefault();

    const pos = this.stage.getPointerPosition();
    var newPoints = this.lastline.points().concat([pos.x, pos.y]);
    this.lastline.points(newPoints);
    this.all_myshape.push(this.lastline); 
    this.layer.add(this.lastline);


  });

  

}
  





copy(){
  this.clear_flags();
  this.temp_copy=true;
  this.stage.on("click",(e:any)=>{
   // this.event_id=e.target.attrs.id;
    
  if(this.event_id!=undefined&&this.temp_copy==true){

    
    var shape=this.stage.findOne("#"+this.event_id);
    if(shape.name()!="brush"){
  
   
    var make_copy:copy_shape=new copy_shape();
      make_copy.ID=this.event_id;      
      make_copy.newID=this.id_shape;
      this.moves++;
      this.clear++;
      this.undo_count=0;
    this.ser.copy(make_copy).subscribe
    (
      res=>{
            this.drawall(res,true);

        error:(error: HttpErrorResponse) =>
        {
          alert(error.message);
        }

     }
    )
    }

    this.temp_copy=false;
    this.id_shape++;
   
    

    
    }    
          
     })
    


}


get_fill_color(value:any){
  //console.log(value);
  this.color=value;
}


remove_selected_shape(){
  this.clear_flags();
  this.flag_remove=true;

  this.stage.on("click",(e:any)=>{
    if(this.flag_remove==true){
    this.event_id=e.target.attrs.id;
  if(this.event_id!=undefined){
  
    
    var shape=this.stage.findOne("#"+this.event_id);
    var delete1:copy_shape=new copy_shape();
    delete1.ID=this.event_id;
    delete1.newID=0;
   
    if(shape.name()!="brush"){
    this.moves++;
     this.clear++;
    this.undo_count=0;
   
    this.ser.erase(delete1).subscribe
    (
      res=>{
          

        error:(error: HttpErrorResponse) =>
        {
          alert(error.message);
        }

     }
    )
    }
    for(let i=0;i<this.all_myshape.length;i++){
      

      if(shape==this.all_myshape[i]){
      this.all_myshape[i].remove();

      const temp_all_myshape: any[] = this.all_myshape.filter((element) => {
        return element!==shape;
      });

      this.all_myshape=temp_all_myshape;          // remove element without leave it empty element 
      this.transform.nodes([]);

          

        //send the deleted shape

      }
  
    }
    this.flag_remove=false;
    
    } 
     
  }        
     })


}

clear_request(){
  this.moves++;
  this.clear=0;
  this.undo_count=0;
  
  this.ser.eraseall().subscribe
    (
      res=>{
        

      error:(error: HttpErrorResponse) =>
      {
        alert(error.message);
      }

     }  
   )


   this.clear_allshapes();

}

clear_allshapes(){


  for(let j=0;j<this.all_myshape.length;j++){

    this.all_myshape[j].remove();
      
  }
  this.all_myshape=[];

  this.transform.nodes([]);
}



 drawrect(){
       this.clear_flags();
      this.flag_rect=true;

      this.stage.on("mousedown",() =>{
        if(this.flag_rect==true){
            this.isdraw=true;
            this.rect=new konva.Rect({
            x:this.stage.getPointerPosition().x,
            y:this.stage.getPointerPosition().y,
            width:200,
            height:100,
            stroke:"black",
            strokeScaleEnabled:false,
            id:""+this.id_shape,
            name:"Rectangle",
            strokeWidth:3,
            fill:"white",
            skewX:0,
            skewY:0,
            draggable:false,
            
                          });



      this.moves++;
      this.clear++;
      this.undo_count=0;
      this.id_shape++;
      this.flag_rect=false;      
      this.all_myshape.push(this.rect);
   

      this.ser.add_shape(this.make_object.get_object(this.rect)).subscribe
      (
        {
          next: () =>
          {
            // Nothing to be returned
          },
          error:(error: HttpErrorResponse) =>
          {
            alert(error.message);
          }
  
       }
      )
  

      this.layer.add(this.rect).batchDraw();
                       

        }
                                     })
    

    

  }
  drawcircle(){
    this.clear_flags();
    this.flage_circle=true;
    this.stage.on("mousedown",() =>{
      if(this.flage_circle==true){
          this.isdraw1=true;
            
          
          this.circle=new konva.Circle({
          x:this.stage.getPointerPosition().x,
          y:this.stage.getPointerPosition().y,
          radius:75,
          stroke:"black",
          id:""+this.id_shape,
          name:"Circle",
          strokeWidth:3,
          fill:"white",
          skewX:0,
          skewY:0,
          draggable:false,


          
                        });
      this.clear++;
      this.moves++;
    this.id_shape++;  
    this.undo_count=0;

    this.flage_circle=false;
    this.all_myshape.push(this.circle);

    this.ser.add_shape(this.make_object.get_object(this.circle)).subscribe
    (
      {
        next: (x) =>
        {
         // console.log(x.f)
          // Nothing to be returned
        },
        error:(error: HttpErrorResponse) =>
        {
          alert(error.message);
        }

     }
    )
    this.layer.add(this.circle).batchDraw();
                  
        
      }
                                   })
                                   



}
 

drawline(){

  this.clear_flags();
  this.flag_line=true;
  this.stage.on("mousedown",() =>{
    if(this.flag_line==true){
        this.isdraw2=true;
        this.x=this.stage.getPointerPosition().x;
        this.y=this.stage.getPointerPosition().y;
        this.line=new konva.Line({
          x:0,
          y:0,
          points:[this.x,this.y,this.x+80,this.y+80],
          width:150,
        strokeWidth: 3,
        strokeScaleEnabled:false,
        stroke:"black",
        id:""+this.id_shape,
        name:"Line",
        fill:"white",
        skewX:0,
        skewY:0,
        draggable:false,




        
                      });

   // console.log(this.line.points().at(0));
    //console.warn(this.line.points());
    this.clear++;
  this.moves++;
  this.undo_count=0;
  this.id_shape++;  
  this.flag_line=false;
  this.all_myshape.push(this.line);
  this.ser.add_shape(this.make_object.get_object(this.line)).subscribe
  (
    {
      next: () =>
      {
        // Nothing to be returned
      },
      error:(error: HttpErrorResponse) =>
      {
        alert(error.message);
      }

   }
  )

  this.layer.add(this.line).batchDraw(); 
    }
        })
                                 



     
          

}



drawellipse(){

  this.clear_flags();
  this.flag_ellipse=true;
  this.stage.on("mousedown",() =>{
    if(this.flag_ellipse==true){
        this.ellipse=new konva.Ellipse({
          x:this.stage.getPointerPosition().x,
          y:this.stage.getPointerPosition().y,
          strokeScaleEnabled:false,
          radiusX: 100,
          radiusY: 50,
          stroke: 'black',
          strokeWidth: 3,
          id:""+this.id_shape,
          name:"Ellipse",
          fill:"white",
          skewX:0,
          skewY:0,
          draggable:false,
          });
  this.clear++;
  this.moves++;
  this.undo_count=0;
  this.id_shape++;  
  this.flag_ellipse=false;
  this.ser.add_shape(this.make_object.get_object(this.ellipse)).subscribe
  (
    {
      next: () =>
      {
        // Nothing to be returned
      },
      error:(error: HttpErrorResponse) =>
      {
        alert(error.message);
      }

   }
  )
  this.all_myshape.push(this.ellipse);
  this.layer.add(this.ellipse).batchDraw(); 

    }
        })
}
drawtriangle(value:any){
  this.side=value;
  this.clear_flags();
  this.flag_triangle=true;
  this.stage.on("mousedown",() =>{
    if(this.flag_triangle==true){

        this.triangle=new konva.RegularPolygon({  
          x:this.stage.getPointerPosition().x,
          y:this.stage.getPointerPosition().y,
          strokeWidth: 3,
          sides:this.side,
          radius:75,
          stroke:"black",
          strokeScaleEnabled:false,
          id:""+this.id_shape,
          name:"Triangle",
          fill:"white",
          skewX:0,
          skewY:0,
          draggable:false,
          
                      });
        this.clear++;
        this.moves++;
        this.undo_count=0;
        if(value==5){
          console.warn(5);
          
        }
       // this.triangle.sides(this.side);
        value=3;
        //this.side++;
    this.ser.add_shape(this.make_object.get_object(this.triangle)).subscribe
      (
        {
          next: () =>
          {
            // Nothing to be returned
          },
          error:(error: HttpErrorResponse) =>
          {
            alert(error.message);
          }
  
       }
      )
  this.id_shape++;  
  this.flag_triangle=false;
  this.all_myshape.push(this.triangle);
  this.layer.add(this.triangle).batchDraw(); 

  //send add shape
  
    }
  })     
      




          


}
drawsquare(){

  this.clear_flags();
  this.flage_square=true;
  this.stage.on("mousedown",() =>{
    if(this.flage_square==true){
        this.isdraw3=true;
        this.square=new konva.Rect({
        x:this.stage.getPointerPosition().x,
        y:this.stage.getPointerPosition().y,
        width:70,
        height:70,
        stroke:"black",
        strokeScaleEnabled:false,
        id:""+this.id_shape,
        name:"Square",
        strokeWidth:3,
        fill:"white",
            skewX:0,
            skewY:0,
            draggable:false,


        
                      });
    this.clear++;
    this.moves++;
    this.undo_count=0;
  this.id_shape++;
  this.flage_square=false;
  this.ser.add_shape(this.make_object.get_object(this.square)).subscribe
  (
    {
      next: () =>
      {
        // Nothing to be returned
      },
      error:(error: HttpErrorResponse) =>
      {
        alert(error.message);
      }

   }
  )
    this.all_myshape.push(this.square);
  this.layer.add(this.square).batchDraw();
                   
    }
                                 });
       
  
}





drawall(array_object:any,clear:boolean){
  if(clear){
  this.clear_allshapes();
  }


  this.moves++;
  
  for(let i=0;i<array_object.length;i++){
if(array_object[i].first=="Error"){
      return;
    }
    if(this.max_id<parseInt(array_object[i].second)){
      this.max_id=parseInt(array_object[i].second);
    }

   

    if(array_object[i].first=="Rectangle"){
      this.rect=new konva.Rect({
        x:parseFloat(array_object[i].third),
        y:parseFloat(array_object[i].fourth),
        width:parseFloat(array_object[i].fifth),
        height:parseFloat(array_object[i].sixth),
        stroke:"black",
        strokeScaleEnabled:false,
        id:array_object[i].second,
        name:"Rectangle",
        strokeWidth:3,
        rotation:parseFloat(array_object[i].eight),
        scaleX:parseFloat(array_object[i].ninth),
        scaleY:parseFloat(array_object[i].tenth),
        fill:array_object[i].seventh,
        skewX:0,
        skewY:0,
        draggable:false,

        
                      });




  this.all_myshape.push(this.rect);
  this.layer.add(this.rect).batchDraw();
    }
    if(array_object[i].first=="Circle"){

      this.circle=new konva.Circle({
        x:parseFloat(array_object[i].third),
        y:parseFloat(array_object[i].fourth),
        radius:parseFloat(array_object[i].fifth),
        stroke:"black",
        strokeScaleEnabled:false,
        id:array_object[i].second,
        name:"Circle",
        strokeWidth:3,
        rotation:parseFloat(array_object[i].seventh),
        scaleX:parseFloat(array_object[i].eight),
        scaleY:parseFloat(array_object[i].ninth),
        fill:array_object[i].sixth,
        skewX:0,
        skewY:0,
                      });




  this.all_myshape.push(this.circle);
  this.layer.add(this.circle).batchDraw();

      



      
    }
    if(array_object[i].first=="Line"){


      this.line=new konva.Line({
        x:parseFloat(array_object[i].third),
        y:parseFloat(array_object[i].fourth),
        width:parseFloat(array_object[i].fifth),
        stroke:array_object[i].sixth,
        strokeScaleEnabled:false,
        id:array_object[i].second,
        name:"Line",
        strokeWidth:3,
        rotation:parseFloat(array_object[i].seventh),
        scaleX:parseFloat(array_object[i].eight),
        scaleY:parseFloat(array_object[i].ninth),
        points:[parseFloat(array_object[i].tenth),parseFloat(array_object[i].eleventh),parseFloat(array_object[i].twelfth),parseFloat(array_object[i].thrteen)],
        draggable:false,
        skewX:0,
        skewY:0,

        
                      });




  this.all_myshape.push(this.line);
  this.layer.add(this.line).batchDraw();



      
    }
    if(array_object[i].first=="Ellipse"){


      this.ellipse=new konva.Ellipse({
        x:parseFloat(array_object[i].third),
        y:parseFloat(array_object[i].fourth),
        radiusX:parseFloat(array_object[i].fifth),
        radiusY:parseFloat(array_object[i].sixth),
        fill:array_object[i].seventh,
        strokeScaleEnabled:false,
        id:array_object[i].second,
        name:"Ellipse",
        strokeWidth:3,
        stroke:"black",
        rotation:parseFloat(array_object[i].eight),
        scaleX:parseFloat(array_object[i].ninth),
        scaleY:parseFloat(array_object[i].tenth),
        skewX:0,
        skewY:0,
        draggable:false,

                      });




  this.all_myshape.push(this.ellipse);
  this.layer.add(this.ellipse).batchDraw();





      
    }
    if(array_object[i].first=="Triangle"){

      this.triangle=new konva.RegularPolygon({
        x:parseFloat(array_object[i].third),
        y:parseFloat(array_object[i].fourth),
        radius:parseFloat(array_object[i].fifth),
        fill:array_object[i].seventh,
        strokeScaleEnabled:false,
        id:array_object[i].second,
        name:"Triangle",
        strokeWidth:3,
        sides:parseFloat(array_object[i].sixth),
        rotation:parseFloat(array_object[i].eight),
        scaleX:parseFloat(array_object[i].ninth),
        scaleY:parseFloat(array_object[i].tenth),
        skewX:0,
        skewY:0,
        draggable:false,
        stroke:"black",

                      });




  this.all_myshape.push(this.triangle);
  this.layer.add(this.triangle).batchDraw();



      
    }
    if(array_object[i].first=="Square"){

      this.square=new konva.Rect({
        x:parseFloat(array_object[i].third),
        y:parseFloat(array_object[i].fourth),
        width:parseFloat(array_object[i].fifth),
        height:parseFloat(array_object[i].sixth),
        stroke:"black",
        strokeScaleEnabled:false,
        id:array_object[i].second,
        name:"Square",
        strokeWidth:3,
        rotation:parseFloat(array_object[i].eight),
        scaleX:parseFloat(array_object[i].ninth),
        scaleY:parseFloat(array_object[i].tenth),
        fill:array_object[i].seventh,
        skewX:0,
        skewY:0,
        draggable:false,

        
                      });




  this.all_myshape.push(this.square);
  this.layer.add(this.square).batchDraw();



      
    }
  }


  this.id_shape=this.max_id+1;
}




undo(){

      this.moves--;
      this.undo_count++;
      this.clear++;
  this.ser.undo().subscribe
      (
        (x)=>{
          {
            console.log(x);

            this.drawall(x,true);


          }
          error:(error: HttpErrorResponse) =>
          {
            alert(error.message);
          }
  
       }
      )

}



redo(){
    this.clear++;
    this.moves++;
    this.undo_count--;
    this.ser.redo().subscribe
        (
          (res)=>{
            {
              console.log(res);
              this.drawall(res,true);
  
  
            }
            error:(error: HttpErrorResponse) =>
            {
              alert(error.message);
            }
    
         }
        )
  
}




}
import { Injectable } from "@angular/core"
import { HttpClient } from '@angular/common/http';
import { object_shape } from './object_shape';
import { Observable } from "rxjs";

// import {class you made} 
@Injectable({
  providedIn: 'root'
})


export class service {
  //private apiServerUrl = 'http://localhost:8080';
  constructor(private http: HttpClient) { }

  public  add_shape(added:object_shape){
    return this.http.post<object_shape>(`http://localhost:8080/addshape`, added);
  }

   public  undo():Observable<any>{
     return this.http.post<any>(`http://localhost:8080/undo`,1);
 }
 public  redo():Observable<any>{
  return this.http.post<any>(`http://localhost:8080/redo`, 1);
}
public  copy(added:any):Observable<any>{
  return this.http.post<any>(`http://localhost:8080/copy`, added);
}
public  save(added:any):Observable<any>{
  return this.http.post<any>(`http://localhost:8080/save`, added);
}


public  load(added:any):Observable<any>{
  return this.http.post<any>(`http://localhost:8080/load`, added);
}

public  erase(added:any){
  return this.http.post<any>(`http://localhost:8080/erase`, added);
}
public  eraseall(){
  return this.http.post<any>(`http://localhost:8080/eraseAll`, 1);
}

}
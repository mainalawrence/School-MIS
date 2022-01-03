/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmis;

/**
 *
 * @author Admin
 */
public class activities {
    private String name;
    private String number;
    private String maxpoint;
    private String grade;
    private String duedate;
    private String instructions;
    
    public activities(){
        
    }
    public activities(String name,String number,String maxpoint,String grade,String duedate,String instructions){
     this.name=name;
    this.number=number;
    this.maxpoint=maxpoint;
    this.grade=grade;
   this.duedate=duedate;
    this.instructions=instructions;
    }
    public void Setname(String name){
        this.name=name;
    }
    public void Setnumber (String number){
        this.number=number;
    }
    public void Setmaxpoint (String maxpoint){
        this.maxpoint=maxpoint;
    }
    public void Setgrade (String grade){
        this.grade=grade;
    }
    public void Setduedate (String duedate){
        this.duedate=duedate;
    }
    public void Setinstructions (String instructions){
       this.instructions=instructions; 
    }
     public String Getname(){
         return this.name;
     }
    public String Getnumber(){
         return this.number;
     }
    public String Getmaxpoint(){
         return this.maxpoint;
     }
    public String Getgrade(){
         return this.grade;
     }
    public String Getduedate(){
         return this.duedate;
     }
    public String Getinstructions(){
         return this.instructions;
     }
}

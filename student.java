/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmis;

public class student {
    private String name;
     private String number;
    private String location;
    private String date;
    private String password;
    public student(){
    }
    public student(String name,String number,String password,String location,String date){
     this.name=name;
     this.number=number;
     this.location=location;
    this.date=date;
    this.password=password;
    }
    
     public String Getname(){
         return this.name=name;
     }
     public String Getnumber()
     {
       return  this.number;
     }
    public String Getlocation(){
    return this.location;
}
    public String Getdate()
    {
        return this.date;
    }
    
     public void  Setname(String name){
         this.name=name;
     }
     public void  Setnumber(String number)
     {
         this.number=number;
     }
    public void  Setlocation (String location)
    {
        this.location=location;
    }
    public  void  Setdate(String date)
    {
        this.date=date;
    }
}

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
public class NoDocument {
    private String id;
    private String name;
    private String actNo;
    private String actname;
    private String duedate;
  public  NoDocument(){
        
    }
    public NoDocument(String id,String name,String actNo,String actname,String duedate)
    {
        this.id=id;
    this.name=name;
    this.actNo=actNo;
    this.actname=actname;
    this.duedate=duedate;
    }
    public String id(){
        return this.id;
    }
    public String name(){
        return this.name;
    }
    public String actNo(){
        return this.actNo;
    }
    public String actname(){
        return this.name;
    }
    public String duedate(){
        return this.duedate;
    }
    
     public void Setid (String id){
        this.id=id;
    }
    public void Setname( String name){
        this.name=name;
    }
    public void SetactNo(String actNo){
        this.actNo=actNo;
    }
    public void Setactname(String actname){
        this.actname=actname;
    }
    public void Setduedate(String duedate){
        this.duedate=duedate;
    }
    
}


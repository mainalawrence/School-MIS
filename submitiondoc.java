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
public class submitiondoc {
    private String stdno;
    private String activityid;
    private String doc;
   public submitiondoc(){
        
    }
    public submitiondoc(String stdno,String activityid,String doc){
       this.stdno=stdno;
   this.activityid=activityid;
    this.doc=doc;
    }
    public void Set(String stdno){
       this.stdno=stdno; 
    }
    public void Setactivityid(String activityid){
        this.activityid=activityid;
    }
    public void Setdoc(String doc){
         this.doc=doc;
    }
    public String stdno(){
        return this.stdno;
    }
    public String activityid(){
       return this.activityid; 
    }
    public String doc(){
        return this.doc;
    }
}

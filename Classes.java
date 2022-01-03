/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmis;

public class Classes {

    private String id;
    private String name;
    private String location;
    private String time;
    private String students;

    Classes() {
    }

    Classes(String id, String name, String location, String time, String students) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.time = time;
        this.students = students;
    }

    public String Getid(){
        return this.id;
    }

    public String Getname() {
return this.name;
    }

    public String Getlocation() {
return this.location;
    }

    public String Gettime() {
return this.time;
    }

    public String Getstudents() {
return  this.students;
    }

    public void SetId(String id) {
        this.id = id;
    }

    public void Setname(String name) {
        this.name = name;
    }

    public void SetLocation(String location) {
        this.location = location;
    }

    public void SetTime(String time) {
        this.time = time;
    }

    public void Setstudents(String students) {
        this.students = students;
    }
}

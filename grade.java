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
public class grade {

    private String stdno;
    private String score;
    private String grade;
    private String activityno;

    public grade() {

    }

    public grade(String stdno, String score, String grade, String activityno) {
        this.stdno = stdno;
        this.score = score;
        this.grade = grade;
        this.activityno = activityno;
    }

    public void Setstdno(String stdno) {
        this.stdno = stdno;
    }

    public void Setscore(String score) {
        this.score = score;
    }

    public void Setgrade(String grade) {
        this.grade = grade;
    }

    public void Setactivityno(String activityno) {
        this.activityno = activityno;
    }

    public String Getstdno() {
        return this.stdno;
    }

    public String Getscore() {
        return this.score;
    }

    public String Getgrade() {
        return this.grade;
    }

    public String Getactivityno() {
        return this.activityno;
    }
}

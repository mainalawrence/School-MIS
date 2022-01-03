package schoolmis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

class ConnectDb {

    private String jdbcURL = "jdbc:mysql://localhost:3306/schoolmis";
    private String dbUser = "root";
    private String dbPassword = "";

    public student LoginStudent(String number, String password) throws SQLException, ClassNotFoundException {
        student stdnt;
        try (
                Connection connection = DriverManager.getConnection(this.jdbcURL, this.dbUser, this.dbPassword)) {
            String sql = "SELECT * FROM student WHERE number =? AND password =? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, number);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();
            stdnt = null;
            if (result.next()) {
                //student(String name,int number,String password,String location,String date)
                stdnt = new student(result.getString("name"), result.getString("number"), result.getString("password"), result.getString("location"), result.getString("time"));

            }
            connection.close();
        }

        return stdnt;

    }

    public faculty LoginFuculty(String username, String password) throws SQLException, ClassNotFoundException {
        faculty fculty;
        try (
                Connection connection = DriverManager.getConnection(this.jdbcURL, this.dbUser, this.dbPassword)) {
            String sql = "SELECT * FROM faculty WHERE username =? AND password =? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();
            fculty = null;

            if (result.next()) {
                //faculty(String id, String name, String username, String password) 
                fculty = new faculty(result.getString("id"), result.getString("name"), result.getString("username"), result.getString("password"));

            }
            connection.close();
        }

        return fculty;
    }

    public Boolean SignUpstudent(String name, String number, String location) throws SQLException, ClassNotFoundException {

        try (
                Connection connection = DriverManager.getConnection(this.jdbcURL, this.dbUser, this.dbPassword)) {
            String sql = "INSERT INTO `student` (`name`, `number`, `location`, `time`, `password`) VALUES (?, ?, ?, current_timestamp(), ?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, number);
            statement.setString(4, "password");
            statement.setString(3, location);

            statement.execute();
            connection.close();
        }

        return true;

    }

    public Boolean Updatestudent(String name, String number, String location, String pass) throws SQLException, ClassNotFoundException {

        try (
                Connection connection = DriverManager.getConnection(this.jdbcURL, this.dbUser, this.dbPassword)) {
            String sql = "UPDATE `student` SET `name` = ? ,location=? ,password=? WHERE `number` = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(3, pass);
            statement.setString(2, location);
            statement.setString(4, number);
            statement.execute();
            connection.close();
        }

        return true;

    }

    //User Information
    //
    /* 
 -delete user->DELETE FROM table_name WHERE condition;
 -update user ->UPDATE table_name SET column1 = value1, column2 = value2 WHERE condition >>;
 -read all users-> SELECT * FROM users;
     */
    public Vector<student> GetStudent() throws SQLException, ClassNotFoundException {
        Vector<student> stdnt = new Vector<student>();
        stdnt.clear();
        try (
                Connection connection = DriverManager.getConnection(this.jdbcURL, this.dbUser, this.dbPassword)) {
            String sql = "SELECT * FROM student";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {

                stdnt.add(new student(result.getString("name"), result.getString("number"), result.getString("password"), result.getString("location"), result.getString("time")));
            }
            connection.close();
        }

        return stdnt;

    }

    public boolean RemoveStudent(int number) throws SQLException, ClassNotFoundException {

        try (
                Connection connection = DriverManager.getConnection(this.jdbcURL, this.dbUser, this.dbPassword)) {
            String sql = "DELETE FROM `student`  WHERE `student`.`number` = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, number);
            statement.execute();
            connection.close();
        }

        return false;

    }

    public Boolean DeleteUser(String NationalId) throws SQLException, ClassNotFoundException {
        try (
                Connection connection = DriverManager.getConnection(this.jdbcURL, this.dbUser, this.dbPassword)) {
            String sql = "DELETE FROM users  WHERE Nationalid=?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, NationalId);
            statement.execute();
            connection.close();
        }

        return true;

    }

    /*
  activities
  Grade
  doc
  faculty
  
     */
    public Vector<activities> Getactivities() throws SQLException, ClassNotFoundException {
        Vector<activities> act = new Vector<activities>();
        act.clear();
        try (
                Connection connection = DriverManager.getConnection(this.jdbcURL, this.dbUser, this.dbPassword)) {
            String sql = "SELECT * FROM activities";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                //activities(String name,String number,String maxpoint,String grade,String duedate,String instructions)
                act.add(new activities(result.getString("name"), result.getString("id"), result.getString("maxpoint"), result.getString("maxpoint"), result.getString("date"), result.getString("instructions")));
            }
            connection.close();
        }
        return act;
    }

    public boolean CreateActivities(String name, String maxpoint, String grade, String duedate, String instructions) throws SQLException, ClassNotFoundException {
        try (
                Connection connection = DriverManager.getConnection(this.jdbcURL, this.dbUser, this.dbPassword)) {
            String sql = "INSERT INTO `activities` (`name`, `maxpoint`, `date`, `instructions`) VALUES (?, ?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, maxpoint);
            statement.setString(3, duedate);
            statement.setString(4, instructions);

            statement.execute();
            connection.close();
        }
        return true;
    }

    public boolean UpdateActivities(String id, String name, String maxpoint, String duedate, String instructions) throws SQLException, ClassNotFoundException {
        try (
                Connection connection = DriverManager.getConnection(this.jdbcURL, this.dbUser, this.dbPassword)) {
            String sql = "UPDATE `activities` SET `name` = ?, `date` = ?, `instructions` = ? WHERE `activities`.`id` = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, duedate);
            statement.setString(3, instructions);
            statement.setString(4, id);

            statement.execute();
            connection.close();
        }
        return true;
    }

    public boolean DeleteActivities(String id) throws SQLException, ClassNotFoundException {
        try (
                Connection connection = DriverManager.getConnection(this.jdbcURL, this.dbUser, this.dbPassword)) {
            String sql = "DELETE FROM `activities` WHERE  `activities`.`id` = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, id);

            statement.execute();
            connection.close();
        }
        return true;
    }

    public Vector<grade> GetGrades() throws SQLException, ClassNotFoundException {
        Vector<grade> grad = new Vector<grade>();
        grad.clear();
        try (
                Connection connection = DriverManager.getConnection(this.jdbcURL, this.dbUser, this.dbPassword)) {
            String sql = "SELECT * FROM grade";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                //grade(String stdno, String score, String grade, String activityno)
                grad.add(new grade(result.getString("studentid"), result.getString("score"), result.getString("grade"), result.getString("activityid")));
            }
            connection.close();
        }
        return grad;
    }

    public boolean CreateGrade(String stdno, String name, String score, String grade, String activityno) throws SQLException, ClassNotFoundException {
        try (
                Connection connection = DriverManager.getConnection(this.jdbcURL, this.dbUser, this.dbPassword)) {
            String sql = "INSERT INTO `grade` (`id`, `name`, `score`, `grade`,`activityid`, `studentid`) VALUES (NULL, ?, ?, ?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, score);
            statement.setString(3, grade);
            statement.setString(4, activityno);
            statement.setString(5, stdno);
            statement.execute();
            connection.close();
        }
        return true;
    }

    public boolean UpdateGrade(String stdno, String name, String score, String grade, String activityno) throws SQLException, ClassNotFoundException {
        try (
                Connection connection = DriverManager.getConnection(this.jdbcURL, this.dbUser, this.dbPassword)) {
            String sql = "UPDATE  `grade`SET  `name`=?, `score`=?, `grade`=?,`activityid`=?, WHERE `studentid`=?  ;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, score);
            statement.setString(3, grade);
            statement.setString(4, activityno);
            statement.setString(5, stdno);
            statement.execute();
            connection.close();
        }
        return true;
    }

    public boolean DeleteGrade(String id) throws SQLException, ClassNotFoundException {
        try (
                Connection connection = DriverManager.getConnection(this.jdbcURL, this.dbUser, this.dbPassword)) {
            String sql = "DELETE FROM `grade` WHERE  `grade`.`id` = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, id);

            statement.execute();
            connection.close();
        }
        return true;
    }

    public Vector<submitiondoc> GetDocs() throws SQLException, ClassNotFoundException {
        Vector<submitiondoc> doc = new Vector<submitiondoc>();
        doc.clear();
        try (
                Connection connection = DriverManager.getConnection(this.jdbcURL, this.dbUser, this.dbPassword)) {
            String sql = "SELECT * FROM grade";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                //submitiondoc(String stdno,String activityid,String doc)
                doc.add(new submitiondoc(result.getString("studentnumber"), result.getString("activityid"), result.getString("doc")));
            }
            connection.close();
        }
        return doc;
    }

    public boolean Createdoc(String stdno, String activityid, File doc) throws SQLException, ClassNotFoundException, FileNotFoundException {
        //submitiondoc(String stdno,String activityid,String doc)
        plagiarismEngine engine = new plagiarismEngine();
        
        try {
            this.GetFiles();
        } catch (IOException ex) {
            Logger.getLogger(ConnectDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        engine.Start( doc);
        try (
                Connection connection = DriverManager.getConnection(this.jdbcURL, this.dbUser, this.dbPassword)) {
            FileInputStream fin = new FileInputStream(doc);
            int len = (int) doc.length();
            String sql = "INSERT INTO `submitteddocs` (`id`, `studentnumber`, `activityid`, `doc`) VALUES (NULL, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setBinaryStream(3, fin, len);
            statement.setString(2, activityid);
            statement.setString(1, stdno);
            statement.execute();
            connection.close();
        }
        return true;
    }

    public boolean Updatedoc(String stdno, String activityid, String doc) throws SQLException, ClassNotFoundException {
        //submitiondoc(String stdno,String activityid,String doc)
        try (
                Connection connection = DriverManager.getConnection(this.jdbcURL, this.dbUser, this.dbPassword)) {
            String sql = "UPDATE  `submitteddocs` SET  `doc`=?,`activityid`=?, WHERE `studentnumber`=?  ;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, doc);
            statement.setString(2, activityid);
            statement.setString(3, stdno);
            statement.execute();
            connection.close();
        }
        return true;
    }

    public boolean DeleteDoc(String id) throws SQLException, ClassNotFoundException {
        try (
                Connection connection = DriverManager.getConnection(this.jdbcURL, this.dbUser, this.dbPassword)) {
            String sql = "DELETE FROM `submitteddocs` WHERE  `submitteddocs`.`id` = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, id);

            statement.execute();
            connection.close();
        }
        return true;
    }

    public Vector<faculty> Getfaculty() throws SQLException, ClassNotFoundException {
        Vector<faculty> facult = new Vector<faculty>();
        facult.clear();
        try (
                Connection connection = DriverManager.getConnection(this.jdbcURL, this.dbUser, this.dbPassword)) {
            String sql = "SELECT * FROM faculty";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                //faculty(String id, String name, String username, String password)
                facult.add(new faculty(result.getString("id"), result.getString("name"), result.getString("username"), result.getString("password")));
            }
            connection.close();
        }
        return facult;
    }

    public boolean Createfaculty(String name, String username, String password) throws SQLException, ClassNotFoundException {
        //submitiondoc(String stdno,String activityid,String doc)
        try (
                Connection connection = DriverManager.getConnection(this.jdbcURL, this.dbUser, this.dbPassword)) {
            String sql = "INSERT INTO `faculty` (`id`, `name`, `username`, `password`) VALUES (NULL, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, username);
            statement.setString(3, password);
            statement.execute();
            connection.close();
        }
        return true;
    }

    public boolean Updatefaculty(String id, String name, String username, String password) throws SQLException, ClassNotFoundException {
        //submitiondoc(String stdno,String activityid,String doc)
        try (
                Connection connection = DriverManager.getConnection(this.jdbcURL, this.dbUser, this.dbPassword)) {
            String sql = "UPDATE  `faculty` SET  `name`=?,`username`=?, password=? WHERE `id`=?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, username);
            statement.setString(3, password);
            statement.setString(4, id);
            statement.execute();
            connection.close();
        }
        return true;
    }

    public boolean Deletefaculty(String id) throws SQLException, ClassNotFoundException {
        try (
                Connection connection = DriverManager.getConnection(this.jdbcURL, this.dbUser, this.dbPassword)) {
            String sql = "DELETE FROM `faculty` WHERE  `faculty`.`id` = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, id);

            statement.execute();
            connection.close();
        }
        return true;
    }

    Vector<Classes> GetClasses() throws SQLException, ClassNotFoundException {
        Vector<Classes> clas = new Vector<Classes>();
        clas.clear();
        try (
                Connection connection = DriverManager.getConnection(this.jdbcURL, this.dbUser, this.dbPassword)) {
            String sql = "SELECT * FROM classes";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                //(String id, String name, String location, String time, String students)
                clas.add(new Classes(result.getString("id"), result.getString("name"), result.getString("location"), result.getString("time"), result.getString("students")));
            }
            connection.close();
        }
        return clas;
    }

    public boolean CreateClasses(String name, String location, String time, String students) throws SQLException, ClassNotFoundException {
        //(String id, String name, String location, String time, String students)
        try (
                Connection connection = DriverManager.getConnection(this.jdbcURL, this.dbUser, this.dbPassword)) {
            String sql = " INSERT INTO `classes` (`id`, `name`, `location`, `time`, `students`) VALUES (NULL, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, location);
            statement.setString(3, time);
            statement.setString(4, students);

            statement.execute();
            connection.close();
        }
        return true;
    }

    public boolean DeleteClasses(String id) throws SQLException, ClassNotFoundException {
        try (
                Connection connection = DriverManager.getConnection(this.jdbcURL, this.dbUser, this.dbPassword)) {
            String sql = "DELETE FROM `classes` WHERE  `faculty`.`id` = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, id);

            statement.execute();
            connection.close();
        }
        return true;
    }

    public boolean UpdateClasses(String id, String name, String location, String time, String students) throws SQLException, ClassNotFoundException {
        //(String id, String name, String location, String time, String students)
        try (
                Connection connection = DriverManager.getConnection(this.jdbcURL, this.dbUser, this.dbPassword)) {
            String sql = "UPDATE  `classes` SET  `name`=?,`location`=?, time=? ,students=? WHERE `id`=?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, location);
            statement.setString(3, time);
            statement.setString(5, students);
            statement.setString(4, id);
            statement.execute();
            connection.close();
        }
        return true;
    }

    public double HighestScore() throws SQLException, ClassNotFoundException {
        double data = 0.0;
        try (Connection connection = DriverManager.getConnection(this.jdbcURL, this.dbUser, this.dbPassword)) {
            String sql = "SELECT MAX(score) FROM grade";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            if (result.next()) {
                data = result.getDouble(1);
            }

            connection.close();
        }
        return data;
    }

    public double LowestScore() throws SQLException, ClassNotFoundException {
        double data = 0.0;
        try (Connection connection = DriverManager.getConnection(this.jdbcURL, this.dbUser, this.dbPassword)) {
            String sql = "SELECT MIN(score) FROM grade";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            if (result.next()) {
                data = result.getDouble(1);
            }

            connection.close();
        }
        return data;
    }

    public double VetageScore() throws SQLException, ClassNotFoundException {
        double data = 0.0;
        try (Connection connection = DriverManager.getConnection(this.jdbcURL, this.dbUser, this.dbPassword)) {
            String sql = "SELECT AVG(score) FROM grade";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            if (result.next()) {
                data = result.getDouble(1);
            }
            connection.close();
        }
        return data;
    }
    //NoDoc   

    public Vector<NoDocument> GetNotDoc() throws SQLException, ClassNotFoundException {
        Vector<NoDocument> doc = new Vector<NoDocument>();
        doc.clear();
        try (
                Connection connection = DriverManager.getConnection(this.jdbcURL, this.dbUser, this.dbPassword)) {
            String sql = "SELECT * FROM student LEFT JOIN submitteddocs ON student.number=submitteddocs.studentnumber;";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                //String id,String name,String actNo,String actname,String duedate
                doc.add(new NoDocument(result.getString("number"), result.getString("name"), result.getString("activityid"), result.getString("name"), result.getString("time")));
            }
            connection.close();
        }

        return doc;

    }
    //

    HashMap<String, String> getActivityName() throws SQLException, ClassNotFoundException {
        HashMap<String, String> hm = new HashMap<>();
        hm.clear();
        try (
                Connection connection = DriverManager.getConnection(this.jdbcURL, this.dbUser, this.dbPassword)) {
            String sql = "SELECT * FROM activities";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                //activities(String name,String number,String maxpoint,String grade,String duedate,String instructions)
                hm.put(result.getString("id"), result.getString("name"));
            }
            connection.close();
        }

        return hm;
    }

    public List<File> GetFiles() throws SQLException, ClassNotFoundException, IOException {
         
        List<File> fileList = new ArrayList<>();
        try (
                Connection connection = DriverManager.getConnection(this.jdbcURL, this.dbUser, this.dbPassword)) {
            String sql = "SELECT * FROM submitteddocs";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
             
                Blob blob = result.getBlob("doc");
                //System.out.println("Read " + blob.length() + " bytes ");
                byte[] array = blob.getBytes(1, (int) blob.length());
                File file = File.createTempFile("file-", ".txt", new File("src/files"));
                FileOutputStream out;
                out = new FileOutputStream(file);
                out.write(array);
                out.close();
                
                
            }

            connection.close();

        }
        return fileList;
    }
}

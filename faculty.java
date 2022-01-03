
package schoolmis;

public class faculty {

    private String id;
    private String name;
    private String username;
    private String password;

    public faculty() {

    }
    public faculty(String id, String name, String username, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public void Setid(String id) {
        this.id = id;
    }

    public void Setname(String name) {
        this.name = name;
    }

    public void Setusername(String username) {
        this.username = username;
    }

    public void Setpassword(String password) {
        this.password = password;
    }

    public String Getid() {
        return this.id;
    }

    public String Getname() {
        return this.name;
    }

    public String Getusername() {
        return this.username;
    }

    public String Getpassword() {
        return this.password;
    }
}

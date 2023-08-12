package Quiz.src.main.java.models;

public class User {
    private int Id;
    private String Username;
    private String PasswordHash;

    public User(int id, String username, String passwordHash){
        Id = id;
        Username = username;
        PasswordHash = passwordHash;
    }

    public int getId() { return Id; }
    public String getUsername() { return Username; }

    public String getPasswordHash() { return PasswordHash; }
}

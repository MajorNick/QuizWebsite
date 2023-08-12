package Quiz.src.main.java.models;

public class User {
    private int Id;
    private String Username;
    private String PasswordHash;
    private String PfpLink;

    public User(int id, String username, String passwordHash){
        Id = id;
        Username = username;
        PasswordHash = passwordHash;
    }

    public int getId() { return Id; }
    public String getUsername() { return Username; }
    public String getPasswordHash() { return PasswordHash; }
    public String getPfpLink(){ return PfpLink; }
    public void setPfpLink(String pfpLink) { PfpLink = pfpLink; }
}

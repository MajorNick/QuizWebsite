package Quiz.src.main.java.models;

public class User {
    private int Id;
    private String Username;
    private String PasswordHash;
    private String PfpLink;
    private String Role;

    public User(int id, String username, String passwordHash, String role){
        Id = id;
        Username = username;
        PasswordHash = passwordHash;
        Role = role;
    }

    public int getId() { return Id; }
    public boolean isAdmin(){ return Role.equals("admin");
    }
    public String getRole(){ return Role;
    }
    public String getUsername() { return Username; }
    public String getPasswordHash() { return PasswordHash; }
    public String getPfpLink(){ return PfpLink; }
    public void setPfpLink(String pfpLink) { PfpLink = pfpLink; }
}

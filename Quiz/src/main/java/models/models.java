package Quiz.src.main.java.models;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class models {
    private static final String account = "oopUser";
    private static final String password = "oopUserPasswrd";
    private static final String server = "localhost";
    private static final String database = "oop_proj";

    public class Notification {
        public Notification(int id, int receiver_id, int sender_id, String notif_type, String notif_body){
            Id = id;
            Receiver_id = receiver_id;
            Sender_id = sender_id;
            Notif_type = notif_type;
            Notif_body = notif_body;
        }
        public int Id;
        public int Receiver_id;
        public int Sender_id;
        public String Notif_type;
        public String Notif_body;
    }

    public ArrayList<Notification> GetNotifications(int receiver_id, int sender_id) {
        String q = "SELECT * FROM notifications";

        if(receiver_id == -1){
            q = String.format("SELECT * FROM notifications n WHERE n.sender_id = '%s'", sender_id);
        } else if (sender_id == -1){
            q = String.format("SELECT * FROM notifications n WHERE n.receiver_id = '%s'", receiver_id);
        } else {
            System.out.println("error");
        }

        ArrayList<Notification> selection = new ArrayList<>();
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://" + server, account, password);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("USE " + database);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(q);

            while (rs.next()) {
                Notification notification = new Notification(rs.getInt("id"),
                                                             rs.getInt("receiver_id"),
                                                             rs.getInt("sender_id"),
                                                             rs.getString("notif_type"),
                                                             rs.getString("notif_body"));
                selection.add(notification);
            }
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            return  null;
        }
        return selection;
    }

    public class Announcement {
        public Announcement(int id, String announcement_body){
            Id = id;
            Announcement_body = announcement_body;
        }
        public int Id;
        public String Announcement_body;
    }

    public ArrayList<Announcement> GetAnnouncements() {
        String q = "SELECT * FROM announcements";

        ArrayList<Announcement> selection = new ArrayList<>();
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://" + server, account, password);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("USE " + database);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(q);

            while (rs.next()) {
                Announcement announcement = new Announcement(rs.getInt("id"), rs.getString("announcement"));
                selection.add(announcement);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            return  null;
        }
        return selection;
    }

    public class Achievement {
        public Achievement(int id, String achievement_body){
            Id = id;
            Achievement_body = achievement_body;
        }
        public int Id;
        public String Achievement_body;
    }

    public ArrayList<Achievement> GetAchievements(int id) {
        String q = "SELECT * FROM achievements";
        if(id != -1){
            q = String.format("SELECT * FROM achievements a where a.id = %d", id);
        }

        ArrayList<Achievement> selection = new ArrayList<>();
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://" + server, account, password);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("USE " + database);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(q);

            while (rs.next()) {
                Achievement achievement = new Achievement(rs.getInt("id"), rs.getString("achievement"));
                selection.add(achievement);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            return  null;
        }
        return selection;
    }

    public class User_achievement {
        public User_achievement(int id, int user_id, int achievement_id){
            Id = id;
            User_id = user_id;
            Achievement_id = achievement_id;
        }
        public int Id;
        public int User_id;
        public int Achievement_id;
    }

    public ArrayList<User_achievement> GetUser_achievements(int user_id) {
        String q = "SELECT * FROM achievements";
        if(user_id != -1){
            q = String.format("SELECT * FROM achievements a where a.user_id = %d", user_id);
        }

        ArrayList<User_achievement> selection = new ArrayList<>();
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://" + server, account, password);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("USE " + database);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(q);

            while (rs.next()) {
                User_achievement user_achievement = new User_achievement(rs.getInt("id"), rs.getInt("user_id"), rs.getInt("achievement_id"));
                selection.add(user_achievement);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            return  null;
        }
        return selection;
    }
}

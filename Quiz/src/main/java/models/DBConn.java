package Quiz.src.main.java.models;

import java.sql.*;
import java.util.ArrayList;

public class DBConn{
    private static final String account = "oopUser";
    private static final String password = "oopUserPasswrd";
    private static final String server = "localhost";
    private static final String database = "oop_proj";

    private Connection conn;
    private Statement stmt;
    private ResultSet rs;

    public DBConn() {
        try{
            conn = DriverManager.getConnection("jdbc:mysql://" + server, account, password);
            stmt = conn.createStatement();
            executeUpdate("USE " + database);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    public void closeDBConn() {
        try {
            if(rs != null)
                rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    private void executeQuery(String q) {
        try{
            System.out.println(String.format("Executing querry: %s", q));
            rs = stmt.executeQuery(q);
        } catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }

    private void executeUpdate(String u) {
        try{
            System.out.println(String.format("Executing update: %s", u));
            stmt.executeUpdate(u);
        } catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }

    public void insertNotification(Notification n){
        if(n == null)
            throw new RuntimeException("Provided Notification is null");

        String q = String.format("INSERT INTO notifications (receiver_id, sender_id, notif_type, notif_body)  VALUES(%d, %d, '%s', '%s')", n.getReceiverId(), n.getSenderId(), n.getNotifType(), n.getNotifBody());
        executeUpdate(q);
    }

    public void insertAnnouncement(Announcement a){
        if(a == null)
            throw new RuntimeException("Provided Announcement is null");

        String q = String.format("INSERT INTO announcements (announcement)  VALUES('%s')", a.getAnnouncementBody());
        executeUpdate(q);
    }

    public void insertAchievement(Achievement a){
        if(a == null)
            throw new RuntimeException("Provided Achievement is null");

        String q = String.format("INSERT INTO achievements (achievement)  VALUES('%s')", a.getAchievementBody());
        executeUpdate(q);
    }

    public void insertUserAchievement(UserAchievement u){
        if(u == null)
            throw new RuntimeException("Provided UserAchievement is null");

        String q = String.format("INSERT INTO user_achievements (user_id, achievement_id)  VALUES(%d, %d)", u.getUserId(), u.getAchievementId());
        executeUpdate(q);
    }

    public void insertUser(User u){
        if(u == null)
            throw new RuntimeException("Provided UserAchievement is null");

        String q = String.format("INSERT INTO users (username, password_hash)  VALUES(%s, %s)", u.getUsername(), u.getPasswordHash());
        executeUpdate(q);
    }

    public void insertFriend(Friend f){
        if(f == null)
            throw new RuntimeException("Provided UserAchievement is null");

        String q = String.format("INSERT INTO friends (user_id, friend_id)  VALUES(%d, %d)", f.getUser_id(), f.getFriend_id());
        executeUpdate(q);
    }

    public void insertQuizHistory(QuizHistory qh){
        if(qh == null)
            throw new RuntimeException("Provided UserAchievement is null");

        String q = String.format("INSERT INTO quiz_history (score, quiz_id, user_id, time_taken)  VALUES(%f, %d, %d, %d)", qh.getScore(), qh.getQuiz_id(), qh.getUser_id(), qh.getTime_taken());
        executeUpdate(q);
    }

    public ArrayList<Notification> getNotifications(int receiver_id, int sender_id) {
        String q = "SELECT * FROM notifications";

        if(receiver_id == -1){
            q = String.format("SELECT * FROM notifications n WHERE n.sender_id = '%s'", sender_id);
        } else if (sender_id == -1){
            q = String.format("SELECT * FROM notifications n WHERE n.receiver_id = '%s'", receiver_id);
        }

        ArrayList<Notification> selection = new ArrayList<>();

        try{
            executeQuery(q);

            while (rs.next()) {
                Notification notification = new Notification(rs.getInt("id"),
                        rs.getInt("receiver_id"),
                        rs.getInt("sender_id"),
                        rs.getString("notif_type"),
                        rs.getString("notif_body"));
                selection.add(notification);
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        return selection;
    }

    public ArrayList<Announcement> getAnnouncements() {
        String q = "SELECT * FROM announcements";
        ArrayList<Announcement> selection = new ArrayList<>();

        try{
            executeQuery(q);

            while (rs.next()) {
                Announcement announcement = new Announcement(rs.getInt("id"), rs.getString("announcement"));
                selection.add(announcement);
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        return selection;
    }

    public ArrayList<Achievement> getAchievements(int id) {
        String q = "SELECT * FROM achievements";
        if(id != -1){
            q = String.format("SELECT * FROM achievements a where a.id = %d", id);
        }

        ArrayList<Achievement> selection = new ArrayList<>();
        try{
            executeQuery(q);

            while (rs.next()) {
                Achievement achievement = new Achievement(rs.getInt("id"), rs.getString("achievement"));
                selection.add(achievement);
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        return selection;
    }

    public ArrayList<UserAchievement> getUserAchievements(int user_id) {
        String q = "SELECT * FROM achievements";
        if(user_id != -1){
            q = String.format("SELECT * FROM achievements a where a.user_id = %d", user_id);
        }

        ArrayList<UserAchievement> selection = new ArrayList<>();
        try{
            executeQuery(q);

            while (rs.next()) {
                UserAchievement userAchievement = new UserAchievement(rs.getInt("id"), rs.getInt("user_id"), rs.getInt("achievement_id"));
                selection.add(userAchievement);
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        return selection;
    }

    public ArrayList<User> getUsers(int id) {
        String query = "SELECT * FROM users";
        if(id != -1){
            query = String.format("SELECT * FROM users u where u.id = %d", id);
        }

        ArrayList<User> selection = new ArrayList<>();
        try{
            executeQuery(query);

            while (rs.next()) {
                User user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("passwordHash"));
                selection.add(user);
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        return selection;
    }

    public ArrayList<Friend> GetUserFriends(int user_id) {
        String query = "SELECT * FROM friends";
        if(user_id != -1){
            query = String.format("SELECT * FROM friends f where f.user_id = %d", user_id);
        }

        ArrayList<Friend> friends = new ArrayList<>();
        try{
            executeQuery(query);

            while (rs.next()) {
                Friend friend = new Friend(rs.getInt("id"), rs.getInt("user_id"), rs.getInt("friend_id"));
                friends.add(friend);
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        return friends;
    }

    public ArrayList<QuizHistory> GetUserQuizHistory(int user_id) {
        String query = "SELECT * FROM quiz_history";
        if(user_id != -1){
            query = String.format("SELECT * FROM quiz_history q where q.user_id = %d", user_id);
        }

        ArrayList<QuizHistory> quizHistory = new ArrayList<>();
        try{
            executeQuery(query);

            while (rs.next()) {
                QuizHistory qh = new QuizHistory(rs.getInt("id"), rs.getDouble("score"), rs.getInt("user_id"), rs.getInt("user_id"), rs.getInt("time_taken"));
                quizHistory.add(qh);
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        return quizHistory;
    }
}

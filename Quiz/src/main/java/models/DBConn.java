package Quiz.src.main.java.models;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private void executeQuery(String q) {
        try{
            System.out.println(String.format("Executing querry: %s", q));
            rs = stmt.executeQuery(q);
        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private void executeUpdate(String u) {
        try{
            System.out.println(String.format("Executing update: %s", u));
            stmt.executeUpdate(u);
        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
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

        String q = String.format("INSERT INTO achievements (achievement, to_earn)  VALUES('%s', '%s')", a.getAchievementBody(), a.getAchievementToEarn());
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

        String q = String.format("INSERT INTO users (username, password_hash, role)  VALUES('%s', '%s', '%s')", u.getUsername(), u.getPasswordHash(), u.getRole());
        executeUpdate(q);
    }

    public void insertFriend(Friend f){
        if(f == null)
            throw new RuntimeException("Provided Friend is null");

        String q = String.format("INSERT INTO friends (user_id, friend_id)  VALUES(%d, %d)", f.getUser_id(), f.getFriend_id());
        executeUpdate(q);
    }

    public void insertQuizHistory(QuizHistory qh){
        if(qh == null)
            throw new RuntimeException("Provided Quiz History is null");

        String q = String.format("INSERT INTO quiz_history (score, quiz_id, user_id, time_taken)  VALUES(%f, %d, %d, %d)", qh.getScore(), qh.getQuiz_id(), qh.getUser_id(), qh.getTime_taken());
        executeUpdate(q);
    }

    public void updateNotification(Notification n){
        int nId = n.getId();
        int targetId = n.getReceiverId();
        int senderId = n.getSenderId();
        String notifType = n.getNotifType();
        String notifBody = n.getNotifBody();

        String q = "UPDATE notifications n\n" +
                "SET " + String.format("n.receiver_id = %d, n.sender_id = %d, n.notif_type = '%s', n.notif_body = '%s'", targetId, senderId, notifType, notifBody) +
                "WHERE " + String.format("n.id = %d", nId);
        executeUpdate(q);
    }

    public ArrayList<Notification> getNotifications(int receiverId, int senderId, String notifType) {
        String q = "SELECT * FROM notifications n";

        boolean needsAnd = false;

        if(receiverId != -1 || senderId != -1 || notifType != ""){
            q += " WHERE";
        }
        if(receiverId != -1){
            q += String.format(" n.receiver_id = %d", receiverId);
            needsAnd = true;
        }
        if(senderId != -1){
            if(needsAnd){
                q += " AND";
            }
            q += String.format(" n.sender_id = %d", senderId);
            needsAnd = true;
        }
        if(notifType != ""){
            if(needsAnd){
                q += " AND";
            }
            q += String.format(" n.notif_type = '%s'", notifType);
        }
        q += " ORDER BY n.id DESC";

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
            e.printStackTrace();
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
            e.printStackTrace();
        }
        return selection;
    }
    public ArrayList<QuizHistory> getUserRecentQuizHistory(int user_id) {
        String query = "SELECT * FROM quiz_history ORDER BY id DESC LIMIT 5";
        if(user_id != -1){
            query = String.format("SELECT * FROM quiz_history q where q.user_id = %d ORDER BY id DESC LIMIT 5", user_id);
        }

        ArrayList<QuizHistory> quizHistory = new ArrayList<>();
        try{
            executeQuery(query);

            while (rs.next()) {
                QuizHistory qh = new QuizHistory(rs.getInt("id"), rs.getDouble("score"), rs.getInt("quiz_id"), rs.getInt("user_id"), rs.getInt("time_taken"));
                quizHistory.add(qh);
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return quizHistory;
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
                Achievement achievement = new Achievement(rs.getInt("id"), rs.getString("achievement"), rs.getString("to_earn"));
                achievement.setAchievementIcon(rs.getString("icon"));
                selection.add(achievement);
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return selection;
    }

    public ArrayList<Achievement> getUserAchievements(int user_id) {
        String q = String.format("SELECT a.id, a.achievement, a.to_earn, a.icon \n" +
                "FROM user_achievements u JOIN achievements a ON(u.achievement_id = a.id) \n" +
                "WHERE u.user_id = %d;", user_id);

        ArrayList<Achievement> selection = new ArrayList<>();
        try{
            executeQuery(q);

            while (rs.next()) {
                Achievement userAchievement = new Achievement(rs.getInt("id"), rs.getString("achievement"), rs.getString("to_earn"));
                userAchievement.setAchievementIcon(rs.getString("icon"));
                selection.add(userAchievement);
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
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
                User user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password_hash"), rs.getString("role"));
                user.setPfpLink(rs.getString("pfp"));
                selection.add(user);
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return selection;
    }

    public boolean isAdmin(int id) {
        String query = "SELECT * FROM users";
        if(id != -1){
            query = String.format("SELECT * FROM users u where u.id = %d", id);
        }

        ArrayList<User> selection = new ArrayList<>();
        try{
            executeQuery(query);

            while (rs.next()) {
                User user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password_hash"), rs.getString("role"));
                user.setPfpLink(rs.getString("pfp"));
                selection.add(user);
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        if (selection.isEmpty()){
            return false;
        }
        return selection.get(0).isAdmin();
    }

    public ArrayList<User> getUsersByUsername(String username) {
        String query = "SELECT * FROM users";
            query = String.format("SELECT * FROM users u where u.username = '%s'", username);

        ArrayList<User> selection = new ArrayList<>();
        try{
            executeQuery(query);

            while (rs.next()) {
                User user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password_hash"), rs.getString("role"));
                user.setPfpLink(rs.getString("pfp"));
                selection.add(user);
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return selection;
    }

    public ArrayList<Friend> getUserFriends(int user_id) {
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
            e.printStackTrace();
        }
        return friends;
    }

    public void removeUser(int user_id){
        String q = String.format("DELETE FROM users u WHERE (u.id = %d)", user_id);

        try{
            executeUpdate(q);

        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void makeUserAdmin(int user_id){
        String q = String.format("UPDATE users SET role = 'admin' WHERE id = %d;", user_id);

        try{
            executeUpdate(q);

        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void removeUserQuizes(int user_id){
        String q = String.format("DELETE FROM quizzes q WHERE (q.creator_id = %d)", user_id);

        try{
            executeUpdate(q);

        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void removeUserNotifications(int user_id){
        String q = String.format("DELETE FROM notifications n WHERE (n.receiver_id = %d) OR (n.sender_id = %d)", user_id, user_id);

        try{
            executeUpdate(q);

        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void removeUserAchievements(int user_id){
        String q = String.format("DELETE FROM user_achievements a WHERE (a.user_id = %d)", user_id);

        try{
            executeUpdate(q);

        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void removeQuizQuestions(int quiz_id){
        String q = String.format("DELETE FROM questions q WHERE (q.quiz_id = %d)", quiz_id);

        try{
            executeUpdate(q);

        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void removeQuizAnswers(int quiz_id){
        String q = String.format("DELETE FROM answers q WHERE (q.question_id = %d)", quiz_id);

        try{
            executeUpdate(q);

        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void removeQuizHistory(int quiz_id){
        String q = String.format("DELETE FROM quiz_history q WHERE (q.quiz_id = %d)", quiz_id);

        try{
            executeUpdate(q);

        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void removeFriend(int user_id, int friend_id){
        String q = String.format("DELETE FROM friends WHERE (user_id = %d AND friend_id = %d) OR (user_id = %d AND friend_id = %d)", user_id, friend_id, friend_id, user_id);

        try{
            executeUpdate(q);

        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean areFriends(int user_id, int friend_id){
        String q = "SELECT COUNT(f.friend_id) AS are_friends\n" +
                "FROM friends f \n" +
                "WHERE " + String.format("f.user_id = %d AND f.friend_id = %d", user_id, friend_id);
        try{
            executeQuery(q);
            rs.next();

            int friends = rs.getInt("are_friends");
            System.out.println(friends);

            return friends > 0 ? true : false;
        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<QuizHistory> GetUserQuizHistory(int user_id) {
        String query = "SELECT qh.id,\n" +
                              "qh.score, \n" +
                              "qh.quiz_id, \n" +
                              "qh.time_taken, \n" +
                              "q.creator_id,\n" +
                              "q.quiz_name,\n" +
                              "q.is_single_page,\n" +
                              "q.can_be_practiced,\n" +
                              "q.description,\n" +
                              "u.username\n" +
                       "FROM quiz_history qh JOIN quizzes q ON(qh.quiz_id = q.id) JOIN users u ON(q.creator_id = u.id)";
        if(user_id != -1){
            query += String.format(" WHERE qh.user_id = %d", user_id);
        }
        query += " ORDER BY qh.id DESC";

        ArrayList<QuizHistory> quizHistory = new ArrayList<>();
        try{
            executeQuery(query);

            while (rs.next()) {
                QuizHistory qh = new QuizHistory(rs.getInt("id"), rs.getDouble("score"), rs.getInt("quiz_id"), rs.getInt("creator_id"), rs.getInt("time_taken"));
                Quiz q = new Quiz(qh.getQuiz_id(), rs.getInt("creator_id"), rs.getString("quiz_name"), rs.getString("description"), rs.getBoolean("is_single_page"), rs.getBoolean("can_be_practiced"));
                q.creatorName = rs.getString("username");
                qh.setQuiz(q);
                quizHistory.add(qh);
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return quizHistory;
    }

    public ArrayList<Quiz> getQuizzes(){
        String quizzesQuery = "SELECT * FROM quizzes;";
        ArrayList<Quiz> selection = new ArrayList<>();
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://" + server, account, password);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("USE " + database);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(quizzesQuery);

            while (rs.next()) {
                Quiz quiz = new Quiz(rs.getInt("id"),
                        rs.getInt("creator_id"),
                        rs.getString("quiz_name"),
                        rs.getString("description"),
                        rs.getBoolean("is_single_page"),
                        rs.getBoolean("can_be_practiced")
                );
                selection.add(quiz);
            }
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return  null;
        }
        return selection;
    }
    public Quiz getQuiz(int id){
        String quizzesQuery = String.format("SELECT * FROM quizzes WHERE id = %d",id);
        Quiz result = null;
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://" + server, account, password);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("USE " + database);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(quizzesQuery);
            rs.next();
             result = new Quiz(rs.getInt("id"),
                    rs.getInt("creator_id"),
                    rs.getString("quiz_name"),
                    rs.getString("description"),
                    rs.getBoolean("is_single_page"),
                    rs.getBoolean("can_be_practiced")
                );

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return  null;
        }
        return result;
    }

    public ArrayList<Integer> getYourBestPerformance(int quiz_id,int userid){
        String query=String.format("SELECT score " +
                "FROM quiz_history " +
                "WHERE quiz_id = %d AND user_id = %d " +
                "ORDER BY score DESC " +
                "LIMIT 3;",quiz_id,userid);

        ArrayList<Integer> scores = new ArrayList<>();
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://" + server, account, password);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("USE " + database);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int result = rs.getInt("user_id");;
                scores.add(result);
            }
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return  null;
        }
        return scores;
    }
    public ArrayList<Integer> getLastQuizPerformers(int quiz_id){
        String query="SELECT  user_id " +
                "FROM quiz_history " +
                "ORDER BY take_date DESC " +
                "LIMIT 3;";

        ArrayList<Integer> users = new ArrayList<>();
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://" + server, account, password);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("USE " + database);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int result = rs.getInt("user_id");;
                users.add(result);
            }
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return  null;
        }
        return users;
    }
    public ArrayList<Integer> getBestPerformance(int quiz_id,boolean today){
        String query;
        if (today){
            query = "SELECT user_id, MAX(score) AS max_score " +
                    "FROM quiz_history " +
                    "WHERE DATE(take_date) = CURDATE() " +
                    "GROUP BY user_id " +
                    "ORDER BY total_score DESC " +
                    "LIMIT 3;";
        }else{
            query = "SELECT user_id, MAX(score) AS max_score " +
                    "FROM quiz_history " +
                    "WHERE DATE_ADD(CURDATE(), INTERVAL 0 DAY) = DATE(user_id) " +
                    "GROUP BY user_id " +
                    "ORDER BY max_score DESC " +
                    "LIMIT 3;";

        }

        ArrayList<Integer> users = new ArrayList<>();
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://" + server, account, password);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("USE " + database);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int result = rs.getInt("user_id");;
                users.add(result);
            }
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return  null;
        }
        return users;
    }
    public ArrayList<Question> getQuestions(int quiz_id){
        String questionQuery = String.format("SELECT * FROM questions where quiz_id = %d",quiz_id);
        ArrayList<Question> selection = new ArrayList<>();
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://" + server, account, password);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("USE " + database);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(questionQuery);

            while (rs.next()) {
                Question question  = new Question(rs.getInt("id"),
                        rs.getInt("quiz_id"),
                        rs.getString("question"),
                        rs.getInt("question_type")
                );
                selection.add(question);
            }
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return  null;
        }
        return selection;
    }
    public ArrayList<Answer> getAnswers( int question_id){
        String questionQuery = String.format("SELECT * FROM answers where question_id = %d",question_id);
        ArrayList<Answer> selection = new ArrayList<>();
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://" + server, account, password);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("USE " + database);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(questionQuery);

            while (rs.next()) {
                Answer answer  = new Answer(rs.getInt("id"),
                        rs.getInt("question_id"),
                        rs.getString("answer"),
                        rs.getBoolean("is_correct")
                );
                selection.add(answer);
            }
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return  null;
        }
        return selection;
    }

    public ArrayList<Quiz> getQuizzesByCreator(int creator_id){
        String query = String.format("SELECT * FROM quizzes q where q.creator_id = %d", creator_id);

        ArrayList<Quiz> createdQuizzes = new ArrayList<>();
        try{
            executeQuery(query);

            while (rs.next()) {
                Quiz q = new Quiz(rs.getInt("id"), rs.getInt("creator_id"), rs.getString("quiz_name"), rs.getString("description"), rs.getBoolean("is_single_page"), rs.getBoolean("can_be_practiced"));
                createdQuizzes.add(q);
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return createdQuizzes;
    }

    public Quiz getQuizById(int id){
        String query = String.format("SELECT * FROM quizzes q where q.id = %d", id);

        Quiz q = null;
        try{
            executeQuery(query);

            while (rs.next()) {
                q = new Quiz(rs.getInt("id"), rs.getInt("creator_id"), rs.getString("quiz_name"), rs.getString("description"), rs.getBoolean("is_single_page"), rs.getBoolean("can_be_practiced"));
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return q;
    }

    public ArrayList<Quiz> getRecentlyCreatedQuizzes(int creator_id){
        String query = String.format("SELECT * FROM quizzes ORDER BY id DESC LIMIT 5");
        if(creator_id != -1) {
            query = String.format("SELECT * FROM quizzes WHERE creator_id = %d ORDER BY id DESC LIMIT 5", creator_id);
        }

        ArrayList<Quiz> rq = new ArrayList<>();
        try{
            executeQuery(query);

            while (rs.next()) {
                Quiz q = new Quiz(rs.getInt("id"), rs.getInt("creator_id"), rs.getString("quiz_name"), rs.getString("description"), rs.getBoolean("is_single_page"), rs.getBoolean("can_be_practiced"));
                rq.add(q);
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return rq;
    }

    public ArrayList<Quiz> getPopularQuizzes(){
        String query = String.format("SELECT quiz_id, COUNT(*) AS frequency FROM quiz_history GROUP BY quiz_id ORDER BY frequency DESC LIMIT 5");
        Map<Integer, Integer> quizFrequencyMap = new HashMap<>();
        ArrayList<Quiz> popularQuizzes = new ArrayList<>();
        try{
            executeQuery(query);

            while (rs.next()) {
                int quizId = rs.getInt("quiz_id");
                int frequency = rs.getInt("frequency");
                quizFrequencyMap.put(quizId, frequency);
            }
            for (int quizId : quizFrequencyMap.keySet()) {
                Quiz quiz = getQuizById(quizId);
                if (quiz != null) {
                    popularQuizzes.add(quiz);
                }
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return popularQuizzes;
    }
}

package Quiz;

import Quiz.src.main.java.HelperMethods.AnswerChecker;
import Quiz.src.main.java.HelperMethods.PassHasher;
import Quiz.src.main.java.models.*;
import Quiz.src.main.java.servlets.CreateQuizJson;
import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Tests extends TestCase{


    public void testDBConn() {
        String AchBody = "test achievement 1";
        String AchToEarn = "get tested";

        String AnnBody = "got tested";

        String NotifType = "test";
        String NotifBody = "tap tap tap";

        int userId = 1;

        DBConn dbConn = new DBConn();

        var achBefore = dbConn.getAchievements(-1);
        var annBefore = dbConn.getAnnouncements();
        var notsBefore = dbConn.getNotifications(-1,-1,"");
        var userAchBefore = dbConn.getUserAchievements(userId);

        Achievement achievement = new Achievement(-1, AchBody, AchToEarn);
        dbConn.insertAchievement(achievement);
        var achievements = dbConn.getAchievements(-1);
        int testAchId = achievements.get(achievements.size()-1).getId();

        Announcement announcement = new Announcement(-1, AnnBody);
        Notification notification = new Notification(-1, userId, userId, NotifType, NotifBody);
        UserAchievement userAchievement = new UserAchievement(-1, userId, testAchId);

        dbConn.insertAnnouncement(announcement);
        dbConn.insertNotification(notification);
        dbConn.insertUserAchievement(userAchievement);

        var achAfter = dbConn.getAchievements(-1);
        var annAfter = dbConn.getAnnouncements();
        var notsAfter = dbConn.getNotifications(-1,-1,"");
        var userAchAfter = dbConn.getUserAchievements(userId);

        assertEquals(achBefore.size() + 1, achAfter.size());
        assertEquals(annBefore.size() + 1, annAfter.size());
        assertEquals(notsBefore.size() + 1, notsAfter.size());
        assertEquals(userAchBefore.size() + 1, userAchAfter.size());

        var lastAch = achAfter.get(achAfter.size() - 1);
        var lastAnn = annAfter.get(annAfter.size() - 1);
        var lastNot = notsAfter.get(notsAfter.size() - 1);
        var lastUsAch = userAchAfter.get(userAchAfter.size() - 1);

        assertTrue(AchBody.equals(lastAch.getAchievementBody()));
        assertTrue(AchToEarn.equals(lastAch.getAchievementToEarn()));
        assertTrue(AnnBody.equals(lastAnn.getAnnouncementBody()));
//        assertTrue(NotifType.equals(lastNot.getNotifType()));
//        assertTrue(NotifBody.equals(lastNot.getNotifBody()));
        assertEquals(lastAch.getId(), achAfter.size());
        assertEquals(lastAnn.getId(), annAfter.size());
//        assertEquals(lastNot.getId(), notsAfter.size());

        User user = new User(1555555,"mefe", "eacd2617f105704f51c912099316c7aece2df8ef","user",false);
        dbConn.insertUser(user);
        dbConn.getUsersByUsername("mefe").get(0);
        User user1 = dbConn.getUsersByUsername("mefe").get(0);
        assertTrue(dbConn.getUsers(user1.getId()).get(0).getUsername().equals(user1.getUsername()));
        dbConn.removeUser(user1.getId());


        dbConn.restartDBbase("./oop_proj.sql");


    }

    public void testPasHasher(){
        PassHasher passHasher = new PassHasher();
        assertTrue(PassHasher.hashPassword("giorgi").equals(PassHasher.hashPassword("giorgi")));
        assertTrue(!PassHasher.hashPassword("giorgi").equals(PassHasher.hashPassword("giorgi ")));
    }
    public void testDBExceptions(){
        DBConn dbConn = new DBConn();

        try{
            dbConn.insertAchievement(null);
        } catch (Exception e) {
            assertTrue(e.getMessage().equals("Provided Achievement is null"));
        }

        try{
            dbConn.insertAnnouncement(null);
        } catch (Exception e) {
            assertTrue(e.getMessage().equals("Provided Announcement is null"));
        }

        try{

        } catch (Exception e) {
            assertTrue(e.getMessage().equals("Provided Announcement is null"));
        }

        try{
            dbConn.insertNotification(null);
        } catch (Exception e) {
            assertTrue(e.getMessage().equals("Provided Notification is null"));
        }

        try{
            dbConn.insertUserAchievement(null);
        } catch (Exception e) {
            assertTrue(e.getMessage().equals("Provided UserAchievement is null"));
        }




    }
    public void testDbQuizHistoryDB(){
        DBConn con = new DBConn();
        QuizHistory qh1 = new QuizHistory(0,30,1,1,15);
        QuizHistory qh2 = new QuizHistory(0,35,1,2,18);
        QuizHistory qh3 = new QuizHistory(0,15,1,3,11);
        QuizHistory[] histories = new QuizHistory[]{qh3,qh2,qh1};
        con.insertQuizHistory(qh1);
        con.insertQuizHistory(qh2);
        con.insertQuizHistory(qh3);
        List<Map.Entry<Integer, Double>> result = con.getLastQuizPerformers(1);
        assertEquals(3,result.size());
        int i=0;
        List <Integer> ids = result.stream().map(Map.Entry<Integer, Double>::getKey).toList();
        List <Double> scores = result.stream().map(Map.Entry<Integer, Double>::getValue).toList();
        for(QuizHistory history : histories){
            assertTrue(ids.contains(history.getUser_id()));
        }
        for(QuizHistory history : histories){
            assertTrue(scores.contains(history.getScore()));
        }
        con.restartDBbase("./oop_proj.sql");
        con.closeDBConn();
    }

    public void testDBHighScore(){
        DBConn con = new DBConn();
        QuizHistory qh1 = new QuizHistory(0,110,1,1,15);
        QuizHistory qh2 = new QuizHistory(0,120,1,2,18);
        QuizHistory qh3 = new QuizHistory(0,130,1,3,11);
        QuizHistory[] histories = new QuizHistory[]{qh3,qh2,qh1};
        con.insertQuizHistory(qh1);
        con.insertQuizHistory(qh2);
        con.insertQuizHistory(qh3);
        List<Map.Entry<Integer, Double>> result = con.getBestPerformance(1,true);
        assertEquals(3,result.size());

        List <Integer> ids = result.stream().map(Map.Entry<Integer, Double>::getKey).toList();
        List <Double> scores = result.stream().map(Map.Entry<Integer, Double>::getValue).toList();
        int i = 0;
        for(QuizHistory history : histories){
            assertEquals(history.getScore(),scores.get(i++));
        }
        i=0;
        ;
        for(QuizHistory history : histories){
            assertEquals(history.getUser_id(),(int)ids.get(i++));
        }
        con.restartDBbase("./oop_proj.sql");
        con.closeDBConn();
    }

    public void testDBQuestionsAndAnswers(){
        DBConn con = new DBConn();
        con.restartDBbase("./oop_proj.sql");
        var questions = con.getQuestions(1);
        assertEquals(7,questions.size());

        assertTrue("Sauketeso Gundi msoflioshi?".equalsIgnoreCase(questions.get(1).question));
        var answer = con.getAnswers(questions.get(1).id,true);
        assertEquals(1,answer.size());
        assertTrue("Milani".equalsIgnoreCase(answer.get(0).answer));
        ArrayList<String> testAnswers = new ArrayList<>();
        testAnswers.add("Milani");
        assertTrue(AnswerChecker.checkAnswerBool(questions.get(1).id, testAnswers));
        assertEquals(AnswerChecker.checkAnswer(questions.get(1).id, testAnswers), testAnswers);
        con.closeDBConn();
    }

    public void

//    public void testJson(){
//        String jsonString = "{\n" +
//                "  \"creator_id\": 123,\n" +
//                "  \"quiz_name\": \"Random Quiz\",\n" +
//                "  \"category\": \"Animals\",\n" +
//                "  \"description\": \"A quiz with random questions and answers\",\n" +
//                "  \"tags\": [\n" +
//                "    \"super\",\n" +
//                "    \"funny\",\n" +
//                "    \"ketchup\"\n" +
//                "  ],\n" +
//                "  \"is_single_page\": false,\n" +
//                "  \"can_be_practiced\": true,\n" +
//                "  \"rand_question_order\": false,\n" +
//                "  \"questions\": [\n" +
//                "    {\n" +
//                "      \"question_type\": 2,\n" +
//                "      \"question\": \"What is the capital of France?\",\n" +
//                "      \"answers\": [\n" +
//                "        {\n" +
//                "          \"answer\": \"Paris\",\n" +
//                "          \"is_correct\": true\n" +
//                "        },\n" +
//                "        {\n" +
//                "          \"answer\": \"London\",\n" +
//                "          \"is_correct\": false\n" +
//                "        },\n" +
//                "        {\n" +
//                "          \"answer\": \"Berlin\",\n" +
//                "          \"is_correct\": false\n" +
//                "        }\n" +
//                "      ]\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"question_type\": 2,\n" +
//                "      \"question\": \"Which of the following is a mammal?\",\n" +
//                "      \"answers\": [\n" +
//                "        {\n" +
//                "          \"answer\": \"Shark\",\n" +
//                "          \"is_correct\": false\n" +
//                "        },\n" +
//                "        {\n" +
//                "          \"answer\": \"Dolphin\",\n" +
//                "          \"is_correct\": true\n" +
//                "        },\n" +
//                "        {\n" +
//                "          \"answer\": \"Salmon\",\n" +
//                "          \"is_correct\": false\n" +
//                "        }\n" +
//                "      ]\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"question_type\": 5,\n" +
//                "      \"question\": \"What is 7 + 3?\",\n" +
//                "      \"answers\": [\n" +
//                "        {\n" +
//                "          \"answer\": \"10\",\n" +
//                "          \"is_correct\": true\n" +
//                "        },\n" +
//                "        {\n" +
//                "          \"answer\": \"9\",\n" +
//                "          \"is_correct\": false\n" +
//                "        },\n" +
//                "        {\n" +
//                "          \"answer\": \"12\",\n" +
//                "          \"is_correct\": false\n" +
//                "        }\n" +
//                "      ]\n" +
//                "    }\n" +
//                "  ]\n" +
//                "}";
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        try{
//            JSONQuiz jsonQuiz = objectMapper.readValue(jsonString, JSONQuiz.class);
//            System.out.println(jsonQuiz.quiz_name);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}



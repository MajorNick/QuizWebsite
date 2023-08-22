package Quiz;

import Quiz.src.main.java.HelperMethods.PassHasher;
import Quiz.src.main.java.models.*;
import Quiz.src.main.java.models.enums.QuestionType;
import junit.framework.TestCase;

import static org.junit.jupiter.api.Assertions.assertThrows;

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


        dbConn.restartDBbase("C:/Users/giorgi/IdeaProjects/oop-final-project-placefolder/oop_proj.sql");


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

public void testQuestionTypeValues() {
    assertEquals(0, QuestionType.QUESTION_RESPONSE.value);
    assertEquals(1, QuestionType.FILL_IN_THE_BLANK.value);
    assertEquals(2, QuestionType.MULTIPLE_CHOICE.value);
    assertEquals(3, QuestionType.PICTURE_RESPONSE.value);
    assertEquals(4, QuestionType.MULTI_ANSWER.value);
    assertEquals(5, QuestionType.MULTI_AN_CHOICE.value);
    assertEquals(6, QuestionType.MATCHING.value);
}

    public void testQuestionTypeFromInt() {
        assertEquals(QuestionType.QUESTION_RESPONSE, QuestionType.fromInt(0));
        assertEquals(QuestionType.FILL_IN_THE_BLANK, QuestionType.fromInt(1));
        assertEquals(QuestionType.MULTIPLE_CHOICE, QuestionType.fromInt(2));
        assertEquals(QuestionType.PICTURE_RESPONSE, QuestionType.fromInt(3));
        assertEquals(QuestionType.MULTI_ANSWER, QuestionType.fromInt(4));
        assertEquals(QuestionType.MULTI_AN_CHOICE, QuestionType.fromInt(5));
        assertEquals(QuestionType.MATCHING, QuestionType.fromInt(6));
    }

    public void testQuestionTypeFromIntInvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            QuestionType.fromInt(7);
        });
    }
}

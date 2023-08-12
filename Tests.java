import Quiz.src.main.java.models.*;
import junit.framework.TestCase;

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
        assertTrue(NotifType.equals(lastNot.getNotifType()));
        assertTrue(NotifBody.equals(lastNot.getNotifBody()));

        assertEquals(lastAch.getId(), achAfter.size());
        assertEquals(lastAnn.getId(), annAfter.size());
        assertEquals(lastNot.getId(), notsAfter.size());

        dbConn.closeDBConn();

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
            dbConn.insertNotification(null);
        } catch (Exception e) {
            assertTrue(e.getMessage().equals("Provided Notification is null"));
        }

        try{
            dbConn.insertUserAchievement(null);
        } catch (Exception e) {
            assertTrue(e.getMessage().equals("Provided UserAchievement is null"));
        }

        dbConn.closeDBConn();
    }

}

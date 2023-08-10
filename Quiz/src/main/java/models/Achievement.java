package Quiz.src.main.java.models;

public class Achievement {
    private int Id;
    private String AchievementBody;

    public Achievement(int id, String achievementBody){
        Id = id;
        AchievementBody = achievementBody;
    }

    public int getId() {
        return Id;
    }

    public String getAchievementBody() {
        return AchievementBody;
    }
}
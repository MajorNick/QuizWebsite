package models;

public class models {
}

public class notifications {
    public int id;
    public int receiver_id;
    public int sender_id;
    public string notif_type;
    public string notif_body;
}

public class announcments {
    public int id;
    public string achievement;
}

public class achievements {
    public int id;
    public string achievement;
}

public class user_achievements {
    public int id;
    public int user_id;
    public int achievement_id;
}

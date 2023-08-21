package Quiz.src.main.java.HelperMethods;

import java.sql.Time;
import java.util.ArrayList;

public class TimeInStrings {
    private String time;
    public TimeInStrings(String time){
        this.time = time;
    }

    public int getSeconds(){
        return Integer.parseInt(time.split(" ")[2]);
    }
    public int getMinutes(){
        return Integer.parseInt(time.split(" ")[1]);
    }
    public int getHours(){
        return Integer.parseInt(time.split(" ")[0]);
    }
    public static String timeToStrings(int hours, int minutes, int seconds){
        String time = "";
        time+=hours;
        time+=" ";
        time+=minutes;
        time+=" ";
        time+= seconds;
        return time;
    }

}

import java.awt.event.*;
import javax.swing.*;
public class Module{
    public Timer timer;
    public int tick;
    public double clockTime;
    public int seconds;
    public int minutes;
    public int hours;
    public String timeTaken;
//    public static String timeTaken;
    
    public boolean completed = false;
    
    public boolean getCompleted(){
        return completed;
    }

    public String getTimeTaken(){
        return timeTaken;
    }
}

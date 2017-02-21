package qa.edu.qu.cmps312.todolist;

import java.sql.Time;
import java.util.Date;

public class toDo {

    private String title;
    private Integer priority;
    private String  date;
    private String time;
    private Boolean done;

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public toDo( String title, Integer priority, String date, String time, Boolean done) {
        this.title = title;
        this.priority = priority;
        this.date = date;
        this.time = time;
        this.done = done;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}

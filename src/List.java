import java.util.ArrayList;

public class List {
    private ArrayList<Task> tasks = new ArrayList<>();

    public String toString(){
        StringBuilder sOut = new StringBuilder();
        for (Task t : tasks){
            sOut.append(t.toString()).append("\n");
        }
        return sOut.toString();
    }
    public void addTask(String text, String status){
       tasks.add(new Task(text, status));
    }
}
class Task {
    private String text;
    private String status;

    Task(String text, String status) {
        this.text = text;
        this.status = status;
    }
    public String toString(){
        return String.format("[%s] %s", status, text);
    }
}

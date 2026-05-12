import java.util.ArrayList;

public class List {
    private ArrayList<Task> tasks = new ArrayList<>();
    /**
     * @return String
     */
    public String toString(){
        StringBuilder sOut = new StringBuilder();
        for (Task t : tasks){
            sOut.append(t.toString()).append("\n");
        }
        return sOut.toString();
    }
    /**
     * Adds entries
     * @param text
     * @param status
     */
    public void addTask(String text, String status){
       tasks.add(new Task(text, status));
    }
    /**
     * Task getter
     * @return tasks
     */
    public ArrayList<Task> getTasks(){
        return tasks;
    }
    /**
     * To edit prev entries via edit button
     * @param index
     * @param newText
     * @param newStatus
     */
    public void updateTask(int index, String newText, String newStatus){
        if(index >= 0 && index < tasks.size()){
            Task t = tasks.get(index);
            t.text = newText;
            t.status = newStatus;
        }
    }

    public static class Task {
        private String text;
        private String status;
        /**
         * Text getter
         * @return text
         */
        public String getText(){
            return text;
        }
        /**
         * Status getter
         * @return status
         */
        public String getStatus(){
            return status;
        }
        /**
         * Task Class constructor
         * @param text
         * @param status
         */
        Task(String text, String status) {
            this.text = text;
            this.status = status;
        }
        /**
         * @return String
         */
        public String toString(){
        return String.format("[%s] %s", status, text);
        }
    }
}

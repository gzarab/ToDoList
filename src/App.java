import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.util.Calendar;
/**
 * Weekly to do list
 * @author Gabriel Zarabanda
 * @since 05/11/2026
 */
public class App {

    private static ArrayList<List> myToDo = new ArrayList<>();
    private static ArrayList<JTextArea> displayArea = new ArrayList<>();
    private static String[] weekDays=  new String[8];
    /**
     * Gui for Weekly to do list
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Calendar cal = Calendar.getInstance();//invoques calendar class for box titles
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        SimpleDateFormat form = new SimpleDateFormat("EEEE MM/dd");

        /**
         * adds days to todo arraylist, except for when i == 0
         */
        weekDays[0] = "Notes";
        for (int i = 0; i < 8; i++){
            weekDays[i] = (i == 0) ? "Notes" : form.format(cal.getTime());
            myToDo.add(new List());
            if (i > 0 )
                cal.add(Calendar.DATE, 1);
        }

        /**
         * Creates JFrame
         */
        JFrame mainframe = new JFrame("Weekly To Do List");
        mainframe.setSize(600,600);
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainframe.setLayout(new BorderLayout(10,10)); // mainframe border
        
        JLabel title = new JLabel("Weekly Planner", SwingConstants.CENTER); //Title label
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        mainframe.add(title, BorderLayout.NORTH);

        JPanel dayGrid = new JPanel(new GridLayout(2,4,10,10)); //creation of 8 panels
        for (int i = 0; i < 8; i++){
        JTextArea display = new JTextArea();//for text display
            display.setEditable(false);
            display.setLineWrap(true);
            display.setWrapStyleWord(true);
            displayArea.add(display);

            JScrollPane scroll = new JScrollPane(display); //internal boxes display scroll bar
            scroll.setBorder(BorderFactory.createTitledBorder(weekDays[i]));
            dayGrid.add(scroll);
        }
        mainframe.add(dayGrid, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new BorderLayout(5,5)); //input panel
        JTextField entryField = new JTextField();
        JComboBox<String> dayPicker = new JComboBox<>(weekDays); //combobox selection from weekdays
        
        String[] statuses = {"Pending", "In Progress", "Completed"};
        JComboBox<String> statusPicker = new JComboBox<>(statuses); //combobox selection of status

        JButton saveButton = new JButton("Save"); //Save button
        saveButton.addActionListener(e ->  {
            int index = dayPicker.getSelectedIndex(); 
            String text = entryField.getText().trim(); 
            String status = (String) statusPicker.getSelectedItem(); 
            try {
                if(text.isEmpty()){
                    throw new Exception("Entry cannot be blank"); //check if text is blank
                }
                myToDo.get(index).addTask(text, status);
                displayArea.get(index).setText(myToDo.get(index).toString());
                JOptionPane.showMessageDialog(mainframe, "Saved!");
                entryField.setText("");
                statusPicker.setSelectedIndex(0);
            }
            catch (Exception exception) {
                JOptionPane.showMessageDialog(mainframe, exception.getMessage());// gets exception message
            }
        });

        JButton ediButton = new JButton("Edit"); //edit button
        ediButton.addActionListener(e ->{
            int dayIndex = dayPicker.getSelectedIndex();
            List selectedList = myToDo.get(dayIndex);
            if(selectedList.getTasks().isEmpty()){
                JOptionPane.showMessageDialog(mainframe, "No Tasks to edit");
                return;
            }
            Object[] taskOptions = selectedList.getTasks().toArray();
            List.Task selectedTask = (List.Task) JOptionPane.showInputDialog(mainframe, "Select Task", "Edit Task", JOptionPane.QUESTION_MESSAGE, null, taskOptions, taskOptions[0]);
            if (selectedTask != null){
                int taskIndex = selectedList.getTasks().indexOf(selectedTask);
                String newText =JOptionPane.showInputDialog(mainframe, "Update Task", selectedTask.getText());
                if (newText != null && !newText.trim().isEmpty()){
                    String newStatus = (String) JOptionPane.showInputDialog(mainframe, "Update Status:", "Edit Status", JOptionPane.QUESTION_MESSAGE, null, statuses, selectedTask.getStatus());
                    if (newStatus != null){
                        selectedList.updateTask(taskIndex, newText, newStatus);
                        displayArea.get(dayIndex).setText(selectedList.toString());
                        JOptionPane.showMessageDialog(mainframe, "Task Updated");
                    }
                }
            }
        });

        inputPanel.add(new JLabel("New Entry:"), BorderLayout.WEST);
        inputPanel.add(entryField, BorderLayout.CENTER);

        JPanel buttonGroup = new JPanel(new FlowLayout());
        buttonGroup.add(new JLabel("Status:"));
        buttonGroup.add(statusPicker);
        buttonGroup.add(dayPicker);
        buttonGroup.add(saveButton);
        buttonGroup.add(ediButton);
        inputPanel.add(buttonGroup, BorderLayout.EAST);
        
        mainframe.add(inputPanel, BorderLayout.SOUTH);
        mainframe.setSize(700, 500);
        mainframe.setVisible(true);
    }
}
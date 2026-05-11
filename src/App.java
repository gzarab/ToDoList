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

public class App {

    private static ArrayList<List> myToDo = new ArrayList<>();
    private static ArrayList<JTextArea> displayArea = new ArrayList<>();
    private static String[] weekDays=  new String[8];
    public static void main(String[] args) throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        SimpleDateFormat form = new SimpleDateFormat("EEEE MM/dd");

        weekDays[0] = "Notes";
        for (int i = 0; i < 8; i++){
            weekDays[i] = (i == 0) ? "Notes" : form.format(cal.getTime());
            myToDo.add(new List());
            if (i > 0 )
                cal.add(Calendar.DATE, 1);
        }

        //main frame
        JFrame mainframe = new JFrame("Weekly To Do List");
        mainframe.setSize(600,600);
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //borders
        mainframe.setLayout(new BorderLayout(10,10));
        //top panels
        JLabel title = new JLabel("Weekly Planner", SwingConstants.CENTER);
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        mainframe.add(title, BorderLayout.NORTH);

        //boxes
        JPanel dayGrid = new JPanel(new GridLayout(4,2,10,10));
        for (int i = 0; i < 8; i++){
            JTextArea display = new JTextArea();//for display boxes
            display.setEditable(false);
            display.setLineWrap(true);
            display.setWrapStyleWord(true);
            displayArea.add(display);

            JScrollPane scroll = new JScrollPane(display);
            scroll.setBorder(BorderFactory.createTitledBorder(weekDays[i]));//box titles
            dayGrid.add(scroll);
        }
        mainframe.add(dayGrid, BorderLayout.CENTER);

        //input
        JPanel inputPanel = new JPanel(new BorderLayout(5,5));
        JTextField entryField = new JTextField();
        JComboBox<String> dayPicker = new JComboBox<>(weekDays);
        //status
        String[] statuses = {"Pending", "In Progress", "Completed"};
        JComboBox<String> statusPicker = new JComboBox<>(statuses);

        //save button
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e ->  {
            int index = dayPicker.getSelectedIndex();
            String text = entryField.getText().trim();
            String status = (String) statusPicker.getSelectedItem();
            try {
                if(text.isEmpty()){
                    throw new Exception("Entry cannot be blank");
                }
                myToDo.get(index).addTask(text, status);
                displayArea.get(index).setText(myToDo.get(index).toString());
                JOptionPane.showMessageDialog(mainframe, "Saved!");
                entryField.setText("");
                statusPicker.setSelectedIndex(0);
            }
            catch (Exception exception) {
                JOptionPane.showMessageDialog(mainframe, exception.getMessage());
            }
        });

        inputPanel.add(new JLabel("New Entry:"), BorderLayout.WEST);
        inputPanel.add(entryField, BorderLayout.CENTER);

        JPanel buttonGroup = new JPanel(new FlowLayout());
        buttonGroup.add(new JLabel("Status:"));
        buttonGroup.add(statusPicker);
        buttonGroup.add(dayPicker);
        buttonGroup.add(saveButton);
        inputPanel.add(buttonGroup, BorderLayout.EAST);
        
        mainframe.add(inputPanel, BorderLayout.SOUTH);
        mainframe.setSize(600, 600);
        mainframe.setVisible(true);
    }
}
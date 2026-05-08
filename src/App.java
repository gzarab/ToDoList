import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
    private static ArrayList<JLabel> displayLabels = new ArrayList<>();
    private static String[] weekDays=  new String[8];
    public static void main(String[] args) throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        SimpleDateFormat form = new SimpleDateFormat("EEEE MM/dd");

        weekDays[0] = "Notes";
        for (int i = 0; i < 8; i++){
            weekDays[i] = form.format(cal.getTime());
            myToDo.add(new List(new StringBuilder(" "), 0, "Pending", cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH) + 1));
            cal.add(Calendar.DATE, 1);
        }
        myToDo.add(0, new List(new StringBuilder(" "), 0 ,"Pending", 0, 0));

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
            JLabel display = new JLabel(myToDo.get(i).toString());//for display boxes
            display.setVerticalAlignment(SwingConstants.TOP);
            displayLabels.add(display);

            JScrollPane scroll = new JScrollPane(display);
            scroll.setBorder(BorderFactory.createTitledBorder(weekDays[i]));//box titles
            dayGrid.add(scroll);
        }
        mainframe.add(dayGrid, BorderLayout.CENTER);

        //input
        JPanel inputPanel = new JPanel(new BorderLayout(5,5));
        JTextField entryField = new JTextField();
        JComboBox<String> dayPicker = new JComboBox<>(weekDays);
        //save button
        JButton saveButton = new JButton("Save");

        saveButton.addActionListener(e ->  {
            int index = dayPicker.getSelectedIndex();
            String text = entryField.getText().trim();
            try {
                if(text.isEmpty()){
                    throw new Exception("Entry cannot be blank");
                }
                myToDo.get(index).setEntry(new StringBuilder(text));
                displayLabels.get(index).setText(myToDo.get(index).toString());
                System.out.println("Current To Do List" + myToDo);
                JOptionPane.showMessageDialog(mainframe, "Saved!");
                entryField.setText(" ");
            }
            catch (Exception exception) {
                JOptionPane.showMessageDialog(mainframe, exception.getMessage());
            }
        });

        inputPanel.add(new JLabel("New Entry:"), BorderLayout.WEST);
        inputPanel.add(entryField, BorderLayout.CENTER);

        JPanel buttonGroup = new JPanel(new FlowLayout());
        buttonGroup.add(dayPicker);
        buttonGroup.add(saveButton);
        inputPanel.add(buttonGroup, BorderLayout.EAST);
        
        mainframe.add(inputPanel, BorderLayout.SOUTH);
        mainframe.setSize(600, 600);
        mainframe.setVisible(true);
    }
}
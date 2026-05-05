import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Calendar;



public class App {

    private static ArrayList<String> myToDo = new ArrayList<>();
    public static void main(String[] args) throws Exception {

        for (int i = 0; i < 8; i++){
            myToDo.add(" ");
        }

        //main frame
        JFrame mainframe = new JFrame("Weekly To Do List");
        mainframe.setSize(600,600);
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //borders
        mainframe.setLayout(new BorderLayout(10,10));
        //top panels
        JLabel title = new JLabel("Weekly Planner");
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        mainframe.add(title, BorderLayout.NORTH);
        //boxes
        JPanel dayGrid = new JPanel(new GridLayout(4,2,10,10));
        ArrayList<JTextArea> entries = new ArrayList<>();

        for (int i = 0; i < 8; i++){
            JTextArea textArea = new JTextArea();
            textArea.setText(myToDo.get(i));
            
            entries.add(textArea);

            //boxes title
            JScrollPane scroll = new JScrollPane(textArea);
           //if (i = 0){
                //scroll.setBorder(BorderFactory.createTitledBorder("Notes"));
            //}
            //else {
                scroll.setBorder(BorderFactory.createTitledBorder("Day"+ i));
            //}
            dayGrid.add(scroll);
        }
        //save button
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e ->  {
            myToDo.clear(); 
            for (JTextArea box : entries) {
                try{
                myToDo.add(box.getText());
                System.out.println("Current To Do List" + myToDo);
                }
                catch (Exception exception) {
                    if(myToDo.equals(entries));
                    JOptionPane.showMessageDialog(mainframe, "Values must be entered");
                }
            }
        });

        mainframe.add(dayGrid, BorderLayout.CENTER);
        mainframe.add(saveButton, BorderLayout.SOUTH);

        mainframe.setSize(600, 600);
        mainframe.setVisible(true);
    }
}

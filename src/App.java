import java.util.Calendar;
import java.util.ArrayList;
import java.util.Scanner;


public class App {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("entry");
        String ent = in.nextLine();
        System.out.println("count");
        int num = in.nextInt();
        in.nextLine();
        System.out.println("status");
        String stat = in.nextLine();
        System.out.println("day");
        int day = in.nextInt();
        System.out.println("month");
        int month = in.nextInt();

      
        ArrayList<List> myToDo = new ArrayList<List>();

        myToDo.add(new List(ent, num, stat, day, month));
        System.out.println(myToDo);
        in.close();
    }
}

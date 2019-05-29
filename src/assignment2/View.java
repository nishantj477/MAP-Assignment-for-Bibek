/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2;
import java.util.Scanner;

/**
 *
 * @author Nishant
 */
public class View {
    private MAPAnalyser a;
    
    public View(MAPAnalyser a) {
        this.a = a;
    }
    
    public void commandLoop() {
        Scanner reader = new Scanner(System.in);
        menu();
        boolean bool = true;
        while(bool){
            int choice = reader.nextInt();
            switch(choice) {
                case 0: menu();
                        break;
                case 1: String id = reader.next();
                        int flag = 0;
                        for (Record rec: a.getData()) {
                            if(rec.getId().equals(id)) {
                                flag = 1;
                                break;
                            }
                        }
                        if (flag == 1) {
                            Record r = a.find(id);
                            //display();
                            System.out.println(r.toString());
                        } else {
                            System.out.println(id + " doesn't exist in record.");
                        }
                        break;
                case 2: int map1 = reader.nextInt();
                        int map2 = reader.nextInt();
                        if((map1 < 0 || map2 > 200) || (map1 > map2) ) {
                            System.out.println("map1 cannot be < 0 or map2 cannot be > 200 or "
                                    + "map1 cannot be > map2");
                        } else {
                            Record [] data = a.find(map1, map2);
                            //display();
                            for(Record rec: data) {
                                System.out.print(rec.toString());
                            }
                        }   
                        break;

                case 3: System.out.println("Lowest MAP is " + a.lowest() + 
                        "\nHighest MAP is " + a.highest() + 
                        "\nMedian MAP is " + a.median() + "\n");
                        break;

                case 9: bool = false;
                        break;
            }
        }
    }
    
    /* void display() {
        System.out.println(""
                + "-----------------------------------------------------------------\n"
                + "Person Id        SBP         DBP         MAP         Category\n"
                + "-----------------------------------------------------------------");
    } */
    
    void menu() {
         System.out.println("The following commands are recognised:");
         System.out.println("        Display this message                                   > 0\n" +
                "	Display a specific subject record:                     > 1 id\n" +
                "	Display records for all subject records within a range > 2 map1 map2\n" +
                "	Display statistics (minimum, maximum and median)       > 3\n" +
                "	Exit the application                                   > 9 \n");
    }
}

package Main;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import AdditionalMethods.SpecialFeatures;
import Colors.ColoredText;
import StudentWork.StudentCenter;
import SupervisorWork.HostelSupervisorCenter;

import java.util.*;
public class Main {

    public static void main(String[] args) {
        Scanner input =new Scanner(System.in);



        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
//        Scanner input =new Scanner(System.in);
        System.out.print(ColoredText.BOLD_BLUE);
        System.out.println("****************************************************************************************************************************************************");
        System.out.println("***                                                          !!!!WELCOME TO SSN HOSTEL !!!!                                                      ***");
        System.out.println("****************************************************************************************************************************************************");
        System.out.print(ColoredText.RESET);

        System.out.println(ColoredText.BOLD_PURPLE+"\n\n\tEnter 1 - Student Login\n\tEnter 2 - Hostel Supervisor");
        System.out.println(ColoredText.BOLD_RED+"\tEnter 3 - Exit\n\n"+ColoredText.RESET);

        int choice= SpecialFeatures.GetChoice(3);
        if(choice==1){
            // Student Page
            StudentCenter.main(args);
        }
        else if(choice==2){

            // Supervisor Page
            HostelSupervisorCenter.main(args);
        }
        else{
            System.out.println(ColoredText.BOLD_BLUE+"\n\t\t\t\t\t\t\t\t\t\t\t\t******************************************"+ColoredText.RESET);
            System.out.println(ColoredText.BOLD_BLUE+"\t\t\t\t\t\t\t\t\t\t\t\t*                 THANK YOU              *"+ColoredText.RESET);
            System.out.println(ColoredText.BOLD_BLUE+"\t\t\t\t\t\t\t\t\t\t\t\t******************************************"+ColoredText.RESET);
            System.exit(0);
        }


    }
}
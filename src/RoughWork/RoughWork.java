package RoughWork;//import java.io.File;
//import java.util.Scanner;
//
//public class RoughWork.RoughWork {
//
//
//
//
//    public static void main(String []args){
//
//
////        System.out.print(Colors.ColoredText.BOLD);
////        System.out.print( "This text is \uD83D\uDC4B" );
////        System.out.print(Colors.ColoredText.BOLD);
////        System.out.print( "This text is " );
////        System.out.print("egfhdghafhv");
////        System.out.print(Colors.ColoredText.RESET);
////        System.out.println(Colors.ColoredText.REVERSED + "This is reversed text (inverts foreground and background)." + Colors.ColoredText.RESET);
//////        String []Column=CsvOperations.ReadCSV.readHeader("DataBase/Attendance/test.csv");
////        String [][]data=CsvOperations.ReadCSV.readFile("DataBase/Attendance/test.csv");
////        JTable.JFrameTable.DisplayTable(new String[][]{attdata[0]},Column);
//
////        String [][]roomData=CsvOperations.ReadCSV.readFileInPipeLine("DataBase/RoomsDetails.csv");
////        String []StdRoomDataHeader=CsvOperations.ReadCSV.readHeader("DataBase/RoomsDetails.csv");
////        JTable.JFrameTable.DisplayTable(roomData,StdRoomDataHeader);
////        String n="DataBase/Attendance/10-2024.csv";
////        String o="DataBase/Attendance/2-2024.csv";
//
////        SupervisorWork.AttendanceCsvEditor.EditCsv(n,n,"4","P","A");
////        System.out.println("vikraman");
//
////        File file = new File(n);
//
////        if(file.exists()){
////            System.out.println(file+" Exists");
////            CsvEditor.EditCsv(n,n);
////
////        }
////        else{
////            System.out.println(file+" Not Exists");
////            CsvEditor.EditCsv("DataBase/Attendance/HostelAttendanceModelEmpty.csv",n);
////        }
//
//
//
////        CsvOperations.CsvChanger.EditCsv("DataBase/StudentsPersonalInfo.csv","DataBase/StudentsPersonalInfo.csv");
////
////        Scanner sc = new Scanner(System.in);
////        System.out.print("Eneter : ");
////        int a =sc.nextInt();
////        if(a%2==0){
////            System.out.println("even");
////        }
////        else{
////            System.out.println("Odd");
////        }
//
////        String name="vikraman";
////        String status="Approved";
////        String instructions1="hwgfhfw";
////        String instructions2="hwgfhfw";
////        String instructions3="hwgfhfw";
//////        String instructions="Please come to the Hostel office to collect your Home Pass.\nMake sure to bring your student ID and a copy of this email.";
//////        String from="KANNAN S\nCheif Wardern\nkannan24@gmail.com\nSSN HOSTEL";
////        String emailTemplate = "Hi "+name+",\n\nI hope you're doing well!\n\nI wanted to update you on your Home Pass application.\n\nApplication Status: "+status+".\n\nNext Steps: "+instructions+".\n\nIf you have any questions or need further assistance, feel free to reach out to me.\n\nThanks, and take care!\n\nBest,\n"+from;
//////        System.out.println(emailTemplate);
////        String []a={" ",instructions1,name,name,name,name,name,name,status};
////        CsvOperations.WriteCSV.appendToCSV("DataBase/HomePass/HomePassRegister.csv",a);
//
//
////        String [][]fbduelist=CsvOperations.ReadCSV.readFileWithHeader("DataBase/FeedBack/FBdue.csv");// feedback - student submitted or not list
////        for(int i=1;i<fbduelist.length;i++){
////            System.out.println(fbduelist[i].length);
////        }
//
//
//
//    }
//}


import Colors.ColoredText;

public class RoughWork {


    public static void main(String []args){

//        String[][] names = {
//                {"Alice", "Bob", "Charlie"},
//                {"David", "Eve", "Frank"},
//                {"Grace", "Heidi", "Ivan"}
//        };
//        CsvOperations.WriteCSV.writeFile("DataBase/test.csv",names);

//        String[][] names=CsvOperations.ReadCSV.readFileWithHeader("DataBase/test.csv");
//        for(String[] s:names){
//            System.out.println(s.length);
//        }

//        String S="vikram s w";
//        String []S1=S.split(" ");
//        System.out.println(S1[0]);

//        String []line={"9122135,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,"};
//        CsvOperations.WriteCSV.appendToCSVWtN("DataBase/test1.csv",line);

//        System.out.print(Colors.ColoredText.BOLD_YELLOW+"\n************************************************************************************************\n*"+Colors.ColoredText.RESET);
//        System.out.println(Colors.ColoredText.BOLD_GREEN+"  !!! Home Pass Applied Successfully \t\t\t\t\t\t\t\t\t\t\t\t\t\t   "+Colors.ColoredText.BOLD_YELLOW+"*\n*"+Colors.ColoredText.BOLD_GREEN+"  Later,You will receive an email notifying you if your request has been accepted or rejected"+Colors.ColoredText.BOLD_YELLOW+" *\n*"+Colors.ColoredText.BOLD_GREEN+"\t\t\t\t\t\t\t\t\t\t\tThank You\t\t\t\t\t\t\t\t\t\t  "+Colors.ColoredText.BOLD_YELLOW+" *"+Colors.ColoredText.RESET);
//        System.out.println(Colors.ColoredText.BOLD_YELLOW+"************************************************************************************************"+Colors.ColoredText.RESET);
//

//        System.out.println(Colors.ColoredText.BOLD_YELLOW+"------------------------------------------------------------\n|"+Colors.ColoredText.BOLD_RED+"  !!! Your previous Complaint is being processed        "+Colors.ColoredText.BOLD_YELLOW+"  |\n------------------------------------------------------------\n"+Colors.ColoredText.RESET);
//        System.out.println("Enter 1 - Change Student Email ("+Colors.ColoredText.BOLD_RED+"* Required Verification"+Colors.ColoredText.RESET+" )\nEnter 2 - Change Parent Email ("+Colors.ColoredText.BOLD_RED+"* Required Verification"+Colors.ColoredText.RESET+" )\nEnter 3 - Change Student Mobile Number\nEnter 4 - Change Parent Mobile Number\nEnter 5 - Back\n");

//        System.out.println(Colors.ColoredText.BOLD_YELLOW+"--------------------------------------\n|"+Colors.ColoredText.BOLD_RED+"  !!! No Application Found        "+Colors.ColoredText.BOLD_YELLOW+"  |\n--------------------------------------\n"+Colors.ColoredText.RESET);

        System.out.println(ColoredText.BOLD_YELLOW+"--------------------------------------\n|"+ColoredText.BOLD_RED+"   !!! No Complaints Available     "+ColoredText.BOLD_YELLOW+"  |\n--------------------------------------\n\n"+ ColoredText.RESET);


    }
}

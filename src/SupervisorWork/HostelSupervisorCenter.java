package SupervisorWork;

import AdditionalMethods.SpecialFeatures;
import Colors.ColoredText;
import CsvOperations.CsvChanger;
import CsvOperations.ReadCSV;
import CsvOperations.WriteCSV;
import Email.Mail;
import JTable.JFrameTable;
import MyExceptions.*;
import StudentWork.StudentCenter;
import StudentWork.StudentLogin;
import Main.Main;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class HostelSupervisorCenter extends StudentCenter {
    private String[][] StdPersonalData;
    private String[][] StdPersonalDatawoh;
    private String[] StdPersonalDataHeader;
    private String[] StdRoomDataHeader;

    public HostelSupervisorCenter(String filepath){
        super(filepath);
        StdPersonalData=ReadCSV.readFileWithHeader("DataBase/StudentsPersonalInfo.csv");
        StdPersonalDatawoh=ReadCSV.readFileWithHeader("DataBase/StudentsPersonalInfo.csv");
        StdPersonalDataHeader=ReadCSV.readHeader("DataBase/StudentsPersonalInfo.csv");
        StdRoomDataHeader=ReadCSV.readHeader("DataBase/RoomsDetails.csv");
    }


    public static void main(String[] args) {

        StudentLogin HostelSpv = new StudentLogin();
        HostelSupervisorCenter Hsc = new HostelSupervisorCenter("DataBase/SupervisorsCredentials.csv");
        Scanner input = new Scanner(System.in);

        if (HostelSpv.CheckUser("DataBase/SupervisorsCredentials.csv")) {
            System.out.println("Login successful.");
            Hsc.SupervisorPath();
        } else {
            System.out.println("Login failed.");
            boolean retry = true;
            while (retry) {
                System.out.println("Enter 1 - Re-Enter Credentials");
                System.out.println("Enter 2 - Forget Password");
                System.out.println("Enter 3 - Back to Home");
                int choice = SpecialFeatures.GetChoice(3);
                if (choice == 1) {
                    HostelSupervisorCenter.main(args);
                    return;
                }
                else if(choice==2){
                    Hsc.ChangePasword();
                        HostelSupervisorCenter.main(args);
                        return;
                    }else {
                    Main.main(args);  // Go back to home page
                    retry = false;
                }
            }
        }

    }

    public void SupervisorPath(){
        Scanner scanner = new Scanner(System.in);
        System.out.println(ColoredText.BOLD_BLUE+"Enter 1 - Student List\nEnter 2 - Rooms List\nEnter 3 - Search Student\nEnter 4 - Add / Remove Student\nEnter 5 - Home Pass\nEnter 6 - Mark Attendance\nEnter 7 - View Attendance\nEnter 8 - Change Student Information\nEnter 9 - Circulate Feedback\nEnter 10 - FeedBack Analysis\nEnter 11 - View Complaints\n"+ ColoredText.RESET+ ColoredText.RED+"Enter 12 - Log Out\n"+ ColoredText.BOLD_RED+"Enter 13 - Exit\n"+ ColoredText.RESET);
        int choice = SpecialFeatures.GetChoice(13);
        switch (choice) {
            case 1:
                String [][]StdPersonalData1=ReadCSV.readFile("DataBase/StudentsPersonalInfo.csv");
                System.out.println("Student List");
                JFrameTable.DisplayTable(StdPersonalData1,StdPersonalDataHeader,"Student List");
                System.out.print("Press Enter to Main Menu...");
                scanner.nextLine();
                SupervisorPath();
                break;
            case 2:
                System.out.println(" Room Wise Details...");
                JFrameTable.DisplayTable(ReadCSV.readFileInPipeLine("DataBase/RoomsDetails.csv"),StdRoomDataHeader,"Room Details");
                System.out.print("Press Enter to Main Menu...");
                scanner.nextLine();
                SupervisorPath();
                break;
            case 3:
                System.out.println("Search Student");
                SearchStd();
                SupervisorPath();
                break;
            case 4:
                AddRemove();
                SupervisorPath();
                break;
            case 5:
                System.out.println("Home Pass Details");
                GiveHomePass();
                System.out.print("Press Enter to Main Menu...");
                scanner.nextLine();
                SupervisorPath();
                break;
            case 6:
                System.out.println("Mark attendance ...");
                MarkAttendance();
                System.out.print("Press Enter to Main Menu...");
                scanner.nextLine();
                SupervisorPath();
                break;
            case 7:
                System.out.println("View attendance ...");
                ViewAttendance();
                System.out.print("Press Enter to Main Menu...");
                scanner.nextLine();
                SupervisorPath();
                break;
            case 8:
                System.out.println("Change Student Information");
                CsvChanger.EditCsv("DataBase/StudentsPersonalInfo.csv","DataBase/StudentsPersonalInfo.csv","Student Details","Details Updated Successfully");
                System.out.print("Press Enter to Main Menu...");
                scanner.nextLine();
                SupervisorPath();
                break;
            case 9:
                System.out.println("Circulate Feedback...");
                CirculateFeedBack();
                System.out.print("Press Enter to Main Menu...");
                scanner.nextLine();
                SupervisorPath();
                break;
            case 10:
                System.out.println("Feedback Analysis");
                getFeedBackAnalysis();
                System.out.print("Press Enter to Main Menu...");
                scanner.nextLine();
                SupervisorPath();
                break;
            case 11:
                System.out.println("View Complaints ");
                GetComplaints();
                System.out.print("Press Enter to Main Menu...");
                scanner.nextLine();
                SupervisorPath();
                break;
            case 12:
                System.out.println("LogOut");
                String[] args = new String[0];
                Main.main(args);
                break;
            case 13:
                System.exit(0);
                break;

            default:        //There is No default Condition Occurs it just for best practices of switch case
                System.out.println("Invalid choice, please enter a valid number.");
        }
    }

    public void SearchStd(){
        System.out.println("Enter 1 - Search through Digital ID \nEnter 2 - Search through  Name\nEnter 3 - Main Menu\n");
        int opt= SpecialFeatures.GetChoice(3);
        Scanner input = new Scanner(System.in);
        switch (opt) {
            case 1:
                System.out.print("Enter Digital ID : ");
                int DigitalId = input.nextInt();
                SearchStudent(DigitalId);
                SearchStd();
                break;
            case 2:
                System.out.print("Enter Name : ");
                String Name = input.next();
                SearchStudent(Name);
                SearchStd();
                break;
            case 3:
                return;

            default:        //There is No default Condition Occurs it just for best practices of switch case
                System.out.println("Invalid choice, please enter a valid number.");
        }
    }

    public void MarkAttendance(){
        int Mon= SpecialFeatures.GetCurrentDate('M');
        int Year= SpecialFeatures.GetCurrentDate('Y');
        String filepath="DataBase/Attendance/"+Mon+"-"+Year+".csv";
        System.out.println(filepath);
        String Day=""+ SpecialFeatures.GetCurrentDate('D');
        System.out.println(Day);
        File file = new File(filepath);

        if(file.exists()){
            AttendanceCsvEditor.EditCsv(filepath,filepath,Day,"P","A","Attendance Sheet","Attendance Marked Successfully");
        }
        else{
            AttendanceCsvEditor.EditCsv("DataBase/Attendance/HostelAttendanceModel.csv",filepath,Day,"P","A","Attendance Sheet","Attendance Has been Recorded Successfully");
        }

    }

    public void AddRemove(){
        System.out.println("Enter 1 - Add Student\nEnter 2 - Remove Student\nEnter 3 - Main Menu");
        int choice = SpecialFeatures.GetChoice(3);
        switch (choice){
            case 1:
                AddStudent();
                break;
            case 2:
                RemoveStudent();
                break;
            case 3:
                return;
            default:        //There is No default Condition Occurs it just for best practices of switch case
                System.out.println("Invalid choice, please enter a valid number.");
        }
    }

    public int RemoveStudent(){
        String [][]stData=ReadCSV.readFileWithHeader("DataBase/StudentsPersonalInfo.csv");
        System.out.print("Enter Digital Id to Remove : ");
        Scanner input =new Scanner(System.in);
        String Id;
        int pos=-1;
        try {
            Id = input.next();

            // Check if input is a valid integer
            if (!Id.matches("-?\\d+")) {
                throw new InvalidMobileNumberException("Invalid Digital Id ! It Must Be an Integer");
            }
            String [][]userlst=ReadCSV.readFileWithHeader("DataBase/users.csv");

            for(int i=1;i<userlst.length;i++){
                if(userlst[i][2].equals(Id)){
                    pos=i;
                    break;
                }
            }
            if(pos==-1){
                throw new DigitalIdNotFoundException("!!! Digital Id "+Id+" is Not Found");
            }
            System.out.println("\nNAME : "+stData[pos][1]);
            System.out.println("YEAR  : "+stData[pos][3]);
            System.out.println("DEPT : "+stData[pos][2]);
            System.out.print("Are You Want to Delete (y/n) : ");
            if(SpecialFeatures.GetChoice('y')){
                RemoveFromDatas(Id,pos);
            }
            else{
                System.out.println(ColoredText.BOLD_RED+"Remove Student Process Cancelled"+ ColoredText.RESET);
            }

        } catch (InvalidMobileNumberException | DigitalIdNotFoundException e) {
            System.out.println(ColoredText.BOLD_RED+e.getMessage()+ ColoredText.RESET);
            AddRemove();
            return 0;
        }
        return 0;
    }

    public void RemoveFromDatas(String Id,int pos){
        String [][]userlst=ReadCSV.readFileWithHeader("DataBase/users.csv");
        String [][]stdlst= ReadCSV.readFileWithHeader("DataBase/StudentsPersonalInfo.csv");
        String [][]fblst=ReadCSV.readFileWithHeader("DataBase/FeedBack/FBDue.csv");
        String [][]attlst=ReadCSV.readFileWithHeader("DataBase/Attendance/HostelAttendanceModel.csv");

        String [][]Newuserlst=new String[userlst.length-1][];
        String [][]Newstdlst=new String[stdlst.length-1][];
        String [][]Newfblst=new String[fblst.length-1][];
        String [][]Newattlst=new String[attlst.length-1][];
        int newIdx=0;
        String name=stdlst[pos][1];
        for(int i=0;i<userlst.length;i++){
            if(i!=pos){
                Newuserlst[newIdx]=userlst[i];
                Newstdlst[newIdx]=stdlst[i];
                Newfblst[newIdx]=fblst[i];
                Newattlst[newIdx++]=attlst[i];
            }
        }
        WriteCSV.writeFile("DataBase/users.csv",Newuserlst);
        WriteCSV.writeFile("DataBase/StudentsPersonalInfo.csv",Newstdlst);
        WriteCSV.writeFile("DataBase/FeedBack/FBDue.csv",Newfblst);
        WriteCSV.writeFile("DataBase/Attendance/HostelAttendanceModel.csv",Newattlst);



        String [][]roomlst=ReadCSV.readFileWithHeader("DataBase/RoomsDetails.csv");

        for(int i=0;i<roomlst.length;i++){
            if(roomlst[i].length==6){
                if(roomlst[i][5].contains(Id)){
                    System.out.println(roomlst[i][5]);
                    roomlst[i][5]=roomlst[i][5].replace("|","");
                    System.out.println(roomlst[i][5]);
                    roomlst[i][5]=roomlst[i][5].replace(Id,"");
                    System.out.println(roomlst[i][5]);
                    System.out.println(roomlst[i][4]);
                    roomlst[i][4]=roomlst[i][4].replace("|","");
                    System.out.println(roomlst[i][4]);
                    System.out.println(name);
                    roomlst[i][4]=roomlst[i][4].replace(name,"");
                    System.out.println(roomlst[i][4]);
                    break;
                }
            }
        }
        WriteCSV.writeFile("DataBase/RoomsDetails.csv",roomlst);
        System.out.println(ColoredText.BOLD_GREEN+"\nStudent Removed Successfully\n"+ ColoredText.RESET);




    }

    public int AddStudent(){
        Scanner input =new Scanner(System.in);
        System.out.print("Enter Digital ID : ");
        String s;
        try {
            s = input.nextLine();

            if (!s.matches("-?\\d+")) {
                throw new InvalidIntegerInputException("Invalid input: expected an integer");
            }
            String [][]user=ReadCSV.readFileWithHeader("DataBase/users.csv");
            for(int i=1;i<user.length;i++){
                if(user[i][2].equals(s)){
                    throw new DigitalIdExistsException("Digital Id "+s+" Is Already Exists\n");
                }
            }

        } catch (InvalidIntegerInputException | DigitalIdExistsException e) {
            System.out.println(ColoredText.BOLD_RED+e.getMessage()+ ColoredText.RESET);
            AddRemove();
            return 0;
        }
        setDigitalId(s);

        System.out.print("Enter Student Name : ");
        String name=input.nextLine();
        setName(name);

        String year=TakeYear();
        setYear(year);

        String Dept=TakeDept();
        setDept(Dept);

        String email=TakeEmail("Enter Student Email : ");
        setEmail(email);

        String mobno=TakeMobileNo("Enter Student Mobile No : ");
        setMobileNo(mobno);

        System.out.print("Enter Native Place : ");
        String place=input.nextLine();
        setPlace(place);

        System.out.print("Enter Parent Name : ");
        String PName=input.nextLine();
        setParentName(PName);

        String Pemail=TakeEmail("Enter Parent Email : ");
        setParentEmail(Pemail);

        String Pmobno=TakeMobileNo("Enter Parent Mobile No : ");
        setParentMobileNo(Pmobno);

        System.out.print("Are You Want To Continue To Allocate Room (y/n) : ");
        if(SpecialFeatures.GetChoice('y')){
            if(AllocateRoom()){

                String []line={getDigitalId(),getName(),getDept(),getYear(),getEmail(),getMobileNo(),getPlace(),getParentName(),getParentMobileNo(),getParentEmail()};
                WriteCSV.appendToCSVWtN("DataBase/StudentsPersonalInfo.csv",line);

                ProvideLogin();

                String []fbline={getDigitalId(),"null"};
                WriteCSV.appendToCSVWtN("DataBase/FeedBack/FBDue.csv",fbline);

                String []atdline={getDigitalId()+",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,"};
                WriteCSV.appendToCSVWtN("DataBase/Attendance/HostelAttendanceModel.csv",atdline);



            }
            else{
                System.out.println(ColoredText.BOLD_RED+"!!! Student Admission Cancelled"+ ColoredText.RESET);
            }
        }
        else{
            System.out.println(ColoredText.BOLD_RED+"!!! Student Admission Cancelled"+ ColoredText.RESET);

        }


        return 0;
    }

    public boolean AllocateRoom(){
        boolean status=false;
        System.out.println("Enter 1 - Combined Non-AC\nEnter 2 - Combined AC\nEnter 3 - Single Non-AC\nEnter 4 - Single AC\n");
        int choice = SpecialFeatures.GetChoice(4);
        String [][]RoomData=ReadCSV.readFileWithHeader("DataBase/RoomsDetails.csv");
        if(choice==1){
            for(int i=1;i<RoomData.length;i++){
                if(RoomData[i][0].equals("1")){
                    if(RoomData[i].length==6){
                        if(!RoomData[i][4].contains("|")){
                            System.out.println("!!! Room alloted Successfullly");
                            System.out.println("Hostel No : "+RoomData[i][0]+"\nBlock Name : "+RoomData[i][1]+"\nRoom Number : "+RoomData[i][2]+"\n");
                            RoomData[i][4]=RoomData[i][4]+"|"+getName();
                            RoomData[i][5]+="|"+getDigitalId();
                            status=true;
                            break;
                        }
                    }
                    else if(RoomData[i].length==4){
                        System.out.println("!!! Room alloted Successfullly");
                        System.out.println("Hostel No : "+RoomData[i][0]+"\nBlock Name : "+RoomData[i][1]+"\nRoom Number : "+RoomData[i][2]+"\n");
                        RoomData[i][3]+=","+getName()+","+getDigitalId();
                        status=true;
                        break;
                    }
                }
            }
        }

        else if(choice==2){
            for(int i=1;i<RoomData.length;i++){
                if(RoomData[i][0].equals("2")){
                    if(RoomData[i].length==6){
                        if(!RoomData[i][4].contains("|")){
                            System.out.println("!!! Room alloted Successfullly");
                            System.out.println("Hostel No : "+RoomData[i][0]+"\nBlock Name : "+RoomData[i][1]+"\nRoom Number : "+RoomData[i][2]+"\n");
                            RoomData[i][4]+="|"+getName();
                            RoomData[i][5]+="|"+getDigitalId();
                            status=true;
                            break;
                        }
                    }
                    else if(RoomData[i].length==4){
                        System.out.println("!!! Room alloted Successfullly");
                        System.out.println("Hostel No : "+RoomData[i][0]+"\nBlock Name : "+RoomData[i][1]+"\nRoom Number : "+RoomData[i][2]+"\n");
                        RoomData[i][3]+=","+getName()+","+getDigitalId();
                        status=true;
                        break;
                    }
                }
            }
        }
        else if(choice==3){
            for(int i=1;i<RoomData.length;i++){
                if(RoomData[i][0].equals("3")){
                    if(RoomData[i].length==4){
                        System.out.println("!!! Room alloted Successfullly");
                        System.out.println("Hostel No : "+RoomData[i][0]+"\nBlock Name : "+RoomData[i][1]+"\nRoom Number : "+RoomData[i][2]+"\n");
                        RoomData[i][3]+=","+getName()+","+getDigitalId();
                        status=true;
                        break;
                    }

                }
            }
        }
        else if(choice==4){
            for(int i=1;i<RoomData.length;i++){
                if(RoomData[i][0].equals("4")){
                    if(RoomData[i].length==4){
                        System.out.println("!!! Room alloted Successfullly");
                        System.out.println("Hostel No : "+RoomData[i][0]+"\nBlock Name : "+RoomData[i][1]+"\nRoom Number : "+RoomData[i][2]+"\n");
                        RoomData[i][3]+=","+getName()+","+getDigitalId();
                        status=true;
                        break;
                    }

                }
            }
        }

        WriteCSV.writeFile("DataBase/RoomsDetails.csv",RoomData);
        return status;


    }

    public void ProvideLogin(){
        String []str=getName().split(" ");
        String username=str[0].toLowerCase()+getDigitalId();
        String password=""+ SpecialFeatures.GenerateOTP(3);

        String []line={username,password,getDigitalId()};
        WriteCSV.appendToCSVWtN("DataBase/users.csv",line);
        System.out.println("Student Login Credentials Sent to Registered Email");

        try {
            Mail.MainFunction(getEmail(),"Login Credentials For Hostel System","Hello "+getName()+",\n\n\tBelow Thing  is the Your Permanent Username And Password used For Hostel System Login.\n\tUSERNAME : "+username+"\n\tPASSWORD : "+password+"\n\nIMPORTANT NOTE : Don't Share these Crendentials to others\n\n"+"KANNAN S\nCheif Wardern\nkannan24@gmail.com\nSSN HOSTEL");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String TakeYear(){
        Scanner input =new Scanner(System.in);
        System.out.print("Enter Year (I,II,III,IV) : ");
        String s;
        try{
            s=input.nextLine();
            if(!s.equals("II") && !s.equals("II") && !s.equals("III") && !s.equals("IV")){
                throw new YearNotFoundExcetion("!!! Invalid Year : Enter like(I,II,III,IV)");
            }
        }
        catch (YearNotFoundExcetion e){
            System.out.println(ColoredText.BOLD_RED+e.getMessage()+ ColoredText.RESET);
            return TakeYear();
        }
        return s;
    }

    public String TakeDept(){
        Scanner input =new Scanner(System.in);
        System.out.print("Enter Year (CSE,IT,....) : ");
        String s;
        try{
            s=input.nextLine();
            if(!s.equals("CSE") && !s.equals("IT") && !s.equals("ECE") && !s.equals("EEE") && !s.equals("MECH") && !s.equals("CIVIL") && !s.equals("BME") && !s.equals("CHEM")){
                throw new DeptNotFoundExcetion("!!! Invalid Dept : Enter like(CSE,IT,ECE,EEE,MECH,CIVIL,BME,CHEM)");
            }
        }
        catch (DeptNotFoundExcetion e){
            System.out.println(ColoredText.BOLD_RED+e.getMessage()+ ColoredText.RESET);
            return TakeDept();
        }
        return s;
    }



    @Override
    public void ViewAttendance(){
        System.out.println(ColoredText.BOLD_GREEN+"\n*  Only one year of Attendance can be viewed here\n"+ ColoredText.RESET);
        Scanner sc=new Scanner(System.in);
        int Mon,Year;
        int CurrentYear= SpecialFeatures.GetCurrentDate('Y');
        Year= SpecialFeatures.GetChoice("Enter Year : ", ColoredText.BOLD_RED+"!!! Invalid Year"+ ColoredText.RESET+"\nRe-",CurrentYear-1,CurrentYear);
        if(Year==CurrentYear){

            Mon= SpecialFeatures.GetChoice("Enter Month (1-"+(SpecialFeatures.GetCurrentDate('M')-1)+") : ", ColoredText.BOLD_RED+"!!! Invalid Month "+ ColoredText.RESET+"\nRe-",1, SpecialFeatures.GetCurrentDate('M')-1);
        }
        else{
            Mon= SpecialFeatures.GetChoice("Enter Month ("+(SpecialFeatures.GetCurrentDate('M')-1)+"-12) : ", ColoredText.BOLD_RED+"!!! Invalid Month "+ ColoredText.RESET+"\nRe-",(SpecialFeatures.GetCurrentDate('M')-1),12);
        }
        String filepath="DataBase/Attendance/"+Mon+"-"+Year+".csv";
        String[][] AttendaceData = ReadCSV.readFile(filepath);
        String[] AttendanceDataColumns=ReadCSV.readHeader(filepath);

        JFrameTable.DisplayTable(AttendaceData,AttendanceDataColumns,"Attendance");
        System.out.print("Are You Want To Search More (y/n) : ");
        if(SpecialFeatures.GetChoice('Y')){
            ViewAttendance();
        }
    }


    public void SearchStudent(int DigitalId){

        String DigitalID=""+DigitalId;
        String[][] StdData = ReadCSV.readFileWithHeader("DataBase/StudentsPersonalInfo.csv");
        boolean studentFound = false;

        for (int i = 1; i < StdData.length; i++) {
            String[] stdDatum = StdData[i];
            if (stdDatum[0].equals(DigitalID)) {
                setDigitalId(stdDatum[0]);
                setName(stdDatum[1]);
                setDept(stdDatum[2]);
                setYear(stdDatum[3]);
                setEmail(stdDatum[4]);
                setMobileNo(stdDatum[5]);
                setPlace(stdDatum[6]);
                setParentName(stdDatum[7]);
                setParentMobileNo(stdDatum[8]);
                setParentEmail(stdDatum[9]);
                studentFound = true;
                break;
            }
        }


        if (!studentFound) {
            System.out.println(ColoredText.BOLD_RED+"Student with DigitalID " + DigitalID + " not found\n"+ ColoredText.RESET);
            return;
        }

        String[][] roomData = ReadCSV.readFileWithHeader("DataBase/RoomsDetails.csv");

        boolean roomFound = false;
        for (int i=1;i<roomData.length;i++) {
            if(roomData[i].length==6){
                if (roomData[i][0].equals("1") || roomData[i][0].equals("2")) {
                    if(roomData[i][5].contains("|")) {
                        String[] twoId = roomData[i][5].split("\\|");
                        if (twoId[0].equals(DigitalID) || twoId[1].equals(DigitalID)) {
                            setHostelNo(roomData[i][0]);
                            setBlockName(roomData[i][1]);
                            setRoomNo(roomData[i][2]);
                            setType(roomData[i][3]);
                            roomFound = true;
                            break;
                        }
                    }
                    else{
                        setHostelNo(roomData[i][0]);
                        setBlockName(roomData[i][1]);
                        setRoomNo(roomData[i][2]);
                        setType(roomData[i][3]);
                        roomFound = true;
                        break;
                    }
                }
                else {
                    if (roomData[i][5].equals(DigitalID)) {  // Fixed indexing issue here

                        setHostelNo(roomData[i][0]);
                        setBlockName(roomData[i][1]);
                        setRoomNo(roomData[i][2]);
                        setType(roomData[i][3]);
                        roomFound = true;
                        break;
                    }
                }
            }

        }

        if (!roomFound) {
            System.out.println("Room details for DigitalID " + DigitalID + " not found.");
        }
        ViewProfile();
        RoomDetails();
    }

    public void SearchStudent(String Name) {
        String[][] StdData = ReadCSV.readFileWithHeader("DataBase/StudentsPersonalInfo.csv");
        int cp=0;
        for (int i=1;i<StdData.length;i++) {
            String[] stdDatum=StdData[i];
            if (stdDatum[1].equals(Name)) {
                cp=1;
                SearchStudent(Integer.parseInt(stdDatum[0]));
                System.out.println("\n\n");
            }
        }
        if(cp==0)
            System.out.println(ColoredText.BOLD_RED+"!!! Student Name Not Found\n"+ ColoredText.RESET);
    }


    @Override
    public boolean AssignStudentInfo(String DigitalId){

        boolean studentFound = false;

        for (String[] stdDatum : StdPersonalData) {
            if (stdDatum[0].equals(DigitalId)) {
                setDigitalId(stdDatum[0]);
                setName(stdDatum[1]);
                setDept(stdDatum[2]);
                setYear(stdDatum[3]);
                setEmail(stdDatum[4]);
                setMobileNo(stdDatum[5]);
                setPlace(stdDatum[6]);
                setParentName(stdDatum[7]);
                setParentMobileNo(stdDatum[8]);
                setParentEmail(stdDatum[9]);
                studentFound = true;
                break;
            }
        }
        return studentFound;





    }

    public void GiveHomePass() {
        int minApplication=0;
        String [][]PassApplication=ReadCSV.readFileWithHeader("DataBase/HomePass/HomePassRegister.csv");
        for(int i=1;i<PassApplication.length;i++){
            if(PassApplication[i][10].equals("Submitted")){
                minApplication=1;
                System.out.println("Digital ID   :  "+PassApplication[i][1]);
                System.out.println("Name         :  "+PassApplication[i][2]);
                System.out.println("Year         :  "+PassApplication[i][3]);
                System.out.println("Department   :  "+PassApplication[i][4]);
                System.out.println("Email        :  "+PassApplication[i][5]);
                System.out.println("Parent Email :  "+PassApplication[i][6]);
                System.out.println("OUT Date     :  "+PassApplication[i][7]);
                System.out.println("IN Date      :  "+PassApplication[i][8]);
                System.out.println("Reason       :  "+PassApplication[i][9]);
                System.out.println("Status       :  "+PassApplication[i][10]);


                String name=PassApplication[i][2];
                String StdEmail=PassApplication[i][5];
                String parentMail=PassApplication[i][6];

                System.out.println("Enter 1 - Provide Home Pass\nEnter 2 - Reject Home Pass");
                int choice= SpecialFeatures.GetChoice(2);
                if(choice==1){

                    PassApplication[i][10]="Provided";


                    String status="Approved";
                    String instructions="Your Request for Home Pass as been Accepted.\nMake sure to Show your student ID and  this email to Entrance Gate security.";
                    String from="KANNAN S\nCheif Wardern\nkannan24@gmail.com\nSSN HOSTEL";
                    String Dates="OUT Date : "+PassApplication[i][7]+"\n"+"IN  Date : "+PassApplication[i][8];


                    String Subject="Request for Home Pass as been Accepted";
                    String MailBody = "Hi "+name+",\n\nI hope you're doing well!\n\nI wanted to update you on your Home Pass application.\n\nApplication Status: "+status+".\n\nNext Steps: "+instructions+".\n"+Dates+"\n\nIf you have any questions or need further assistance, feel free to reach out to me.\n\nThanks, and take care!\n\nBest,\n"+from;

//                    System.out.println("approve Email.Mail Sent to std Successfully"+"\n"+MailBody);

                    try {
                        Mail.MainFunction(StdEmail,Subject,MailBody);
                    } catch (MessagingException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    MailBody = "Hi "+name+"'s Parent,\n\nI hope you're doing well!\n\nI wanted to update you on your Son's Home Pass application.\n\nApplication Status: "+status+".\n\n"+Dates+"\n\nIf you have any questions or need further assistance, feel free to reach out to me.\n\nThanks, and take care!\n\nBest,\n"+from;

//                    System.out.println("approve Email.Mail Sent to parent Successfully"+"\n"+MailBody);


                    try {
                        Mail.MainFunction(parentMail,Subject,MailBody);
                    } catch (MessagingException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }
                else{
                    PassApplication[i][10]="Rejected";


                    Scanner sc=new Scanner(System.in);
                    System.out.print("Enter Any Reason (If Applicable) : ");
                    String Reason=sc.nextLine();
                    if(!Reason.equals("")) { Reason="Reason for Rejection : "+Reason;}

                    String status="Rejected";
                    String Subject="Request for Home Pass as been Rejected";
                    String instructions="Your Request for Home Pass as been Rejected.";
                    String from="KANNAN S\nCheif Wardern\nkannan24@gmail.com\nSSN HOSTEL";
                    String Dates="OUT Date : "+PassApplication[i][7]+"\n"+"IN  Date : "+PassApplication[i][8];
                    String MailBody = "Hi "+name+",\n\nI hope you're doing well!\n\nI wanted to update you on your Home Pass application.\n\nApplication Status: "+status+".\n\nNext Steps: "+instructions+".\n"+Reason+"\n\n"+Dates+"\n\nIf you have any questions or need further assistance, feel free to reach out to me.\n\nThanks, and take care!\n\nBest,\n"+from;

//                    System.out.println("reject Email.Mail Sent to std Successfully"+"\n"+MailBody);

                    try {
                        Mail.MainFunction(StdEmail,Subject,MailBody);
                    } catch (MessagingException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    MailBody = "Hi "+name+"'s Parent,\n\nI hope you're doing well!\n\nI wanted to update you on your Son's Home Pass application.\n\nApplication Status: "+status+".\n\nNext Steps: "+instructions+".\n"+Reason+"\n\n"+Dates+"\n\nIf you have any questions or need further assistance, feel free to reach out to me.\n\nThanks, and take care!\n\nBest,\n"+from;

//                    System.out.println("reject Email.Mail Sent to parent Successfully"+"\n"+MailBody);

                    try {
                        Mail.MainFunction(parentMail,Subject,MailBody);
                    } catch (MessagingException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }


                }
            }
        }


        if(minApplication==0){
            System.out.println(ColoredText.BOLD_YELLOW+"--------------------------------------\n|"+ ColoredText.BOLD_RED+"  !!! No Application Found        "+ ColoredText.BOLD_YELLOW+"  |\n--------------------------------------\n\n"+ ColoredText.RESET);
        }
        else{
            WriteCSV.writeFile("DataBase/HomePass/HomePassRegister.csv",PassApplication);
        }

    }

    public void CirculateFeedBack(){
        Scanner sc =new Scanner(System.in);
        String [][]FeedbackData=ReadCSV.readFileWithHeader("DataBase/FeedBack/FeedbackRegister.csv");
        String []Qs= new String[8];// feedback preparing
        Qs[0]="FB-"+FeedbackData.length;
        System.out.print("Enter FeedBack Name : ");
        Qs[1]=sc.nextLine();
        Qs[2] = SpecialFeatures.GetCurrentDate();
        System.out.println("Enter Question Below : ");
        for(int i=0;i<5;i++){
            System.out.print("Quention "+(i+1)+" : ");
            Qs[i+3]=sc.nextLine();
        }
        WriteCSV.appendToCSVWtN("DataBase/FeedBack/FeedbackRegister.csv",Qs);
        int stdlen=StdPersonalDatawoh.length;
        String [][]fbduelist=ReadCSV.readFileWithHeader("DataBase/FeedBack/FBdue.csv");// feedback - student submitted or not list
        for(int i=1;i<fbduelist.length;i++){
            if(fbduelist[i][1].equals("null")){
                fbduelist[i][1]=Qs[0];
            }
            else{
                fbduelist[i][1]=fbduelist[i][1]+"|"+Qs[0];
            }

        }
        WriteCSV.writeFile("DataBase/FeedBack/FBdue.csv",fbduelist);
        System.out.println(ColoredText.BOLD_GREEN+"\n\n!!! FeedBack Circulated Successfully\n\n"+ ColoredText.RESET);
    }

    public  void getFeedBackAnalysis(){
        String [][]fb=ReadCSV.readFileWithHeader("DataBase/FeedBack/FeedbackRegister.csv");
        System.out.println("FeedBack ID\tFeedBack Name\tIssue Date\n");
        for(int i=1;i<fb.length;i++){
            System.out.println(""+fb[i][0]+"       "+fb[i][1]+"        "+fb[i][2]);
        }
        System.out.println("Enter FeedBack Id (like for FB-1 give 1 ) : ");
        int id= SpecialFeatures.GetChoice(fb.length-1);

        SentimentAnalysis SA = new SentimentAnalysis();

        SA.getSentimentAnalysis(fb[id][0]);
    }

    public void GetComplaints(){
        int minComplaint=0;
        Scanner scanner = new Scanner(System.in);
        String [][]cmpData=ReadCSV.readFileWithHeader("DataBase/ComplaintRegister/ComplaintRegisterTable.csv");
        for(int i=1;i<cmpData.length;i++){
            if(cmpData[i][3].equals("Submitted")){
                minComplaint=1;
                String []comptxt= SpecialFeatures.ReadTxtFile("DataBase/ComplaintRegister/Complaints/"+cmpData[i][0]+".txt");
                System.out.println("\n\n");
                for(String s : comptxt){
                    System.out.println(s);
                }
                System.out.println("\n");
                System.out.print("write Down Your Comments (press Enter twice to finish) : \nWrite : ");
                StringBuilder paragraph = new StringBuilder();
                String line;

                while (!(line = scanner.nextLine()).isEmpty()) {
                    paragraph.append(line).append("\n");
                }
                cmpData[i][3]="Verified";
                try {
                    Mail.MainFunction(cmpData[i][2],"Response to Your Complaint - "+cmpData[i][0],paragraph.toString()+"\n\nBest Wishes,\nKANNAN S\nCheif Wardern\nkannan24@gmail.com\nSSN HOSTEL");
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }
        if(minComplaint==1){
            WriteCSV.writeFile("DataBase/ComplaintRegister/ComplaintRegisterTable.csv",cmpData);
            System.out.println("All Complaints Are Viewed Successfully\nNo More Complaints");
        }
        else{
            System.out.println(ColoredText.BOLD_YELLOW+"--------------------------------------\n|"+ ColoredText.BOLD_RED+"   !!! No Complaints Available     "+ ColoredText.BOLD_YELLOW+"  |\n--------------------------------------\n\n"+ ColoredText.RESET);

        }

    }





}



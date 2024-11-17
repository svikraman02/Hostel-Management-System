package StudentWork;

import AdditionalMethods.SpecialFeatures;
import Colors.ColoredText;
import CsvOperations.ReadCSV;
import CsvOperations.WriteCSV;
import Email.Mail;
import JTable.JFrameTable;
import Main.Main;
import MyExceptions.*;
import User.ForgetPassword;

import javax.mail.MessagingException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Pattern;

public class StudentCenter extends ForgetPassword {
    private String DigitalId;
    private String Name;
    private String Dept;
    private String Year;
    private String Email;
    private String MobileNo;
    private String Place;
    private String ParentName;
    private String ParentMobileNo;
    private String ParentEmail;
    private String HostelNo;
    private String BlockName;
    private String RoomNo;
    private String Type;

    public StudentCenter(String filepath) {
        super(filepath);
    }

    // Method to assign student information from the CSV
    public boolean AssignStudentInfo(String DigitalID) {
        String[][] StdData = ReadCSV.readFileWithHeader("DataBase/StudentsPersonalInfo.csv");

        boolean studentFound = false;

        // Check if DigitalID matches any student data
        for (int i=1;i<StdData.length;i++) {
            String[] stdDatum=StdData[i];
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
            System.out.println("Student with DigitalID " + DigitalID + " not found.");
            return studentFound;
        }

        // Assign room details if the student exists
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
        return studentFound;

    }

    public void fbFirst(){
        String fbstatus=CheckFeedBackDue();
        if(fbstatus.equals("null")){
            StudentPath();
        }
        else{
            fbFirst();
        }
    }       // Check For FeedBack Due
    // Main.Main.Main.Main method to handle login and assignment of student info
    public static void main(String[] args) {
        StudentLogin Stl = new StudentLogin();
        StudentCenter sdc = new StudentCenter("DataBase/users.csv");
        Scanner input = new Scanner(System.in);

        // Check if user credentials are valid
        if (Stl.CheckUser("DataBase/users.csv")) {
            System.out.println(ColoredText.BOLD_GREEN+"\nLogin successful \u2705 \n"+ ColoredText.RESET);
            sdc.AssignStudentInfo(Stl.getDigitalID());
//            System.out.println("Student Information Assigned Successfully!");
            System.out.println(ColoredText.BOLD_PURPLE+"! Hello "+sdc.getName()+" \uD83D\uDC4B \n\n"+ ColoredText.RESET);
//            sdc.StudentPath();
            sdc.fbFirst();



        } else {
//            System.out.println("Login failed.");
            boolean retry = true;
            while (retry) {
                System.out.println("Enter 1 - Re-Enter Credentials");
                System.out.println("Enter 2 - Forget Password");
                System.out.println("Enter 3 - Back to Home");
                int choice = SpecialFeatures.GetChoice(3);
                if (choice == 1) {
                    StudentCenter.main(args);
                    return;

                }
                else if(choice==2){
                    sdc.ChangePasword();
                    StudentCenter.main(args);
                    return;
                }
                else {
                    Main.main(args);  // Go back to home page
                    retry = false;
                }
            }
        }
    }

    // Getters and setters for StudentWork.StudentCenter fields
    public String getDigitalId() {
        return DigitalId;
    }

    public void setDigitalId(String digitalId) {
        DigitalId = digitalId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDept() {
        return Dept;
    }

    public void setDept(String dept) {
        Dept = dept;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }

    public String getPlace() {
        return Place;
    }

    public void setPlace(String place) {
        Place = place;
    }

    public String getParentName() {
        return ParentName;
    }

    public void setParentName(String parentName) {
        ParentName = parentName;
    }

    public String getParentMobileNo() {
        return ParentMobileNo;
    }

    public void setParentMobileNo(String parentMobileNo) {
        ParentMobileNo = parentMobileNo;
    }

    public String getParentEmail() {
        return ParentEmail;
    }

    public void setParentEmail(String parentEmail) {
        ParentEmail = parentEmail;
    }

    public String getHostelNo() {
        return HostelNo;
    }

    public void setHostelNo(String hostelNo) {
        HostelNo = hostelNo;
    }

    public String getBlockName() {
        return BlockName;
    }

    public void setBlockName(String blockName) {
        BlockName = blockName;
    }

    public String getRoomNo() {
        return RoomNo;
    }

    public void setRoomNo(String roomNo) {
        RoomNo = roomNo;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public void StudentPath(){
        System.out.println(ColoredText.BOLD_BLUE+"Enter 1 - View Profile\nEnter 2 - Room Detail\nEnter 3 - Home Pass\nEnter 4 - Attendance Details\nEnter 5 - Edit Profile\nEnter 6 - Raise Complaint\n"+ ColoredText.RESET+ ColoredText.RED+"Enter 7 - Log Out\n"+ ColoredText.BOLD_RED+"Enter 8 - Exit\n"+ ColoredText.RESET);
        int choice = SpecialFeatures.GetChoice(8);
        switch (choice) {
            case 1:
                System.out.println("\nYOUR PROFILE : \n");
                ViewProfile();
                StudentPath();
                break;
            case 2:
                System.out.println("\nROOM DETAILS : \n");
                RoomDetails();
                StudentPath();
                break;
            case 3:
                System.out.println("\nHome Pass DETAILS : \n");
                ClaimHomePass();
                StudentPath();
                break;
            case 4:
                System.out.println("\nATTENDANCE DETAILS\n");
                ViewAttendance();
                StudentPath();
                break;

            case 5:
                System.out.println("\nEDIT PROFILE : \n");
                EditProfile();
                StudentPath();
                break;
            case 6:
                System.out.println("\nCOMPLAINT REGISTER : \n");
                RaiseComplaint();
                StudentPath();
                break;

            case 7:
//                System.out.println("Main.Main.Main.Main Menu...");
                String[] args = new String[0];
                Main.main(args);
                break;
            case 8:
                System.exit(0);
                break;
            default:        //There is No default Condition Occurs it just for best practices of switch case
                System.out.println("Invalid choice, please enter a valid number.");
        }
    }

    public void ViewProfile(){
        System.out.println("Digital Id        : " + getDigitalId());
        System.out.println("Name              : " + getName());
        System.out.println("Department        : " + getDept());
        System.out.println("Year              : " + getYear());
        System.out.println("Email             : " + getEmail());
        System.out.println("Mobile No         : " + getMobileNo());
        System.out.println("Place             : " + getPlace());
        System.out.println("Parent Name       : " + getParentName());
        System.out.println("Parent Mobile No  : " + getParentMobileNo());
        System.out.println("Parent Email      : " + getParentEmail());
        System.out.println("\n\n");
    }

    public void RoomDetails(){
        System.out.println("Hostel No  : " + getHostelNo());
        System.out.println("Block Name : " + getBlockName());
        System.out.println("Room No    : " + getRoomNo());

        String room=getType();
        switch (room){
            case "CNAC":
                System.out.println("Type       : COMBINE NON - AC");
                break;
            case "CAC":
                System.out.println("Type       : COMBINE AC");
                break;
            case "SNAC":
                System.out.println("Type       : SINGLE NON - AC");
                break;
            case "SAC":
                System.out.println("Type       : SINGLE AC");
                break;
            default:
                System.out.println("Room Type not found");
        }
        System.out.println("\n\n");

    }


    public void UpdateInFile(){
        String[][] StdData = ReadCSV.readFileWithHeader("DataBase/StudentsPersonalInfo.csv");
        String DigitalId=getDigitalId();
        int idInx=-1;
        for(int i=1;i<StdData.length;i++){
            if(StdData[i][0].equals(DigitalId)){
                idInx=i;
                break;
            }
        }

        StdData[idInx][4]=getEmail();
        StdData[idInx][9]=getParentEmail();
        StdData[idInx][5]=getMobileNo();
        StdData[idInx][8]=getMobileNo();
        WriteCSV.writeFile("DataBase/StudentsPersonalInfo.csv",StdData);
    }

    public void EditProfile(){
        System.out.println("Enter 1 - Change Student Email ("+ ColoredText.BOLD_RED+"* Required Verification"+ ColoredText.RESET+" )\nEnter 2 - Change Parent Email ("+ ColoredText.BOLD_RED+"* Required Verification"+ ColoredText.RESET+" )\nEnter 3 - Change Student Mobile Number\nEnter 4 - Change Parent Mobile Number\nEnter 5 - Back\n");
        int choice = SpecialFeatures.GetChoice(5);
        switch (choice) {
            case 1:
                System.out.print("Are you want to Change Your Email (y/n) : ");
                if(SpecialFeatures.GetChoice('Y')){
                    ChangeSelfEmail();
                    UpdateInFile();
                }
                else{
                    EditProfile();
                }
                break;
            case 2:
                System.out.print("Are you want to Change Your Parent Email (y/n) : ");
                if(SpecialFeatures.GetChoice('Y')){
                    ChangeParentEmail();
                    UpdateInFile();
                }
                else{
                    EditProfile();
                }
                break;
            case 3:
                System.out.print("Are you want to Change Your Mobile Number (y/n) : ");
                if(SpecialFeatures.GetChoice('Y')){
                    ChangeSelfMobile();
                    UpdateInFile();
                }
                else{
                    EditProfile();
                }
                break;
            case 4:
                System.out.print("Are you want to Change Your Parent Mobile Number (y/n) : ");
                if(SpecialFeatures.GetChoice('Y')){
                    ChangeParetMobile();
                    UpdateInFile();
                }
                else{
                    EditProfile();
                }
                break;
            case 5:
                StudentPath();
            default:
                System.out.print("Error in Choosing Choices");
        }
    }

    public void ChangeSelfEmail(){
        System.out.println("Your Current Email : "+getEmail());
        System.out.print("Are you want to Send OTP to Your Registered Email ID (y/n) : ");
        if(SpecialFeatures.GetChoice('Y')){
            int GeneratedOTP = SpecialFeatures.GenerateOTP(6);
            SendOTP(getEmail(),"Hello,\n\t "+ GeneratedOTP +" is OTP for Changing Student Email Id (Don't Share OTP With Anyone) \n\n\nWarm Regards,\nSSN HOSTEL","OTP For Changing Student Email");
            System.out.print("Enter OTP Sent To Your Registered Email : ");
            Scanner input =new Scanner(System.in);
            int EnteredOTP=input.nextInt();
            if(EnteredOTP==GeneratedOTP){
                System.out.println(ColoredText.BOLD_GREEN+"OTP Verified Successfuly"+ ColoredText.RESET);
//                System.out.print("Enter new Email : ");
                String newEmail=TakeEmail("Enter New Email : ");
                System.out.println(ColoredText.BOLD_GREEN+"Email Changed Successfully\n\n"+ ColoredText.RESET);
                setEmail(newEmail);
            }
            else{
                System.out.println(ColoredText.BOLD_RED+"!!! Wrong OTP"+ ColoredText.RESET);
                System.out.print("Re-enter OTP : ");
                EnteredOTP=input.nextInt();
                if(EnteredOTP==GeneratedOTP) {
                    System.out.println(ColoredText.BOLD_GREEN+"OTP Verified Successfuly"+ ColoredText.RESET);
                    String newEmail = TakeEmail("Enter New Email : ");
                    System.out.println(ColoredText.BOLD_GREEN+"Email Changed Successfully\n\n"+ ColoredText.RESET);
                    setEmail(newEmail);
                }
                else{
                    System.out.println((ColoredText.BOLD_RED+"!!! Wrong OTP Try Again Later"+ ColoredText.RESET));
                }
            }
        }
        else{
            EditProfile();
        }


    }

    public void ChangeParentEmail(){
        System.out.println("Your Parent Email : "+getParentEmail());
        System.out.print("Are you want to Send OTP to Your Parent Registered Email ID (y/n) : ");
        if(SpecialFeatures.GetChoice('Y')){
            int GeneratedOTP = SpecialFeatures.GenerateOTP(6);
            SendOTP(getParentEmail(),"Hello Parent ,\n\t "+ GeneratedOTP +" is OTP for Changing Parent Email Id (Don't Share OTP With Anyone) \n\n\nWarm Regards,\nSSN HOSTEL","OTP For Changing Parent Email");
            System.out.print("Enter OTP Sent To Your Registered Email : ");
            Scanner input =new Scanner(System.in);
            int EnteredOTP=input.nextInt();
            if(EnteredOTP==GeneratedOTP){
                System.out.println(ColoredText.BOLD_GREEN+"OTP Verified Successfuly"+ ColoredText.RESET);
//                System.out.print("Enter new Email : ");
                String newEmail=TakeEmail("Enter New Email : ");
                System.out.println(ColoredText.BOLD_GREEN+"Email Changed Successfully\n\n"+ ColoredText.RESET);
                setParentEmail(newEmail);
            }
            else{
                System.out.println(ColoredText.BOLD_RED+"!!! Wrong OTP"+ ColoredText.RESET);
                System.out.print("Re-enter OTP : ");
                EnteredOTP=input.nextInt();
                if(EnteredOTP==GeneratedOTP) {
                    System.out.println(ColoredText.BOLD_GREEN+"OTP Verified Successfuly"+ ColoredText.RESET);
                    String newEmail = TakeEmail("Enter New Email : ");
                    System.out.println(ColoredText.BOLD_GREEN+"Email Changed Successfully\n\n"+ ColoredText.RESET);
                    setParentEmail(newEmail);
                }
                else{
                    System.out.println((ColoredText.BOLD_RED+"!!! Wrong OTP Try Again Later"+ ColoredText.RESET));
                }
            }
        }
        else{
            EditProfile();
        }

    }

    public String TakeMobileNo(String prompt){
        Scanner sc=new Scanner(System.in);
        String NewNumber;
        System.out.print(prompt);

        try {
            NewNumber = sc.next();

            // Check if input is a valid integer
            if (!NewNumber.matches("-?\\d+")) {
                throw new InvalidIntegerInputException("Invalid Mobile Number! It Must Be an Interger");
            }
            if (NewNumber.length()!=10) {
                throw new InvalidMobileNumberException("Invalid Mobile Number! It should contain exactly 10 digits");
            }

        } catch (InvalidMobileNumberException e) {
            System.out.println(ColoredText.BOLD_RED+e.getMessage()+ ColoredText.RESET);
            return TakeMobileNo(prompt);
        } catch (InvalidIntegerInputException e){
            System.out.println(ColoredText.BOLD_RED+e.getMessage()+ ColoredText.RESET);
            return TakeMobileNo(prompt);
        }
        return NewNumber;
    }

    public String TakeEmail(String prompt){
        String EMAIL_REGEX = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$";
        Scanner sc=new Scanner(System.in);
        String NewEmail = "";
        System.out.print(prompt);

        try {
            NewEmail=sc.nextLine();
            if (NewEmail == null || !Pattern.matches(EMAIL_REGEX, NewEmail)) {
                throw new InvalidEmailException("Invalid email address format.");
            }
        }
        catch(InvalidEmailException e){
            System.out.println(ColoredText.BOLD_RED+e.getMessage()+ ColoredText.RESET);
            return TakeEmail(prompt);
        }
        return NewEmail;
    }

    public void ChangeSelfMobile(){
        System.out.println("Your Mobile NO : "+getMobileNo());
        System.out.print("Are you want to Change (y/n) : ");
        if(SpecialFeatures.GetChoice('Y')){
            String NewNumber=TakeMobileNo("Enter New No : ");
            setMobileNo(NewNumber);
        }
    }

    public void ChangeParetMobile(){
        System.out.println("Your Parent Mobile NO : "+getParentMobileNo());
        System.out.print("Are you want to Change (y/n) : ");
        if(SpecialFeatures.GetChoice('Y')){
            String NewNumber=TakeMobileNo("Enter New No : ");
            setParentEmail(NewNumber);
        }
    }

    public void SendOTP(String Email,String Body,String subject){
//        SendEmail(Email,message)
        try {
            Mail.MainFunction(Email,subject,Body);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void RaiseComplaint(){

        String [][]Register=ReadCSV.readFileWithHeader("DataBase/ComplaintRegister/ComplaintRegisterTable.csv");

        try{
            for(int i=1;i<Register.length;i++){
                if(Register[i][1].equals(DigitalId)){
                    if(Register[i][3].equals("Submitted")){
                        throw new ComplainExceedException(ColoredText.BOLD_YELLOW+"------------------------------------------------------------\n|"+ ColoredText.BOLD_RED+"  !!! Your previous Complaint is being processed        "+ ColoredText.BOLD_YELLOW+"  |\n------------------------------------------------------------\n"+ ColoredText.RESET);
                    }
                }
            }
        }
        catch (ComplainExceedException e){
            System.out.println(e.getMessage());
            return;
        }
        String cmpId=getDigitalId()+Register.length;
        String filePath = "DataBase/ComplaintRegister/Complaints/"+cmpId+".txt";

        Scanner scanner = new Scanner(System.in);
        System.out.println("Submit your Complaint (press Enter twice to finish) :\n");
        System.out.print("Write Down Here :  ");
        WriteCSV.appendToCSVWtN("DataBase/ComplaintRegister/ComplaintRegisterTable.csv", new String[]{cmpId,getDigitalId(),getEmail(),"Submitted"});
        StringBuilder paragraph = new StringBuilder();
        String line;

        while (!(line = scanner.nextLine()).isEmpty()) {
            paragraph.append(line).append("\n");
        }
//        paragraph.append("\n").append("\n")
        paragraph.append("\n\nComplaint Registration Details:\nComplainant Name : "+getName()+"\nDigital ID : "+getDigitalId()+"\nDate of Registration : "+ SpecialFeatures.GetCurrentDate()+"\nTime of Registration : "+ SpecialFeatures.GetCurrentTime()+"\n");


        SpecialFeatures.writeTxtFile(paragraph.toString(),filePath);
        System.out.println(ColoredText.BOLD_GREEN+"Complaint Registered Successfully\n\n"+ ColoredText.RESET);
    }

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
        String DigitalId=getDigitalId();
        int idInx=-1;
        for(int i=0;i<AttendaceData.length;i++){
            if(AttendaceData[i][0].equals(DigitalId)){
                idInx=i;
                break;
            }
        }
        int PresentDays=0;
        int AbsentDays=0;
        for(int i=1;i<AttendaceData[idInx].length;i++) {

            if (AttendaceData[idInx][i].equals("P")) {
                PresentDays++;
            } else if(AttendaceData[idInx][i].equals("A")){
                AbsentDays++;
            }
        }

        JFrameTable.DisplayTable(new String[][]{AttendaceData[0]},AttendanceDataColumns,"Attendance");
        double PresentPercent=(PresentDays*100)/(PresentDays+AbsentDays);
        System.out.println("Attendance Percentage : \n"+PresentPercent);
        System.out.print("Are You Want To Search More (y/n) : ");
        if(SpecialFeatures.GetChoice('Y')){
            ViewAttendance();
        }



    }

    public void ClaimHomePass(){
        String [][]PassData=ReadCSV.readFileWithHeader("DataBase/HomePass/HomePassRegister.csv");
        try{
            for(int i=1;i<PassData.length;i++){
                if(PassData[i][1].equals(DigitalId)){
                    if(PassData[i][10].equals("Submitted")){
                        throw new PassExceedException(ColoredText.BOLD_YELLOW+"------------------------------------------------------------\n|"+ ColoredText.BOLD_RED+"  !!! Your previous Home pass request is being processed"+ ColoredText.BOLD_YELLOW+"  |\n------------------------------------------------------------\n"+ ColoredText.RESET);
                    }
                }
            }
        }
        catch (PassExceedException e){
            System.out.println(e.getMessage());
            return;
        }

        LocalDate Out= SpecialFeatures.GoingDate();
        String OutDate=""+Out;
        String InDate=""+ SpecialFeatures.ReturnDate(Out);
        Scanner sc=new Scanner(System.in);

        System.out.print("Enter Any Reason (If Applicable) : ");
        String Reason=sc.nextLine();
        if(Reason.equals("")) { Reason="Null";}
        String status="Submitted";
        String []line={" ",getDigitalId(),getName(),getYear(),getDept(),getEmail(),getParentEmail(),OutDate,InDate,Reason,status};
        WriteCSV.appendToCSV("DataBase/HomePass/HomePassRegister.csv",line);
        System.out.print(ColoredText.BOLD_YELLOW+"\n\n************************************************************************************************\n*"+ ColoredText.RESET);
        System.out.println(ColoredText.BOLD_GREEN+"  !!! Home Pass Applied Successfully \t\t\t\t\t\t\t\t\t\t\t\t\t\t   "+ ColoredText.BOLD_YELLOW+"*\n*"+ ColoredText.BOLD_GREEN+"  Later,You will receive an email notifying you if your request has been accepted or rejected"+ ColoredText.BOLD_YELLOW+" *\n*"+ ColoredText.BOLD_GREEN+"\t\t\t\t\t\t\t\t\t\t\tThank You\t\t\t\t\t\t\t\t\t\t  "+ ColoredText.BOLD_YELLOW+" *"+ ColoredText.RESET);
        System.out.println(ColoredText.BOLD_YELLOW+"************************************************************************************************"+ ColoredText.RESET);


    }

    public String CheckFeedBackDue(){
        String [][]stdDue=ReadCSV.readFileWithHeader("DataBase/FeedBack/FBDue.csv");
        String fb;
        int i;
        for(i=1;i<stdDue.length;i++){
            if(stdDue[i][0].equals(getDigitalId())){
                if(stdDue[i][1].equals("null"))
                    return "null";
                else{
                    fb=stdDue[i][1];
                    String []ids=fb.split("\\|");
                    if(SubmitFeedBack(ids[0])){
                        if(ids.length==1) {
                            fb = "null";
                            stdDue[i][1] = fb;
                        }
                        else{
                            fb=fb.replace(ids[0]+"|","");
                            stdDue[i][1]=fb;
                        }
                        WriteCSV.writeFile("DataBase/FeedBack/FBDue.csv",stdDue);
                        return "filled";
                    }
                }
            }
        }
        return "null";
    }

    public boolean SubmitFeedBack(String id){
        Scanner sc = new Scanner(System.in);
        System.out.println(ColoredText.BOLD_RED+"You have a Feed Back Due - "+id+ ColoredText.RESET+"\n");
        String [][]fbqts=ReadCSV.readFileWithHeader("DataBase/FeedBack/FeedbackRegister.csv");
        String []ans=new String[5];
        for(int i=1;i<fbqts.length;i++){
//            System.out.println("IDS : "+fbqts[i][0]);
            if(fbqts[i][0].equals(id)){

                System.out.println("FEED BACK : "+fbqts[i][1]);
                System.out.println("Question 1 : "+fbqts[i][3]);
                ans[0]=sc.nextLine();
                System.out.println("Question 2 : "+fbqts[i][4]);
                ans[1]=sc.nextLine();
                System.out.println("Question 3 : "+fbqts[i][5]);
                ans[2]=sc.nextLine();
                System.out.println("Question 4 : "+fbqts[i][6]);
                ans[3]=sc.nextLine();
                System.out.println("Question 5 : "+fbqts[i][7]);
                ans[4]=sc.nextLine();

                System.out.println(ColoredText.BOLD_GREEN+"\n!!! Submitted Succesfully \nThank You\n"+ ColoredText.RESET);

                String filePath = "DataBase/FeedBack/FeedBackList/"+id+"-"+getDigitalId()+".txt";
                SpecialFeatures.writeTxtFileLines(filePath,ans);
                return true;

            }
        }
        return false;



//        String [][]stdDue=CsvOperations.ReadCSV.readFileWithHeader("DataBase/FeedBack/FBDue.csv");
//        String Duefb="";
////        ArrayList<String> numfb = new ArrayList<>();
//
//        for(String []i : stdDue){
//            if(i[0].equals(getDigitalId())){
//                Duefb=i[1];
//                break;
//            }
//        }
//        String []numfb=Duefb.split("|");
//        String []fbidx=numfb[0].split("-");
//        String [][]fbreg=CsvOperations.ReadCSV.readFileWithHeader("DataBase/FeedBack/FeedbackRegister.csv");
//        System.out.println("FEED BACK : "+fbreg);


    }

}


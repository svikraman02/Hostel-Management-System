package StudentWork;

import Colors.ColoredText;
import CsvOperations.ReadCSV;
import User.UserInfo;

public class StudentLogin extends UserInfo {
//    static Scanner input =new Scanner(System.in);
    private String[][] UsersDetails;

    public void getInputs(){
        this.GetCredentials();
    }
    public StudentLogin(){
        this.GetCredentials();
    }

    public boolean CheckUser(String filePath){
        UsersDetails= ReadCSV.readFileWithHeader(filePath);
        int username_cp=-1;
//        System.out.println(getUsername());
        for(int i=1;i<UsersDetails.length;i++){
            if(UsersDetails[i][0].equals(getUsername())){
                setMailAddress(UsersDetails[i][3]);
                username_cp=i;
                break;
            }
        }
        if(username_cp==-1){
            System.out.println(ColoredText.BOLD_RED+"\n!!! Username Not Exists\n"+ ColoredText.RESET);
//            System.out.println();
            return false;
        }
        else{
            if(UsersDetails[username_cp][1].equals(getPassword())){
                setDigitalID(UsersDetails[username_cp][2]);
//                System.out.println("Login Succesfully");
//                this.Username=UsersDetails[username_cp][0];
//                this.Password=UsersDetails[username_cp][1];
//                this.DigitalID=UsersDetails[username_cp][2];
//                this.Email="asd";
                return true;
            }
            else{
                System.out.println(ColoredText.BOLD_RED+"\n!!! Wrong Password\n"+ ColoredText.RESET);
//                System.out.println();
                return false;
            }
        }
    }

}
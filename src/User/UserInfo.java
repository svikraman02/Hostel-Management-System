package User;

import java.util.Scanner;

public class UserInfo {
    public String Username;
    public String Password;
    public String DigitalID;
    public String MailAddress;

    public void GetCredentials(){
        Scanner input =new Scanner(System.in);
        System.out.print("Enter UserName : ");
        this.Username=input.nextLine();
        System.out.print("Enter Password : ");
        this.Password=input.nextLine();
    }

    // Getter for DigitalID
    public String getDigitalID() {
        return this.DigitalID;
    }
    // Getter for Username
    public String getUsername() {
        return this.Username;
    }

    // Getter for Password
    public String getPassword() {
        return this.Password;
    }
    public void setDigitalID(String digitalID) {
        this.DigitalID = digitalID;
    }

    public void setMailAddress(String mail) {
        this.MailAddress = mail;
    }



}

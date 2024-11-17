package User;

import AdditionalMethods.SpecialFeatures;
import Colors.ColoredText;
import CsvOperations.ReadCSV;
import CsvOperations.WriteCSV;
import Email.Mail;
import Main.Main;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Scanner;

public class ForgetPassword {

    private String path;
    private String [][]UserLogins;
    private String username;

    public ForgetPassword(String filepath){
        this.path=filepath;
        this.UserLogins= ReadCSV.readFileWithHeader(filepath);
    }

    public void ChangePasword(){
        String []args={};
        System.out.println(ColoredText.BOLD_CYAN +ColoredText.BG_BLACK+"\nForget Password Section : \n"+ColoredText.RESET);
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter username : ");
        username=sc.nextLine();
        int cp=0;
        for(int i=1;i<UserLogins.length;i++){
            if(UserLogins[i][0].equals(username)){
                cp=1;
                System.out.println("Username : "+username);
                System.out.println("we’ll send an OTP to your registered email ("+UserLogins[i][3]+") for verification.");
                System.out.print("Are you want to Continue (y/n) : ");
                if(SpecialFeatures.GetChoice('y')){
                    int otp=SpecialFeatures.GenerateOTP(6);
                    System.out.println("OTP SENT : "+otp);

                    try {
                        Mail.MainFunction(UserLogins[i][3],"Security Alret - OTP for Password Recovery","Hello User!,\n\t\tWe received a request to reset the password for your account. Please use the following OTP (One-Time Password) to complete the verification process and reset your password:\n"+otp+"Thank You,SSN HOSTEL");
                    } catch (MessagingException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    int Ent_otp;
                    String pass;
                    System.out.print("Enter OTP To Change Password : ");
                    Ent_otp=sc.nextInt();
                    if(Ent_otp==otp){
                        System.out.println(ColoredText.BOLD_GREEN+"OTP Verified Successfully"+ColoredText.RESET);
                        System.out.print("Enter You New Password : ");
                        pass= sc.next();
                        UserLogins[i][1]=pass;
                        WriteCSV.writeFile(path,UserLogins);
                        System.out.println(ColoredText.BOLD_GREEN+"Password Changed Successfully"+ColoredText.RESET);
                        System.out.println(ColoredText.BOLD_BLUE+"Redirecting To Login....."+ColoredText.RESET);
                        return;
                    }
                    else {
                        System.out.print(ColoredText.BOLD_RED+"!!! Wrong OTP Chances left : 1"+ColoredText.RESET);
                        System.out.print("Re-Enter OTP To Change Password : ");
                        Ent_otp=sc.nextInt();
                        if(Ent_otp==otp){
                            System.out.println(ColoredText.BOLD_GREEN+"OTP Verified Successfully"+ColoredText.RESET);
                            System.out.print("Enter You New Password : ");
                            pass= sc.next();
                            UserLogins[i][1]=pass;
                            WriteCSV.writeFile(path,UserLogins);
                            System.out.println(ColoredText.BOLD_GREEN+"Password Changed Successfully"+ColoredText.RESET);
                            System.out.println(ColoredText.BOLD_BLUE+"Redirecting To Login....."+ColoredText.RESET);
                            return;
                        }
                        else {
                            System.out.print(ColoredText.BOLD_RED+"!!! Wrong OTP, Redirecting to Main Menu"+ColoredText.RESET);
                            Main.main(args);
                        }
                    }
                }
                else{
                    System.out.println(ColoredText.BOLD_RED+"Forget Password Process Cancelled"+ColoredText.RESET);
                    Main.main(args);
                }

            }
        }
        System.out.println("Username Not Exist, Redirecting to Main Menu---");
        Main.main(args);

    }
}

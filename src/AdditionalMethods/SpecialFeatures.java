package AdditionalMethods;

import Colors.ColoredText;
import MyExceptions.ChoiceExceedException;
import MyExceptions.InvalidIntegerInputException;

import java.awt.*;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.List;


public class SpecialFeatures {

    public static int GetChoice(int num){
        Scanner input =new Scanner(System.in);
        System.out.print("Enter Choice : ");
        int tempIn;
        try {
//            System.out.print("Enter an integer: ");
            String s = input.nextLine();

            // Check if input is a valid integer
            if (!s.matches("-?\\d+")) {
                throw new InvalidIntegerInputException("Invalid input: expected an integer");
            }

            tempIn = Integer.parseInt(s);
//            System.out.println("You entered: " + number);

        } catch (InvalidIntegerInputException e) {
            System.out.println(ColoredText.BOLD_RED+e.getMessage()+ColoredText.RESET);
            return GetChoice(num);
        }

//        int tempIn=input.nextInt();
        try{
            if(tempIn<0 || tempIn>num){
                throw new ChoiceExceedException(""+ColoredText.BOLD_RED+"!!! Invalid Choice"+ColoredText.RESET+"\nRe-");
            }

        }
        catch (ChoiceExceedException e){
            System.out.print(e.getMessage());
            return GetChoice(num);
        }
        return tempIn;

    }

    public static int GetChoice(String InputMsg,String WrongMsg,int MinNum,int MaxNum){
        Scanner input =new Scanner(System.in);
        System.out.print(InputMsg);
        int tempIn=input.nextInt();
        if(tempIn>=MinNum && tempIn<=MaxNum){
            return tempIn;
        }
        else{
            System.out.print(WrongMsg);
            return GetChoice(InputMsg,WrongMsg,MinNum,MaxNum);
        }
    }

    public static boolean GetChoice(char y){
        Scanner input =new Scanner(System.in);
        char tempIn=input.next().charAt(0);
        if(tempIn=='y' || tempIn=='Y'){
            return true;
        }
        else if(tempIn=='n' || tempIn=='N'){
            return false;
        }
        else{
            System.out.print(ColoredText.BOLD_RED+"!!! Invalid Choice"+ColoredText.RESET+"\nRe-Enter : ");
            return GetChoice(y);
        }
    }

    public static int GenerateOTP(int digit){
        Random random = new Random();
        int randomNumber = (int)Math.pow(10,digit-1) + random.nextInt((int)(Math.pow(10,digit)-Math.pow(10,digit-1)));
        return randomNumber;
    }

    public static String GetCurrentDate() {

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = currentDate.format(dateFormatter);
        return formattedDate;
    }

    public static int GetCurrentDate(char finder) {

        if(finder=='M' || finder =='m'){
            int currentMonth = LocalDate.now().getMonthValue();
            return currentMonth;
        }
        else if(finder=='D' || finder=='d'){
            int currentDay = LocalDate.now().getDayOfMonth();
            return currentDay;
        }
        else{
            int currentYear = LocalDate.now().getYear();
            return currentYear;
        }
    }

    public static String GetCurrentTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return LocalTime.now().format(formatter);
    }

    public static void writeTxtFile(String content, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(content);
//            System.out.println("Paragraph written to " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    public static void writeTxtFileLines(String filePath,String[] lines) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();  // Adds a new line after each line of content
            }
//            System.out.println("All lines have been written to " + filePath);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    public static String[] ReadTxtFile(String filePath) {

        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return lines.toArray(new String[0]); // Convert List to String array
    }

    public static LocalDate GoingDate(){
        Scanner scanner = new Scanner(System.in);
        LocalDate goingDate = null;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate today = LocalDate.now();

        while (goingDate == null) {
            System.out.print("Enter going date (DD/MM/YYYY): ");
            String goingInput = scanner.nextLine();

            try {
                goingDate = LocalDate.parse(goingInput, formatter);
                if (goingDate.isBefore(today)) {
                    System.out.println("Going date must be greater than today. Please try again.");
                    goingDate = null; // Reset if invalid
                }
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please try again.");
            }
        }

        return goingDate;

    }

    public static LocalDate ReturnDate(LocalDate goingDate){
        Scanner scanner = new Scanner(System.in);
        LocalDate returningDate = null;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate today = LocalDate.now();

        while (returningDate == null) {
            System.out.print("Enter returning date (DD/MM/YYYY): ");
            String returningInput = scanner.nextLine();

            try {
                returningDate = LocalDate.parse(returningInput, formatter);
                if (returningDate.isBefore(today)) {
                    System.out.println("Returning date must be greater than today. Please try again.");
                    returningDate = null; // Reset if invalid
                } else if (returningDate.isBefore(goingDate)) {
                    System.out.println("Returning date must be greater than going date. Please try again.");
                    returningDate = null; // Reset if invalid
                }
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please try again.");
            }
        }

        return returningDate;

    }

    public static Dimension ScreenSizeExample(){

        Toolkit toolkit = Toolkit.getDefaultToolkit();

        // Get the screen size as a Dimension object
        Dimension screenSize = toolkit.getScreenSize();

        // Extract width and height
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        return screenSize;

    }

}

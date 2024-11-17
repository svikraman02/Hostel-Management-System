package CsvOperations;

import java.io.*;

public class WriteCSV {

    public static void writeFile(String csvFile, String[][] data) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(csvFile))) {
            for (String[] row : data) {
                pw.println(String.join(",", row));
            }
//            System.out.println("CSV file updated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error writing to the CSV file.");
        }
    }

    public static void appendToCSVWtN(String csvFile,String[] line) {   ///without serial number
        try (PrintWriter pw = new PrintWriter(new FileWriter(csvFile, true))) { // 'true' to append
            pw.println(String.join(",", line));
//            System.out.println("Line appended to CSV file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error appending to the CSV file.");
        }
    }


    public static void appendToCSV( String csvFile,String[] line) { //with Serial number
        int newSerialNo = getNextSerialNo(csvFile);
        line[0] = String.valueOf(newSerialNo); // Set the new serial number

        try (PrintWriter pw = new PrintWriter(new FileWriter(csvFile, true))) { // 'true' to append
            pw.println(String.join(",", line));
//            System.out.println("Line appended to CSV file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error appending to the CSV file.");
        }
    }

    private static int getNextSerialNo(String csvFile) {
        int maxSerialNo = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            // Skip the header
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length > 0) {
                    try {
                        int serialNo = Integer.parseInt(values[0].trim());
                        maxSerialNo = Math.max(maxSerialNo, serialNo);
                    } catch (NumberFormatException e) {
                        // Handle invalid number format, if necessary
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading from the CSV file.");
        }

        return maxSerialNo + 1; // Return the next serial number
    }

}

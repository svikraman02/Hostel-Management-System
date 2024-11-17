package CsvOperations;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadCSV {


    public static String[][] readFile(String csvFile) {
        String line;
        String csvSplitBy = ",";
        List<String[]> dataList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            // Read the header (optional)
            line = br.readLine();  // Skip or handle the header as needed
//            System.out.println("Header: " + line);

            // Read and process each line
            while ((line = br.readLine()) != null) {
                // Split by comma
                String[] data = line.split(csvSplitBy);

                // Add row data to the list
                dataList.add(data);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Convert the list to a 2D array
        String[][] dataArray = new String[dataList.size()][];
        return dataList.toArray(dataArray);
    }


    public static String[] readHeader(String csvFile) {
        String line;
        String csvSplitBy = ",";
        String[] Headline = new String[0];
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            // Read the header (optional)
            line = br.readLine();
            Headline = line.split(csvSplitBy);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return Headline;
    }

    public static String[][] readFileInPipeLine(String csvFile){
        String line;
        String csvSplitBy = ",";
        List<String[]> dataList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            // Read the header (optional)
            line = br.readLine();  // Skip or handle the header as needed
//            System.out.println("Header: " + line);

            // Read and process each line
            while ((line = br.readLine()) != null) {
                // Split by comma
                String[] data = line.split(csvSplitBy);
                if(data.length==6){
                    data[4]=data[4].replace("|"," , ");
                    data[5]=data[5].replace("|"," , ");
                }


                // Add row data to the list
                dataList.add(data);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Convert the list to a 2D array
        String[][] dataArray = new String[dataList.size()][];
        return dataList.toArray(dataArray);
    }

    public static String[][] readFileWithHeader(String csvFile) {
        String line;
        String csvSplitBy = ",";
        List<String[]> dataList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            // Read the header and add it to the list
            line = br.readLine();
            if (line != null) {
                dataList.add(line.split(csvSplitBy)); // Add header
            }

            // Read and process each line
            while ((line = br.readLine()) != null) {
                // Split by comma
                String[] data = line.split(csvSplitBy);
                dataList.add(data); // Add row data to the list
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Convert the list to a 2D array
        String[][] dataArray = new String[dataList.size()][];
        return dataList.toArray(dataArray);
    }

}

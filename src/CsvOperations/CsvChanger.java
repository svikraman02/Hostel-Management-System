package CsvOperations;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvChanger extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private String csvFilePath;
    private String newFilePath;

    public void StartCsvEditor(String csvFilePath, String newFilePath,String heading,String Status) {
        this.csvFilePath = csvFilePath;
        this.newFilePath = newFilePath;
        setTitle(heading);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Load data from CSV
        String[][] data = loadCsvData();
        String[] columnNames = loadCsvHeader();

        // Set up the table model and table
        tableModel = new DefaultTableModel(data, columnNames);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Save button
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> saveCsvData(Status));
        add(saveButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    public String[] loadCsvHeader() {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String headerLine = br.readLine();
            if (headerLine != null) {
                return headerLine.split(",");
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading CSV header: " + e.getMessage());
        }
        return new String[0];
    }

    public String[][] loadCsvData() {
        List<String[]> dataList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            // Skip the header line
            br.readLine();

            String line;
            // Read the rest of the data
            while ((line = br.readLine()) != null) {
                // Split each line by comma and add to the list
                dataList.add(line.split(","));
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading CSV data: " + e.getMessage());
        }

        // Convert List to 2D array for JTable
        return dataList.toArray(new String[0][]);
    }

    public void saveCsvData(String Status) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(newFilePath))) {
            int rowCount = tableModel.getRowCount();
            int colCount = tableModel.getColumnCount();

            // Write header
            for (int j = 0; j < colCount; j++) {
                pw.print(tableModel.getColumnName(j));
                if (j < colCount - 1) pw.print(",");
            }
            pw.println();

            // Write data
            for (int i = 0; i < rowCount; i++) {
                StringBuilder row = new StringBuilder();
                for (int j = 0; j < colCount; j++) {
                    row.append(tableModel.getValueAt(i, j).toString());
                    if (j < colCount - 1) row.append(",");
                }
                pw.println(row);
            }

            JOptionPane.showMessageDialog(this, " !!!! "+Status);

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving CSV file: " + e.getMessage());
        }
    }

    public static void EditCsv(String csvFilePath, String newFilePath,String status,String heading) {
        SwingUtilities.invokeLater(() -> {
            CsvChanger editor = new CsvChanger();
            editor.StartCsvEditor(csvFilePath, newFilePath,status,heading);
        });
    }
}

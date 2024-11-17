package SupervisorWork;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AttendanceCsvEditor extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private String csvFilePath;
    private String newFilePath;

    public void StartCsvEditor(String csvFilePath, String newFilePath, String targetColumnName, String data1, String data2,String heading,String status) {
        this.csvFilePath = csvFilePath;
        this.newFilePath = newFilePath;
        setTitle(heading);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Close only the window, not the whole app
        setLayout(new BorderLayout());

        // Load data and column names from CSV
        String[][] data = loadCsvData();
        String[] columnNames = loadCsvHeaders();

        // Set up the table model and table
        tableModel = new DefaultTableModel(data, columnNames);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Initialize the specified column with data1 if cells are empty
        initializeColumnWithData(targetColumnName, data1);

        // Add mouse listener to alternate data in specified column
        addCellToggleListener(targetColumnName, data1, data2);

        // Save button
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> saveCsvData(status));
        add(saveButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    public String[] loadCsvHeaders() {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String headerLine = br.readLine();
            if (headerLine != null) {
                return headerLine.split(",");
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading CSV headers: " + e.getMessage());
        }
        return new String[0];
    }

    public String[][] loadCsvData() {
        List<String[]> dataList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            br.readLine(); // Skip the header line

            // Read the data rows
            String line;
            while ((line = br.readLine()) != null) {
                dataList.add(line.split(","));
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading CSV data: " + e.getMessage());
        }

        return dataList.toArray(new String[0][]);
    }

    public void initializeColumnWithData(String targetColumnName, String data1) {
        int targetColumnIndex = tableModel.findColumn(targetColumnName);

        if (targetColumnIndex != -1) {  // If the column exists
            int rowCount = tableModel.getRowCount();
            for (int i = 0; i < rowCount; i++) {
                Object cellValue = tableModel.getValueAt(i, targetColumnIndex);
                if (cellValue == null || cellValue.toString().trim().isEmpty()) {  // Check if cell is empty
                    tableModel.setValueAt(data1, i, targetColumnIndex);  // Set each empty cell in the column to data1
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Column '" + targetColumnName + "' not found.");
        }
    }

    public void addCellToggleListener(String targetColumnName, String data1, String data2) {
        int targetColumnIndex = tableModel.findColumn(targetColumnName);

        if (targetColumnIndex != -1) {  // If the column exists
            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int row = table.getSelectedRow();
                    int col = table.getSelectedColumn();

                    if (col == targetColumnIndex) {  // Only toggle if clicked in the target column
                        Object currentValue = tableModel.getValueAt(row, col);
                        String newValue = (currentValue != null && currentValue.toString().equals(data1)) ? data2 : data1;
                        tableModel.setValueAt(newValue, row, col);
                    }
                }
            });
        } else {
            JOptionPane.showMessageDialog(this, "Column '" + targetColumnName + "' not found.");
        }
    }

    public void saveCsvData(String status) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(newFilePath))) {
            int rowCount = tableModel.getRowCount();
            int colCount = tableModel.getColumnCount();

            // Save headers
            for (int j = 0; j < colCount; j++) {
                pw.print(tableModel.getColumnName(j));
                if (j < colCount - 1) pw.print(",");
            }
            pw.println();

            // Save data rows
            for (int i = 0; i < rowCount; i++) {
                for (int j = 0; j < colCount; j++) {
                    Object cellValue = tableModel.getValueAt(i, j);
                    pw.print(cellValue != null ? cellValue.toString() : ""); // Use empty string if cell is null
                    if (j < colCount - 1) pw.print(",");
                }
                pw.println();
            }

            JOptionPane.showMessageDialog(this, "!!! "+status);

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving CSV file: " + e.getMessage());
        }
    }

    public static void EditCsv(String csvFilePath, String newFilePath, String targetColumnName, String data1, String data2,String heading,String status) {
        SwingUtilities.invokeLater(() -> {
            AttendanceCsvEditor editor = new AttendanceCsvEditor();
            editor.StartCsvEditor(csvFilePath, newFilePath, targetColumnName, data1, data2,heading,status);
        });
    }
}

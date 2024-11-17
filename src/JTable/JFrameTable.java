package JTable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class JFrameTable {

    public static void DisplayTable (String[][] data,String[] columnNames,String Heading) {

        // Create the JFrame
        JFrame frame = new JFrame(Heading);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Only close the JFrame
        frame.setSize(400, 300);

        // Create a JTable with the data and column names
        JTable table = new JTable(new DefaultTableModel(data, columnNames));

        // Add the table to a JScrollPane for scrolling capability
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Set the JFrame to be visible
        frame.setVisible(true);


    }
}

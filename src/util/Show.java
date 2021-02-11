package util;

import javax.swing.*;

public class Show {
    public static void asTable(Object[] columns, Object[][] rows, String title){
        JTable table = new JTable(rows, columns);
        JOptionPane.showMessageDialog(null, new JScrollPane(table), title, 3);
    }

    public static void msg(String msg){
        JOptionPane.showMessageDialog(null, msg);
    }
}

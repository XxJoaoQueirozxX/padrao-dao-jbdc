package util;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Input {

    public static String s(String msg){
        return JOptionPane.showInputDialog(msg);
    }

    public static Double d(String msg){
        return Double.parseDouble(s(msg));
    }

    public static Integer i(String msg){
        return Integer.parseInt(s(msg));
    }

    public static Float f(String msg){
        return Float.parseFloat(s(msg));
    }

    public static LocalDate ldt(String msg, String pattern){
        return LocalDate.parse(s(msg), DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDateTime ldtt(String msg, String pattern){
        return LocalDateTime.parse(s(msg), DateTimeFormatter.ofPattern(pattern));
    }
}

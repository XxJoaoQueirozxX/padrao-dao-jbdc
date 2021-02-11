package application;

import model.entities.Department;
import model.entities.Seller;

import java.time.LocalDate;

public class Program {
    public static void main(String[] args) {
        Department dep = new Department(1, "Books");
        System.out.println(dep);

        Seller seller = new Seller(10, "Bob", "bob@gmail.com", LocalDate.now(), 4500.0, dep);
        System.out.println(seller);
    }
}

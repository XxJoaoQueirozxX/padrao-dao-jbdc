package application;

import db.DB;
import model.dao.DAO;
import model.dao.DaoFactory;
import model.entities.Department;
import util.Input;
import util.Show;

import java.sql.Connection;
import java.util.List;

public class Program2 {

    public static void main(String[] args) {
        Connection con = DB.getConnection();
        DAO<Department> departmentDAO = DaoFactory.createDepartmentDAO(con);
        Object[] columns = {"ID", "NAME"};

        System.out.println("=== TEST 1: findById ===");
        int id = Input.i("Enter a deparment id: ");
        Department dep = departmentDAO.findById(id);
        Show.asTable(columns, new Object[][] {dep.toArray()}, "Result");


        System.out.println("\n=== TEST 2: findAll ===");
        List<Department> departments = departmentDAO.findAll();
        Object[][] rows = departments.stream().map(Department::toArray).toArray(Object[][]::new);
        Show.asTable(columns, rows, "All departments");



        System.out.println("\n=== TEST 3: insert ===");
        dep = new Department(null, Input.s("Enter a department name: "));
        departmentDAO.insert(dep);
        System.out.println("Insert completed: id = " + dep.getId());


        System.out.println("\n=== TEST 4: update ===");
        dep = new Department(
                Input.i("Enter a department id for update: "),
                Input.s("Enter a new department name: ")
        );
        departmentDAO.update(dep);
        System.out.println("Update completed");


        System.out.println("\n=== TEST 5: delete ===");
        id = Input.i("Enter a id for department delete: ");
        departmentDAO.deleteById(id);
        System.out.println("Delete completed");
    }
}

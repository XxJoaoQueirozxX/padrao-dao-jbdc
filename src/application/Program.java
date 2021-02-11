package application;

import db.DB;
import model.dao.DAO;
import model.dao.DaoFactory;
import model.dao.impl.SellerDaoJDBC;
import model.entities.Department;
import model.entities.Seller;
import util.Show;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;


public class Program {
    public static void main(String[] args) {
        Object[] columns = {"Id", "Name", "Email", "BaseSalary", "BirthDate", "Department"};

        Connection con = DB.getConnection();
        DAO<Seller> sellerDAO = DaoFactory.createSellerDAO(con);


        Seller seller = sellerDAO.findById(3);
        Show.asTable(
                columns,
                new Object[][]{seller.toArray()} ,
                "=== TEST 1: seller findById ==="
        );



        List<Seller> sellers = ((SellerDaoJDBC) sellerDAO).findByDepartment(new Department(2, null));
        Object[][] rows = sellers.stream().map(Seller::toArray).toArray(Object[][]::new);
        Show.asTable(
                columns,
                rows,
                "=== TEST 2: seller findByDepartment ==="
        );


        DB.closeConnection();
    }
}

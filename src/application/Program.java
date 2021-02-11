package application;

import db.DB;
import model.dao.DAO;
import model.dao.DaoFactory;
import model.dao.impl.SellerDaoJDBC;
import model.entities.Department;
import model.entities.Seller;
import util.Show;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;


public class Program {
    public static void main(String[] args) {
        Object[] sellerColumns = {"Id", "Name", "Email", "BaseSalary", "BirthDate", "Department"};

        Connection con = DB.getConnection();
        DAO<Seller> sellerDAO = DaoFactory.createSellerDAO(con);

        Seller seller = sellerDAO.findById(3);
        Show.asTable(
                sellerColumns,
                new Object[][]{seller.toArray()} ,
                "=== TEST 1: seller findById ==="
        );


        Department dep =new Department(2, null);
        List<Seller> sellers = ((SellerDaoJDBC) sellerDAO).findByDepartment(dep);
        Object[][] rows = sellers.stream().map(Seller::toArray).toArray(Object[][]::new);
        Show.asTable(
                sellerColumns,
                rows,
                "=== TEST 2: seller findByDepartment ==="
        );


        sellers = sellerDAO.findAll();
        rows = sellers.stream().map(Seller::toArray).toArray(Object[][]::new);
        Show.asTable(
                sellerColumns,
                rows,
                "=== TEST 3: seller findAll ==="
        );


        System.out.println("=== TEST 4: seller insert ===");
        seller = new Seller(null, "Carlos", "carlos@gmail.com", LocalDate.now(), 3680.9, dep );
        sellerDAO.insert(seller);
        System.out.println("Inserted| New id = " + seller.getId());

    }
}

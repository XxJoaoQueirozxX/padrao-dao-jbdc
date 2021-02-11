package application;

import db.DB;
import model.dao.DAO;
import model.dao.DaoFactory;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.time.LocalDate;

public class Program {
    public static void main(String[] args) {
        Connection con = DB.getConnection();
        DAO<Seller> sellerDAO = DaoFactory.createSellerDAO(con);

        System.out.println("=== TEST 1: seller findById ===");
        Seller seller = sellerDAO.findById(3);
        System.out.println(seller);

        DB.closeConnection();
    }
}

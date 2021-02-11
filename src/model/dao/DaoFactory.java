package model.dao;

import model.dao.impl.DepartmentDaoJDBC;
import model.dao.impl.SellerDaoJDBC;
import model.entities.Department;
import model.entities.Seller;

public class DaoFactory {

    public static DAO<Seller> createSellerDAO(){
        return new SellerDaoJDBC();
    }

    public static DAO<Department> createDepartmentDAO() {
        return new DepartmentDaoJDBC();
    }
}

package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.DAO;
import model.entities.Department;
import model.entities.Seller;

import java.nio.channels.SelectableChannel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SellerDaoJDBC implements DAO<Seller> {
    private Connection con;


    public SellerDaoJDBC(Connection con){
        this.con = con;
    }

    @Override
    public void insert(Seller o) {

    }

    @Override
    public void update(Seller o) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        Seller data = null;

        try{
            st = this.con.prepareStatement("SELECT seller.*,department.Name as DepName\n" +
                    "FROM seller INNER JOIN department\n" +
                    "ON seller.DepartmentId = department.Id\n" +
                    "WHERE seller.Id = ?");

            st.setInt(1, id);

            rs = st.executeQuery();

            if (rs.next()){
                Department dep = instantiateDepartment(rs);
                data = instantiateSeller(rs, dep);
            }
            return data;
        } catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException{
        Seller data = new Seller();
        data.setId(rs.getInt("Id"));
        data.setName(rs.getString("Name"));
        data.setEmail(rs.getString("Email"));
        data.setBaseSalary(rs.getDouble("BaseSalary"));
        data.setBirthDate(rs.getDate("BirthDate").toLocalDate());
        data.setDepartment(dep);
        return data;
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dep = new  Department();
        dep.setId(rs.getInt("DepartmentId"));
        dep.setName(rs.getString("DepName"));
        return dep;
    }

    @Override
    public List<Seller> findAll() {
        return null;
    }
}

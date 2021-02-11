package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.DAO;
import model.entities.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements DAO<Department> {
    private Connection con;

    public DepartmentDaoJDBC(Connection con) {
        this.con = con;
    }

    @Override
    public void insert(Department o) {

    }

    @Override
    public void update(Department o) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Department dep = null;

        try{
            ps = this.con.prepareStatement("SELECT * FROM department WHERE Id=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()){
                dep = instantiateDepartment(rs);
            }
            return dep;
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Department> deps = new ArrayList<>();

        try {
            ps = con.prepareStatement("SELECT * FROM department");
            rs = ps.executeQuery();
            while (rs.next()){
                deps.add(instantiateDepartment(rs));
            }
            return deps;
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("Id"));
        dep.setName(rs.getString("Name"));
        return dep;
    }
}

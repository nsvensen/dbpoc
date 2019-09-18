package com.telenor.ftv.dbpoc.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DbpocRepository {

    private JdbcTemplate jdbcTemplate;

    public DbpocRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void printDataSourceInfo() throws SQLException {

        Connection conn = jdbcTemplate.getDataSource().getConnection();
        System.out.println("Client info before: " + conn.getClientInfo());
        conn.setClientInfo("ClientUser", "Nils");
        System.out.println("Client info after: " + conn.getClientInfo());

    }


    public List<Map<String, Object>> execProc(String procName, String args, String userName) throws SQLException {

        Connection conn = jdbcTemplate.getDataSource().getConnection();
//        conn.setClientInfo("ClientUser", userName);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(String.format("exec %s %s", procName, args));
        List<Map<String, Object>> resultList = resultSetToList(rs);
        stmt.close();

//        conn.close();
        return resultList;

//        return jdbcTemplate.queryForList(String.format("exec %s %s", procName, args));
    }

    /**
     * Convert the ResultSet to a List of Maps, where each Map represents a row with columnNames and columValues
     * @param rs
     * @return
     * @throws SQLException
     */
    private List<Map<String, Object>> resultSetToList(ResultSet rs) throws SQLException {
        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
        while (rs.next()){
            Map<String, Object> row = new HashMap<String, Object>(columns);
            for(int i = 1; i <= columns; ++i){
                row.put(md.getColumnName(i), rs.getObject(i));
            }
            rows.add(row);
        }
        return rows;
    }

}

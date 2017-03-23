package ru.board.labma.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.board.labma.json.RequestBoard;
import ru.board.labma.json.Results;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by ivangeel on 20.02.17.
 */

@Repository
public class JDBCHandler {

    @Autowired
    DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init(){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Results> takeJSONs(){
        System.out.println("takeJSONs is working!");

        List<Results> results = this.jdbcTemplate.query("select * from graphicboard", new RowMapper<Results>() {
            @Override
            public Results mapRow(ResultSet resultSet, int i) throws SQLException {
                Results results1 = new Results(resultSet.getString("client"), resultSet.getString("typeC"), resultSet.getInt("x"), resultSet.getInt("y"));
                return results1;
            }
        });
        return results;
    }

    public void setJSONs (RequestBoard rqB)
    {
        System.out.println("setJSONs is working!");

        this.jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement("insert into graphicboard (client, typeC, x, y) values(?, ?, ?, ?)");
                preparedStatement.setString(1, rqB.getClientId());
                preparedStatement.setString(2, rqB.getType());
                preparedStatement.setInt(3, rqB.getX());
                preparedStatement.setInt(4, rqB.getY());
                return preparedStatement;
            }
        });
    }
}

package com.brainstorm.product.generator;

import org.hibernate.engine.jdbc.connections.spi.JdbcConnectionAccess;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductIdGenerator implements IdentifierGenerator {

    @Override
    public Object generate(SharedSessionContractImplementor session, Object object){

        String prefix = "P";
        try {
            JdbcConnectionAccess jdbcConnectionAccess = session.getJdbcConnectionAccess();
            Connection connection = jdbcConnectionAccess.obtainConnection();
            Statement statement = connection.createStatement();
            String query = "select count(id) as Id from product";

            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                int id=resultSet.getInt(1)+001;
                String generatedId = prefix + id;
                return generatedId;
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

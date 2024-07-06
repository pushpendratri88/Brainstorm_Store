package com.brainstorm.order.generator;

import org.hibernate.engine.jdbc.connections.spi.JdbcConnectionAccess;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OrderIdGenerator implements IdentifierGenerator {

    @Override
    public Object generate(SharedSessionContractImplementor session, Object object){

        String prefix = "Ord";
        try {
            JdbcConnectionAccess jdbcConnectionAccess = session.getJdbcConnectionAccess();
            Connection connection = jdbcConnectionAccess.obtainConnection();
            Statement statement = connection.createStatement();
            String query = "select count(order_id) as Id from ecom_order";

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

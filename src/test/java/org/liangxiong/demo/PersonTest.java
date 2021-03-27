package org.liangxiong.demo;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.liangxiong.demo.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author liangxiong
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void testGetData() throws SQLException {
        ResultSet resultSet = dataSource.getConnection().createStatement().executeQuery("SELECT id,title,firstName,lastName FROM person");
        while (resultSet.next()) {
            long id = resultSet.getLong(1);
            String title = resultSet.getString(2);
            String firstName = resultSet.getString(3);
            String lastName = resultSet.getString(4);
            Person person = Person.builder().title(title).firstName(firstName).lastName(lastName).build();
            person.setId(id);
            Assertions.assertEquals(person.getId(), 1);
        }
    }
}

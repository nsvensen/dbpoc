package com.telenor.ftv.dbpoc.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DbpocRepositoryTest {

    @Autowired
    private DbpocRepository dbpocRepository;

    @Test
    public void testPrintDbClientInfo() throws SQLException {
        dbpocRepository.printDataSourceInfo();
    }

}

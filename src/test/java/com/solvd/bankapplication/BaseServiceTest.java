package com.solvd.bankapplication;

import com.solvd.bankapplication.utils.TestDatabaseSetup;
import org.testng.annotations.BeforeSuite;

public class BaseServiceTest {
    @BeforeSuite
    public void setupTestDatabase() {
        TestDatabaseSetup.instantiateTestDatabase();
    }
}

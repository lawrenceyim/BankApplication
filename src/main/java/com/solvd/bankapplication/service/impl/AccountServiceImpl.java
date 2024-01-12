package com.solvd.bankapplication.service.impl;

import com.solvd.bankapplication.domain.Account;
import com.solvd.bankapplication.menu.Menu;
import com.solvd.bankapplication.persistence.AccountDao;
import com.solvd.bankapplication.service.AccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Properties;

public class AccountServiceImpl implements AccountService {
    private final Logger logger = (Logger) LogManager.getLogger("Output");
    private final String configFile = "config.properties";
    private AccountDao accountDao;

    public AccountServiceImpl() {
        try (InputStream inputStream = Menu.class.getClassLoader().getResourceAsStream(configFile)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            final String classPath = properties.getProperty("implementation-path");
            accountDao = (AccountDao) Class.forName(classPath + "AccountDaoImpl").getConstructor().newInstance();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void findAll() {
        List<Account> accounts = accountDao.findAll();
        if (accounts.isEmpty()) {
            logger.info("No accounts found");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-30s%-30s%-30s%-30s" + System.lineSeparator(), "Account ID", "Customer ID",
                "Account Type", "Balance"));
        accounts.stream().forEach(account -> {
            sb.append(String.format("%-30s%-30s%-30s%-30s" + System.lineSeparator(), account.getAccountID(),
                    account.getCustomerID(), account.getAccountType(), account.getBalance()));
        });
        logger.info(sb.toString());
    }
}

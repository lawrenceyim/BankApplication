package com.solvd.bankapplication.service.impl;

import com.solvd.bankapplication.domain.Transfer;
import com.solvd.bankapplication.menu.Menu;
import com.solvd.bankapplication.persistence.TransferDao;
import com.solvd.bankapplication.service.TransferService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Properties;

public class TransferServiceImpl implements TransferService {
    private final Logger logger = (Logger) LogManager.getLogger("Output");
    private final String configFile = "config.properties";
    private TransferDao transferDao;

    public TransferServiceImpl() {
        try (InputStream inputStream = Menu.class.getClassLoader().getResourceAsStream(configFile)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            final String classPath = properties.getProperty("implementation-path");
            transferDao = (TransferDao) Class.forName(classPath + "TransferDaoImpl").getConstructor().newInstance();
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
        List<Transfer> transfers = transferDao.findAll();
        if (transfers.isEmpty()) {
            logger.info("No transfers found");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-30s%-30s%-30s%-30s%-30s" + System.lineSeparator(), "Transfer ID", "Date", "amount", "To", "From"));
        transfers.stream().forEach(transfer -> {
            sb.append(String.format("%-30s%-30s%-30s%-30s%-30s" + System.lineSeparator(), transfer.getTransferID(), transfer.getDate(),
                    transfer.getAmount(), transfer.getToAccountID(), transfer.getFromAccountID()));
        });
        logger.info(sb.toString());
    }
}

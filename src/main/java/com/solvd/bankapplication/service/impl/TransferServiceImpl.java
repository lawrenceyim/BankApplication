package com.solvd.bankapplication.service.impl;

import com.solvd.bankapplication.domain.Transfer;
import com.solvd.bankapplication.menu.Menu;
import com.solvd.bankapplication.persistence.TransferDao;
import com.solvd.bankapplication.service.TransferService;
import com.solvd.bankapplication.utils.JdbcClassGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Properties;

public class TransferServiceImpl implements TransferService {
    private final Logger logger = (Logger) LogManager.getLogger("Output");
    private TransferDao transferDao;

    public TransferServiceImpl() {
        transferDao = JdbcClassGenerator.generateClassInstance("TransferDaoImpl");
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

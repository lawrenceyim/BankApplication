package com.solvd.bankapplication.service.impl;

import com.solvd.bankapplication.domain.Transfer;
import com.solvd.bankapplication.persistence.TransferDao;
import com.solvd.bankapplication.persistence.impl.TransferDaoImpl;
import com.solvd.bankapplication.persistence.impl.TransferDaoMybatisImpl;
import com.solvd.bankapplication.service.TransferService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.List;

public class TransferServiceImpl implements TransferService {
    private final Logger logger = (Logger) LogManager.getLogger("Output");
    private TransferDao transferDao;

    public TransferServiceImpl(String mode) {
        if (mode.equals("mybatis")) {
            transferDao = new TransferDaoMybatisImpl();
        } else if (mode.equals("jdbc")) {
            transferDao = new TransferDaoImpl();
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
        sb.append(String.format("%-30s%-30s%-30s%-30s%-30s\n", "Transfer ID", "Date", "amount", "To", "From"));
        transfers.stream().forEach(transfer -> {
            sb.append(String.format("%-30s%-30s%-30s%-30s%-30s\n", transfer.getTransferID(), transfer.getDate(),
                    transfer.getAmount(), transfer.getToAccountID(), transfer.getFromAccountID()));
        });
        logger.info(sb.toString());
    }
}

package com.solvd.bankapplication.service.impl;

import com.solvd.bankapplication.domain.Card;
import com.solvd.bankapplication.domain.Transfer;
import com.solvd.bankapplication.persistence.CardRepository;
import com.solvd.bankapplication.persistence.TransferRepository;
import com.solvd.bankapplication.persistence.impl.CardRepositoryImpl;
import com.solvd.bankapplication.persistence.impl.TransferRepositoryImpl;
import com.solvd.bankapplication.service.TransferService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class TransferServiceImpl implements TransferService {
    private final Logger logger = (Logger) LogManager.getLogger("Output");
    private final TransferRepository transferRepository;

    public TransferServiceImpl() {
        transferRepository = new TransferRepositoryImpl();
    }

    @Override
    public void findAll() {
        List<Transfer> transfers = transferRepository.findAll();
        if (transfers.isEmpty()) {
            logger.info("No transfers found");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-30s%-30s%-30s%-30s%-30s\n","Transfer ID", "Date", "amount", "To", "From"));
        transfers.stream().forEach(transfer -> {
            sb.append(String.format("%-30s%-30s%-30s%-30s%-30s\n", transfer.getTransferID(), transfer.getDate(),
                    transfer.getAmount(), transfer.getToAccountID(), transfer.getFromAccountID()));
        });
        logger.info(sb.toString());
    }
}

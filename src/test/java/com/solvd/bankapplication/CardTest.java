package com.solvd.bankapplication;

import com.solvd.bankapplication.service.CardService;
import com.solvd.bankapplication.service.impl.CardServiceImpl;
import org.testng.annotations.Test;

public class CardTest {
    @Test(description = "Verify that the findAllCardsAndPayments method works does not throw an exception")
    public void verifyFindAllCardAndPaymentsWork() {
        CardService cardService = new CardServiceImpl();
        cardService.findAllCardsAndPayments();
    }
}

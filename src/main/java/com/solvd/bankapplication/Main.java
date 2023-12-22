package com.solvd.bankapplication;

import com.solvd.bankapplication.service.IService;
import com.solvd.bankapplication.service.impl.ViewLoans;
import com.solvd.bankapplication.util.Generator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class Main {
    private final Logger logger = (Logger) LogManager.getLogger("Output");

    static {
        Generator.createDatabase();
        Generator.fillDataBase();
    }

    public static void main(String[] args) {
        IService service = new ViewLoans();
        service.performService();
    }
}

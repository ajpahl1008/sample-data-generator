package com.pahlsoft.utilities.threader;

import com.pahlsoft.utilities.engine.CreditCardTransactionEngine;
import com.pahlsoft.utilities.engine.DebitCardTransactionEngine;
import com.pahlsoft.utilities.engine.FradulentCreditTransactionEngine;
import com.pahlsoft.utilities.engine.FradulentDebitTransactionEngine;
import com.pahlsoft.utilities.interfaces.TransactionEngine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class TransactionEngineThreader {
    static Logger log = LoggerFactory.getLogger(TransactionEngineThreader.class);


    private TransactionEngineThreader() {
        throw new IllegalStateException("Utility class");
    }

    public static void runEngine(int threadCount, TransactionEngine engine)   {

        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(threadCount);

        try {

            for (int threads = 0; threads < threadCount; threads++) {
                if (engine instanceof CreditCardTransactionEngine) {
                    TransactionEngine cardTransactionEngine = new CreditCardTransactionEngine();
                    threadPoolExecutor.execute(cardTransactionEngine);
                } else if (engine instanceof DebitCardTransactionEngine) {
                    TransactionEngine debitTransactionEngine = new DebitCardTransactionEngine();
                    threadPoolExecutor.execute(debitTransactionEngine);
                } else if (engine instanceof FradulentDebitTransactionEngine) {
                    TransactionEngine fraudulentDebitTransactionEngine = new FradulentDebitTransactionEngine();
                    threadPoolExecutor.execute(fraudulentDebitTransactionEngine);
                } else if (engine instanceof FradulentCreditTransactionEngine) {
                    TransactionEngine fraudulentCreditTransactionEngine = new FradulentCreditTransactionEngine();
                    threadPoolExecutor.execute(fraudulentCreditTransactionEngine);
                }
            }

        } catch (Exception e) {
            if (log.isDebugEnabled()) { log.warn(e.getMessage()); }
        }

    }
}
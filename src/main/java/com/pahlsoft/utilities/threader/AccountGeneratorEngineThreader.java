package com.pahlsoft.utilities.threader;

import com.pahlsoft.utilities.engine.*;
import com.pahlsoft.utilities.interfaces.AccountEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class AccountGeneratorEngineThreader {

    static Logger log = LoggerFactory.getLogger(AccountGeneratorEngineThreader.class);

    AccountGeneratorEngineThreader() {
        throw new IllegalStateException("Utility class");
    }

    public static void runEngine(int threadCount, AccountEngine engine)   {

        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(threadCount);

        try {

            for (int threads = 0; threads < threadCount; threads++) {
                if (engine instanceof AccountGeneratorEngine) {
                    AccountEngine accountTransactionEngine = new AccountGeneratorEngine();
                    threadPoolExecutor.execute(accountTransactionEngine);
                }
            }

        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.warn(e.getMessage()); }
        }

    }
}
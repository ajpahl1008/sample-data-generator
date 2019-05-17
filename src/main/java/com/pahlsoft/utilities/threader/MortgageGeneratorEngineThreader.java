package com.pahlsoft.utilities.threader;

import com.pahlsoft.utilities.engine.*;
import com.pahlsoft.utilities.interfaces.MortgageEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class MortgageGeneratorEngineThreader {

    static Logger log = LoggerFactory.getLogger(MortgageGeneratorEngineThreader.class);


    private MortgageGeneratorEngineThreader() {
        throw new IllegalStateException("Utility class");
    }

    public static void runEngine(int threadCount, MortgageEngine engine)   {

        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(threadCount);

        try {

            for (int threads = 0; threads < threadCount; threads++) {
                if (engine instanceof MortgageGeneratorEngine) {
                    MortgageEngine mortgageEngine = new MortgageGeneratorEngine();
                    threadPoolExecutor.execute(mortgageEngine);
                }
            }

        } catch (Exception e) {
            if (log.isDebugEnabled()) { log.warn(e.getMessage()); }
        }

    }

}

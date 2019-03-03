package com.pahlsoft.utilities.threader;

import com.pahlsoft.utilities.engine.LocomotiveGeneratorEngine;
import com.pahlsoft.utilities.interfaces.LocomotiveEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class LocomotiveGeneratorEngineThreader {

    static Logger log = LoggerFactory.getLogger(LocomotiveGeneratorEngineThreader.class);

    LocomotiveGeneratorEngineThreader() {
        throw new IllegalStateException("Utility class");
    }

    public static void runEngine(int threadCount, LocomotiveEngine engine)   {

        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(threadCount);

        try {

            for (int threads = 0; threads < threadCount; threads++) {
                if (engine instanceof LocomotiveGeneratorEngine) {
                    LocomotiveEngine locomotiveEngineEngine = new LocomotiveGeneratorEngine();
                    threadPoolExecutor.execute(locomotiveEngineEngine);
                }
            }

        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.warn(e.getMessage()); }
        }

    }
}


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Entry point for crawler application. Instantiates a thread-pool with multiple crawlers
 */

public class CrawlerDemo {
    private static int THREAD_POOL_SIZE = 20;

    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println("Check arguments! Restart your program!");
            System.exit(1);
        }

        CrawlerDataStructure ds = new CrawlerDataStructure(args[0], Integer.parseInt(args[1]));

        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

        for (int i = 0; i < THREAD_POOL_SIZE; i++) {
            Runnable runnable = new Crawler(ds);
            executorService.submit(runnable);
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException ex) {
            System.out.println("The error found out was :" + ex.getMessage());
        }
    }
}

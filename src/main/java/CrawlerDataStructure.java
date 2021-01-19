import com.sun.tools.javac.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * This class defines the data structure needed as well as initializes their value in the constructor
 */

public class CrawlerDataStructure {

    private LinkedBlockingDeque<Pair<String, Integer>> queue;
    private List<String> linkedSet;

    /**
     * @param URL   Starting URL
     * @param DEPTH Number of levels of crawling
     */

    public CrawlerDataStructure(String URL, int DEPTH) {
        queue = new LinkedBlockingDeque<>();
        linkedSet = Collections.synchronizedList(new ArrayList<>());
        queue.add(new Pair<>(URL, DEPTH));
        linkedSet.add(URL);
    }


    /**
     * Used to poll the first URL of the queue
     *
     * @return a pair of URL and depth at the which it was found.
     */

    public Pair<String, Integer> getNext() {
        return queue.poll();
    }


    public boolean isQueueEmpty() {
        return queue.isEmpty();
    }

    public boolean isVisited(String URL) {
        return linkedSet.contains(URL);
    }

    /**
     * Used to add to URL to visited set
     */

    public void markVisted(String URL) {
        linkedSet.add(URL);
    }

    /**
     * Used to add URL to queue for crawling.
     */

    public void addLinkToQueue(String URL, Integer DEPTH) {
        queue.add(new Pair<>(URL, DEPTH));
    }
}

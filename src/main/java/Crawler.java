
import com.sun.tools.javac.util.Pair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Crawling each page to check for URL at a particular depth.
 */

public class Crawler implements Runnable {


    private final CrawlerDataStructure ds;
    private static final String USER_AGENT = "Chrome";
    private static final String HREF = "a[href]";
    private static final String ABS_HREF = "abs:href";

    public Crawler(CrawlerDataStructure ds) {

        this.ds = ds;
    }

    /**
     * Method checks for a valid unvisited URL. After that, it adds to a queue. It continues to crawl until depth 0
     * is reached or the queue is empty.
     */

    public void getPageLinks() {

        while (!ds.isQueueEmpty()) {
            Pair<String, Integer> linkPair = ds.getNext();
            String currentLink = linkPair.fst;
            int depth = linkPair.snd;

            if (depth <= 0) {
                continue;
            }

            try {
                System.out.println(currentLink + " at depth: " + depth);

                // document fetches page at current link.
                Document document = Jsoup.connect(currentLink).userAgent(USER_AGENT).get();

                Elements pageLinks = document.select(HREF);
                for (Element page : pageLinks) {
                    String absHref = page.attr(ABS_HREF);

                    if (absHref == null || absHref.isEmpty() || !absHref.startsWith("http")) {
                        continue;
                    }

                    /**
                     Checking if the link has been previously visited. If not, add link to queue and mark visited.
                     */
                    if (!ds.isVisited(absHref)) { ds.markVisted(absHref);

                        if (depth - 1 > 0) {
                            ds.addLinkToQueue(absHref, depth - 1);
                        }

                        System.out.println(" " + absHref);
                    }
                }
            } catch (IllegalArgumentException | IOException ex) {
                System.out.println("The error found out was :" + ex.getMessage());
            }
        }
    }

    @Override
    public void run() {
        getPageLinks();
    }
}

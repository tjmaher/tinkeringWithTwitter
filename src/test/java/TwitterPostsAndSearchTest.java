import org.testng.annotations.Test;
import twitter4j.*;

import java.util.List;

import static utils.PrintUtils.prettyPrint;

public class TwitterPostsAndSearchTest {


    @Test
    public void test_postToTwitterUsingTwitter4J() throws TwitterException {
        Twitter twitter = TwitterFactory.getSingleton();
        String message="Testing Twitter4J, posting to Twitter programatically. @t4j_news http://twitter4j.org/en/";
        Status status = twitter.updateStatus(message);
        System.out.println("Successfully updated the status to [" + status.getText() + "].");
    }

    @Test
    public void test_printListOfTwitterPostsOnHomePage() throws TwitterException {
        Twitter twitter = TwitterFactory.getSingleton();
        List<Status> statuses = twitter.getHomeTimeline();
        System.out.println("Showing home timeline.");
        for (Status status : statuses) {
            System.out.println(status.getUser().getName() + ": \n"
                    + "\t" + status.getText());
        }
    }

    @Test
    public void test_searchAndPrintTweets() throws TwitterException {
        Twitter twitter = TwitterFactory.getSingleton();
        Query query = new Query("tjmaher.com");
        QueryResult result = twitter.search(query);

        System.out.println("\nRESULTS FOUND: " + result.getTweets().size() );
        for (Status status : result.getTweets()) {
            System.out.println("@" + status.getUser().getScreenName()  + ": \n"
                    + "\t" + status.getText());
        }
    }

    @Test
    public void test_searchQueryAndPrintText() throws TwitterException {
        Twitter twitter = TwitterFactory.getSingleton();
        Query query = new Query("tjmaher.com");
        QueryResult result = twitter.search(query);

        System.out.println("\nRESULTS FOUND: " + result.getTweets().size() );
        for (Status status : result.getTweets()) {
            System.out.println(
                    "\nUser: " + status.getUser().getName()
                            + "\nScreenName: @" + status.getUser().getURL()
                            + "\n\t ID: " + status.getId()
                            + "\n\t Text: " + status.getText()
                            + "\n\t Created At: " + status.getCreatedAt()
                            + "\n\t Favorite Count: " + status.getFavoriteCount()
                            + "\n\t Retweet Count: " + status.getRetweetCount()
            );
        }
    }

    @Test
    public void test_searchAndPrintFormattedTweets() throws TwitterException {
        Twitter twitter = TwitterFactory.getSingleton();
        Query query = new Query("tjmaher.com");
        QueryResult result = twitter.search(query);

        System.out.println("\nRESULTS FOUND: " + result.getTweets().size() );
        for (Status status : result.getTweets()) {
            System.out.println(
                    "\nUser: " + status.getUser().getName()
                            + "\nScreenName: @" + status.getUser().getScreenName()
                            + "\nUser URL: " + status.getUser().getURL()
                            + "\n\t ID: " + status.getId()
                            + "\n\t Text: " + status.getText()
                            + "\n\t Created At: " + status.getCreatedAt()
                            + "\n\t Favorite Count: " + status.getFavoriteCount()
                            + "\n\t Retweet Count: " + status.getRetweetCount()

            );
        }
    }

    //
    // NOTE: See how I repeated the same code twice?
    // It would be better to refactor that, creating a method called
    // "prettyPrint" and place it in a "utils" folder.

    @Test
    public void test_searchAndPrettyPrintFormattedTweets() throws TwitterException {
        Twitter twitter = TwitterFactory.getSingleton();
        Query query = new Query("tjmaher.com");
        QueryResult result = twitter.search(query);
        prettyPrint(result);
    }

    @Test
    public void test_whoIsTalkingAboutMe() throws TwitterException {
        Twitter twitter = TwitterFactory.getSingleton();
        Query query = new Query(
                "-from:tjmaher1 AND "                   // Exclude my own postings
                + "(+tjmaher1 OR tjmaher.com)"          // Who is mentioning me?
        );
        QueryResult result = twitter.search(query);
        prettyPrint(result);
    }

    @Test
    public void test_searchForQANearBoston() throws TwitterException {
        Twitter twitter = TwitterFactory.getSingleton();
        Query query = new Query(
                "(#QA OR #SQA OR quality) AND #Boston"
        );
        QueryResult result = twitter.search(query);
        prettyPrint(result);
    }

    @Test
    public void test_searchForNewsAboutQAField() throws TwitterException {
        Twitter twitter = TwitterFactory.getSingleton();
        Query query = new Query(
                "(#QA OR #SQA OR quality assurance) AND filter:news"
        );
        QueryResult result = twitter.search(query);
        prettyPrint(result);
    }

//    SAMPLE OUTPUT:
//      ------------
//    RESULTS FOUND: 15
//
//    User: T.J. Maher
//    ScreenName: @tjmaher1
//          ID: 917806403049357312
//          Text: RT @SmartBear: Want to learn about @xbrowsertesting from the experts? Join us for our live meet up on October 24th at 6 PM: https://t.co/NJ…
//          Created At: Tue Oct 10 13:37:53 EDT 2017
//          Favorite Count: 0
//          Retweet Count: 2
//
//    User: Badari
//    ScreenName: @badarikashyap
//          ID: 917743256145973248
//          Text: @tjmaher1 Awesome write up !!! Please try to cover up Uber APIs as well as part of ur next series:)
//          Created At: Tue Oct 10 09:26:57 EDT 2017
//          Favorite Count: 0
//          Retweet Count: 0


}

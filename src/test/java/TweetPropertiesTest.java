import org.junit.Test;
import org.testng.Assert;
import twitter4j.*;

public class TweetPropertiesTest {

    @Test
    public void test_languageIsEnglish() throws TwitterException {
        // Search for the tag for user "@tjmaher1
        // Set the query to only return English language results.
        // Confirm that there are only English language results returned.
        Twitter twitter = TwitterFactory.getSingleton();
        String queryText = "q:@tjmaher1 lang:en";
        Query query = new Query(queryText);
        QueryResult result = twitter.search(query);

        String expectedLanguageCode = "en";

        System.out.println("\nVerifying: If lang:en, only English language search returned...");

        for (Status status : result.getTweets()) {
            Assert.assertEquals(status.getLang(), expectedLanguageCode);
        }
    }

    @Test
    public void test_defaultReturnSetToFifteen()throws TwitterException {
        // Search for the word "Twitter".
        // Make sure we only get 15 results.
        Twitter twitter = TwitterFactory.getSingleton();
        String queryText = "Twitter";
        Query query = new Query(queryText);
        QueryResult result = twitter.search(query);

        System.out.println("\nVerifying: Default 15 search entries are returned...");
        Assert.assertEquals(result.getTweets().size(), 15);
    }

    @Test
    public void test_queryTextExclusion() throws TwitterException {
        // Let's search for tjmaher1, but let's exclude anything
        // specifically from user tjmaher1.
        Twitter twitter = TwitterFactory.getSingleton();
        String queryText = "tjmaher1 -from:tjmaher1";
        Query query = new Query(queryText);

        QueryResult result = twitter.search(query);

        System.out.println("\nQuery Text: " + queryText);
        System.out.println("\t* Verifying: That user @tjmaher1 can be excluded from search results.");
        System.out.println("\t* RESULTS FOUND: " + result.getTweets().size() );

        for (Status status : result.getTweets()) {
            Assert.assertNotEquals(status.getUser(), "@tjmaher1");
        }
    }

//  RESULTS OF RUNNING ParametersTests.java:
//
//    Query Text: tjmaher1 -from:tjmaher1
//	* Verifying: That user @tjmaher1 can be excluded from search results.
//            * RESULTS FOUND: 15
//
//    Verifying: If lang:en, only English language search returned...
//
//    Verifying: Default 15 search entries are returned...

    //ALL GREEN at time of running.

}


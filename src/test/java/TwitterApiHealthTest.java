import org.junit.Test;
import org.testng.Assert;
import twitter4j.*;

public class TwitterApiHealthTest {

    @Test
    public void test_rateLimitShouldBe180()throws TwitterException {
        // For this test, we are checking the default rate limit.
        Twitter twitter = TwitterFactory.getSingleton();
        String queryText = "tjmaher1";
        Query query = new Query(queryText);
        TwitterResponse response = twitter.search(query);

        int expectedRateLimit = 180;
        int actualRateLimit = response.getRateLimitStatus().getLimit();

        System.out.println("\nTesting Rate Limits:");
        System.out.println("\t* Expected Rate Limit: " + expectedRateLimit);
        System.out.println("\t* Actual Rate Limit: " + actualRateLimit);
        Assert.assertEquals(actualRateLimit, expectedRateLimit);
    }

    @Test
    public void test_SearchTime() throws TwitterException {
        // How long does it take to return results?
        Twitter twitter = TwitterFactory.getSingleton();
        String queryText = "tjmaher1";
        Query query = new Query(queryText);
        QueryResult result = twitter.search(query);

        double expectedSearchTime = .1; // Making this figure up.
        double actualTime = result.getCompletedIn();

        System.out.println("\nTesting Fetch Rate:");
        System.out.println("\t* Expected Rate: " + expectedSearchTime );
        System.out.println("\t* Actual Rate: " + actualTime);
        Assert.assertTrue(expectedSearchTime >= actualTime);
    }


//    SAMPLE OUTPUT:
//
//    Testing Rate Limits:
//            * Expected Rate Limit: 180
//            * Actual Rate Limit: 180
//
//    Testing Fetch Rate:
//            * Expected Rate: 0.1
//            * Actual Rate: 0.062
}


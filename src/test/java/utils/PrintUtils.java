package utils;

import twitter4j.QueryResult;
import twitter4j.Status;

public class PrintUtils {

    public static void prettyPrint(QueryResult result) {
        System.out.println("\nRESULTS FOUND: " + result.getTweets().size() );
        for (Status status : result.getTweets()) {
            System.out.println(
                    "\nUser: " + status.getUser().getName()
                            + "\nScreenName: @" + status.getUser().getScreenName()
                            + "\n\t ID: " + status.getId()
                            + "\n\t Text: " + status.getText()
                            + "\n\t Created At: " + status.getCreatedAt()
                            + "\n\t Favorite Count: " + status.getFavoriteCount()
                            + "\n\t Retweet Count: " + status.getRetweetCount()

            );
        }
    }
}

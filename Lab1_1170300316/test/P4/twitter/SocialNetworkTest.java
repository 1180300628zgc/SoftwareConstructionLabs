/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P4.twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import P4.twitter.*;

public class SocialNetworkTest {

	private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
    
    /*
     * TODO: your testing strategies for these methods should go here.
     * See the ic03-testing exercise for examples of what a testing strategy comment looks like.
     * Make sure you have partitions.
     */
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testInfluencer() {    	
    	Tweet tweet0 = new Tweet(1, "Tom", "I don't want to invite @Mary to my party.", d1);
    	Tweet tweet1 = new Tweet(1, "Tom", "@Sam should be included in the invitation list.", d1);
    	Tweet tweet2 = new Tweet(2, "Sam", "Thank you @Tom.", d1);
    	Tweet tweet3 = new Tweet(3, "Mary", "@Tom such an incredible freak.", d1);
    	Tweet tweet4 = new Tweet(1, "Tom", "Do not bother @Sam!", d2);   	
    	Tweet tweet5 = new Tweet(4, "Chell", "Go reply @Mary, Tom.", d2);
    	Tweet tweet6 = new Tweet(4, "Chell", "You are so rude.", d1);
    	Tweet tweet7 = new Tweet(5, "Bruce", "I'll notify her.", d2);   	
    	Tweet tweet8 = new Tweet(5, "Bruce", "Hi, Tom didn't invite you.", d1);
    	Tweet tweet9 = new Tweet(7, "Sam", "It's a pity I don't agree with @Bruce.", d1);
    	Tweet tweet10 = new Tweet(5, "Bruce", "@Sam's stupid. From @Bruce.", d1);
    	
    	List<Tweet> tweets = new ArrayList<Tweet>();
    	
    	tweets.add(tweet0);
    	tweets.add(tweet1);
    	tweets.add(tweet2);
    	tweets.add(tweet3);
    	tweets.add(tweet4);
    	tweets.add(tweet5);
    	tweets.add(tweet6);
    	tweets.add(tweet7);
    	tweets.add(tweet8);
    	tweets.add(tweet9);
    	tweets.add(tweet10);
    	
    	Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(tweets);

    	Set<String> tom = new HashSet<String>();
    	Set<String> sam = new HashSet<String>();
    	Set<String> bruce = new HashSet<String>();
    	
    	tom.add("sam");
    	tom.add("mary");
    	tom.add("bruce");
    	sam.add("tom");
    	sam.add("mary");
    	sam.add("bruce");
    	bruce.add("mary");
    	bruce.add("sam");
    	bruce.add("tom");
    	
    	List<String> verificationList = SocialNetwork.influencers(followsGraph);
    	List<String> resultList = new ArrayList<String>();
    	resultList.add("mary");
    	resultList.add("sam");
    	resultList.add("bruce");
    	resultList.add("tom");
    	resultList.add("chell");
    	assertEquals(resultList, verificationList);
    }

    /*
     * Warning: all the tests you write here must be runnable against any
     * SocialNetwork class that follows the spec. It will be run against several
     * staff implementations of SocialNetwork, which will be done by overwriting
     * (temporarily) your version of SocialNetwork with the staff's version.
     * DO NOT strengthen the spec of SocialNetwork or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in SocialNetwork, because that means you're testing a
     * stronger spec than SocialNetwork says. If you need such helper methods,
     * define them in a different class. If you only need them in this test
     * class, then keep them in this test class.
     */

}

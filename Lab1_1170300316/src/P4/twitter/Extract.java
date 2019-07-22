/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P4.twitter;

import static org.junit.Assert.assertTrue;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Extract consists of methods that extract information from a list of tweets.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Extract {

	/**
	 * Get the time period spanned by tweets.
	 * 
	 * @param tweets list of tweets with distinct ids, not modified by this method.
	 * @return a minimum-length time interval that contains the timestamp of every
	 *         tweet in the list.
	 */
	public static Timespan getTimespan(List<Tweet> tweets) {
		// throw new RuntimeException("not implemented");

		Instant end = tweets.get(0).getTimestamp();
		Instant begin = tweets.get(0).getTimestamp();
		for (int i = 0; i < tweets.size(); i++) {
			for (int j = 0; j < tweets.size(); j++) {
				if (tweets.get(i).getTimestamp().isAfter(tweets.get(j).getTimestamp()))
					end = tweets.get(i).getTimestamp();
			}
		}

		for (int i = 0; i < tweets.size(); i++) {
			for (int j = 0; j < tweets.size(); j++) {
				if (tweets.get(i).getTimestamp().isBefore(tweets.get(j).getTimestamp()))
					begin = tweets.get(i).getTimestamp();
			}
		}

		Timespan timespan = new Timespan(begin, end);

		return timespan;
	}

	/**
	 * Get usernames mentioned in a list of tweets.
	 * 
	 * @param tweets list of tweets with distinct ids, not modified by this method.
	 * @return the set of usernames who are mentioned in the text of the tweets. A
	 *         username-mention is "@" followed by a Twitter username (as defined by
	 *         Tweet.getAuthor()'s spec). The username-mention cannot be immediately
	 *         preceded or followed by any character valid in a Twitter username.
	 *         For this reason, an email address like bitdiddle@mit.edu does NOT
	 *         contain a mention of the username mit. Twitter usernames are
	 *         case-insensitive, and the returned set may include a username at most
	 *         once.
	 */
	public static Set<String> getMentionedUsers(List<Tweet> tweets) {
		Set<String> UsersMentioned = new HashSet<String>();
		for (Tweet element : tweets) {
			for (int i = 1; i < element.getText().length(); i++) {
				for (int j = i; j < element.getText().length() - 1; j++) {
					if ( !isValid(element.getText().charAt(i - 1)) && element.getText().charAt(i) == '@' && !isValid(element.getText().charAt(j+1))) {
						if(!UsersMentioned.contains(element.getText().substring(i + 1, j + 1).toLowerCase())) {
							UsersMentioned.add(element.getText().substring(i + 1, j + 1).toLowerCase());
						}
						break;
					}
				}	
			}
			if(element.getText().charAt(0) == '@') {
				for(int i = 1; i < element.getText().length(); i++) {
					if(!isValid(element.getText().charAt(i))) {
						if(!UsersMentioned.contains(element.getText().substring(1, i).toLowerCase())) {
							UsersMentioned.add(element.getText().substring(1, i).toLowerCase());
							
						}break;
					}
				}		
			}
		}
		
		
		for (String name : UsersMentioned) {
			System.out.println(name);
        }
        
		return UsersMentioned;
	}
	
	// 判断以 @ 开头的字符串前后是否合法
	public static boolean isValid(char namesplit) {
			if(((int)namesplit >= 48 && (int)namesplit <= 57) || ((int)namesplit >= 65 && (int)namesplit <= 90) || ((int)namesplit <= 122 && (int)namesplit >= 97) || (int)namesplit == 95 || (int)namesplit == 45 )
				return true;
		return false;			
	}
}

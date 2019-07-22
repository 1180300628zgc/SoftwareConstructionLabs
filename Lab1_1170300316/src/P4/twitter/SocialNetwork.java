/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P4.twitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

//import com.sun.tools.classfile.Annotation.element_value;

/**
 * SocialNetwork provides methods that operate on a social network.
 * 
 * A social network is represented by a Map<String, Set<String>> where map[A] is
 * the set of people that person A follows on Twitter, and all people are
 * represented by their Twitter usernames. Users can't follow themselves. If A
 * doesn't follow anybody, then map[A] may be the empty set, or A may not even
 * exist as a key in the map; this is true even if A is followed by other people
 * in the network. Twitter usernames are not case sensitive, so "ernie" is the
 * same as "ERNie". A username should appear at most once as a key in the map or
 * in any given map[A] set.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class SocialNetwork {

	/**
	 * Guess who might follow whom, from evidence found in tweets.
	 * 
	 * @param tweets a list of tweets providing the evidence, not modified by this
	 *               method.
	 * @return a social network (as defined above) in which Ernie follows Bert if
	 *         and only if there is evidence for it in the given list of tweets. One
	 *         kind of evidence that Ernie follows Bert is if Ernie
	 * @-mentions Bert in a tweet. This must be implemented. Other kinds of evidence
	 *            may be used at the implementor's discretion. All the Twitter
	 *            usernames in the returned social network must be either authors
	 *            or @-mentions in the list of tweets.
	 */
	public static Map<String, Set<String>> guessFollowsGraph(List<Tweet> tweets) {

		Map<String, Set<String>> Result = new HashMap<String, Set<String>>();
		Set<String> followSet = new HashSet<String>();
		List<Tweet> SingletoList = new ArrayList<Tweet>();

		// 将 followSet 中放入被提到的用户，是可能 follow 的第一种情况
		for (Tweet element : tweets) {
			SingletoList.add(element);
			followSet = Extract.getMentionedUsers(SingletoList);
			followSet.remove(element.getAuthor().toLowerCase());
			if (!Result.keySet().contains(element.getAuthor().toLowerCase())) {
				Result.put(element.getAuthor().toLowerCase(), new HashSet<String>());
			}
			Result.get(element.getAuthor().toLowerCase()).addAll(followSet);
			// Result.put(element.getAuthor().toLowerCase(), followSet);
			SingletoList.clear();
		}

		System.out.println("原份：");
		for (String eleString : Result.keySet()) {
			System.out.println("用户：" + eleString);
			for (String eleString2 : Result.get(eleString)) {
				System.out.println("关注了：" + eleString2);
			}
		}

		// 一些并没有被提到的用户也不应该被忽视，而应该分配一个空映射给他们
		Set<String> fullNameList = new HashSet<String>();

		fullNameList = Extract.getMentionedUsers(tweets);
		for (String element : fullNameList) {
			if (!Result.containsKey(element.toLowerCase())) {
				Result.put(element.toLowerCase(), new HashSet<String>());
			}
		}

		// For test only
		System.out.println("以下是查找到的所有用户：");
		for (String eleString : Result.keySet()) {
			System.out.println(eleString);
		}

		// 用另一张 Map 存储结果，避免造成重复迭代
		// Map<String, Set<String>> smarterResultMap = Result;
		Map<String, Set<String>> smarterResultMap = new HashMap<String, Set<String>>();
		smarterResultMap.putAll(Result);

		// 添加一个闭包关系，完成 Triadic closure 要求
		for (String element : Result.keySet()) {
			for (String eleString1 : Result.get(element)) {
				// 用户 element 和 eleString1 要求互关
				if (Result.get(eleString1).contains(element)) {
					for (String eleString2 : Result.get(eleString1)) {
						// 用户 eleString2 和 eleString1 要求互关
						if (Result.get(eleString2).contains(eleString1)) {
							// 添加闭包关系的时候，要小心被添加的 user 正好是用户本人
							if (!Result.get(element).contains(eleString2) && !element.equals(eleString2))
								smarterResultMap.get(element).add(eleString2);
							if (!Result.get(eleString2).contains(element) && !element.equals(eleString2))
								smarterResultMap.get(eleString2).add(element);
						}
					}
				}
			}
		}

		// Output info for Debugging
		System.out.println("关系结果：");
		for (String eleString : smarterResultMap.keySet()) {
			System.out.println("用户：" + eleString);
			for (String eleString2 : smarterResultMap.get(eleString)) {
				System.out.println("关注了：" + eleString2);
			}
		}

		return smarterResultMap;
	}

	/**
	 * Find the people in a social network who have the greatest influence, in the
	 * sense that they have the most followers.
	 * 
	 * @param followsGraph a social network (as defined above)
	 * @return a list of all distinct Twitter usernames in followsGraph, in
	 *         descending order of follower count.
	 */
	public static List<String> influencers(Map<String, Set<String>> followsGraph) {

		Map<String, Set<String>> Result = new HashMap<String, Set<String>>();
		List<String> ResultList = new ArrayList<String>();

		Result = inverse(followsGraph); // Result 存储被翻转过来的矩阵

		int cmp = 0;

		String store = null;
		for (int j = 0; j < followsGraph.keySet().size(); j++) {
			for (String element : followsGraph.keySet()) {
				if (Result.get(element).size() >= cmp && !ResultList.contains(element)) {
					cmp = Result.get(element).size();
					store = element; // store 存储到目前为止最小的被关注者
				}
			}
			cmp = 0;
			ResultList.add(store);
		}
		
		System.out.println("以下是 influencer：");
		for (String eleString : ResultList) {
			System.out.println(eleString);
		}
		return ResultList;
	}

	// 用于将一个 followsGraph 翻转的方法
	public static Map<String, Set<String>> inverse(Map<String, Set<String>> followsGraph) {

		Map<String, Set<String>> afterInverse = new HashMap<String, Set<String>>();

		for (String eleString : followsGraph.keySet()) {
			for (String eleString2 : followsGraph.get(eleString)) {
				if (afterInverse.keySet().contains(eleString2))
					afterInverse.get(eleString2).add(eleString);
				else {
					afterInverse.put(eleString2, new HashSet<>());
					// afterInverse.keySet().add(eleString2);
					afterInverse.get(eleString2).add(eleString);
				}
			}
		}

		// 对名单中存在但翻转后不存在
		for (String eleString : followsGraph.keySet()) {
			if (!afterInverse.keySet().contains(eleString)) {
				afterInverse.put(eleString, new HashSet<>());
			}
		}

		return afterInverse;
	}

}

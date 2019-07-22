package P3;

import java.util.*;

public class FriendshipGraph {

	public static int[][] relationGraph = new int[9999][9999]; // 人物关系图
	public static final int INF = 9999;
	public static final int CONNECTED = -2;
	public static int friendTotal = 0; // 以人物号码建立图关系
	public static List<Person> friendName = new ArrayList<Person>();

	/**
	 * 初始化人物关系图，所有人物间的距离设置为无限，本人与本人之间的距离为 0
	 * 
	 * @param 任意 FriendshipGraph 实例
	 * @return 无
	 */
	public void initialization(FriendshipGraph graph) {
		for (int i = 0; i < 100; i++)
			for (int j = 0; j < 100; j++)
				relationGraph[i][j] = INF; // relationGraph 仅仅是关系图，不代表距离，-1 代表没有连接
		for (int k = 0; k < 100; k++)
			relationGraph[k][k] = 0;
	}

	/**
	 * 添加新的 Person，给朋友数量计数器 +1
	 * 
	 * @param Person 类名称
	 * @return 无
	 */
	public void addVertex(Person person) {
		friendName.add(person);
		friendTotal++;
	}

	/**
	 * 添加新的朋友关系（只添加单边的关系）
	 * 
	 * @param 朋友 1
	 * @param 朋友 2
	 */
	public boolean addEdge(Person person1, Person person2) {
		if (relationGraph[friendName.indexOf(person2)][friendName.indexOf(person1)] == CONNECTED) {
			relationGraph[friendName.indexOf(person1)][friendName.indexOf(person2)] = 1;
			relationGraph[friendName.indexOf(person2)][friendName.indexOf(person1)] = 1;
			return true;

		} else {
			relationGraph[friendName.indexOf(person1)][friendName.indexOf(person2)] = CONNECTED;
			return false;
		}
	}

	/**
	 * 计算两人间最短距离，要调用 floydDistance()
	 * 
	 * @param 朋友 1
	 * @param 朋友 2
	 * @return 两人间最短距离
	 */
	public int getDistance(Person person1, Person person2) {
		if ((relationGraph[friendName.indexOf(person1)][friendName.indexOf(person2)] == INF))
			return -1;
		return relationGraph[friendName.indexOf(person1)][friendName.indexOf(person2)];
	}

	/**
	 * 计算两人互相为朋友的人之间最短路
	 * 
	 * @param 需要计算最短路的图
	 */
	public void floydDistance(int[][] map) {
		for (int i = 0; i < friendTotal; i++) {
			for (int j = 0; j < friendTotal; j++) {
				if (map[i][j] == CONNECTED) {
					map[i][j] = INF; // 在图被创建完后，仍然为 connected 状态的两点之间距离设置为无限
				}
			}
		}

		for (int i = 0; i < friendTotal; i++)
			for (int j = 0; j < friendTotal; j++)
				for (int k = 0; k < friendTotal; k++)
					map[i][j] = (map[i][j] > (map[i][k] + map[k][j]) ? (map[i][k] + map[k][j]) : map[i][j]);
	}

	public static void main(String[] args) {
		FriendshipGraph graph = new FriendshipGraph();
		Person rachel = new Person("Rachel");
		Person ross = new Person("Ross");
		Person ben = new Person("Ben");
		Person kramer = new Person("Kramer");
		graph.addVertex(rachel);
		graph.addVertex(ross);
		graph.addVertex(ben);
		graph.addVertex(kramer);
		graph.addEdge(rachel, ross);
		graph.addEdge(ross, rachel);
		graph.addEdge(ross, ben);
		graph.addEdge(ben, ross);
		System.out.println(graph.getDistance(rachel, ross));
		// should print 1
		System.out.println(graph.getDistance(rachel, ben));
		// should print 2
		System.out.println(graph.getDistance(rachel, rachel));
		// should print 0
		System.out.println(graph.getDistance(rachel, kramer));
		// should print -1
	}
}

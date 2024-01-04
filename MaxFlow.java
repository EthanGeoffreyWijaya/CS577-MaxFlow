import java.util.Scanner;
import java.util.LinkedList;
//import java.util.Arrays;

public class MaxFlow {

	public static int getMaxFlow(int[][] graph, int[] visited, int startI, int max) {
		int maxFlow = 0;
		int tempMax = max;
		int mf = 0;

		if (startI >= graph.length - 1) {
			return max;
		}
		if (visited[startI] == 1) {
			return 0;
		}

		for (int i = 0; i < graph.length; i++) {
			if (graph[startI][i] > 0 && visited[i] == 0) {
				visited[startI] = 1;
				tempMax = Math.min(max, graph[startI][i]);
				mf = getMaxFlow(graph, visited, i, tempMax);

				graph[i][startI] += mf;
				graph[startI][i] -= mf;

				visited[startI] = 0;
				maxFlow += mf;
				/*
				  System.out.println(startI + 1 + " " + (i + 1) + "|" + tempMax + ", " + mf + ", " + maxFlow);
				  System.out.println(Arrays.toString(visited)); for (int j = 0; j <
				  graph.length; j++) { System.out.println(Arrays.toString(graph[j])); }
				  System.out.println();
				 */
				if (startI == 0) {
					if (mf <= 0) {
						graph[startI][i] = 0;
					} else {
						i = 0;
					}
				} else if (mf != 0) {
					break;
				}

				// 1 4 5 1 2 20 1 3 10 2 3 30 2 4 10 3 4 20

			}
		}

		return maxFlow;
	}

	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		int instances = scnr.nextInt();
		LinkedList<int[][]> graphs = new LinkedList<int[][]>();

		for (int i = 0; i < instances; i++) {
			int nodes = scnr.nextInt();
			int edges = scnr.nextInt();
			graphs.add(new int[nodes][nodes]);
			for (int j = 0; j < edges; j++) {
				graphs.get(i)[scnr.nextInt() - 1][scnr.nextInt() - 1] += scnr.nextInt();
			}
		}
		scnr.close();

		/*
		 * for (int i = 0; i < instances; i++) { for (int j = 0; j <
		 * graphs.get(i).length; j++) {
		 * System.out.println(Arrays.toString(graphs.get(i)[j])); }
		 * System.out.println(); }
		 */

		for (int i = 0; i < instances; i++) {
			System.out.println(getMaxFlow(graphs.get(i), new int[graphs.get(i).length], 0, Integer.MAX_VALUE));
		}

	}

}

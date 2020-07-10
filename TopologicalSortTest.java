//***************************
// 파일명: TopologicalSortTest
// 작성자: 진형준
// 작성일: 6/11
// 프로그램 설명:hw10_1 프로그램을 사용 하여 위상 정렬 알고리즘 버전 2 (DFS 이용한 버전) 구현하기
//***************************
import java.util.Scanner;

public class TopologicalSortTest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("hw10_2 : 진형준");
		System.out.println("위상 정렬을 수행합니다. DAG를 입력하세요.");
		System.out.println("정점 수 입력 : ");
		// 정점 수 n을 입력받음
		int n = sc.nextInt();
		Directed graph = new Directed(n);

		System.out.println("간선 수 입력");
		int e = sc.nextInt();

		for (int i = 1; i < e + 1; i++) {
			System.out.println("간선" + i + " 입력 : ");
			// e개의 정점 쌍 v1과 v2를 입력받아 그래프에 <v1, v2>를 삽입
			int x1 = sc.nextInt();
			int x2 = sc.nextInt();
			graph.addEdge(x1, x2);
		}
		graph.printAdjList();
		System.out.println();
		System.out.print("위상 정렬 결과 : ");

		graph.topological_sort();
		graph.printTopological();

	}
}

class Directed {
	private static final Node Object = null;
	private static final Node Node = null;
	private Node[] list;// 인접리스트
	private int numberOfVertices;// 정점수(사이즈)
	private Node temp;
	private Node temp1;
	public int[] result_Array;
	private boolean[] visited;
	static int count = 0;

	public class Node {// 리스트의 노드구조를 정의한 클래스

		Node link;
		private int vertex;

		public int getVertex() {
			return vertex;
		}

		public void setVertex(int v) {
			vertex = v;
		}

	}

	public Directed(int n) {// 정점수가 n인 그래프를 생성
		list = new Node[n];
		numberOfVertices = n;
		result_Array = new int[n];
		visited = new boolean[n];
	}

	public int[] topological_sort() {
		for (int i = 0; i < numberOfVertices; i++) {
			if (!visited[i]) {
				visited[i] = true;
				dfs(i);//dfs 호출
			}
		}
		return result_Array;
	}

	public void dfs(int i) { // DFS 수행
		temp1 = list[i];
		Node temp2 = list[i];
		int tempcount = 0;
		while (temp1 != null) {
			temp1 = temp1.link;
			tempcount++;
		}
		int[] tempArray = new int[tempcount];
		int j = 0;
		while (temp2 != null) {
			tempArray[j] = (int) temp2.getVertex();
			temp2 = temp2.link;
			j++;
		}
		for (int v : tempArray) { // u의 방문이 끝나고 앞으로 방문해야하는 각 정점 v에 대해
			if (!visited[v]) {
				visited[v] = true;
				dfs(v);
			}
		}

		result_Array[count] = i; // u에서 진출하는 간선이 더 이상 없으므로 u를 sequence에 추가
		count++;
	}

	public void printTopological() {
		for (int i = numberOfVertices - 1; i >= 0; i--) {
			System.out.print(result_Array[i] + " ");
		}
	}

	// 간선 v1 v2 삽입
	public void addEdge(int v1, int v2) {
		if (v1 >= numberOfVertices || v2 >= numberOfVertices) {
			System.out.println("간선 삽입 오류 - 잘못된 정점 번호입니다.<" + v1 + "," + v2 + ">");

		} else {
			// 올바른 정점이면 다음과 같이 삽입
			// v2가 정수형이기 때문에 노드클래스 vertex변수로 변환
			Node node = new Node();// node 라는 이름으로 객체 생성
			node.vertex = v2;
			node.link = list[v1];
			list[v1] = node;

		}

	}

	// 구현을 확인하기 위해 인접리스트를 모두 출력
	public void printAdjList() {
		temp = new Node();// 저장할 노드 temp 변수 설정
		for (int i = 0; i < numberOfVertices; i++) {

			System.out.printf("정점" + i + "의 인접리스트 = ");
			temp = list[i];
			while (temp != null) {

				System.out.print(temp.getVertex() + " ");
				temp = temp.link;

			}

			System.out.println();
		}

	}

}
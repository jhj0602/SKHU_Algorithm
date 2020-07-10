import java.util.Scanner;

//***************************
// 파일명: MyBinarySearchTreeTest 클래스
// 작성자: 진형준
// 작성일: 2020-04-20
// 내용: 아이디를 관리하는 이진검색트리 프로그램
//***************************
public class MyBinarySearchTreeTest {

   public static void main(String[] args) {
      // TODO Auto-generated method stub
      System.out.println("hw6_1 : 진형준");
      System.out.println("선택과제 삭제기능 완료");
      Scanner sc = new Scanner(System.in);
      MyBinarySearchTree BST;
      BST = new MyBinarySearchTree();//객체 생성
      while (true) {
         System.out.println("1:삽입 2:검색 3:삭제 4:전체조회 5:종료");
         int num = sc.nextInt();
         if (num == 1) {
            System.out.println("삽입할 문자열");
            String tmp = sc.next();
            System.out.println(BST.add(tmp));//삽입

         }
         if (num == 2) {
            System.out.println("검색할 문자열");
            System.out.println(BST.contains(sc.next()));//검색

         }
         if (num == 3) {
            System.out.println("삭제할 문자열");
            String tmp = sc.next();
            System.out.println(BST.remove(tmp));//삭제

         }

         if (num == 4) {
            System.out.println(BST.size());//전체 출력 및 갯수 출력
            BST.print();
         }
         if (num == 5) {
            System.out.println("종료");//종료
            break;

         }
         System.out.println();

      }

   }

}

class MyBinarySearchTree {
   private class Node {//트리의 노드 구조를 정의하는 private 클래스
      Node left, right;//왼쪽 오른쪽 자식 필드
      String data;//아이디 문자열 필드

      public Node(String data) {
         this.data = data;

      }
   }

   private Node root;
   private int Nodecount = 0;

   public boolean contains(String data) {//아이디를 매개변수로 가져와 트리에 존재하는지 검사
      Node tmp = root;
      if (contains(tmp, data) != null)
         return true;
      else
         return false;
   }

   private Node contains(Node root, String key) {
      if (root == null || root.data.equals(key)) {
         if (root == null) {
            return null;
         } else {
            return root;
         }
      } // root.ID.compareTo(key) < 0
      if (root.data.compareTo(key) > 0) {
         return contains(root.left, key);
      } else
         return contains(root.right, key);

   }

   public String size() {
      return "노드 갯수 :" + Nodecount;
   }

   public boolean add(String data) {

      if (contains(data) == true) {
         return false;
      } else {
         root = add(root, data);
         Nodecount++;
         return true;
      }
   }

   private Node add(Node root, String data) {
      if (root == null) {
         root = new Node(data);
         return root;
      }
      if (root.data.compareTo(data) > 0) {
         root.left = add(root.left, data);
      } else if (root.data.compareTo(data) < 0) {
         root.right = add(root.right, data);
      }

      return root;
   }

   public boolean remove(String data) {
      if (contains(data) == true) {
         root = remove(root, data);
         Nodecount--;
         return true;
      } else {
         return false;
      }
   }

   private Node remove(Node root, String data) {//삭제 함수
      if (root == null)
         return root;//노드가 null이면 해당 루트를 반환
      if (root.data.compareTo(data) > 0) {//문자열 비교 
         root.left = remove(root.left, data);
      } else if (root.data.compareTo(data) < 0) {
         root.right = remove(root.right, data);
      } else {
         if (root.left == null && root.right == null) {
            return null;//자식이 없는경우
         } else if (root.left == null) {
            return root.right;
         } else if (root.right == null) {
            return root.left;
         }
         root.data = findMin(root.right);//자식이 둘다 있는경우 바로앞 바로 이후에 데이터를 대체 
                                         //가장 작은 값을 찾는다
         root.right = remove(root.right, root.data);
      }

      return root;//삭제한 노드를 반환
   }

   String findMin(Node root) {//루트 노드를 만나서 계속 왼쪽으로 null 일떄까지 간다
      String min = root.data;
      while (root.left != null) {
         min = root.left.data;//해당 트리에서 가장 작은 값
         root = root.left;
      }
      return min;
   }

   public void print() {
      inorder(root);
   }

   private void inorder(Node root) {//트리를 중순위 순회하여 키값을 출력
      if (root != null) {
         inorder(root.left);

         System.out.print(root.data + " ");

         inorder(root.right);
      }

   }

}
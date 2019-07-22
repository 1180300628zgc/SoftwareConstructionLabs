
public class Test {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    Object array[][] = new Object[5][5];
    for (int i = 0; i < array.length; i++) {
      for (int j = 0; j < array.length; j++) {
        
        array[i][j] = null;
      }
      
    }
    System.out.println(array.length);
  }
}

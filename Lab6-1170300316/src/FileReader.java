import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileReader {

  private int n;
  private int h;
  private String[][] monkeySet = new String[1000][3];
  private int N = 0;

  public FileReader(String path) {
    FileInputStream inputStream;
    try {
      // inputStream = new FileInputStream("src/input/Competition_1.txt");
      inputStream = new FileInputStream(path);

      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
      String str = null;

      Pattern numbern = Pattern.compile("n=(.*?)$");
      Pattern numberh = Pattern.compile("h=(.*?)$");
      Pattern monkeyInfo = Pattern.compile("monkey=<(.*?),(.*?),(.*?),(.*?)>");

      while ((str = bufferedReader.readLine()) != null) {
        Matcher matchern = numbern.matcher(str);
        if (matchern.find()) {
          n = Integer.valueOf(matchern.group(1));
        }
        Matcher matcherh = numberh.matcher(str);
        if (matcherh.find()) {
          h = Integer.valueOf(matcherh.group(1));
        }
        Matcher matcherInfo = monkeyInfo.matcher(str);
        if (matcherInfo.find()) {
          monkeySet[Integer.valueOf(matcherInfo.group(2)) - 1][0] = matcherInfo.group(1);
          monkeySet[Integer.valueOf(matcherInfo.group(2)) - 1][1] = matcherInfo.group(3);
          monkeySet[Integer.valueOf(matcherInfo.group(2)) - 1][2] = matcherInfo.group(4);
        }

        N++;
      }

      N -= 2;
      
      inputStream.close();
      bufferedReader.close();
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public int getn() {
    return this.n;
  }

  public int geth() {
    return this.h;
  }

  public int getTimeInterval() {
    for (int i = 0; i < monkeySet.length; i++) {
      if (Integer.valueOf(monkeySet[i][0]) != 0) {
        return Integer.valueOf(monkeySet[i][0]);
      }
    }
    return 0;
  }

  public String getDirection(int ID) {
    return monkeySet[ID][1];
  }

  public int getSpeed(int ID) {
    return Integer.valueOf(monkeySet[ID][2]);
  }

  public int getk() {
    for (int i = 0; i < monkeySet.length; i++) {
      if (monkeySet[i][0].equals(String.valueOf(getTimeInterval()))) {
        return i;
      }
    }
    return 0;
  }

  public int getN() {
    return this.N;
  }

  public static void main(String[] args) {
    FileReader fileReader = new FileReader("src/input/Competition_1.txt");
    System.out.println(fileReader.getTimeInterval());
    System.out.println(fileReader.getSpeed(34));
    System.out.println(fileReader.geth());
    System.out.println(fileReader.getk());
    System.out.println(fileReader.getDirection(5));
    
  }
}

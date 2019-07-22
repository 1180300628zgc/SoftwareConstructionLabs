import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.*;
import java.nio.file.*;
import java.io.*;

public class TestWrite {
  public static void main(String[] args) {
    writeFileByLines("src/input/OutputStellarSystemBig.txt");
    // Convert the string to a
    // byte array.

  }

  private static void writeFileByLines(String fileName) {
    String s = "Hello World3! ";
    byte data[] = s.getBytes();
    Path p = Paths.get(fileName);

    try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(p, CREATE, APPEND))) {
      out.write(data, 0, data.length);
    } catch (IOException x) {
      System.err.println(x);
    }
  }
}

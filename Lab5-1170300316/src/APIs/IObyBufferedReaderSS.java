package APIs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import centralObject.CentralObject;
import centralObject.Stellar;
import circularOrbit.CircularOrbit;
import physicalObject.Planet;
import track.Track;

public class IObyBufferedReaderSS implements StrategySS {
  /**
   * Referring to the IO strategy "readByBufferedReader".
   *
   * @param fileName
   * @param orbit
   */
  public void StellarSystemI(String fileName, CircularOrbit<CentralObject, Track> orbit) {

    Planet[] planet = new Planet[330000];
    Charset charset = Charset.forName("UTF-8");
    Path path = Path.of(fileName);
    try {
      BufferedReader reader = Files.newBufferedReader(path, charset);
      String tempString = null;
      int line = 1;

      while ((tempString = reader.readLine()) != null) {
        String temp2 = tempString.replace(" ", "");
        if (tempString.length() != 0) {
          // System.out.println(tempString);
          // System.out.println("line " + line + ":" + tempString);
          if (tempString.startsWith("Stellar")) {
            Pattern regex = Pattern.compile("<(.*?),(.*?),(.*?)>");
            Matcher m = regex.matcher(tempString);
            if (m.find()) {
              // Add to THE STELLAR
              Stellar stellar =
                  new Stellar(m.group(1), Double.valueOf(m.group(2)), Double.valueOf(m.group(3)));

              orbit.AddObjectToCentre(stellar);
            }
            // System.out.println("Found value: " + m.group(0));
          }

          if (tempString.startsWith("Planet")) {
            Pattern regex = Pattern.compile("<(.*?),(.*?),(.*?),(.*?),(.*?),(.*?),(.*?),(.*?)>");
            Matcher m = regex.matcher(tempString);
            if (m.find()) {
              // Add to planet
              planet[line] =
                  new Planet(
                      m.group(1),
                      m.group(2),
                      m.group(3),
                      Double.valueOf(m.group(4)),
                      Double.valueOf(m.group(5)),
                      Double.valueOf(m.group(6)),
                      m.group(7),
                      Double.valueOf(m.group(8)));

              orbit.AddObjectToTrack(planet[line]);
            }
          }
          line++;
        }
      }
      System.out.println("All " + line + " lines has been read.");

    } catch (IOException e) { // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Override
  public void StellarSystemO(String fileName, CircularOrbit<CentralObject, Track> orbit) {
    // TODO Auto-generated method stub

    Track[] sortedTracks = new Track[orbit.GetTrack().size()];
    System.arraycopy(orbit.SortedTracks(), 0, sortedTracks, 0, orbit.GetTrack().size());

    Path file = Path.of(fileName);
    Charset charset = Charset.forName("UTF-8");
    try {
      BufferedWriter writer = Files.newBufferedWriter(file, charset);
      for (Track track : sortedTracks) {
        for (Planet planet : track.getPhysicalObjects()) {
          writer.write(
              ("Planet ::= <"
                  + track.GetName()
                  + ","
                  + planet.form
                  + ","
                  + planet.color
                  + ","
                  + planet.planetRadius
                  + ","
                  + planet.trackRadius
                  + ","
                  + planet.trackSpeed
                  + ","
                  + planet.trackOrientation
                  + ","
                  + planet.initDegree
                  + ">"),
              0,
              ("Planet ::= <"
                      + track.GetName()
                      + ","
                      + planet.form
                      + ","
                      + planet.color
                      + ","
                      + planet.planetRadius
                      + ","
                      + planet.trackRadius
                      + ","
                      + planet.trackSpeed
                      + ","
                      + planet.trackOrientation
                      + ","
                      + planet.initDegree
                      + ">")
                  .length());
        }

        byte enter[] = "\n".getBytes();
        writer.write("\n", 0, "\n".length());
      }

    } catch (IOException e) { // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}

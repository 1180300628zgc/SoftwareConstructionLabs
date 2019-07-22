package APIs;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import centralObject.CentralObject;
import centralObject.Stellar;
import circularOrbit.CircularOrbit;
import physicalObject.Planet;
import track.Track;

public class IOWithoutBufferSS implements StrategySS {
  /**
   * Referring to the IO strategy "readByFiles".
   *
   * @param fileName
   * @param orbit
   */
  public void StellarSystemI(String fileName, CircularOrbit<CentralObject, Track> orbit) {
    BufferedReader reader = null;
    Planet[] planet = new Planet[330000];

    try {
      System.out.println("Start reading from files.");
      reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));
      String tempString = null;
      int line = 1;

      try {
        while ((tempString = reader.readLine()) != null) {
          String temp2 = tempString.replace(" ", "");
          if (tempString.length() != 0) {
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
        // System.out.println("All " + line + " lines has been read.");
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

    } catch (UnsupportedEncodingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Override
  public void StellarSystemO(
      String fileName,
      CircularOrbit<CentralObject, Track> orbit) { // TODO Auto-generated method stub
    Path p = Paths.get(fileName);

    Track[] sortedTracks = new Track[orbit.GetTrack().size()];
    System.arraycopy(orbit.SortedTracks(), 0, sortedTracks, 0, orbit.GetTrack().size());

    // Planet ::= <p0,Gas,Grey,9449.29,4.1488e12,4683,CCW,141>
    try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(p, CREATE, APPEND))) {
      for (Track track : sortedTracks) {
        for (Planet planet : track.getPhysicalObjects()) {
          byte data[] =
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
                  .getBytes();
          out.write(data, 0, data.length);
        }

        byte enter[] = "\n".getBytes();
        out.write(enter, 0, enter.length);
      }

    } catch (IOException x) {
      System.err.println(x);
    }
  }
}

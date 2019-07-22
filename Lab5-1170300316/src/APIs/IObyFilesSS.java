package APIs;

import static java.nio.file.StandardOpenOption.APPEND;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import centralObject.CentralObject;
import centralObject.Stellar;
import circularOrbit.CircularOrbit;
import physicalObject.Planet;
import track.Track;

public class IObyFilesSS implements StrategySS {
  /**
   * Referring to the IO strategy "readByReadAllBytes".
   *
   * @param fileName
   * @param orbit
   */
  public void StellarSystemI(String fileName, CircularOrbit<CentralObject, Track> orbit) {
    Path file = Path.of(fileName);
    Planet[] planet = new Planet[330000];

    java.util.List<String> stringList = new ArrayList();
    try {
      stringList = Files.readAllLines(file);
      for (int i = 0; i < stringList.size(); i++) {
        String tempString = stringList.get(i);
        String temp2 = tempString.replace(" ", "");
        if (tempString.length() != 0) {
          // System.out.println(tempString);
          // System.out.println("line " + (i + 1) + ":" + tempString);
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
              planet[i + 1] =
                  new Planet(
                      m.group(1),
                      m.group(2),
                      m.group(3),
                      Double.valueOf(m.group(4)),
                      Double.valueOf(m.group(5)),
                      Double.valueOf(m.group(6)),
                      m.group(7),
                      Double.valueOf(m.group(8)));

              orbit.AddObjectToTrack(planet[i + 1]);
            }
          }
          // line++;
        }
      }

    } catch (IOException e) { // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Override
  public void StellarSystemO(String fileName, CircularOrbit<CentralObject, Track> orbit) {
    // TODO Auto-generated method stub
    Path file = Path.of(fileName);

    Track[] sortedTracks = new Track[orbit.GetTrack().size()];
    System.arraycopy(orbit.SortedTracks(), 0, sortedTracks, 0, orbit.GetTrack().size());

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
        try {
          Files.write(file, data, APPEND);
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }

      byte enter[] = "\n".getBytes();
      try {
        Files.write(file, enter, APPEND);
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }
}

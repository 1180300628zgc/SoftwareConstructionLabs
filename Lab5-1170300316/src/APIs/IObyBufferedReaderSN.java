package APIs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import centralObject.CentralObject;
import centralObject.Person;
import circularOrbit.CircularOrbit;
import extensions.FriendRelation;
import physicalObject.Friend;
import track.Track;

public class IObyBufferedReaderSN implements StrategySN {

  @Override
  public void SocialNetworkI(
      String fileName,
      CircularOrbit<CentralObject, Track> orbit,
      Set<Friend> addedFriends,
      FriendRelation relation) {
    // TODO Auto-generated method stub
    Charset charset = Charset.forName("UTF-8");
    Path path = Path.of(fileName);

    Friend centerFriend;

    try {
      BufferedReader reader = Files.newBufferedReader(path, charset);
      BufferedReader reader2 = Files.newBufferedReader(path, charset);
      String tempString = null;
      int line = 1;

      while ((tempString = reader.readLine()) != null) {
        String temp2 = tempString.replace(" ", "");
        if (tempString.length() != 0) {
          System.out.println("line " + line + ":" + tempString);
          if (tempString.startsWith("CentralUser")) {
            Pattern regex = Pattern.compile("<(.*?),(.*?),(.*?)>");
            Matcher m = regex.matcher(tempString);
            if (m.find()) {
              Person person = new Person(m.group(1), Integer.valueOf(m.group(2)), m.group(3));
              centerFriend = new Friend(m.group(1), Integer.valueOf(m.group(2)), m.group(3));
              orbit.AddObjectToCentre(person);
              orbit.NewFriend(centerFriend);

              addedFriends.add(centerFriend);
            }
          }

          if (tempString.startsWith("Friend")) {
            System.out.println("Stuck here");
            Pattern regex = Pattern.compile("<(.*?), (.*?), (.*?)>");
            Matcher m = regex.matcher(tempString);
            if (m.find()) {
              Friend friend = new Friend(m.group(1), Integer.valueOf(m.group(2)), m.group(3));
              orbit.NewFriend(friend);
            }
          }

          // ----DEBUG LOG-----//

          line++;
        }
      }

      line = 1;

      while ((tempString = reader2.readLine()) != null) {
        String temp2 = tempString.replace(" ", "");
        if (tempString.length() != 0) {
          if (tempString.startsWith("SocialTie")) {
            Pattern regex = Pattern.compile("<(.*?), (.*?), (.*?)>");
            Matcher m = regex.matcher(tempString);
            if (m.find()) {
              relation.NewRelation(new Friend(m.group(1), 1, "M"), new Friend(m.group(2), 1, "F"));
            }
          }

          // ----DEBUG LOG-----//

          line++;
        }
      }

      for (Friend friend : relation.GetMap().keySet()) {
        System.out.println("MAP " + friend.GetName());
        System.out.println(relation.GetMap().get(friend));
      }

      //
      Track[] track = new Track[5];

      int i = 0;
      track[i] = new Track(i);

      for (Friend friend : addedFriends) {
        track[i].AddFriendObject(friend);
      }

      for (Friend friend : track[i].GetFriendObject()) {
        System.out.println(friend.GetName());
      }

      orbit.AddTrack(track[i]);

      i++;

    } catch (IOException e) { // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Override
  public void SocialNetworkO(
      String fileName,
      CircularOrbit<CentralObject, Track> orbit,
      Set<Friend> addedFriends,
      FriendRelation relation) {
    // TODO Auto-generated method stub
    Charset charset = Charset.forName("UTF-8");
    Path p = Paths.get(fileName);
    try {
      BufferedWriter writer = Files.newBufferedWriter(p, charset);
      for (Friend friend : orbit.GetFriends()) {
        String data =
            ("Friend ::= <" + friend.GetName() + "," + friend.age + "," + friend.gender + ">");
        writer.write(data, 0, data.length());
      }
    } catch (IOException e) { // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}

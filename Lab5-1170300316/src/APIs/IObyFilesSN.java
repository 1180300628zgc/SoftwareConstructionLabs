package APIs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import centralObject.CentralObject;
import centralObject.Person;
import circularOrbit.CircularOrbit;
import extensions.FriendRelation;
import physicalObject.Friend;
import track.Track;

public class IObyFilesSN implements StrategySN {

  @Override
  public void SocialNetworkI(
      String fileName,
      CircularOrbit<CentralObject, Track> orbit,
      Set<Friend> addedFriends,
      FriendRelation relation) {
    // TODO Auto-generated method stub
    Path file = Path.of(fileName);

    Friend centerFriend;

    System.out.println("Start reading from files.");
    java.util.List<String> stringList = new ArrayList();
    java.util.List<String> stringList2 = new ArrayList();

    try {
      stringList = Files.readAllLines(file);
      stringList2 = Files.readAllLines(file);
      String emptyString = null;
      stringList.add(emptyString);
      stringList2.add(emptyString);
    } catch (IOException e) { // TODO Auto-generated catch block
      e.printStackTrace();
    }

    String tempString = null;
    int line = 1;

    while (stringList.get(line - 1) != null) {
      tempString = new String(stringList.get(line - 1));
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
          // Stuck before because of the empty line.
          System.out.println("Stuck here");
          Pattern regex = Pattern.compile("<(.*?), (.*?), (.*?)>");
          Matcher m = regex.matcher(tempString);
          if (m.find()) {
            Friend friend = new Friend(m.group(1), Integer.valueOf(m.group(2)), m.group(3));
            orbit.NewFriend(friend);
          }
        }
        System.out.println("line++");
        // ----DEBUG LOG-----//
        line++;
      }
    }

    line = 1;

    while (stringList2.get(line - 1) != null) {
      tempString = new String(stringList2.get(line - 1));
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
  }

  @Override
  public void SocialNetworkO(
      String fileName,
      CircularOrbit<CentralObject, Track> orbit,
      Set<Friend> addedFriends,
      FriendRelation relation) {
    // TODO Auto-generated method stub
    Path file = Path.of(fileName);
    for (Friend friend : orbit.GetFriends()) {
      byte data[] =
          ("Friend ::= <" + friend.GetName() + "," + friend.age + "," + friend.gender + ">")
              .getBytes();
      try {
        Files.write(file, data);
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }
}

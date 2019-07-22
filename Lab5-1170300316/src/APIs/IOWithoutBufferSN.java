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
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import centralObject.CentralObject;
import centralObject.Person;
import circularOrbit.CircularOrbit;
import extensions.FriendRelation;
import physicalObject.Friend;
import track.Track;

public class IOWithoutBufferSN implements StrategySN {

  @Override
  public void SocialNetworkI(
      String fileName,
      CircularOrbit<CentralObject, Track> orbit,
      Set<Friend> addedFriends,
      FriendRelation relation) {
    // TODO Auto-generated method stub
    BufferedReader reader = null;
    BufferedReader reader2 = null;

    Friend centerFriend;
    // Planet[] planet = new Planet[100];

    try {
      System.out.println("Start reading from files.");
      reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));
      reader2 = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));
      String tempString = null;
      int line = 1;

      try {
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
              Pattern regex = Pattern.compile("<(.*?),(.*?),(.*?)>");
              Matcher m = regex.matcher(tempString);
              if (m.find()) {
                Friend friend = new Friend(m.group(1), Integer.valueOf(m.group(2)), m.group(3));
                orbit.NewFriend(friend);
              }
            }
            line++;
          }
        }

        line = 1;

        while ((tempString = reader2.readLine()) != null) {
          String temp2 = tempString.replace(" ", "");
          if (tempString.length() != 0) {
            if (tempString.startsWith("SocialTie")) {
              Pattern regex = Pattern.compile("<(.*?),(.*?),(.*?)>");
              Matcher m = regex.matcher(tempString);
              if (m.find()) {
                // Deleted Verifying Part
                relation.NewRelation(
                    new Friend(m.group(1), 1, "M"), new Friend(m.group(2), 1, "F"));
              }
            }
            line++;
          }
        }

        System.out.println("Here");
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

        // Delete GUI Part
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
  public void SocialNetworkO(
      String fileName,
      CircularOrbit<CentralObject, Track> orbit,
      Set<Friend> addedFriends,
      FriendRelation relation) {
    // TODO Auto-generated method stub
    Path p = Paths.get(fileName);
    try {
      OutputStream out = new BufferedOutputStream(Files.newOutputStream(p, CREATE, APPEND));
      for (Friend friend : orbit.GetFriends()) {
        byte data[] =
            ("Friend ::= <" + friend.GetName() + "," + friend.age + "," + friend.gender + ">")
                .getBytes();
        out.write(data, 0, data.length);
      }
    } catch (IOException e) { // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}

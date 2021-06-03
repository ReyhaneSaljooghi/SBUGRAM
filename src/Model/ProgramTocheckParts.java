package Model;

import Model.ServerAndClient.Post;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ProgramTocheckParts {
    public static void main(String[] args) {
         Post p1=new Post();
         Post p3=new Post();
         Post p4=new Post();
         p1.setTitle("1");
         p3.setTitle("3");
         p4.setTitle("4");


         p1.setCreatedTime(Instant.now().toEpochMilli());
         p3.setCreatedTime(   Instant.now().toEpochMilli());
         p4.setCreatedTime(   Instant.now().toEpochMilli());
        List<Post>postss=new ArrayList<>();
        postss.add(p1);
        postss.add(p4);
        Post p2=new Post();
        System.out.println("i");
        System.out.println("i");
        System.out.println("i");
        System.out.println("i");
        System.out.println("i");
        System.out.println("i");
        System.out.println("i");
        System.out.println("i");
        System.out.println("i");
        p2.setCreatedTime(  Instant.now().toEpochMilli());
        p2.setTitle("2");
        postss.add(p2);
        postss.add(p3);
    Collections.sort(postss);
      System.out.println(postss);
    }
}

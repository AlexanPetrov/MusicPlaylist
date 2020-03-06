import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Stack;

public class Driver {
    public static void main(String[] args) throws IOException {
        // Add .csv files to Stack
        Stack<String> s = new Stack<>(); 
        s.add("file1.csv");
        s.add("file2.csv");
        s.add("file3.csv");
        s.add("file4.csv");
        // Transfer data from .csv files on Stack to 2D ArrayList (Part I)
        ArrayList<ArrayList<String>> al = new ArrayList<>(); 
        for (int i = 0; i < s.size(); i++) { 
            try (Scanner sc = new Scanner(new File(s.get(i)))) { 
                while (sc.hasNextLine()) {
                    al.add(toTokens(sc.nextLine()));
                }
            }
        }
        // Functional calls
        PlayList songs = new PlayList();
        songs.convertList(al);
        songs.sortList();
        songs.printList();
        songs.playSong(5);
        songs.playSong(12);
        songs.printRecentlyPlayed();
        songs.printLastPlayed();
        
        // Transfer data from LinkedList to csv. file 
        File file = new File("totalInput.csv");
        try (PrintWriter pw = new PrintWriter(file)) {
            LinkedList<PlayList.Song> tsil = songs.getList();            
            for (int i = 0; i < tsil.size(); i++) {
                pw.println(tsil.get(i));
            }
        }  
    }
    public static ArrayList<String> toTokens(String line) {
        // Transfer data from .csv files on Stack to 2D ArrayList (Part II)
        ArrayList<String> list = new ArrayList<String>();
        try (Scanner sc = new Scanner(line)) {
            sc.useDelimiter(",");
            while (sc.hasNext()) {
                list.add(sc.next());
            }
            return list;
        }
    }
}

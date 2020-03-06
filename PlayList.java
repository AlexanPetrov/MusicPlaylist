import java.util.Stack;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collections;

public class PlayList {
    private LinkedList<Song> list; 
    private RecenteHistory history = new RecenteHistory();
    public PlayList() { // Constructor
        list = new LinkedList<Song>();
    }
    // Convert 2D ArrayList to LinkedList
    public void convertList(ArrayList<ArrayList<String>> array) {
        LinkedList<Song> songs = new LinkedList<>();
        for (ArrayList<String> row : array) {
            String position = row.get(0);  
            String track = row.get(1);
            String name = row.get(2);
            String streams = row.get(3);
            String url = row.get(4);
            Song s = new Song(position, track, name, streams, url);
            songs.add(s);
        }
        list = songs;
    }
    public LinkedList<Song> getList() { // Getter to get all songs
        return list;
    }
    public Song playSong(int i) { 
        // Composite getter that gets a specific song based on its index on the list
        // Adds song to recente history stack & returns song based on index (starts at zero)
        history.addSong(list.get(i));
        return list.get(i);
    }
    public void printRecentlyPlayed() { // Printer for recente history stack
        for (Song s : history.recentlyPlayed) {
            System.out.println(s + " ");
        }
    }
    public void printLastPlayed() { // Printer for last song played (from recente history atack)
        System.out.println(history.lastPlayed());
    }
    public void printList() { // Printer for complete list of songs from all files
        for (Song s : list) {
            System.out.println(s + " ");
        }
    }
    public void sortList() { // Sort function that uses Collections (sorts based on song name) 
        Collections.sort(list);
    }
    public class Song implements Comparable { 
        // Song class implements comparable to enable sort function to sort based on song name
        private String position;
        private String track;
        private String artist;
        private String streams;
        private String url;
        // Constructors
        public Song() {
            this("", "", "", "", "");
        }
        public Song(String position, String track, String artist, String streams, String url) {
            this.position = position;
            this.track = track;
            this.artist = artist;
            this.streams = streams;
            this.url = url;
        }
        @Override
        public String toString() { // Overridden toString
            return position +" "+ track +" "+ artist +" "+ streams +" "+ url;
        }
        @Override
        public int compareTo(Object o) { // Overridden compareTo
            Song other = (Song) o;
            int compared = this.track.compareTo(other.track);
            return compared;
        }
        @Override
        public boolean equals(Object o) { // Overriden equals
            if (o instanceof Song) {
                return this.url.equals(((Song) o).url);
            }
            else {
                return false;
            }
        }
    }
    public class RecenteHistory {
        // Function that's responsible for collecting songs played & displaying last played
        private Stack<Song> recentlyPlayed;
        public RecenteHistory() { // Constructor
            recentlyPlayed = new Stack();
        }
        public void addSong(Song s) { // Add
            recentlyPlayed.push(s);
        }
        public Song lastPlayed() { // Getter 
            return recentlyPlayed.peek();
        }
    }
}

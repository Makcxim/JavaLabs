import java.util.ArrayList;
import java.util.List;

public class Laba7Number1 {
    public static void main(String[] args) {
        List<Song> playlist = new ArrayList<>();
        playlist.add(new Song("Тест1", "Тест1"));
        playlist.add(new Song("Тест2", "Тест2"));
        playlist.add(new Song("Тест3", "Тест3"));

        PlayerView view = new ConsolePlayerView();
        PlayerController controller = new PlayerController(view, playlist);

        controller.play();
        controller.nextTrack();
        controller.pause();
        controller.previousTrack();
        controller.play();
    }
}


interface PlayerView {
    void displayCurrentSong(String title, String artist, boolean isPlaying);
}


class PlayerController {
    private final List<Song> playlist;
    private int currentIndex;
    private final PlayerView view;

    public PlayerController(PlayerView view, List<Song> playlist) {
        this.view = view;
        this.playlist = playlist;
        this.currentIndex = 0;
    }

    public void play() {
        playlist.get(currentIndex).setPlaying(true);
        updateView();
    }

    public void pause() {
        playlist.get(currentIndex).setPlaying(false);
        updateView();
    }

    public void nextTrack() {
        playlist.get(currentIndex).setPlaying(false);
        currentIndex = (currentIndex + 1) % playlist.size();
        playlist.get(currentIndex).setPlaying(true);
        updateView();
    }

    public void previousTrack() {
        playlist.get(currentIndex).setPlaying(false);
        currentIndex = (currentIndex - 1 + playlist.size()) % playlist.size();
        playlist.get(currentIndex).setPlaying(true);
        updateView();
    }

    private void updateView() {
        Song currentSong = playlist.get(currentIndex);
        view.displayCurrentSong(currentSong.getTitle(), currentSong.getArtist(), currentSong.isPlaying());
    }
}


class ConsolePlayerView implements PlayerView {
    @Override
    public void displayCurrentSong(String title, String artist, boolean isPlaying) {
        System.out.println("Сейчас играет:");
        System.out.println("Песня: " + title);
        System.out.println("Исполнитель: " + artist);
        System.out.println("Статус: " + (isPlaying ? "Воспроизведение" : "Пауза"));
        System.out.println();
    }
}


class Song {
    private final String title;
    private final String artist;
    private boolean isPlaying;

    public Song(String title, String artist) {
        this.title = title;
        this.artist = artist;
        this.isPlaying = false;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        this.isPlaying = playing;
    }
}
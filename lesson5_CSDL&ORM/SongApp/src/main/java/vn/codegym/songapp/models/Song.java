package vn.codegym.songapp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "song")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tên bài hát không được trống")
    @Size(max = 800)
    @Pattern(
            regexp = "^[\\p{L}\\p{N}\\s]+$",
            message = "Tên bài hát không được chứa ký tự đặc biệt như @ ; , . = - + , …."
    )
    private String title;

    @NotBlank(message = "Tên Nghệ sĩ không được trống")
    @Size(max = 300)
    @Pattern(
            regexp = "^[\\p{L}\\p{N}\\s]+$",
            message = "Tên bài hát không được chứa ký tự đặc biệt như @ ; , . = - + , …."
    )
    private String artist;

    @NotBlank(message = "Tên thể loại không được trống")
    @Size(max = 1000)
    @Pattern(
            regexp = "^[\\p{L}\\p{N}\\s,]+$",
            message = "Ký tự đặc biệt chỉ được dùng dấu ,"
    )
    private String genre;

    @Column(name = "file_path", nullable = false)
    private String filePath;

    public Song() {}

    public Song(String title, String artist, String genre, String filePath) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.filePath = filePath;
    }

    public Song(Long id, String title, String artist, String genre, String filePath) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.filePath = filePath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}

package vn.codegym.songapp.service;

import vn.codegym.songapp.models.Song;

import java.util.List;

public interface ISongService {
    List<Song> findAll();

    Song create(Song song);

    Song getById(Long id);

    void update(Song song);

    void delete(Long id);

}

package vn.codegym.songapp.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.codegym.songapp.models.Song;
import vn.codegym.songapp.repositories.ISongRepository;

import java.util.List;
import java.util.Optional;

@Service // để string tạo bean(Object) trong Ioc contener
@Transactional
public class SongService implements ISongService {
    @Autowired
    ISongRepository songRepository;

    @Override
    public List<Song> findAll() {
        return (List<Song>) songRepository.findAll();
    }


    public Song create(Song song) {
        songRepository.save(song);
        return song;
    }


    @Override
    public Song getById(Long id) {
        Optional<Song> songOptional = songRepository.findById(id);
        return songOptional.orElse(null);
    }


    @Override
    public void update(Song song) {
        songRepository.save(song);
    }


    @Override
    public void delete(Long id) {
        songRepository.deleteById(id);
    }
}

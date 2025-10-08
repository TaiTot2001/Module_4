package vn.codegym.songapp.repositories;

import org.springframework.data.repository.CrudRepository;
import vn.codegym.songapp.models.Song;


public interface ISongRepository extends CrudRepository<Song, Long> {
}

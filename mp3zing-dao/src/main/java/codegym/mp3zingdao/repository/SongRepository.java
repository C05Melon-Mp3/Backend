package codegym.mp3zingdao.repository;

import codegym.mp3zingdao.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song,Integer> {
    List<Song> findAllByDeletedIsFalse();
    Long countByNameSong(String nameSong);
    Song findByNameSongAndDeletedIsFalse(String s);
}


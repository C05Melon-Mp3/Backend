package codegym.mp3zingservice.service;

import codegym.mp3zingdao.dto.SongDTO;
import codegym.mp3zingdao.entity.Song;

import java.util.List;

public interface SongService {
    List<Song> findAllByDeletedIsFalse();

    void save(SongDTO songDTO);

    SongDTO findById(Integer id);

    void updateSong(SongDTO songDTO);

    void delete(Integer id);
}

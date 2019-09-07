package codegym.mp3zingservice.service;

import codegym.mp3zingdao.dto.PlaylistDTO;
import codegym.mp3zingdao.entity.Playlist;

import java.util.List;


public interface PlaylistService {
    List<Playlist> findAll();

    void save(PlaylistDTO playlistDTO);

    PlaylistDTO findById(Integer id);
    void updatePlaylist(PlaylistDTO playlistDTO);
}

package codegym.mp3zingservice.service.impl;

import codegym.mp3zingdao.dto.ArtistDTO;
import codegym.mp3zingdao.dto.PlaylistDTO;
import codegym.mp3zingdao.entity.Artist;
import codegym.mp3zingdao.entity.Playlist;
import codegym.mp3zingdao.repository.PlaylistRepository;
import codegym.mp3zingservice.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistServiceImpl implements PlaylistService {
    @Autowired
    private PlaylistRepository playlistRepository;
    @Override
    public List<Playlist> findAll() {
        return playlistRepository.findAll();
    }

    @Override
    public void save(PlaylistDTO playlistDTO) {
        Playlist playlist = new Playlist();
        playlist.setName(playlistDTO.getName());
        playlist.setAvatar(playlistDTO.getAvatar());
        playlist.setDeleted(Boolean.FALSE);
        playlistRepository.save(playlist);

    }

    @Override
    public PlaylistDTO findById(Integer id) {
        Playlist playlist = playlistRepository.findById(id).orElse(null);
        if(playlist != null){
            PlaylistDTO playlistDTO = new PlaylistDTO();
            playlistDTO.setId(playlist.getId());
            playlistDTO.setName(playlist.getName());
            playlistDTO.setAvatar(playlist.getAvatar());
            return playlistDTO;
        }
        return null;
    }

    @Override
    public void updatePlaylist(PlaylistDTO playlistDTO) {
        Playlist playlist = playlistRepository.findById(playlistDTO.getId()).orElse(null);
        playlist.setName(playlistDTO.getName());
        playlist.setAvatar(playlistDTO.getAvatar());
        playlist.setDeleted(Boolean.FALSE);
        playlistRepository.save(playlist);
    }

}

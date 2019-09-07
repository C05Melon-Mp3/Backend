package codegym.mp3zingservice.service;

import codegym.mp3zingdao.dto.ArtistDTO;
import codegym.mp3zingdao.entity.Artist;

import java.util.List;

public interface ArtistService {
    List<Artist> findAllByDeletedIsFalse();

    void save(ArtistDTO artistDTO);

    ArtistDTO findById(Integer id);
    void updateArtist(ArtistDTO artistDTO);
    ArtistDTO deleteArtist(Integer id);
}

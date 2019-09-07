package codegym.mp3zingservice.service.impl;

import codegym.mp3zingdao.dto.ArtistDTO;
import codegym.mp3zingdao.entity.Artist;
import codegym.mp3zingdao.repository.ArtistRepository;
import codegym.mp3zingservice.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArtistServiceImpl implements ArtistService {
    @Autowired
    private ArtistRepository artistRepository;

    @Override
    public List<Artist> findAllByDeletedIsFalse() {
        return artistRepository.findAllByDeletedIsFalse();
    }

    @Override
    public void save(ArtistDTO artistDTO) {
        Artist artist = new Artist();
        artist.setName(artistDTO.getName());
        artist.setAvatar(artistDTO.getAvatar());
        artist.setCountry(artistDTO.getCountry());
        artist.setDeleted(Boolean.FALSE);
        artistRepository.save(artist);
    }

    @Override
    public ArtistDTO findById(Integer id) {
        Artist artist = artistRepository.findById(id).orElse(null);
        if(artist != null){
            ArtistDTO artistDTO = new ArtistDTO();
            artistDTO.setId(artist.getId());
            artistDTO.setName(artist.getName());
            artistDTO.setAvatar(artist.getAvatar());
            artistDTO.setCountry(artist.getCountry());
            return artistDTO;
        }
        return null;
    }

    @Override
    public void updateArtist(ArtistDTO artistDTO) {
        Artist artist = artistRepository.findById(artistDTO.getId()).orElse(null);
        artist.setName(artistDTO.getName());
        artist.setAvatar(artistDTO.getAvatar());
        artist.setCountry(artistDTO.getCountry());
        artist.setDeleted(Boolean.FALSE);
        artistRepository.save(artist);
    }
    @Override
    public ArtistDTO deleteArtist(Integer id) {
        Artist artist = artistRepository.findById(id).orElse(null);
        artistRepository.delete(artist);
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete", Boolean.TRUE);
        return null;
    }
}

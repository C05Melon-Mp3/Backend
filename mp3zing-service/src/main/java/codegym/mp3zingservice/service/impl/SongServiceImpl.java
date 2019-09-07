package codegym.mp3zingservice.service.impl;

import codegym.mp3zingdao.dto.SongDTO;
import codegym.mp3zingservice.service.SongService;
import codegym.mp3zingdao.entity.Song;
import codegym.mp3zingdao.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.Boolean.TRUE;

@Service
public class SongServiceImpl implements SongService {
    @Autowired
    private SongRepository songRepository;
    @Override
    public List<Song> findAllByDeletedIsFalse() {
        return songRepository.findAllByDeletedIsFalse();
    }

    @Override
    public void save(SongDTO songDTO) {
        Song song = new Song();
        song.setNameSong(songDTO.getNameSong());
        song.setDescriptionSong(songDTO.getDescriptionSong());
        song.setFileMp3(songDTO.getFileMp3());
        song.setAvatarSong(songDTO.getAvatarSong());
        song.setComment(songDTO.getComment());
        song.setDeleted(Boolean.FALSE);

        songRepository.save(song);
    }

    @Override
    public SongDTO findById(Integer id) {
        Song song = songRepository.findById(id).orElse(null);
        if(song != null) {
            SongDTO songDTO = new SongDTO();
            songDTO.setId(song.getId());
            songDTO.setNameSong(song.getNameSong());
            songDTO.setDescriptionSong(song.getDescriptionSong());
            songDTO.setFileMp3(song.getFileMp3());
            songDTO.setAvatarSong(song.getAvatarSong());
            songDTO.setComment(song.getComment());

            return songDTO;
        }
        return null;
    }

    @Override
    public void updateSong(SongDTO songDTO) {
        Song song = songRepository.findById(songDTO.getId()).orElse(null);

        song.setId(songDTO.getId());
        song.setNameSong(songDTO.getNameSong());
        song.setDescriptionSong(songDTO.getDescriptionSong());
        song.setFileMp3(songDTO.getFileMp3());
        song.setAvatarSong(songDTO.getAvatarSong());
        song.setComment(songDTO.getComment());
        song.setDeleted(Boolean.FALSE);

        songRepository.save(song);
    }

    @Override
    public void delete(Integer id) {
        Song song = songRepository.findById(id).orElse(null);
        song.setDeleted(TRUE);
        songRepository.save(song);
    }

}

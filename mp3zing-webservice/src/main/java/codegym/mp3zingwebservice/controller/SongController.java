package codegym.mp3zingwebservice.controller;


import codegym.mp3zingdao.dto.SongDTO;
import codegym.mp3zingdao.entity.Song;
import codegym.mp3zingservice.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*",  allowedHeaders = "*")
@RequestMapping("")

public class SongController {

    @Autowired
    private SongService songService;

    @PostMapping("/songs/add")
    public ResponseEntity<?> createSong(@Valid @RequestBody SongDTO songDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<List>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        songService.save(songDTO);
        return ResponseEntity.ok(songDTO);
    }

    @PutMapping("/songs/update-song/{id}")
    public ResponseEntity<SongDTO> updateSong(@PathVariable(value="id") Integer id, @RequestBody SongDTO songDTO) {
        songService.updateSong(songDTO);
        return ResponseEntity.ok(songDTO);
    }

    @GetMapping("/songs/show/{id}")
    public ResponseEntity<SongDTO> getSongById(@PathVariable(value="id") Integer id) {
        SongDTO songDTO = songService.findById(id);
        return ResponseEntity.ok().body(songDTO);
    }

    @GetMapping("/songs")
    public List<Song> getAllSongs() {
        List<Song> songs;
        songs = songService.findAllByDeletedIsFalse();
        return songs;
    }

    @DeleteMapping("/songs/delete-song/{id}")
    public Map<String, Boolean> deleteSong(@PathVariable(value = "id") Integer id) {
        SongDTO songDTO = songService.findById(id);
        songService.delete(songDTO.getId());
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}

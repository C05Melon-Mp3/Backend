package codegym.mp3zingwebservice.controller;

import codegym.mp3zingdao.dto.PlaylistDTO;
import codegym.mp3zingdao.entity.Playlist;
import codegym.mp3zingservice.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping("")
public class PlaylistController {

    @Autowired
    PlaylistService playlistService;

    @GetMapping("/playlists")
    public List<Playlist> getAllPlaylist() {
        List<Playlist> playlists = playlistService.findAll();
        return playlists;
    }
    @GetMapping("/playlists/{id}")
    public ResponseEntity<PlaylistDTO> getPlaylistById(@PathVariable(value = "id") Integer id) {
        PlaylistDTO playlistDTO = playlistService.findById(id);
        return ResponseEntity.ok().body(playlistDTO);
    }
    @PutMapping("/playlists/update-playlist/{id}")
    public ResponseEntity<PlaylistDTO> updatePlaylist(@PathVariable(value = "id") Integer id ,
                                                      @RequestBody PlaylistDTO playlistDTO){
        playlistService.updatePlaylist(playlistDTO);
        return ResponseEntity.ok(playlistDTO);
    }
    @PostMapping("/playlists")
    public ResponseEntity<?> createPlaylist(@Valid @RequestBody PlaylistDTO playlistDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<List>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        playlistService.save(playlistDTO);
        return ResponseEntity.ok(playlistDTO);
    }

}

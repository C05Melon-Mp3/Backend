package codegym.mp3zingwebservice.controller;
import codegym.mp3zingdao.dto.ArtistDTO;
import codegym.mp3zingdao.entity.Artist;
import codegym.mp3zingservice.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping("")
public class ArtistController {
    @Autowired
    ArtistService artistService;

    @GetMapping("/artists")
    public List<Artist> getAllArtist() {
        List<Artist> artists = artistService.findAllByDeletedIsFalse();
        return artists;
    }
    @GetMapping("/artists/{id}")
    public ResponseEntity<ArtistDTO> getArtistById(@PathVariable(value = "id") Integer id) {
        ArtistDTO artistDTO = artistService.findById(id);
        return ResponseEntity.ok().body(artistDTO);
    }
    @PutMapping("/artists/update-artist/{id}")
    public ResponseEntity<ArtistDTO> updatePlaylist(@PathVariable(value = "id") Integer id ,
                                                      @RequestBody ArtistDTO artistDTO){
        artistService.updateArtist(artistDTO);
        return ResponseEntity.ok(artistDTO);
    }
    @PostMapping("/artists")
    public ResponseEntity<?> createArtist(@Valid @RequestBody ArtistDTO artistDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<List>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        artistService.save(artistDTO);
        return ResponseEntity.ok(artistDTO);
    }
    @DeleteMapping("/artists/{id}")
    public ResponseEntity<ArtistDTO> deleteArtist(@PathVariable(value = "id") Integer id){
        ArtistDTO artistDTO = artistService.deleteArtist(id);
        return ResponseEntity.ok().body(artistDTO);
    }
}

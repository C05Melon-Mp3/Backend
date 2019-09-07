package codegym.mp3zingdao.repository;

import codegym.mp3zingdao.entity.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist,Integer> {
    List<Playlist> findAll();
}

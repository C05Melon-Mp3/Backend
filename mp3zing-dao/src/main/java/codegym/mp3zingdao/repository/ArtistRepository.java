package codegym.mp3zingdao.repository;

import codegym.mp3zingdao.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepository extends JpaRepository<Artist,Integer>{
    List<Artist> findAllByDeletedIsFalse();
}

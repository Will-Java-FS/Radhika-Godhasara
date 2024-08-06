package com.revature.Respository;

import com.revature.Model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    Song findSongBySongID(Long songID);

    @Query("SELECT s FROM Song s WHERE s.savedBy = :savedBy")
    List<Song> findAllSongsSavedBy(@Param("savedBy") Long savedBy);
}

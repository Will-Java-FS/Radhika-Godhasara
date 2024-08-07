package com.revature.services;

import com.revature.Model.Song;
import com.revature.Respository.SongRepository;
import com.revature.Service.SongService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class SongServiceTest {

    @Autowired
    SongService ss;

    @MockBean
    SongRepository sr;

    @Test
    void addSong(){
        Song s = new Song(0L, "Blinding Lights", "The Weeknd", "Pop", "2020", null);

        Mockito.when(sr.save(s)).thenReturn(new Song(1L, "Blinding Lights", "The Weeknd", "Pop", "2020", null));

        s = ss.addSong(s);

        Assertions.assertEquals(1, s.getSongID());
        Assertions.assertEquals("Blinding Lights", s.getSongName());
    }

    @Test
    void deleteSong(){
        Song s = new Song(0L, "Blinding Lights", "The Weeknd", "Pop", "2020", null);

        Mockito.doNothing().when(sr).deleteById((s.getSongID()));

        int result = ss.deleteSong(s.getSongID());
        boolean r = false;

        if(result == 0){
            r = true;
        }

        Assertions.assertTrue(r);
    }

    @Test
    void getSongByID(){
        Song s = new Song(0L, "Blinding Lights", "The Weeknd", "Pop", "2020", null);
        Optional<Song> songOptional = Optional.of(s);

        Mockito.when(sr.findById(s.getSongID())).thenReturn(songOptional);

        Song actual = ss.getSongsByID(s.getSongID());
        Assertions.assertEquals(s.getSongID(), actual.getSongID());
    }

    @Test
    public void getSongs(){
        List<Song> songs = sr.findAll();
        Song u = new Song(0L, "Blinding Lights", "The Weeknd", "Pop", "2020", null);
        songs.add(u);

        Assertions.assertFalse(songs.isEmpty());
    }
}

/*package com.revature.Controller;

import com.revature.Model.Song;
import com.revature.Service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SongController {

    private SongService songService;

    @Autowired
    public SongController(SongService songService){
        this.songService = songService;
    }

    @GetMapping("songs")
    public ResponseEntity<List<Song>> getAllSongs() {
        List<Song> songs = songService.getAllSongs();
        return ResponseEntity.status(200).body(songs);
    }

    @PostMapping("songs")
    public ResponseEntity<Song> saveSong(@RequestBody Song song) {
        songService.saveSong(song);
        return ResponseEntity.status(200).body(song);
    }
}*/

package com.revature.Controller;

import com.revature.Model.Song;
import com.revature.Service.SongService;
import com.revature.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.revature.Model.User;
import java.util.List;

@RestController
public class SongController {
    private SongService songService;
    private UserService userService;

    @Autowired
    public SongController(SongService songService, UserService userService) {
        this.songService = songService;
        this.userService = userService;
    }

    @GetMapping("songs")
    public ResponseEntity<List<Song>> getAllSongs() {
        List<Song> songs = songService.getAllSongs();
        return ResponseEntity.status(200).body(songs);
    }

    @PostMapping("users/{userID}/songs")
    public ResponseEntity<Song> saveSong(@PathVariable Long userID, @RequestBody Song song) {
        songService.saveSong(userID, song);
        return ResponseEntity.status(200).body(song);
    }

    @PostMapping("songs")
    public ResponseEntity<Song> addSong(@RequestBody Song song) {
        songService.addSong(song);
        return ResponseEntity.status(200).body(song);
    }

    @GetMapping("songs/{songID}")
    public ResponseEntity<Song> getSongsBySongID(@PathVariable Long songID) {
        Song song = songService.getSongsBySongName(songID);
        return ResponseEntity.status(200).body(song);
    }

    @PatchMapping("songs/{songID}")
    public ResponseEntity<Song> updateSong(@PathVariable Long songID, @RequestBody Song song) {
        Song updatedSong = songService.updateSong(songID, song);
        return ResponseEntity.status(200).body(updatedSong);
    }

    @DeleteMapping("songs/{songID}")
    public ResponseEntity<Integer> deleteSong(@PathVariable Long songID) {
        int songsDeleted = songService.deleteSong(songID);
        if(songsDeleted > 0) {
            return ResponseEntity.status(200).body(1);
        } else {
            return ResponseEntity.status(200).body(null);
        }
    }

    @GetMapping("/users/{userID}/songs")
    public ResponseEntity<List<Song>> getSongsByUser(@PathVariable Long userID){
        List<Song> songs = songService.getAllSongsByUserID(userID);
        if(songs == null){
            return ResponseEntity.status(200).build();
        }
        else{
            return ResponseEntity.status(200).body(songs);
        }
    }

//    @GetMapping("/users/{userID}")
//    public ResponseEntity<List<User>> getUsersAndSongs(@RequestBody User user){
//        List<User> users = userService.getAllUsers(user);
//        if(users == null){
//            return ResponseEntity.status(200).build();
//        }
//        else {
//            return ResponseEntity.status(200).body(users);
//        }
//    }

    @GetMapping("/users/{userID}")
    public ResponseEntity<List<User>> getUsersAndSongs(@PathVariable Long userID){
        List<User> users = userService.getAllUsers(userID);
        if(users == null){
            return ResponseEntity.status(200).build();
        }
        else {
            return ResponseEntity.status(200).body(users);
        }
    }
}

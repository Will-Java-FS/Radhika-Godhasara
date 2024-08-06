package com.revature.Controller;

import com.revature.Model.Song;
import com.revature.Model.User;
import com.revature.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registration(@RequestBody User user){
        if(userService.login(user.getUsername()) != null){
            return ResponseEntity.status(404).build();
        }
        else{
            return ResponseEntity.status(200).body(userService.register(user));
        }
    }

    @GetMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        User u = userService.login((user.getUsername()));

        if (u == null || !u.getPassword().equals(user.getPassword())) {
            return ResponseEntity.status(404).build();
        } else {
            return ResponseEntity.status(200).body(u);
        }
    }

//    @GetMapping("/users")
//    public ResponseEntity<List<User>> getAllUsers(){
//        List<User> users = userService.getAllUsers();
//        return ResponseEntity.status(200).body(users);
//    }

    /*@GetMapping("songs")
    public ResponseEntity<List<Song>> getAllSongs() {
        List<Song> songs = songService.getAllSongs();
        return ResponseEntity.status(200).body(songs);
    }*/

//    @GetMapping("songs")
//    public ResponseEntity<List<Song>> getAllSongs() {
//        List<Song> songs = songService.getAllSongs();
//        return ResponseEntity.status(200).body(songs);
//    }

//    @GetMapping("admin")
//    public ResponseEntity<List<User>> getByAdmin(){
//        List<User> users = userService.getByAdmin();
//        return ResponseEntity.status(200).body(users);
//    }
}

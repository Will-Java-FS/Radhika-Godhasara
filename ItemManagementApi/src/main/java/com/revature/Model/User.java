package com.revature.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userID;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String firstname;
    @Column
    private String lastname;
    @Column
    private boolean admin;

//    @OneToMany(fetch = FetchType.EAGER)
//    //@JoinColumn(name = "savedBy")
//    //@JsonManagedReference
//    //@JoinTable(name = "Song", joinColumns = @JoinColumn(name = "savedBy"))
//    @JoinColumn(name = "user_fk")
//    private List<Song> songs;

    @Column
    @OneToMany(fetch = FetchType.EAGER)
//  @JoinColumn(name = "user_fk")
    private List<Song> songs;
}

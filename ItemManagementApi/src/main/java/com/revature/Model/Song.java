package com.revature.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long songID;
    @Column
    private String songName;
    @Column
    private String artist;
    @Column
    private String genre;
    @Column
    private String year;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JsonBackReference
//    //@JoinColumn(name = "savedBy")
//    //@JoinTable(name = "User", joinColumns = @JoinColumn(name = "savedBy"))
//    //private User userID;
//    @JoinColumn(name = "user_fk")
//    private Song song; //-- working
//    //private Long userID;

    @Column
    private Long savedBy;

    @Column
    private List<Long> userID;
}

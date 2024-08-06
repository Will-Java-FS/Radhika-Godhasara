package com.revature.Respository;

import com.revature.Model.Song;
import com.revature.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    //@Query("SELECT u FROM User u WHERE s.savedBy = :savedBy")
    //List<User> findByAdmin();
}

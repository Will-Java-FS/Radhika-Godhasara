package com.revature.repositories;

import com.revature.models.*;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Repository interface for performing CRUD operations on PropertyModel entities.
 * Extends JpaRepository to provide built-in database interaction methods.
 */
@Repository
public interface PropertyRepo extends JpaRepository<PropertyModel, Long> {
    
    /**
     * Finds a list of properties located in a specific city.
     * 
     * @param city the city where properties are located
     * @return a list of PropertyModel entities matching the specified city
     */
    List<PropertyModel> findByCity(String city);
    
    /**
     * Finds a list of properties located in a specific state.
     * 
     * @param state the state where properties are located
     * @return a list of PropertyModel entities matching the specified state
     */
    List<PropertyModel> findByState(String state);

    /**
     * Finds a list of properties with a specific zipcode.
     * 
     * @param zipcode the zipcode of the properties
     * @return a list of PropertyModel entities matching the specified zipcode
     */
    List<PropertyModel> findByZipcode(String zipcode);

    /**
     * Finds a list of properties associated with a specific user.
     * 
     * @param user the {@link UserModel} entity associated with the properties
     * @return a list of {@link PropertyModel} entities that belong to the specified user
     */
    List<PropertyModel> findByUser(UserModel user);
}
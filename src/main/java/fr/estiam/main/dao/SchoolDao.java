package fr.estiam.main.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.estiam.main.entities.School;


public interface SchoolDao extends JpaRepository<School, Long> {
    
    //List<School> findByNameContainingIgnoreCase(String name);
    
}


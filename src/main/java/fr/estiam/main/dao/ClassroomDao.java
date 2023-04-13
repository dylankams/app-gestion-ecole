package fr.estiam.main.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.estiam.main.entities.Classroom;


public interface ClassroomDao extends JpaRepository<Classroom, Long> {
    
    List<Classroom> findBySchoolId(Long schoolId);
    List<Classroom> findByNameContainingIgnoreCase(String name);

    
    default List<Classroom> search(String keyword) {
        return findByNameContainingIgnoreCase(keyword);
    }  
}
package fr.estiam.main.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fr.estiam.main.dao.SchoolDao;
import fr.estiam.main.entities.School;


@RestController
@RequestMapping("/schools")
public class SchoolController {

    @Autowired
    private SchoolDao schoolDao;

    // GET request to retrieve all schools
    @GetMapping("/")
    public List<School> getAllSchools() {
    	System.out.println("test");
        return schoolDao.findAll();
    }

    // GET request to retrieve a school by its ID
    @GetMapping("/{id}")
    public ResponseEntity<School> getSchoolById(@PathVariable(value = "id") Long schoolId) {
        Optional<School> school = schoolDao.findById(schoolId);
        if (school.isPresent()) {
            return ResponseEntity.ok().body(school.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // POST request to create a new school
    @PostMapping("")
    public ResponseEntity<School> createSchool(@RequestBody School school) {
        School createdSchool = schoolDao.save(school);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSchool);
    }

    // PUT request to update an existing school
    @PutMapping("/{id}")
    public ResponseEntity<School> updateSchool(@PathVariable(value = "id") Long schoolId, @RequestBody School schoolDetails) {
        Optional<School> optionalSchool = schoolDao.findById(schoolId);
        if (optionalSchool.isPresent()) {
            School school = optionalSchool.get();
            school.setName(schoolDetails.getName());
            school.setAddress(schoolDetails.getAddress());
            school.setPhoneNumber(schoolDetails.getPhoneNumber());
            School updatedSchool = schoolDao.save(school);
            return ResponseEntity.ok().body(updatedSchool);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE request to delete a school
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchool(@PathVariable(value = "id") Long schoolId) {
        Optional<School> optionalSchool = schoolDao.findById(schoolId);
        if (optionalSchool.isPresent()) {
        	schoolDao.delete(optionalSchool.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}


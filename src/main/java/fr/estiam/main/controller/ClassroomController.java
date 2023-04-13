package fr.estiam.main.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.estiam.main.dao.ClassroomDao;
import fr.estiam.main.entities.Classroom;

@RestController
@RequestMapping("/classrooms")
public class ClassroomController {

    @Autowired
    private ClassroomDao classroomDao;

    @GetMapping("/")
    public List<Classroom> getAllClassrooms() {
        return classroomDao.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Classroom> getClassroomById(@PathVariable(value = "id") Long classroomId) {
        Optional<Classroom> classroom = classroomDao.findById(classroomId);
        if (classroom.isPresent()) {
            return ResponseEntity.ok().body(classroom.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Classroom> createClassroom(@RequestBody Classroom classroom) {
        Classroom createdClassroom = classroomDao.save(classroom);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdClassroom);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Classroom> updateClassroom(@PathVariable(value = "id") Long classroomId, @RequestBody Classroom classroomDetails) {
        Optional<Classroom> optionalClassroom = classroomDao.findById(classroomId);
        if (optionalClassroom.isPresent()) {
            Classroom classroom = optionalClassroom.get();
            classroom.setName(classroomDetails.getName());
            classroom.setDescription(classroomDetails.getDescription());
            classroom.setCapacity(classroomDetails.getCapacity());
            Classroom updatedClassroom = classroomDao.save(classroom);
            return ResponseEntity.ok().body(updatedClassroom);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClassroom(@PathVariable(value = "id") Long classroomId) {
        Optional<Classroom> optionalClassroom = classroomDao.findById(classroomId);
        if (optionalClassroom.isPresent()) {
        	classroomDao.delete(optionalClassroom.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

   @GetMapping("/search")
    public ResponseEntity<List<Classroom>> searchClassrooms(@RequestParam(value = "keyword") String keyword) {
        List<Classroom> classrooms = classroomDao.search(keyword);
        return ResponseEntity.ok().body(classrooms);
    }
}


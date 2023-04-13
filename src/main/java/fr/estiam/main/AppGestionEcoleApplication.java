package fr.estiam.main;

import fr.estiam.main.dao.SchoolDao;
import fr.estiam.main.entities.Classroom;
import fr.estiam.main.entities.School;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;


@SpringBootApplication
public class AppGestionEcoleApplication implements CommandLineRunner {

    @Autowired
    private SchoolDao schoolDao;


    public static void main(String[] args) {
        SpringApplication.run(AppGestionEcoleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Ajout de deux écoles
        School school1 = new School("Ecole primaire A", "12 rue de la Liberté", "01 23 45 67 89");
        School school2 = new School("Ecole primaire B", "6 avenue des Lilas", "01 23 45 68 79");
        schoolDao.saveAll(Arrays.asList(school1, school2));

        // Ajout de trois salles de classe dans l'école primaire A
        Classroom classroom1 = new Classroom("CP", 20, "Salle pour les élèves de CP");
        Classroom classroom2 = new Classroom("CE1", 25, "Salle pour les élèves de CE1");
        Classroom classroom3 = new Classroom("CE2", 30, "Salle pour les élèves de CE2");
        school1.addClassroom(classroom1);
        school1.addClassroom(classroom2);
        school1.addClassroom(classroom3);
        schoolDao.save(school1);

        // Ajout de deux salles de classe dans l'école primaire B
        Classroom classroom4 = new Classroom("CM1", 30, "Salle pour les élèves de CM1");
        Classroom classroom5 = new Classroom("CM2", 35, "Salle pour les élèves de CM2");
        school2.addClassroom(classroom4);
        school2.addClassroom(classroom5);
        schoolDao.save(school2);
    }
}
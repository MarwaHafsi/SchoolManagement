package com.school.students.Repositories;

import com.school.students.Entities.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface EtudiantRepository extends JpaRepository<Etudiant,Long> {

}

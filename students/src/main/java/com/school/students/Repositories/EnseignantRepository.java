package com.school.students.Repositories;

import com.school.students.Entities.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface   EnseignantRepository extends JpaRepository<Enseignant,Long> {
}

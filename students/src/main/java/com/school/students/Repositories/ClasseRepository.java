package com.school.students.Repositories;

import com.school.students.Entities.Classe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ClasseRepository extends JpaRepository<Classe,Long>{
}

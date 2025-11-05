package iuh.fit.se.ontapth02.repository;

import iuh.fit.se.ontapth02.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * @description: TeacherRepository
 * @author: Trần Ngọc Huyền
 * @date: 11/4/2025
 * @version: 1.0
 */
@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
}

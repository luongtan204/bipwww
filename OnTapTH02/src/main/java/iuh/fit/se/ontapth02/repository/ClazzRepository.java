package iuh.fit.se.ontapth02.repository;

import iuh.fit.se.ontapth02.model.Clazz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/*
 * @description: ClazzRepository
 * @author: Trần Ngọc Huyền
 * @date: 11/4/2025
 * @version: 1.0
 */
@Repository
public interface ClazzRepository extends JpaRepository<Clazz, Integer> {

    @Query("select c from Clazz c " +
            "where (:fromMonth is null or " +
            "MONTH(c.startdate) >= :fromMonth) and " +
            "(:toMonth is null or " +
            "MONTH(c.startdate) <= :toMonth)")
    List<Clazz> findByMonthStartDateBetween(int fromMonth, int toMonth);

    @Query("select c from Clazz c " +
            "where (:keyword is null or " +
            "lower(c.name) like lower(concat ('%', :keyword, '%'))) or " +
            "(lower(c.teacher.name) like (lower(concat('%', :keyword, '%') )))")
    List<Clazz> findByNameClassOrTeacherName(String keyword);

}

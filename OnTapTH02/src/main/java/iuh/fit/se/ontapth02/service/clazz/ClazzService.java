package iuh.fit.se.ontapth02.service.clazz;


import iuh.fit.se.ontapth02.model.Clazz;

import java.util.List;

public interface ClazzService {
    List<Clazz> findAll();
    Clazz findById(Integer id);
    Clazz save(Clazz clazz);
    List<Clazz> findByMonthStartDateBetween(int fromMonth, int toMonth);
    List<Clazz> findByClassNameOrTeacherName(String keyword);
}

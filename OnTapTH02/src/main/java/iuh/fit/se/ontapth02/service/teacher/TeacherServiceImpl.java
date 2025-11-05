package iuh.fit.se.ontapth02.service.teacher;

/*
 * @description: TeacherServiceImpl
 * @author: Trần Ngọc Huyền
 * @date: 11/4/2025
 * @version: 1.0
 */

import iuh.fit.se.ontapth02.model.Teacher;
import iuh.fit.se.ontapth02.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }
}

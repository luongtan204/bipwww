package iuh.fit.se.ontapth02.service.clazz;

import iuh.fit.se.ontapth02.model.Clazz;
import iuh.fit.se.ontapth02.repository.ClazzRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * @description: ClazzServiceImpl
 * @author: Trần Ngọc Huyền
 * @date: 11/4/2025
 * @version: 1.0
 */
@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzRepository clazzRepository;

    @Override
    public List<Clazz> findAll() {
        return clazzRepository.findAll();
    }

    @Override
    public Clazz findById(Integer id) {
        return clazzRepository.findById(id).orElse(null);
    }

    @Override
    public Clazz save(Clazz clazz) {
        return clazzRepository.save(clazz);
    }

    @Override
    public List<Clazz> findByMonthStartDateBetween(int fromMonth, int toMonth) {
        return clazzRepository.findByMonthStartDateBetween(fromMonth, toMonth);
    }

    @Override
    public List<Clazz> findByClassNameOrTeacherName(String keyword) {
        return clazzRepository.findByNameClassOrTeacherName(keyword);
    }
}

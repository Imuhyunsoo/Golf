package edu.global.ex.Command;

import edu.global.ex.Dao.GolfDao;
import edu.global.ex.Dto.TeacherDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class TeacherListCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("TeacherListCommand ..");

        GolfDao golfDao = new GolfDao();
        List<TeacherDto> dtoList = golfDao.TeacherList();

        request.setAttribute("teacherList", dtoList);
    }
}

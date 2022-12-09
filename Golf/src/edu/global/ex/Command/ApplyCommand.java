package edu.global.ex.Command;

import edu.global.ex.Dao.GolfDao;
import edu.global.ex.Dto.ApplyDto;
import edu.global.ex.Dto.ClassNameDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ApplyCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        String c_name = request.getParameter("c_name");
        String class_area = request.getParameter("class_area");
        String class_name = request.getParameter("class_name");

        GolfDao golfDao = new GolfDao();
        List<ApplyDto> applyList = golfDao.apply(c_name, class_area, class_name);
        List<ClassNameDto> classNames = golfDao.className();

        request.setAttribute("list", applyList);
        request.setAttribute("classNames", classNames);
    }
}

package edu.global.ex.Command;

import edu.global.ex.Dao.GolfDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertCommand implements Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        String regist_month = request.getParameter("regist_month");
        String c_name = request.getParameter("c_name");
        String c_no = request.getParameter("c_no");
        String class_area = request.getParameter("class_area");
        String class_name = request.getParameter("class_name");
        String tuition = request.getParameter("tuition");

        GolfDao golfDao = new GolfDao();
        golfDao.insert(regist_month, c_no, class_area, tuition);

    }
}

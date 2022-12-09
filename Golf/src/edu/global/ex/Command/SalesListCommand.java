package edu.global.ex.Command;

import edu.global.ex.Dao.GolfDao;
import edu.global.ex.Dto.SalesDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SalesListCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("SalesListCommand ..");

        GolfDao golfDao = new GolfDao();
        List<SalesDto> dtoList = golfDao.SalesList();

        request.setAttribute("salesList", dtoList);

    }
}

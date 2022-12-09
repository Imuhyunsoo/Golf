package edu.global.ex.Command;

import edu.global.ex.Dao.GolfDao;
import edu.global.ex.Dto.ProfileDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class MemberListCommand implements Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("MemberListCommand ..");

        GolfDao golfDao = new GolfDao();
        List<ProfileDto> dtoList = golfDao.MemberList();

        request.setAttribute("memberList", dtoList);
    }
}

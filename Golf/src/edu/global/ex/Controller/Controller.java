package edu.global.ex.Controller;

import edu.global.ex.Command.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("*.do")
public class Controller extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public Controller() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doGet() ..");
        actionDo(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPost() ..");
        actionDo(request, response);
    }

    private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("actionDo ..");

        request.setCharacterEncoding("UTF-8");

        String viewPage = null;
        Command command = null;

        String url = request.getRequestURI();
        String conPath = request.getContextPath();
        String com = url.substring(conPath.length());

        if(com.equals("/teacher.do")) {
            System.out.println("/teacher.do ..");

            command = new TeacherListCommand();
            command.execute(request, response);
            viewPage = "teacher.jsp";
        }
        else if(com.equals("/member.do")) {
            System.out.println("/member.do ..");

            command = new MemberListCommand();
            command.execute(request, response);
            viewPage = "member.jsp";
        }
        else if(com.equals("/sales.do")) {
            System.out.println("/sales.do ..");

            command = new SalesListCommand();
            command.execute(request, response);
            viewPage = "sales.jsp";
        }
        else if(com.equals("/apply.do")) {
            System.out.println("/apply.do ..");

            command = new ApplyCommand();
            command.execute(request, response);
            viewPage = "insert.jsp";
        }
        else if(com.equals("/insert.do")) {
            System.out.println("/insert.do ..");

            command = new InsertCommand();
            command.execute(request, response);
            viewPage = "index.jsp";
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
        dispatcher.forward(request, response);

    }


}

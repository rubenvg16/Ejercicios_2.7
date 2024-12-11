package org.iesvdm.jsp_servlet_jdbc.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.iesvdm.jsp_servlet_jdbc.dao.SocioDAO;
import org.iesvdm.jsp_servlet_jdbc.dao.SocioDAOImpl;
import org.iesvdm.jsp_servlet_jdbc.model.Socio;

import java.io.IOException;
import java.util.List;

@WebServlet(name= "BorrarSociosServlet", value = "/BorrarSociosServlet")
public class BorrarSociosServlet extends HttpServlet {

    private SocioDAO socioDAO = new SocioDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = null;

        //Recepción del parametro enviado por
        String codigoStr  = request.getParameter("codigo");

        //Valida parámetro
        Integer codigo = null;
        try {
            codigo = Integer.parseInt(codigoStr);
        }catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }

        if (codigo != null) {
            //parámetro válido
            socioDAO.delete(codigo);

            List<Socio> listado = socioDAO.getAll();
            request.setAttribute("listado", listado);

            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/listadoSociosB.jsp");
            dispatcher.forward(request, response);


        }else {
            //parámetro no válido
            response.sendRedirect("ListarSociosServlet");



        }

    }
}
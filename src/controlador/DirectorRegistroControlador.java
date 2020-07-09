package controlador;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DirectorDAO;
import entidad.Director;
import entidad.Grado;
import fabricas.Fabrica;

@WebServlet("/registraDirector")
public class DirectorRegistroControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			// 1 Recibe los parámetros
			// Son los nombres de las caja de textos en el JSP
			String nom = request.getParameter("nombre");
			String fec = request.getParameter("fechaNacimiento");
			String grado = request.getParameter("grado");

			// 2 Se crea el objeto Grado
			Grado objGra = new Grado();
			objGra.setIdGrado(Integer.parseInt(grado));

			Director obj = new Director();
			obj.setNombre(nom);
			obj.setFechaNacimiento(new Date(sdf.parse(fec).getTime()));
			obj.setGrado(objGra);

			Fabrica fabrica = Fabrica.getFabrica(Fabrica.MYSQL);
			DirectorDAO dao = fabrica.getDirectorDAO();

			int s = dao.insertaDirector(obj);
			if (s > 0)
				request.getSession().setAttribute("MENSAJE", "registro exitoso");
			else
				request.getSession().setAttribute("MENSAJE", "registro erróneo");

		} catch (Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("MENSAJE", "registro erróneo");
		} finally {
			response.sendRedirect("registraDirector.jsp");
		}

	}

}

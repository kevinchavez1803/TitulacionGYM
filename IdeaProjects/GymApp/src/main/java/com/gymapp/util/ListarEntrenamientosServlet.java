package com.gymapp.util;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dao.EntrenamientoDAO;
import org.example.model.entrenamientos; // Nombre de clase actualizado

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "ListarEntrenamientosServlet", urlPatterns = {"/listarEntrenamientos"})
public class ListarEntrenamientosServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(ListarEntrenamientosServlet.class.getName());
    private final EntrenamientoDAO entrenamientoDAO = new EntrenamientoDAO(); // DAO para manejar la conexión a la BD

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Configurar el tipo de contenido de la respuesta
        response.setContentType("text/html;charset=UTF-8");

        List<entrenamientos> entrenamientos = null;

        try {
            // Obtener los entrenamientos desde el DAO
            entrenamientos = entrenamientoDAO.findAll();
            if (entrenamientos == null || entrenamientos.isEmpty()) {
                // Caso: no hay entrenamientos disponibles
                request.setAttribute("error", "No se encontraron entrenamientos disponibles.");
            } else {
                // Caso: hay entrenamientos disponibles
                request.setAttribute("entrenamientos", entrenamientos);
                LOGGER.info("Total entrenamientos obtenidos: " + entrenamientos.size());
            }
        } catch (Exception e) {
            // Manejo de errores y registro en logs
            String errorMessage = "[ListarEntrenamientosServlet] Error al obtener entrenamientos: " + e.getMessage();
            LOGGER.log(Level.SEVERE, errorMessage, e);
            request.setAttribute("error", "Ocurrió un error al cargar los entrenamientos. Inténtalo más tarde.");
        }

        // Redirigir al JSP para mostrar resultados
        request.getRequestDispatcher("/listaEntrenamientos.jsp").forward(request, response);
    }
}
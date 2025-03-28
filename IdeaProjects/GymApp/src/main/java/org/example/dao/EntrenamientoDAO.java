package org.example.dao;

import com.gymapp.util.DatabaseConnection;
import org.example.model.entrenamientos;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EntrenamientoDAO {
    private static final String SQL_SELECT_ALL = "SELECT id, nombre, duracion, nivel FROM entrenamientos";

    public List<entrenamientos> findAll() throws SQLException {
        List<entrenamientos> entrenamientos = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_SELECT_ALL);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                entrenamientos entrenamiento = new entrenamientos();
                entrenamiento.setId((long) rs.getInt("id"));
                entrenamiento.setNombre(rs.getString("nombre"));
                entrenamiento.setDuracion(rs.getInt("duracion"));
                entrenamiento.setNivel(rs.getString("nivel"));
                entrenamientos.add(entrenamiento);
            }
        }
        return entrenamientos;
    }

    public void update(entrenamientos entrenamiento) {
    }

    public void delete(Long id) {
    }

    public void create(entrenamientos entrenamiento) {
    }

    public entrenamientos findById(Long id) {

        return null;
    }
}
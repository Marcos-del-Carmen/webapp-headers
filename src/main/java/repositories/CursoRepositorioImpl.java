package repositories;

import models.Curso;
import models.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoRepositorioImpl implements Repository<Curso> {

    private Connection conn;

    public CursoRepositorioImpl(Connection connection) {
        this.conn = connection;
    }
    @Override
    public List<Curso> listar() throws SQLException {

        List<Curso> cursos = new ArrayList<>();

        try(Statement stmt= conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM cursos")) {
            while (rs.next()){
                Curso c = getCurso(rs);
                cursos.add(c);
            }
        }

        return cursos;

    }

    @Override
    public Curso porId(Long id) throws SQLException {

        return null;
    }

    @Override
    public List<Curso> porNombre(String nombre) throws SQLException {

        List<Curso> cursos = new ArrayList<>();

        try (
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM cursos as c WHERE c.nombre LIKE ?")
            ) {
            stmt.setString(1, "%" + nombre + "%");

            try (ResultSet rs = stmt.executeQuery()){
                while (rs.next()) {
                    Curso c = getCurso(rs);
                    cursos.add(c);
                }
            }
        }
        return cursos;
    }

    @Override
    public void guardar(Curso curso) throws SQLException {

    }

    @Override
    public void eliminar(Long id) throws SQLException {

    }

    private static Curso getCurso(ResultSet rs) throws SQLException {
        Curso c = new Curso();
        c.setId(rs.getLong("id"));
        c.setNombre(rs.getString("nombre"));
        c.setDescripcion(rs.getString("descripcion"));
        c.setInstructor(rs.getString("instructor"));
        c.setDuracion(rs.getDouble("duracion"));
        return c;
    }
}

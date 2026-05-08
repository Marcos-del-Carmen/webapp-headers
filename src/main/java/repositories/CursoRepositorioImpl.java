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
        String sql = "SELECT * FROM cursos WHERE id=?";

        Curso curso = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql)){
             stmt.setLong(1, id);

             try (ResultSet rs = stmt.executeQuery()) {
                 if (rs.next()) {
                    curso = getCurso(rs);
                 }
             }
        }

        return curso;
    }

    @Override
    public List<Curso> porNombre(String nombre) throws SQLException {

        List<Curso> cursos = new ArrayList<>();
        String sql = "SELECT * FROM cursos as c WHERE c.nombre LIKE ?";
        try (
                PreparedStatement stmt = conn.prepareStatement(sql)
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

    // actualiza y guarda dependiendo si se revibe el id del curso
    @Override
    public void guardar(Curso curso) throws SQLException {
        String sql = "";

        if(curso.getId() != null && curso.getId() > 0) {
            sql = "UPDATE cursos SET nombre=?, descripcion=?, instructor=?, duracion=? WHERE id=? ";
        } else {
            sql = "INSERT INTO cursos (nombre, descripcion, instructor, duracion) VALUES (?,?,?,?)";
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, curso.getNombre());
            stmt.setString(2, curso.getDescripcion());
            stmt.setString(3, curso.getInstructor());
            stmt.setDouble(4, curso.getDuracion());

            if (curso.getId() != null && curso.getId() > 0) {
                stmt.setLong(5, curso.getId());
            }

            stmt.executeUpdate();
        }

    }


    @Override
    public void eliminar(Long id) throws SQLException {
        String slq = "DELETE FROM cursos WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(slq)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
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

package services;

import models.Curso;
import models.Producto;
import repositories.CursoRepositorioImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CursoServiceJdbcImpl implements CursoService {

    private CursoRepositorioImpl cursoRepositorio;

    public CursoServiceJdbcImpl(Connection connection) {
        this.cursoRepositorio = new CursoRepositorioImpl(connection);
    }
    @Override
    public List<Curso> listar() {

        try {
            return this.cursoRepositorio.listar();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Curso> buscarNombre(String nombre) {
        try {
            return this.cursoRepositorio.porNombre(nombre);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Curso> porId(Long id) {
        try {
            return Optional.ofNullable(this.cursoRepositorio.porId(id));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void guardar(Curso c) {
        try {
            this.cursoRepositorio.guardar(c);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            this.cursoRepositorio.eliminar(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

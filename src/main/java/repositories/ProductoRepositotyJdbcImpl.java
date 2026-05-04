package repositories;

import models.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositotyJdbcImpl implements Repository<Producto> {

    private Connection conn;

    public ProductoRepositotyJdbcImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Producto> listar() throws SQLException {
        List<Producto> productos = new ArrayList<>();

        try(Statement stmt= conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT p.*, c.nombre AS categoria FROM productos as p " +
                    "INNER JOIN categorias AS c ON (p.categoria_id = c.id_categoria)")) {
            while (rs.next()){
                Producto p = getProducto(rs);
                productos.add(p);
            }
        }

        return productos;
    }

    private static Producto getProducto(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        p.setId(rs.getLong("id"));
        p.setNombre(rs.getString("nombre"));
        p.setPrecio(rs.getInt("precio"));
        p.setTipo(rs.getString("categoria"));
        return p;
    }

    @Override
    public Producto porId(Long id) throws SQLException {
        Producto producto = null;

        try(
            PreparedStatement stmt = conn.prepareStatement("SELECT p.*, c.nombre AS categoria FROM productos as p " +
                    "INNER JOIN categorias AS c ON (p.categoria_id = c.id_categoria) WHERE p.categoria_id = ?")) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()){
                if (rs.next()) {
                    producto = getProducto(rs);
                }
            }
        }

        return producto;
    }

    @Override
    public void guardar(Producto producto) throws SQLException {

    }

    @Override
    public void eliminar(Long id) throws SQLException {

    }
}

package services;

import models.Producto;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProductoServicesImp implements ProductService {
    @Override
    public List<Producto> listar() {
        return Arrays.asList(
                new Producto(1L, "Placa Arduino", "Electronico", 159),
                new Producto(2L, "Protoboard", "Electronico", 149),
                new Producto(3L, "Juego de cables de 15 cm", "Electronico", 59),
                new Producto(4L, "Sensor Wi-Fi* detector de agua", "Electronico", 299)
        );
    }

    @Override
    public Optional<Producto> buscar(String nombre) {
        return this.listar().stream().filter(p-> {
            if(nombre ==null || nombre.isEmpty()) {
                return false;
            }
            return p.getNombre().contains(nombre);
        }).findFirst();
    }
}

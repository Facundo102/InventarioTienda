package dao;

import java.util.List;

public interface BaseDAO<T> {
    void agregar(T objeto);
    T obtener(int id);
    List<T> listar();
    void actualizar(T objeto);
    void eliminar(int id);
}
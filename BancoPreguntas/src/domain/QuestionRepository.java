package domain;

import java.util.List;

// Interfaz del repositorio, la implementa la capa access
public interface QuestionRepository {
    List<Question> obtenerTodas();
    Question obtenerPorId(String id);
    void actualizarEstado(String id, String nuevoEstado);
}

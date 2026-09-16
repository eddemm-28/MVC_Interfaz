package domain;

import infra.Observer;
import infra.Subject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// Service de Question. Tiene la logica de negocio y ademas
// hace de Subject en el patron Observer
public class QuestionService implements Subject {

    private QuestionRepository repository;
    private List<Observer> observers = new ArrayList<Observer>();

    public QuestionService(QuestionRepository repository) {
        this.repository = repository;
    }

    public List<Question> listarPreguntas() {
        return repository.obtenerTodas();
    }

    public Question obtenerPregunta(String id) {
        return repository.obtenerPorId(id);
    }

    // cambia el estado y avisa a los observadores
    public void cambiarEstado(String id, String nuevoEstado) {
        repository.actualizarEstado(id, nuevoEstado);
        notifyObservers();
    }

    // cuenta cuantas preguntas hay por cada estado
    public Map<String, Integer> obtenerConteoPorEstado() {
        Map<String, Integer> conteo = new LinkedHashMap<String, Integer>();
        conteo.put(Question.BORRADOR, 0);
        conteo.put(Question.PENDIENTE_REVISION, 0);
        conteo.put(Question.ELIMINADA, 0);

        List<Question> todas = repository.obtenerTodas();
        for (int i = 0; i < todas.size(); i++) {
            Question q = todas.get(i);
            int actual = conteo.get(q.getEstado());
            conteo.put(q.getEstado(), actual + 1);
        }
        return conteo;
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void detach(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).update(this);
        }
    }
}

package infra;

// Interfaz Observer: la implementan las vistas que quieren
// enterarse cuando cambia algo en el Subject
public interface Observer {
    void update(Subject subject);
}

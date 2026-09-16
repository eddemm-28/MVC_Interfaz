package infra;

// Interfaz Subject: la implementa la clase que avisa los cambios
public interface Subject {
    void attach(Observer observer);
    void detach(Observer observer);
    void notifyObservers();
}

package domain;

// Representa una opcion de respuesta (por ejemplo: "A", "Modelar el dominio")
public class QuestionDistractors {

    private String etiqueta;
    private String texto;

    public QuestionDistractors(String etiqueta, String texto) {
        this.etiqueta = etiqueta;
        this.texto = texto;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public String getTexto() {
        return texto;
    }

    public String toString() {
        return etiqueta + ". " + texto;
    }
}

package domain;

import java.util.List;

// Entidad Question: representa una pregunta del banco
public class Question {

    public static final String BORRADOR = "Borrador";
    public static final String PENDIENTE_REVISION = "Pendiente de revisión";
    public static final String ELIMINADA = "Eliminada";

    private String id;
    private String nombre;
    private String materia;
    private String pregunta;
    private List<QuestionDistractors> opciones;
    private String respuestaCorrecta;
    private String estado;

    public Question(String id, String nombre, String materia, String pregunta,
                     List<QuestionDistractors> opciones, String respuestaCorrecta, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.materia = materia;
        this.pregunta = pregunta;
        this.opciones = opciones;
        this.respuestaCorrecta = respuestaCorrecta;
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMateria() {
        return materia;
    }

    public String getPregunta() {
        return pregunta;
    }

    public List<QuestionDistractors> getOpciones() {
        return opciones;
    }

    public String getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    // esto es lo que se ve en el comboBox
    public String toString() {
        return id + " - " + nombre;
    }
}

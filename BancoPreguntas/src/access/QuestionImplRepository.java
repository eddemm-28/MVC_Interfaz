package access;

import domain.Question;
import domain.QuestionDistractors;
import domain.QuestionRepository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// Implementacion simple del repositorio usando un Map en memoria
// (en vez de una base de datos real)
public class QuestionImplRepository implements QuestionRepository {

    private Map<String, Question> preguntas = new LinkedHashMap<String, Question>();

    public QuestionImplRepository() {
        // datos de prueba - una pregunta por cada modulo generico del Saber Pro

        List<QuestionDistractors> op1 = new ArrayList<QuestionDistractors>();
        op1.add(new QuestionDistractors("A", "Porque siempre dice la verdad sobre los hechos"));
        op1.add(new QuestionDistractors("B", "Porque expresa la opinión del autor sobre algo, y puede ser aceptada o rechazada con razones"));
        op1.add(new QuestionDistractors("C", "Porque es un dato estadístico comprobado"));
        op1.add(new QuestionDistractors("D", "Porque nunca aparece en textos argumentativos"));
        preguntas.put("P-001", new Question("P-001", "La idea principal de un texto", "Lectura Crítica",
                "¿Por qué una afirmación de un autor se considera un juicio de valor y no un hecho?",
                op1, "B", Question.BORRADOR));

        List<QuestionDistractors> op2 = new ArrayList<QuestionDistractors>();
        op2.add(new QuestionDistractors("A", "12"));
        op2.add(new QuestionDistractors("B", "15"));
        op2.add(new QuestionDistractors("C", "18"));
        op2.add(new QuestionDistractors("D", "20"));
        preguntas.put("P-002", new Question("P-002", "Promedio de ventas", "Razonamiento Cuantitativo",
                "Una tienda vendió 10, 14 y 18 unidades en tres días. ¿Cuál es el promedio de unidades vendidas por día?",
                op2, "B", Question.PENDIENTE_REVISION));

        List<QuestionDistractors> op3 = new ArrayList<QuestionDistractors>();
        op3.add(new QuestionDistractors("A", "Ignorar la opinión de la minoría porque no tiene mayoría de votos"));
        op3.add(new QuestionDistractors("B", "Imponer la decisión sin discutirla con la comunidad"));
        op3.add(new QuestionDistractors("C", "Buscar un espacio de diálogo donde todas las partes puedan expresar su punto de vista"));
        op3.add(new QuestionDistractors("D", "Evitar el tema para que no haya conflicto"));
        preguntas.put("P-003", new Question("P-003", "Resolución de conflictos", "Competencias Ciudadanas",
                "¿Cuál de las siguientes acciones refleja mejor una resolución pacífica y democrática de un conflicto comunitario?",
                op3, "C", Question.BORRADOR));

        List<QuestionDistractors> op4 = new ArrayList<QuestionDistractors>();
        op4.add(new QuestionDistractors("A", "Un conjunto de ideas sin ningún orden ni conexión"));
        op4.add(new QuestionDistractors("B", "Un texto en el que las ideas se organizan y conectan con coherencia para cumplir un propósito comunicativo"));
        op4.add(new QuestionDistractors("C", "Una lista de palabras clave sin desarrollo"));
        op4.add(new QuestionDistractors("D", "Un resumen literal de otro texto"));
        preguntas.put("P-004", new Question("P-004", "Coherencia textual", "Comunicación Escrita",
                "¿Qué caracteriza a un texto escrito con buena coherencia y cohesión?",
                op4, "B", Question.ELIMINADA));

        List<QuestionDistractors> op5 = new ArrayList<QuestionDistractors>();
        op5.add(new QuestionDistractors("A", "She go to the university every day"));
        op5.add(new QuestionDistractors("B", "She goes to the university every day"));
        op5.add(new QuestionDistractors("C", "She going to the university every day"));
        op5.add(new QuestionDistractors("D", "She gone to the university every day"));
        preguntas.put("P-005", new Question("P-005", "Present simple - tercera persona", "Inglés",
                "Choose the correct sentence:",
                op5, "B", Question.PENDIENTE_REVISION));
    }

    public List<Question> obtenerTodas() {
        return new ArrayList<Question>(preguntas.values());
    }

    public Question obtenerPorId(String id) {
        return preguntas.get(id);
    }

    public void actualizarEstado(String id, String nuevoEstado) {
        Question q = preguntas.get(id);
        if (q != null) {
            q.setEstado(nuevoEstado);
        }
    }
}

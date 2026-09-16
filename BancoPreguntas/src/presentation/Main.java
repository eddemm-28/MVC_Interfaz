package presentation;

import access.QuestionImplRepository;
import domain.QuestionRepository;
import domain.QuestionService;

public class Main {

    public static void main(String[] args) {
        QuestionRepository repository = new QuestionImplRepository();
        QuestionService service = new QuestionService(repository);

        GUIQuestions ventana = new GUIQuestions(service);
        GUIObserver1 vista1 = new GUIObserver1(service);
        GUIObserver2 vista2 = new GUIObserver2(service);

        // se registran las vistas como observadoras del service
        service.attach(vista1);
        service.attach(vista2);

        ventana.setVisible(true);
        vista1.setVisible(true);
        vista2.setVisible(true);
    }
}

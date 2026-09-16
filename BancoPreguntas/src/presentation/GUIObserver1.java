package presentation;

import domain.QuestionService;
import infra.Observer;
import infra.Subject;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

// Vista 1: muestra cuantas preguntas hay por cada estado
public class GUIObserver1 extends JFrame implements Observer {

    private JLabel lblBorrador;
    private JLabel lblPendiente;
    private JLabel lblEliminada;

    public GUIObserver1(QuestionService service) {
        setTitle("Vista de estadisticas");
        setSize(300, 150);
        setLayout(new GridLayout(3, 2));

        add(new JLabel("Borrador:"));
        lblBorrador = new JLabel("0");
        add(lblBorrador);

        add(new JLabel("Pendiente de revision:"));
        lblPendiente = new JLabel("0");
        add(lblPendiente);

        add(new JLabel("Eliminada:"));
        lblEliminada = new JLabel("0");
        add(lblEliminada);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        update(service);
    }

    // el service nos avisa aqui cuando cambia algo
    public void update(Subject subject) {
        QuestionService service = (QuestionService) subject;
        Map<String, Integer> conteo = service.obtenerConteoPorEstado();

        lblBorrador.setText("" + conteo.get("Borrador"));
        lblPendiente.setText("" + conteo.get("Pendiente de revisión"));
        lblEliminada.setText("" + conteo.get("Eliminada"));
    }
}

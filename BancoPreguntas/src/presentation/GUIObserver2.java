package presentation;

import domain.QuestionService;
import infra.Observer;
import infra.Subject;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

// Vista 2: dibuja un pastel con el porcentaje de preguntas por estado
public class GUIObserver2 extends JFrame implements Observer {

    private PanelPastel panel;

    public GUIObserver2(QuestionService service) {
        setTitle("Vista grafica");
        setSize(320, 350);
        panel = new PanelPastel();
        add(panel);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        update(service);
    }

    public void update(Subject subject) {
        QuestionService service = (QuestionService) subject;
        panel.datos = service.obtenerConteoPorEstado();
        panel.repaint();
    }

    // panel que dibuja el pastel a mano con Graphics2D
    class PanelPastel extends JPanel {

        Map<String, Integer> datos = new LinkedHashMap<String, Integer>();
        Color[] colores = { Color.BLUE, Color.ORANGE, Color.RED };

        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            int total = 0;
            for (Integer valor : datos.values()) {
                total = total + valor;
            }
            if (total == 0) {
                g.drawString("Sin datos", 20, 20);
                return;
            }

            int diametro = 200;
            int x = 40;
            int y = 20;
            int anguloInicio = 0;
            int i = 0;
            int leyendaY = y + diametro + 20;

            for (String estado : datos.keySet()) {
                int valor = datos.get(estado);
                int porcentaje = valor * 100 / total;
                int angulo = valor * 360 / total;

                g.setColor(colores[i]);
                g.fillArc(x, y, diametro, diametro, anguloInicio, angulo);

                g.fillRect(20, leyendaY, 10, 10);
                g.setColor(Color.BLACK);
                g.drawString(estado + ": " + porcentaje + "%", 35, leyendaY + 10);

                anguloInicio = anguloInicio + angulo;
                leyendaY = leyendaY + 20;
                i++;
            }
        }
    }
}

package presentation;

import domain.Question;
import domain.QuestionDistractors;
import domain.QuestionService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

// Ventana principal. Aqui se junta la Vista y el Controlador del MVC
public class GUIQuestions extends JFrame {

    private QuestionService service;

    private JComboBox comboPreguntas;
    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtMateria;
    private JTextArea txtPregunta;
    private JTextArea txtOpciones;
    private JLabel lblRespuestaCorrecta;
    private JLabel lblEstadoActual;
    private JComboBox comboNuevoEstado;
    private JButton btnActualizarEstado;

    public GUIQuestions(QuestionService service) {
        this.service = service;

        setTitle("Banco de Preguntas Saber Pro");
        setSize(500, 650);
        setLayout(new BorderLayout());

        // ---- arriba: selector + campos cortos (id, nombre, respuesta, estado) ----
        JPanel panelArriba = new JPanel(new BorderLayout());

        JPanel panelSelector = new JPanel();
        panelSelector.add(new JLabel("Pregunta:"));
        comboPreguntas = new JComboBox();
        panelSelector.add(comboPreguntas);
        panelArriba.add(panelSelector, BorderLayout.NORTH);

        JPanel panelCamposCortos = new JPanel(new GridLayout(5, 2));
        panelCamposCortos.add(new JLabel("Id:"));
        txtId = new JTextField();
        txtId.setEditable(false);
        panelCamposCortos.add(txtId);

        panelCamposCortos.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        txtNombre.setEditable(false);
        panelCamposCortos.add(txtNombre);

        panelCamposCortos.add(new JLabel("Materia:"));
        txtMateria = new JTextField();
        txtMateria.setEditable(false);
        panelCamposCortos.add(txtMateria);

        panelCamposCortos.add(new JLabel("Respuesta correcta:"));
        lblRespuestaCorrecta = new JLabel();
        panelCamposCortos.add(lblRespuestaCorrecta);

        panelCamposCortos.add(new JLabel("Estado actual:"));
        lblEstadoActual = new JLabel();
        panelCamposCortos.add(lblEstadoActual);

        panelArriba.add(panelCamposCortos, BorderLayout.CENTER);
        add(panelArriba, BorderLayout.NORTH);

        // ---- centro: pregunta y opciones, cada una con su propio espacio grande ----
        JPanel panelCentro = new JPanel(new GridLayout(2, 1));

        JPanel panelPregunta = new JPanel(new BorderLayout());
        panelPregunta.setBorder(BorderFactory.createTitledBorder("Pregunta"));
        txtPregunta = new JTextArea();
        txtPregunta.setEditable(false);
        txtPregunta.setLineWrap(true);
        txtPregunta.setWrapStyleWord(true);
        panelPregunta.add(new JScrollPane(txtPregunta), BorderLayout.CENTER);
        panelCentro.add(panelPregunta);

        JPanel panelOpciones = new JPanel(new BorderLayout());
        panelOpciones.setBorder(BorderFactory.createTitledBorder("Opciones"));
        txtOpciones = new JTextArea();
        txtOpciones.setEditable(false);
        txtOpciones.setLineWrap(true);
        txtOpciones.setWrapStyleWord(true);
        panelOpciones.add(new JScrollPane(txtOpciones), BorderLayout.CENTER);
        panelCentro.add(panelOpciones);

        add(panelCentro, BorderLayout.CENTER);

        // ---- abajo: cambiar estado ----
        JPanel panel3 = new JPanel();
        comboNuevoEstado = new JComboBox();
        comboNuevoEstado.addItem(Question.BORRADOR);
        comboNuevoEstado.addItem(Question.PENDIENTE_REVISION);
        comboNuevoEstado.addItem(Question.ELIMINADA);
        btnActualizarEstado = new JButton("Actualizar estado");
        panel3.add(new JLabel("Nuevo estado:"));
        panel3.add(comboNuevoEstado);
        panel3.add(btnActualizarEstado);
        add(panel3, BorderLayout.SOUTH);

        cargarCombo();

        comboPreguntas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarPreguntaSeleccionada();
            }
        });

        btnActualizarEstado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actualizarEstado();
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void cargarCombo() {
        List<Question> preguntas = service.listarPreguntas();
        for (int i = 0; i < preguntas.size(); i++) {
            comboPreguntas.addItem(preguntas.get(i));
        }
        if (comboPreguntas.getItemCount() > 0) {
            comboPreguntas.setSelectedIndex(0);
            mostrarPreguntaSeleccionada();
        }
    }

    private void mostrarPreguntaSeleccionada() {
        Question q = (Question) comboPreguntas.getSelectedItem();
        if (q == null) {
            return;
        }
        txtId.setText(q.getId());
        txtNombre.setText(q.getNombre());
        txtMateria.setText(q.getMateria());
        txtPregunta.setText(q.getPregunta());

        String texto = "";
        List<QuestionDistractors> opciones = q.getOpciones();
        for (int i = 0; i < opciones.size(); i++) {
            texto = texto + opciones.get(i).toString() + "\n";
        }
        txtOpciones.setText(texto);

        lblRespuestaCorrecta.setText(q.getRespuestaCorrecta());
        lblEstadoActual.setText(q.getEstado());
        comboNuevoEstado.setSelectedItem(q.getEstado());
    }

    private void actualizarEstado() {
        Question q = (Question) comboPreguntas.getSelectedItem();
        if (q == null) {
            return;
        }
        String nuevoEstado = (String) comboNuevoEstado.getSelectedItem();
        service.cambiarEstado(q.getId(), nuevoEstado);
        lblEstadoActual.setText(nuevoEstado);
        JOptionPane.showMessageDialog(this, "Estado actualizado a: " + nuevoEstado);
    }
}

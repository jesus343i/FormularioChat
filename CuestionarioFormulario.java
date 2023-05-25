package ChatHilos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CuestionarioFormulario extends JFrame {
    private JLabel imagenLabel;
    private JTextArea areaSentencia;
    private JTextField campoRespuesta;
    private JButton botonEnviar;
    private JTextArea areaResultado;
    private JScrollPane scrollPane;

    private String[] sentencias = {
            "Anne is Paul's: ",
            "Jason and Emily are their: ",
            "Paul is Anne's: ",
            "Emily is Paul's: ",
            "Jason is Emily's: ",
            "Emily is Jason's: ",
            "Paul and Anne are Jason's: "
    };

    private String[] respuestas = {
            "wife", "children", "husband", "daughter", "brother", "sister", "parents"
    };

    private int indicePreguntaActual = 0;

    public CuestionarioFormulario() {
        setTitle("Cuestionario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear el área de la sentencia
        areaSentencia = new JTextArea();
        areaSentencia.setEditable(false);
        areaSentencia.setLineWrap(true);
        areaSentencia.setWrapStyleWord(true);
        areaSentencia.setFont(new Font("Arial", Font.PLAIN, 16));
        scrollPane = new JScrollPane(areaSentencia);
        add(scrollPane, BorderLayout.CENTER);

        // Crear la etiqueta de la imagen
        ImageIcon imagenIcono = new ImageIcon("cuestionario.jpg");
        Image imagen = imagenIcono.getImage().getScaledInstance(263, 333, Image.SCALE_SMOOTH);
        imagenIcono = new ImageIcon(imagen);
        imagenLabel = new JLabel(imagenIcono);
        add(imagenLabel, BorderLayout.NORTH);

        // Crear el campo de respuesta
        campoRespuesta = new JTextField();
        add(campoRespuesta, BorderLayout.SOUTH);

        // Crear el botón de enviar
        botonEnviar = new JButton("Enviar");
        botonEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String respuesta = campoRespuesta.getText().trim();
                verificarRespuesta(respuesta);
                campoRespuesta.setText("");
            }
        });
        add(botonEnviar, BorderLayout.EAST);

        // Crear el área de resultado
        areaResultado = new JTextArea();
        areaResultado.setEditable(false);
        scrollPane = new JScrollPane(areaResultado);
        add(scrollPane, BorderLayout.WEST);

        pack();
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);

        mostrarSiguientePregunta();
    }

    private void verificarRespuesta(String respuesta) {
        if (respuesta.equalsIgnoreCase(respuestas[indicePreguntaActual])) {
            areaResultado.append("¡Respuesta correcta!\n");
        } else {
            areaResultado.append("Incorrecto. La respuesta correcta es: " + respuestas[indicePreguntaActual] + "\n");
        }

        if (indicePreguntaActual < sentencias.length - 1) {
            indicePreguntaActual++;
            mostrarSiguientePregunta();
        } else {
            JOptionPane.showMessageDialog(this, "¡Cuestionario completado!");
            System.exit(0);
        }
    }

    private void mostrarSiguientePregunta() {
        areaSentencia.setText(sentencias[indicePreguntaActual]);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CuestionarioFormulario();
            }
        });
    }
}


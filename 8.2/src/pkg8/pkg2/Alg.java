
package pkg8.pkg2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Alg extends JFrame implements ActionListener {
    
    private List<Double> notas;
    private JTextField[] notaFields;
    private JButton calcularButton;
     private JButton limpiarButton;
    private JLabel promedioLabel;
    private JLabel desviacionLabel;
    private JLabel mayorNotaLabel;
    private JLabel menorNotaLabel;

    public Alg() {
        super("Notas Estudiante");
        setSize(450, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setLayout(new FlowLayout());
        
       
        notas = new ArrayList<>();

        notaFields = new JTextField[5];
        for (int i = 0; i < notaFields.length; i++) {
            notaFields[i] = new JTextField(5);
            add(new JLabel("       \n     Nota " + (i + 1) + ":        "));
            add(notaFields[i]);
        }

        calcularButton = new JButton("Calcular");
        calcularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularNotas();
            }
        });
      add(calcularButton);

        limpiarButton = new JButton("Limpiar");
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarNotas();
            }
        });
        add(limpiarButton);

        promedioLabel = new JLabel();
        add(promedioLabel);

        desviacionLabel = new JLabel();
        add(desviacionLabel);

        mayorNotaLabel = new JLabel();
        add(mayorNotaLabel);

        menorNotaLabel = new JLabel();
        add(menorNotaLabel);

        pack();
        setVisible(true);
    }
            
    private void calcularNotas() {
        notas.clear();

        for (JTextField notaField : notaFields) {
            String notaText = notaField.getText();
            if (notaText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Error: Ingresa todas las notas");
                return;
            }

            try {
                double nota = Double.parseDouble(notaText);
                notas.add(nota);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Error: Ingresa solo números para las notas");
                return;
            }
        }

        if (notas.size() != 5) {
            JOptionPane.showMessageDialog(this, "Error: Ingresa todas las notas");
            return;
        }

        double promedio = calcularPromedio(notas);
        double desviacion = calcularDesviacionEstandar(notas);
        double mayorNota = Collections.max(notas);
        double menorNota = Collections.min(notas);

        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        promedioLabel.setText("Promedio: " + decimalFormat.format(promedio));
        desviacionLabel.setText("Desviación Estándar: " + decimalFormat.format(desviacion));
        mayorNotaLabel.setText("Mayor Nota: " + decimalFormat.format(mayorNota));
        menorNotaLabel.setText("Menor Nota: " + decimalFormat.format(menorNota));
    }

    private double calcularPromedio(List<Double> notas) {
        double suma = 0;
        for (double nota : notas) {
            suma += nota;
        }
        return suma / notas.size();
    }

    private double calcularDesviacionEstandar(List<Double> notas) {
        double promedio = calcularPromedio(notas);
        double sumaDiferenciasCuadrado = 0;

        for (double nota : notas) {
            sumaDiferenciasCuadrado += Math.pow(nota - promedio, 2);
        }

        return Math.sqrt(sumaDiferenciasCuadrado / notas.size());
        
         }

    private void limpiarNotas() {
        for (JTextField notaField : notaFields) {
            notaField.setText("");
        }
        promedioLabel.setText(" ");
        desviacionLabel.setText(" ");
        mayorNotaLabel.setText(" ");
        menorNotaLabel.setText(" ");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Alg();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    
}

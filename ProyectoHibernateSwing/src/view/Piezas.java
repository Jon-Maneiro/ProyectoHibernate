/*
 * Created by JFormDesigner on Mon Dec 12 18:38:25 CET 2022
 */

package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author Jon Maneiro García
 */
public class Piezas extends JFrame {
    public Piezas() {
        initComponents();
    }

    public void iniciarEnPestaña(boolean elegir){
        JTabbedPane tb = tabbedPane1;
        if(elegir == true){//Consultar
            tb.setSelectedIndex(0);
        }else{//gestionar
            tb.setSelectedIndex(1);
        }
    }
    private void cerrarVentana(ActionEvent e) {
        this.dispose();
    }

    private void vaciarCampos(ActionEvent e) {
        JTextField tfCod = this.tfGCodPiez;
        JTextField tfNom = this.tfGNombre;
        JTextField tfPre = this.tfGPrecio;
        JTextField tfDes = this.tfGDescripcion;

        tfCod.setText("");
        tfNom.setText("");
        tfPre.setText("");
        tfDes.setText("");
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        tabbedPane1 = new JTabbedPane();
        panel2 = new JPanel();
        scrollPane1 = new JScrollPane();
        tfCNombre = new JTextField();
        label6 = new JLabel();
        label5 = new JLabel();
        tfCCodigo = new JTextField();
        btnFiltrar = new JButton();
        btnVolver = new JButton();
        panel3 = new JPanel();
        tfGDescripcion = new JTextField();
        label4 = new JLabel();
        label3 = new JLabel();
        tfGPrecio = new JTextField();
        tfGNombre = new JTextField();
        label2 = new JLabel();
        label1 = new JLabel();
        tfGCodPiez = new JTextField();
        btnLimpiar = new JButton();
        btnInsertar = new JButton();
        btnModificar = new JButton();
        btnEliminar = new JButton();
        btnVolver2 = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== tabbedPane1 ========
        {

            //======== panel2 ========
            {
                panel2.setLayout(null);
                panel2.add(scrollPane1);
                scrollPane1.setBounds(0, 5, 600, 190);
                panel2.add(tfCNombre);
                tfCNombre.setBounds(65, 235, 440, 30);

                //---- label6 ----
                label6.setText("Nombre");
                panel2.add(label6);
                label6.setBounds(0, 240, 44, 16);

                //---- label5 ----
                label5.setText("Codigo");
                panel2.add(label5);
                label5.setBounds(0, 205, 39, 16);
                panel2.add(tfCCodigo);
                tfCCodigo.setBounds(65, 200, 130, 30);

                //---- btnFiltrar ----
                btnFiltrar.setText("Filtrar");
                panel2.add(btnFiltrar);
                btnFiltrar.setBounds(0, 280, 78, 30);

                //---- btnVolver ----
                btnVolver.setText("Volver");
                btnVolver.addActionListener(e -> cerrarVentana(e));
                panel2.add(btnVolver);
                btnVolver.setBounds(new Rectangle(new Point(520, 320), btnVolver.getPreferredSize()));

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < panel2.getComponentCount(); i++) {
                        Rectangle bounds = panel2.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = panel2.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    panel2.setMinimumSize(preferredSize);
                    panel2.setPreferredSize(preferredSize);
                }
            }
            tabbedPane1.addTab("Consultar Piezas", panel2);

            //======== panel3 ========
            {
                panel3.setLayout(null);
                panel3.add(tfGDescripcion);
                tfGDescripcion.setBounds(120, 95, 440, tfGDescripcion.getPreferredSize().height);

                //---- label4 ----
                label4.setText("Descripcion");
                panel3.add(label4);
                label4.setBounds(new Rectangle(new Point(5, 100), label4.getPreferredSize()));

                //---- label3 ----
                label3.setText("Precio");
                panel3.add(label3);
                label3.setBounds(new Rectangle(new Point(5, 70), label3.getPreferredSize()));
                panel3.add(tfGPrecio);
                tfGPrecio.setBounds(120, 65, 440, tfGPrecio.getPreferredSize().height);
                panel3.add(tfGNombre);
                tfGNombre.setBounds(120, 35, 440, tfGNombre.getPreferredSize().height);

                //---- label2 ----
                label2.setText("Nombre");
                panel3.add(label2);
                label2.setBounds(new Rectangle(new Point(5, 40), label2.getPreferredSize()));

                //---- label1 ----
                label1.setText("Codigo Pieza");
                panel3.add(label1);
                label1.setBounds(new Rectangle(new Point(5, 10), label1.getPreferredSize()));
                panel3.add(tfGCodPiez);
                tfGCodPiez.setBounds(120, 5, 110, tfGCodPiez.getPreferredSize().height);

                //---- btnLimpiar ----
                btnLimpiar.setText("Limpiar");
                btnLimpiar.addActionListener(e -> vaciarCampos(e));
                panel3.add(btnLimpiar);
                btnLimpiar.setBounds(120, 250, 78, 30);

                //---- btnInsertar ----
                btnInsertar.setText("Insertar");
                panel3.add(btnInsertar);
                btnInsertar.setBounds(220, 250, 78, 30);

                //---- btnModificar ----
                btnModificar.setText("Modificar");
                panel3.add(btnModificar);
                btnModificar.setBounds(320, 250, 85, 30);

                //---- btnEliminar ----
                btnEliminar.setText("Eliminar");
                panel3.add(btnEliminar);
                btnEliminar.setBounds(420, 250, 78, 30);

                //---- btnVolver2 ----
                btnVolver2.setText("Volver");
                btnVolver2.addActionListener(e -> cerrarVentana(e));
                panel3.add(btnVolver2);
                btnVolver2.setBounds(new Rectangle(new Point(520, 320), btnVolver2.getPreferredSize()));

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < panel3.getComponentCount(); i++) {
                        Rectangle bounds = panel3.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = panel3.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    panel3.setMinimumSize(preferredSize);
                    panel3.setPreferredSize(preferredSize);
                }
            }
            tabbedPane1.addTab("Gestion Piezas", panel3);
        }
        contentPane.add(tabbedPane1);
        tabbedPane1.setBounds(15, 15, 605, 390);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JTabbedPane tabbedPane1;
    private JPanel panel2;
    private JScrollPane scrollPane1;
    private JTextField tfCNombre;
    private JLabel label6;
    private JLabel label5;
    private JTextField tfCCodigo;
    private JButton btnFiltrar;
    private JButton btnVolver;
    private JPanel panel3;
    private JTextField tfGDescripcion;
    private JLabel label4;
    private JLabel label3;
    private JTextField tfGPrecio;
    private JTextField tfGNombre;
    private JLabel label2;
    private JLabel label1;
    private JTextField tfGCodPiez;
    private JButton btnLimpiar;
    private JButton btnInsertar;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnVolver2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}

/*
 * Created by JFormDesigner on Mon Dec 12 18:48:18 CET 2022
 */

package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author Jon Maneiro García
 */
public class Proyectos extends JFrame {
    public Proyectos() {
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
        JTextField tfCod = this.tfGCodProy;
        JTextField tfNom = this.tfGNombre;
        JTextField tfCiu = this.tfGCiudad;

        tfCod.setText("");
        tfNom.setText("");
        tfCiu.setText("");
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        tabbedPane1 = new JTabbedPane();
        panel2 = new JPanel();
        scrollPane1 = new JScrollPane();
        label5 = new JLabel();
        label6 = new JLabel();
        label7 = new JLabel();
        tfCCodigo = new JTextField();
        tfCNombre = new JTextField();
        tfCCiudad = new JTextField();
        btnFiltrar = new JButton();
        btnVolver2 = new JButton();
        panel1 = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        tfGCodProy = new JTextField();
        tfGNombre = new JTextField();
        tfGCiudad = new JTextField();
        btnLimpiar = new JButton();
        btnInsertar = new JButton();
        btnModificar = new JButton();
        btnEliminar = new JButton();
        btnVolver = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(null);

            //======== contentPanel ========
            {
                contentPanel.setLayout(null);

                //======== tabbedPane1 ========
                {

                    //======== panel2 ========
                    {
                        panel2.setLayout(null);
                        panel2.add(scrollPane1);
                        scrollPane1.setBounds(5, 5, 600, 190);

                        //---- label5 ----
                        label5.setText("Codigo");
                        panel2.add(label5);
                        label5.setBounds(new Rectangle(new Point(10, 210), label5.getPreferredSize()));

                        //---- label6 ----
                        label6.setText("Nombre");
                        panel2.add(label6);
                        label6.setBounds(new Rectangle(new Point(10, 245), label6.getPreferredSize()));

                        //---- label7 ----
                        label7.setText("Ciudad");
                        panel2.add(label7);
                        label7.setBounds(new Rectangle(new Point(10, 280), label7.getPreferredSize()));
                        panel2.add(tfCCodigo);
                        tfCCodigo.setBounds(75, 205, 130, tfCCodigo.getPreferredSize().height);
                        panel2.add(tfCNombre);
                        tfCNombre.setBounds(75, 240, 440, 30);
                        panel2.add(tfCCiudad);
                        tfCCiudad.setBounds(75, 275, 440, 30);

                        //---- btnFiltrar ----
                        btnFiltrar.setText("Filtrar");
                        panel2.add(btnFiltrar);
                        btnFiltrar.setBounds(new Rectangle(new Point(10, 325), btnFiltrar.getPreferredSize()));

                        //---- btnVolver2 ----
                        btnVolver2.setText("Volver");
                        btnVolver2.addActionListener(e -> cerrarVentana(e));
                        panel2.add(btnVolver2);
                        btnVolver2.setBounds(new Rectangle(new Point(525, 325), btnVolver2.getPreferredSize()));

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
                    tabbedPane1.addTab("Consultar Proyectos", panel2);

                    //======== panel1 ========
                    {
                        panel1.setLayout(null);

                        //---- label1 ----
                        label1.setText("Codigo Proyecto");
                        panel1.add(label1);
                        label1.setBounds(new Rectangle(new Point(10, 15), label1.getPreferredSize()));

                        //---- label2 ----
                        label2.setText("Nombre");
                        panel1.add(label2);
                        label2.setBounds(new Rectangle(new Point(10, 45), label2.getPreferredSize()));

                        //---- label3 ----
                        label3.setText("Ciudad");
                        panel1.add(label3);
                        label3.setBounds(new Rectangle(new Point(10, 75), label3.getPreferredSize()));
                        panel1.add(tfGCodProy);
                        tfGCodProy.setBounds(125, 10, 110, tfGCodProy.getPreferredSize().height);
                        panel1.add(tfGNombre);
                        tfGNombre.setBounds(125, 40, 440, 30);
                        panel1.add(tfGCiudad);
                        tfGCiudad.setBounds(125, 70, 440, 30);

                        //---- btnLimpiar ----
                        btnLimpiar.setText("Limpiar");
                        btnLimpiar.addActionListener(e -> vaciarCampos(e));
                        panel1.add(btnLimpiar);
                        btnLimpiar.setBounds(new Rectangle(new Point(125, 250), btnLimpiar.getPreferredSize()));

                        //---- btnInsertar ----
                        btnInsertar.setText("Insertar");
                        panel1.add(btnInsertar);
                        btnInsertar.setBounds(new Rectangle(new Point(225, 250), btnInsertar.getPreferredSize()));

                        //---- btnModificar ----
                        btnModificar.setText("Modificar");
                        panel1.add(btnModificar);
                        btnModificar.setBounds(new Rectangle(new Point(325, 250), btnModificar.getPreferredSize()));

                        //---- btnEliminar ----
                        btnEliminar.setText("Eliminar");
                        panel1.add(btnEliminar);
                        btnEliminar.setBounds(new Rectangle(new Point(425, 250), btnEliminar.getPreferredSize()));

                        //---- btnVolver ----
                        btnVolver.setText("Volver");
                        btnVolver.addActionListener(e -> cerrarVentana(e));
                        panel1.add(btnVolver);
                        btnVolver.setBounds(new Rectangle(new Point(525, 325), btnVolver.getPreferredSize()));

                        {
                            // compute preferred size
                            Dimension preferredSize = new Dimension();
                            for(int i = 0; i < panel1.getComponentCount(); i++) {
                                Rectangle bounds = panel1.getComponent(i).getBounds();
                                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                            }
                            Insets insets = panel1.getInsets();
                            preferredSize.width += insets.right;
                            preferredSize.height += insets.bottom;
                            panel1.setMinimumSize(preferredSize);
                            panel1.setPreferredSize(preferredSize);
                        }
                    }
                    tabbedPane1.addTab("Gestion Proyectos", panel1);
                }
                contentPanel.add(tabbedPane1);
                tabbedPane1.setBounds(0, 0, 610, 395);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < contentPanel.getComponentCount(); i++) {
                        Rectangle bounds = contentPanel.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = contentPanel.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    contentPanel.setMinimumSize(preferredSize);
                    contentPanel.setPreferredSize(preferredSize);
                }
            }
            dialogPane.add(contentPanel);
            contentPanel.setBounds(12, 12, 609, contentPanel.getPreferredSize().height);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < dialogPane.getComponentCount(); i++) {
                    Rectangle bounds = dialogPane.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = dialogPane.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                dialogPane.setMinimumSize(preferredSize);
                dialogPane.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(dialogPane);
        dialogPane.setBounds(new Rectangle(new Point(0, 0), dialogPane.getPreferredSize()));

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
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JTabbedPane tabbedPane1;
    private JPanel panel2;
    private JScrollPane scrollPane1;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JTextField tfCCodigo;
    private JTextField tfCNombre;
    private JTextField tfCCiudad;
    private JButton btnFiltrar;
    private JButton btnVolver2;
    private JPanel panel1;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JTextField tfGCodProy;
    private JTextField tfGNombre;
    private JTextField tfGCiudad;
    private JButton btnLimpiar;
    private JButton btnInsertar;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnVolver;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}

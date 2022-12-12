/*
 * Created by JFormDesigner on Mon Dec 12 19:32:25 CET 2022
 */

package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author unknown
 */
public class PPP extends JFrame {
    public PPP() {
        initComponents();
    }

    private void cerrarVentana(ActionEvent e) {
        this.dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        btnVolver2 = new JButton();
        label1 = new JLabel();
        label2 = new JLabel();
        cbCodProv = new JComboBox();
        tfNomProv = new JTextField();
        tfDirProv = new JTextField();
        label3 = new JLabel();
        cbCodPiez = new JComboBox();
        tfNomPiez = new JTextField();
        tfPrecioPiez = new JTextField();
        label4 = new JLabel();
        cbCodProy = new JComboBox();
        tfNomProy = new JTextField();
        tfCiuProy = new JTextField();
        tfCantidad = new JTextField();
        label5 = new JLabel();
        btnInsertar = new JButton();
        btnModificar = new JButton();
        btnBorrar = new JButton();
        btnListado = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- btnVolver2 ----
        btnVolver2.setText("Volver");
        btnVolver2.addActionListener(e -> cerrarVentana(e));
        contentPane.add(btnVolver2);
        btnVolver2.setBounds(new Rectangle(new Point(540, 300), btnVolver2.getPreferredSize()));

        //---- label1 ----
        label1.setText("RELACIONES PIEZAS - PROYECTOS - PROVEEDORES");
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(10, 60), label1.getPreferredSize()));

        //---- label2 ----
        label2.setText("Proveedor");
        contentPane.add(label2);
        label2.setBounds(new Rectangle(new Point(10, 90), label2.getPreferredSize()));

        //---- cbCodProv ----
        cbCodProv.setToolTipText("Elige un Codigo");
        contentPane.add(cbCodProv);
        cbCodProv.setBounds(new Rectangle(new Point(85, 85), cbCodProv.getPreferredSize()));

        //---- tfNomProv ----
        tfNomProv.setEditable(false);
        contentPane.add(tfNomProv);
        tfNomProv.setBounds(180, 85, 440, tfNomProv.getPreferredSize().height);

        //---- tfDirProv ----
        tfDirProv.setEditable(false);
        contentPane.add(tfDirProv);
        tfDirProv.setBounds(180, 115, 440, 30);

        //---- label3 ----
        label3.setText("Pieza");
        contentPane.add(label3);
        label3.setBounds(10, 160, 54, 16);

        //---- cbCodPiez ----
        cbCodPiez.setToolTipText("Elige un Codigo");
        contentPane.add(cbCodPiez);
        cbCodPiez.setBounds(85, 155, 79, 30);

        //---- tfNomPiez ----
        tfNomPiez.setEditable(false);
        contentPane.add(tfNomPiez);
        tfNomPiez.setBounds(180, 155, 440, 30);

        //---- tfPrecioPiez ----
        tfPrecioPiez.setEditable(false);
        contentPane.add(tfPrecioPiez);
        tfPrecioPiez.setBounds(180, 185, 440, 30);

        //---- label4 ----
        label4.setText("Proyecto");
        contentPane.add(label4);
        label4.setBounds(10, 230, 54, 16);

        //---- cbCodProy ----
        cbCodProy.setToolTipText("Elige un Codigo");
        contentPane.add(cbCodProy);
        cbCodProy.setBounds(85, 225, 79, 30);

        //---- tfNomProy ----
        tfNomProy.setEditable(false);
        contentPane.add(tfNomProy);
        tfNomProy.setBounds(180, 225, 440, 30);

        //---- tfCiuProy ----
        tfCiuProy.setEditable(false);
        contentPane.add(tfCiuProy);
        tfCiuProy.setBounds(180, 255, 440, 30);
        contentPane.add(tfCantidad);
        tfCantidad.setBounds(85, 295, 95, tfCantidad.getPreferredSize().height);

        //---- label5 ----
        label5.setText("Cantidad");
        contentPane.add(label5);
        label5.setBounds(new Rectangle(new Point(10, 300), label5.getPreferredSize()));

        //---- btnInsertar ----
        btnInsertar.setText("Insertar");
        contentPane.add(btnInsertar);
        btnInsertar.setBounds(new Rectangle(new Point(10, 20), btnInsertar.getPreferredSize()));

        //---- btnModificar ----
        btnModificar.setText("Modificar");
        contentPane.add(btnModificar);
        btnModificar.setBounds(95, 20, 78, 30);

        //---- btnBorrar ----
        btnBorrar.setText("Borrar");
        contentPane.add(btnBorrar);
        btnBorrar.setBounds(180, 20, 78, 30);

        //---- btnListado ----
        btnListado.setText("Listado");
        contentPane.add(btnListado);
        btnListado.setBounds(265, 20, 78, 30);

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
    private JButton btnVolver2;
    private JLabel label1;
    private JLabel label2;
    private JComboBox cbCodProv;
    private JTextField tfNomProv;
    private JTextField tfDirProv;
    private JLabel label3;
    private JComboBox cbCodPiez;
    private JTextField tfNomPiez;
    private JTextField tfPrecioPiez;
    private JLabel label4;
    private JComboBox cbCodProy;
    private JTextField tfNomProy;
    private JTextField tfCiuProy;
    private JTextField tfCantidad;
    private JLabel label5;
    private JButton btnInsertar;
    private JButton btnModificar;
    private JButton btnBorrar;
    private JButton btnListado;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}

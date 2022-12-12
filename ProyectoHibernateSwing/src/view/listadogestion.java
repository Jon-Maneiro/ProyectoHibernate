/*
 * Created by JFormDesigner on Mon Dec 12 19:44:17 CET 2022
 */

package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author unknown
 */
public class listadogestion extends JFrame {
    public listadogestion() {
        initComponents();
    }

    private void cerrarVentana(ActionEvent e) {
        this.dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        btnVolver = new JButton();
        scrollPane1 = new JScrollPane();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- btnVolver ----
        btnVolver.setText("Volver");
        btnVolver.addActionListener(e -> cerrarVentana(e));
        contentPane.add(btnVolver);
        btnVolver.setBounds(new Rectangle(new Point(545, 380), btnVolver.getPreferredSize()));
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(5, 5, 620, 375);

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
    private JButton btnVolver;
    private JScrollPane scrollPane1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}

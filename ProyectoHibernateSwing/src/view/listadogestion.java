/*
 * Created by JFormDesigner on Mon Dec 12 19:44:17 CET 2022
 */

package view;

import com.company.GestionEntity;
import org.hibernate.Session;
import util.HibernateUtil;
import org.hibernate.query.Query;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;

/**
 * @author Jon Maneiro García
 */
public class listadogestion extends JFrame {
    public listadogestion() {
        initComponents();
        cargarTablaGestiones();
    }

    /**
     * Cierra la ventana actual
     * @param e
     */
    private void cerrarVentana(ActionEvent e) {
        this.dispose();
    }

    /**
     * Carga los datos de la tabla Gestiones/ GestionesEntity
     */
    private void cargarTablaGestiones(){
        JTextArea ta = taConsultas;
        ta.setText("");

        String hql = "from GestionEntity";

        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Query query = sesion.createQuery(hql);
        List<GestionEntity> resultado = query.list();
        sesion.close();

        for(GestionEntity g: resultado){
            ta.append(""+g.getCodProveedor()+" "+g.getCodPieza()+" "+g.getCodProyecto()+" "+g.getCantidad()+"\n");
        }

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        btnVolver = new JButton();
        scrollPane1 = new JScrollPane();
        taConsultas = new JTextArea();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- btnVolver ----
        btnVolver.setText("Volver");
        btnVolver.addActionListener(e -> cerrarVentana(e));
        contentPane.add(btnVolver);
        btnVolver.setBounds(new Rectangle(new Point(545, 380), btnVolver.getPreferredSize()));

        //======== scrollPane1 ========
        {

            //---- taConsultas ----
            taConsultas.setEditable(false);
            scrollPane1.setViewportView(taConsultas);
        }
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
    private JTextArea taConsultas;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}

package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import net.miginfocom.swing.*;
/*
 * Created by JFormDesigner on Mon Nov 28 19:00:59 CET 2022
 */



/**
 * @author Jon Maneiro García
 */
public class Inicio extends JFrame {
    public Inicio() {
        initComponents();
    }

    private void abrirInfo(ActionEvent e) {
        Informacion info = new Informacion();
        info.setVisible(true);
    }

    private void abrirConsultarProveedores(ActionEvent e) {
        Proveedores prov = new Proveedores();
        prov.iniciarEnPestaña(true);
        prov.setVisible(true);
    }
    private void abrirGestionProveedores(ActionEvent e) {
        Proveedores prov = new Proveedores();
        prov.iniciarEnPestaña(false);
        prov.setVisible(true);
    }
    private void abrirConsultarPiezas(ActionEvent e) {
        Piezas piez = new Piezas();
        piez.iniciarEnPestaña(true);
        piez.setVisible(true);
    }
    private void abrirGestionarPiezas(ActionEvent e) {
        Piezas piez = new Piezas();
        piez.iniciarEnPestaña(false);
        piez.setVisible(true);
    }

    private void abrirConsultarProyectos(ActionEvent e) {
        Proyectos proy = new Proyectos();
        proy.iniciarEnPestaña(true);
        proy.setVisible(true);
    }

    private void abrirGestionProyectos(ActionEvent e) {
        Proyectos proy = new Proyectos();
        proy.iniciarEnPestaña(false);
        proy.setVisible(true);
    }

    private void irAPPP(ActionEvent e) {
        PPP ppp = new PPP();
        ppp.setVisible(true);
    }







    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        menuBar1 = new JMenuBar();
        meBBDD = new JMenu();
        meProveedores = new JMenu();
        miGestProv = new JMenuItem();
        miConsProv = new JMenuItem();
        mePiezas = new JMenu();
        miGestPiez = new JMenuItem();
        miConsPiez = new JMenuItem();
        meProyectos = new JMenu();
        miGestProy = new JMenuItem();
        miConsProy = new JMenuItem();
        meGestion = new JMenu();
        miProvPieProy = new JMenuItem();
        miSumProv = new JMenuItem();
        miSumPie = new JMenuItem();
        miStats = new JMenuItem();
        meAyuda = new JMenu();
        miInfo = new JMenuItem();
        label1 = new JLabel();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== menuBar1 ========
        {

            //======== meBBDD ========
            {
                meBBDD.setText("Base de Datos");
            }
            menuBar1.add(meBBDD);

            //======== meProveedores ========
            {
                meProveedores.setText("Proveedores");

                //---- miGestProv ----
                miGestProv.setText("Gestionar Proveedores");
                miGestProv.addActionListener(e -> abrirGestionProveedores(e));
                meProveedores.add(miGestProv);

                //---- miConsProv ----
                miConsProv.setText("Consultar Proveedores");
                miConsProv.addActionListener(e -> abrirConsultarProveedores(e));
                meProveedores.add(miConsProv);
            }
            menuBar1.add(meProveedores);

            //======== mePiezas ========
            {
                mePiezas.setText("Piezas");

                //---- miGestPiez ----
                miGestPiez.setText("Gestionar Piezas");
                miGestPiez.addActionListener(e -> abrirGestionarPiezas(e));
                mePiezas.add(miGestPiez);

                //---- miConsPiez ----
                miConsPiez.setText("Consultar Piezas");
                miConsPiez.addActionListener(e -> abrirConsultarPiezas(e));
                mePiezas.add(miConsPiez);
            }
            menuBar1.add(mePiezas);

            //======== meProyectos ========
            {
                meProyectos.setText("Proyectos");

                //---- miGestProy ----
                miGestProy.setText("Gestionar Proyectos");
                miGestProy.addActionListener(e -> abrirGestionProyectos(e));
                meProyectos.add(miGestProy);

                //---- miConsProy ----
                miConsProy.setText("Consultar Proyectos");
                miConsProy.addActionListener(e -> abrirConsultarProyectos(e));
                meProyectos.add(miConsProy);
            }
            menuBar1.add(meProyectos);

            //======== meGestion ========
            {
                meGestion.setText("Gestion");

                //---- miProvPieProy ----
                miProvPieProy.setText("Proveedores,Piezas y Proyectos");
                miProvPieProy.addActionListener(e -> irAPPP(e));
                meGestion.add(miProvPieProy);

                //---- miSumProv ----
                miSumProv.setText("Suministros por Proveedor");
                meGestion.add(miSumProv);

                //---- miSumPie ----
                miSumPie.setText("Suministros por Piezas");
                meGestion.add(miSumPie);

                //---- miStats ----
                miStats.setText("Estadisticas");
                meGestion.add(miStats);
            }
            menuBar1.add(meGestion);

            //======== meAyuda ========
            {
                meAyuda.setText("Ayuda");

                //---- miInfo ----
                miInfo.setText("Informacion");
                miInfo.addActionListener(e -> abrirInfo(e));
                meAyuda.add(miInfo);
            }
            menuBar1.add(meAyuda);
        }
        setJMenuBar(menuBar1);

        //---- label1 ----
        label1.setIcon(new ImageIcon("\\Recursos\\Logo.png"));
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(85, 25), label1.getPreferredSize()));

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
    private JMenuBar menuBar1;
    private JMenu meBBDD;
    private JMenu meProveedores;
    private JMenuItem miGestProv;
    private JMenuItem miConsProv;
    private JMenu mePiezas;
    private JMenuItem miGestPiez;
    private JMenuItem miConsPiez;
    private JMenu meProyectos;
    private JMenuItem miGestProy;
    private JMenuItem miConsProy;
    private JMenu meGestion;
    private JMenuItem miProvPieProy;
    private JMenuItem miSumProv;
    private JMenuItem miSumPie;
    private JMenuItem miStats;
    private JMenu meAyuda;
    private JMenuItem miInfo;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}

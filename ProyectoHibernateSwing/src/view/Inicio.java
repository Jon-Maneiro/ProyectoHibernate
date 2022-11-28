package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import net.miginfocom.swing.*;
/*
 * Created by JFormDesigner on Mon Nov 28 19:00:59 CET 2022
 */



/**
 * @author unknown
 */
public class Inicio extends JFrame {
    public Inicio() {
        initComponents();
    }

    private void abrirInfo(ActionEvent e) {
        Informacion info = new Informacion();
        info.setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        menuBar1 = new JMenuBar();
        meBBDD = new JMenu();
        meProveedores = new JMenu();
        memeGestProv = new JMenu();
        miGPInsert = new JMenuItem();
        miGPModif = new JMenuItem();
        miGPDelete = new JMenuItem();
        miConsProv = new JMenuItem();
        mePiezas = new JMenu();
        memeGestPiez = new JMenu();
        miGPiInsert = new JMenuItem();
        miGPiModif = new JMenuItem();
        miGPiDelete = new JMenuItem();
        miConsPiez = new JMenuItem();
        meProyectos = new JMenu();
        memeGestProy = new JMenu();
        miGPrInsert = new JMenuItem();
        miGPrModif = new JMenuItem();
        miGPrDelete = new JMenuItem();
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

                //======== memeGestProv ========
                {
                    memeGestProv.setText("Gestion Proveedores");

                    //---- miGPInsert ----
                    miGPInsert.setText("Insertar");
                    memeGestProv.add(miGPInsert);

                    //---- miGPModif ----
                    miGPModif.setText("Modificar");
                    memeGestProv.add(miGPModif);

                    //---- miGPDelete ----
                    miGPDelete.setText("Borrar");
                    memeGestProv.add(miGPDelete);
                }
                meProveedores.add(memeGestProv);

                //---- miConsProv ----
                miConsProv.setText("Consultar Proveedores");
                meProveedores.add(miConsProv);
            }
            menuBar1.add(meProveedores);

            //======== mePiezas ========
            {
                mePiezas.setText("Piezas");

                //======== memeGestPiez ========
                {
                    memeGestPiez.setText("Gestion Piezas");

                    //---- miGPiInsert ----
                    miGPiInsert.setText("Insertar");
                    memeGestPiez.add(miGPiInsert);

                    //---- miGPiModif ----
                    miGPiModif.setText("Modificar");
                    memeGestPiez.add(miGPiModif);

                    //---- miGPiDelete ----
                    miGPiDelete.setText("Borrar");
                    memeGestPiez.add(miGPiDelete);
                }
                mePiezas.add(memeGestPiez);

                //---- miConsPiez ----
                miConsPiez.setText("Consultar Piezas");
                mePiezas.add(miConsPiez);
            }
            menuBar1.add(mePiezas);

            //======== meProyectos ========
            {
                meProyectos.setText("Proyectos");

                //======== memeGestProy ========
                {
                    memeGestProy.setText("Gestionar Proyectos");

                    //---- miGPrInsert ----
                    miGPrInsert.setText("Insertar");
                    memeGestProy.add(miGPrInsert);

                    //---- miGPrModif ----
                    miGPrModif.setText("Modificar");
                    memeGestProy.add(miGPrModif);

                    //---- miGPrDelete ----
                    miGPrDelete.setText("Borrar");
                    memeGestProy.add(miGPrDelete);
                }
                meProyectos.add(memeGestProy);

                //---- miConsProy ----
                miConsProy.setText("Consultar Proyectos");
                meProyectos.add(miConsProy);
            }
            menuBar1.add(meProyectos);

            //======== meGestion ========
            {
                meGestion.setText("Gestion");

                //---- miProvPieProy ----
                miProvPieProy.setText("Proveedores,Piezas y Proyectos");
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
        label1.setIcon(new ImageIcon("C:\\Users\\9FDAM08\\Desktop\\ProyectoHibernate\\ProyectoHibernate\\Recursos\\Logo.png"));
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
    private JMenu memeGestProv;
    private JMenuItem miGPInsert;
    private JMenuItem miGPModif;
    private JMenuItem miGPDelete;
    private JMenuItem miConsProv;
    private JMenu mePiezas;
    private JMenu memeGestPiez;
    private JMenuItem miGPiInsert;
    private JMenuItem miGPiModif;
    private JMenuItem miGPiDelete;
    private JMenuItem miConsPiez;
    private JMenu meProyectos;
    private JMenu memeGestProy;
    private JMenuItem miGPrInsert;
    private JMenuItem miGPrModif;
    private JMenuItem miGPrDelete;
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

/*
 * Created by JFormDesigner on Mon Dec 12 19:32:25 CET 2022
 */

package view;

import com.company.*;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;

/**
 * @author Jon Maneiro García
 */
public class PPP extends JFrame {
    public PPP() {
        initComponents();
        cargarDatosEnComboBox();
    }

    private void cerrarVentana(ActionEvent e) {
        this.dispose();
    }

    private void cargarDatosEnComboBox(){
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        String hql = "from ProveedoresEntity ";
        Query query = sesion.createQuery(hql);
        List<ProveedoresEntity> proveedores = query.list();

        hql = "from PiezasEntity ";
        query = sesion.createQuery(hql);
        List<PiezasEntity> piezas = query.list();

        hql = "from ProyectosEntity ";
        query = sesion.createQuery(hql);
        List<ProyectosEntity> proyectos = query.list();

        JComboBox cbProv = cbCodProv;
        JComboBox cbPie = cbCodPiez;
        JComboBox cbProy = cbCodProy;


        cbProv.addItem("Elige");
        for(ProveedoresEntity p:proveedores){
            cbProv.addItem(p.getCodigo());
        }
        cbPie.addItem("Elige");
        for(PiezasEntity p:piezas){
            cbPie.addItem(p.getCodigo());
        }
        cbProy.addItem("Elige");
        for(ProyectosEntity p:proyectos){
            cbProy.addItem(p.getCodigo());
        }
        sesion.close();
    }

    private boolean hasDataCantidad(){
        JTextField x = tfCantidad;
        if(x.getText().isBlank()){
            return false;
        }else{
            if(util.funcionesComunes.isDouble(x.getText())) {
                return true;
            }else{
                JOptionPane.showMessageDialog(this, "La cantidad debe ser un numero","Error" , JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
    }

    private boolean checkCbCodProv(){
        JComboBox c = cbCodProv;
        if(c.getSelectedIndex() == 0){
            return false;
        }else{
            return true;
        }
    }

    private boolean checkCbCodPiez(){
        JComboBox c = cbCodPiez;
        if(c.getSelectedIndex() == 0){
            return false;
        }else{
            return true;
        }
    }

    private boolean checkCbCodProy(){
        JComboBox c = cbCodProy;
        if(c.getSelectedIndex() == 0){
            return false;
        }else{
            return true;
        }
    }

    private void cargarProveedor(ActionEvent e) {
        if(checkCbCodProv()) {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            try {
                ProveedoresEntity p = (ProveedoresEntity) sesion.load(ProveedoresEntity.class, cbCodProv.getSelectedItem().toString());
                tfNomProv.setText(p.getNombre());
                tfDirProv.setText(p.getDireccion());
                sesion.close();
                cargarRelacion();
            } catch (ObjectNotFoundException o) {
                JOptionPane.showMessageDialog(this, "Ha ocurrido un error y no puede recuperarse el Proveedor", "Error", JOptionPane.ERROR_MESSAGE);

            }
        }
    }

    private void cargarPieza(ActionEvent e) {
        if(checkCbCodPiez()) {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            try {
                PiezasEntity p = (PiezasEntity) sesion.load(PiezasEntity.class, cbCodPiez.getSelectedItem().toString());
                tfNomPiez.setText(p.getNombre());
                tfPrecioPiez.setText(p.getPrecio() + "");
                sesion.close();
                cargarRelacion();
            } catch (ObjectNotFoundException o) {
                JOptionPane.showMessageDialog(this, "Ha ocurrido un error y no puede recuperarse la Pieza", "Error", JOptionPane.ERROR_MESSAGE);

            }
        }
    }

    private void cargarProyecto(ActionEvent e) {
        if(checkCbCodProy()) {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            try {
                ProyectosEntity p = (ProyectosEntity) sesion.load(ProyectosEntity.class, cbCodProy.getSelectedItem().toString());
                tfNomProy.setText(p.getNombre());
                tfCiuProy.setText(p.getCiudad());
                sesion.close();
                cargarRelacion();
            } catch (ObjectNotFoundException o) {
                JOptionPane.showMessageDialog(this, "Ha ocurrido un error y no puede recuperarse el Proyecto", "Error", JOptionPane.ERROR_MESSAGE);

            }
        }
    }

    private void insertarRelacion(ActionEvent e) {
        if(checkCbCodProv() && checkCbCodPiez() && checkCbCodProy() && hasDataCantidad()){
            String codProv = cbCodProv.getSelectedItem().toString();
            String codPiez = cbCodPiez.getSelectedItem().toString();
            String codProy = cbCodProy.getSelectedItem().toString();
            Double cantidad = Double.parseDouble(tfCantidad.getText());
            //Empezamos la insercion
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();

            GestionEntity gest = new GestionEntity();
            gest.setCodProveedor(codProv);
            gest.setCodPieza(codPiez);
            gest.setCodProyecto(codProy);
            gest.setCantidad(cantidad);

            sesion.save(gest);

            sesion.getTransaction().commit();

            sesion.close();
            JOptionPane.showMessageDialog(this, "Se ha introducido la relacion","Insercion" , JOptionPane.INFORMATION_MESSAGE);

        }else{
            JOptionPane.showMessageDialog(this, "Es necesario seleccionar todos los ID y escribir una cantidad","Error" , JOptionPane.ERROR_MESSAGE);
        }
    }

    private void modificarRelacion(ActionEvent e) {
        if(checkCbCodProv() && checkCbCodPiez() && checkCbCodProy() && hasDataCantidad()){
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            try{
                GestionEntity gest;
                GestionEntityPK gestPK = new GestionEntityPK();
                gestPK.setCodProveedor(cbCodProv.getSelectedItem().toString());
                gestPK.setCodPieza(cbCodPiez.getSelectedItem().toString());
                gestPK.setCodProyecto(cbCodProy.getSelectedItem().toString());
                gest = (GestionEntity) sesion.load(GestionEntity.class,gestPK);

                gest.setCantidad(Double.parseDouble(tfCantidad.getText()));

                sesion.update(gest);
                sesion.getTransaction().commit();

                sesion.close();
                JOptionPane.showMessageDialog(this, "Se ha modificado la relacion","Modificacion" , JOptionPane.INFORMATION_MESSAGE);


            }catch(ObjectNotFoundException o){
                JOptionPane.showMessageDialog(this, "No puede accederse al objeto","Error" , JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(this, "Es necesario seleccionar todos los ID y escribir una cantidad","Error" , JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarRelacion(){
        if(checkCbCodProv() && checkCbCodPiez() && checkCbCodProy()){
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();

            try{
                GestionEntity gest;
                GestionEntityPK gestPK = new GestionEntityPK();
                gestPK.setCodProveedor(cbCodProv.getSelectedItem().toString());
                gestPK.setCodPieza(cbCodPiez.getSelectedItem().toString());
                gestPK.setCodProyecto(cbCodProy.getSelectedItem().toString());

                gest = (GestionEntity) sesion.load(GestionEntity.class, gestPK);

                ProveedoresEntity prov =(ProveedoresEntity) sesion.load(ProveedoresEntity.class, gest.getCodProveedor());
                PiezasEntity pie = (PiezasEntity) sesion.load(PiezasEntity.class, gest.getCodPieza());
                ProyectosEntity proy = (ProyectosEntity) sesion.load(ProyectosEntity.class, gest.getCodProyecto());

                sesion.close();
                tfCantidad.setText(gest.getCantidad() +"");
                tfNomProv.setText(prov.getNombre());
                tfDirProv.setText(prov.getDireccion());
                tfNomPiez.setText(pie.getNombre());
                tfPrecioPiez.setText(pie.getPrecio()+"");
                tfNomProy.setText(proy.getNombre());
                tfCiuProy.setText(proy.getCiudad());

            }catch(ObjectNotFoundException o){
                System.out.println("Cosita de testeo");
            }
        }
    }

    private void eliminarRelacion(ActionEvent e) {
        if(checkCbCodProv() && checkCbCodPiez() && checkCbCodProy()){
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            try{
                GestionEntity gest;
                GestionEntityPK gestPK = new GestionEntityPK();
                gestPK.setCodProveedor(cbCodProv.getSelectedItem().toString());
                gestPK.setCodPieza(cbCodPiez.getSelectedItem().toString());
                gestPK.setCodProyecto(cbCodProy.getSelectedItem().toString());
                gest = (GestionEntity) sesion.load(GestionEntity.class,gestPK);


                sesion.delete(gest);
                sesion.getTransaction().commit();

                sesion.close();
                JOptionPane.showMessageDialog(this, "Se ha eliminado la relacion","Eliminacion" , JOptionPane.INFORMATION_MESSAGE);


            }catch(ObjectNotFoundException o){
                JOptionPane.showMessageDialog(this, "No puede accederse al objeto","Error" , JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(this, "Es necesario seleccionar todos los ID","Error" , JOptionPane.ERROR_MESSAGE);
        }
    }

    private void listarGestiones(ActionEvent e) {
        listadogestion x = new listadogestion();
        x.setVisible(true);
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
        cbCodProv.addActionListener(e -> cargarProveedor(e));
        contentPane.add(cbCodProv);
        cbCodProv.setBounds(85, 85, 95, cbCodProv.getPreferredSize().height);

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
        cbCodPiez.addActionListener(e -> cargarPieza(e));
        contentPane.add(cbCodPiez);
        cbCodPiez.setBounds(85, 155, 95, 30);

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
        cbCodProy.addActionListener(e -> cargarProyecto(e));
        contentPane.add(cbCodProy);
        cbCodProy.setBounds(85, 225, 95, 30);

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
        btnInsertar.addActionListener(e -> insertarRelacion(e));
        contentPane.add(btnInsertar);
        btnInsertar.setBounds(10, 20, 90, btnInsertar.getPreferredSize().height);

        //---- btnModificar ----
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(e -> modificarRelacion(e));
        contentPane.add(btnModificar);
        btnModificar.setBounds(105, 20, 95, 30);

        //---- btnBorrar ----
        btnBorrar.setText("Borrar");
        btnBorrar.addActionListener(e -> eliminarRelacion(e));
        contentPane.add(btnBorrar);
        btnBorrar.setBounds(205, 20, 90, 30);

        //---- btnListado ----
        btnListado.setText("Listado");
        btnListado.addActionListener(e -> listarGestiones(e));
        contentPane.add(btnListado);
        btnListado.setBounds(300, 20, 90, 30);

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

/*
 * Created by JFormDesigner on Mon Dec 12 18:38:25 CET 2022
 */

package view;

import com.company.ProveedoresEntity;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;
import util.HibernateUtil;
import com.company.PiezasEntity;
import org.hibernate.Session;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import javax.swing.*;

/**
 * @author Jon Maneiro García
 */
public class Piezas extends JFrame {

    private static List<PiezasEntity> listaPiezas;
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

    private void insertarPieza(ActionEvent e) {
        if(checkCodeField() && checkNameField() && checkPrizeField() && checkDescriptionField()){
            turnCodeGUpp();
            String codigo = tfGCodPiez.getText();
            String nombre = tfGNombre.getText();
            String precio = tfGPrecio.getText();
            String desc = tfGDescripcion.getText();

            //Empezamos la insercion
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();

            PiezasEntity piez = new PiezasEntity();
            piez.setCodigo(codigo);
            piez.setNombre(nombre);
            piez.setPrecio(Double.parseDouble(precio));
            piez.setDescripcion(desc);

            sesion.save(piez);

            sesion.getTransaction().commit();
            sesion.close();
            JOptionPane.showMessageDialog(this, "La pieza ha sido insertada", "Insercion", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(this, "Los datos introducidos no son correctos","Error" , JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updatePieza(ActionEvent e) {
        if(checkCodeField()&&(checkNameField() || checkPrizeField() || checkDescriptionField())){
            turnCodeGUpp();
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            try {
                PiezasEntity pe = (PiezasEntity) sesion.load(PiezasEntity.class, tfGCodPiez.getText());

                if(checkNameField()){
                    pe.setNombre(tfGNombre.getText());
                }
                if(checkPrizeField()){
                    pe.setPrecio(Double.parseDouble(tfGPrecio.getText()));
                }
                if(checkDescriptionField()){
                    pe.setDescripcion(tfGDescripcion.getText());
                }

                sesion.update(pe);
                sesion.getTransaction().commit();

                sesion.close();
                JOptionPane.showMessageDialog(this, "La pieza ha sido actualizada","Actualizacion" , JOptionPane.INFORMATION_MESSAGE);

            }catch(ObjectNotFoundException o){
                JOptionPane.showMessageDialog(this, "Esa pieza no existe en la BBDD","Error" , JOptionPane.ERROR_MESSAGE);
            }catch(NumberFormatException n){
                JOptionPane.showMessageDialog(this, "El campo Precio debe ser numerico","Error" , JOptionPane.ERROR_MESSAGE);

            }
        }else{
            JOptionPane.showMessageDialog(this, "Para poder modificar un objeto, se necesita el codigo y minimo otro campo a editar","Error" , JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarPieza(ActionEvent e) {
        if(checkCodeField()){
            turnCodeGUpp();
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            try{
                PiezasEntity pe = (PiezasEntity) sesion.load(PiezasEntity.class , tfGCodPiez.getText());

                sesion.delete(pe);
                sesion.getTransaction().commit();

                sesion.close();
                JOptionPane.showMessageDialog(this, "La pieza ha sido eliminada","Eliminacion" , JOptionPane.INFORMATION_MESSAGE);

            }catch(ObjectNotFoundException o){
                JOptionPane.showMessageDialog(this, "Esa pieza no existe en la BBDD","Error" , JOptionPane.ERROR_MESSAGE);
            }catch(ConstraintViolationException c){
                JOptionPane.showMessageDialog(this, "La pieza que deseas eliminar tiene relaciones, elimina primero esas relaciones","Error" , JOptionPane.ERROR_MESSAGE);
            }

        }else{
            JOptionPane.showMessageDialog(this, "Para poder eliminar un objeto hace falta su codigo)","Error" , JOptionPane.ERROR_MESSAGE);

        }
    }
    private void eliminarBusquedas(ActionEvent e) {
        int reply = JOptionPane.showConfirmDialog(null, "¿Vas a proceder con la eliminacion de todos los objetos de la busqueda?\n Los objetos que tengan relaciones no se borrarán", "ELIMINACION MASIVA", JOptionPane.YES_NO_OPTION);
        int contador = 0;
        if (reply == JOptionPane.YES_OPTION) {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            for(PiezasEntity p: listaPiezas){
                try{
                    sesion.delete(p);
                }catch(ConstraintViolationException c){
                    contador++;
                }
            }

            sesion.getTransaction().commit();//Esto podría ponerse dentro del bucle si da algun tipo de error
            sesion.close();
            JOptionPane.showMessageDialog(null, "Eliminacion Completada.\n Han quedado "+contador+" piezas sin eliminar de la consulta");
        } else {
            JOptionPane.showMessageDialog(null, "No se ha borrado nada");
        }
    }
    private void buscarPiezas(ActionEvent e) {
        JTextArea ta = this.taTextoConsulta;
        //Se vacia el textArea para no liar datos
        ta.setText("");
        //Primero hay que mirar que campos tienen datos, y si esos datos son correctos
        String codigo = "";
        String nombre = "";
        //select new list(codigo,nombre,precio,descripcion)
        String hql = "from PiezasEntity ";
        if(hasDataCodeCField()){
            turnCodeCUpp();
            if(tfCCodigo.getText().length() > 6){
                JOptionPane.showMessageDialog(this, "El Codigo no puede tener mas de 6 caracteres","Error" , JOptionPane.ERROR_MESSAGE);
            }else{
                codigo = tfCCodigo.getText();
            }
        }

        if(hasDataNameCField()){
            if(tfCNombre.getText().length() > 20){
                JOptionPane.showMessageDialog(this, "El Nombre no puede tener mas de 20 caracteres","Error" , JOptionPane.ERROR_MESSAGE);
            }else{
                nombre = tfCNombre.getText();
            }
        }

        Session sesion = HibernateUtil.getSessionFactory().openSession();

        if(!codigo.isBlank() || !nombre.isBlank()){

            hql = hql + " where ";

            if(!codigo.isBlank()){
                hql = hql + "codigo = '"+codigo+"' ";
            }
            if(!nombre.isBlank()){
                hql = hql + "nombre like '%"+nombre+"%' ";
            }

        }

        Query query = sesion.createQuery(hql);
        List<PiezasEntity> resultado = query.list();
        sesion.close();
        listaPiezas = resultado;

        for(PiezasEntity p: resultado){
            ta.append(""+p.getCodigo()+" "+p.getNombre()+" "+p.getPrecio()+" "+p.getDescripcion()+"\n");
        }
    }
    /*
    *
    *
    *
    * HAY QUE COMPROBAR QUE LOS DATOS TENGAN SENTIDO
    *
    *
    * */

    private boolean hasDataCodeCField(){
        JTextField tfCod = this.tfCCodigo;
        if(tfCod.getText().isBlank()){
            return false;
        }else{
            return true;
        }
    }

    private boolean hasDataNameCField(){
        JTextField tfName = this.tfCNombre;
        if(tfName.getText().isBlank()){
            return false;
        }else{
            return true;
        }
    }

    private void turnCodeGUpp(){
        tfGCodPiez.setText(tfGCodPiez.getText().toUpperCase());
    }
    private void turnCodeCUpp(){
        tfCCodigo.setText(tfCCodigo.getText().toUpperCase());
    }

    private boolean checkCodeField(){
        JTextField tfCod = this.tfGCodPiez;
        if(tfCod.getText().isBlank()){
            return false;
        }else{
            return true;
        }
    }

    private boolean checkNameField(){
        JTextField tfNom = this.tfGNombre;
        if(tfNom.getText().isBlank()){
            return false;
        }else{
            return true;
        }
    }

    private boolean checkPrizeField(){
        JTextField tfPre = this.tfGPrecio;
        if(tfPre.getText().isBlank()){
            return false;
        }else{
            if(util.funcionesComunes.isDouble(tfPre.getText())){
                return true;
            }else {
                JOptionPane.showMessageDialog(this, "El dato introducido en \"Precio\" no es correcto","Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
    }

    private boolean checkDescriptionField(){
        JTextField tfDes = this.tfGDescripcion;
        if(tfDes.getText().isBlank()){
            return false;
        }else{
            return true;
        }

    }








    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        tabbedPane1 = new JTabbedPane();
        panel2 = new JPanel();
        scrollPane1 = new JScrollPane();
        taTextoConsulta = new JTextArea();
        tfCNombre = new JTextField();
        label6 = new JLabel();
        label5 = new JLabel();
        tfCCodigo = new JTextField();
        btnFiltrar = new JButton();
        btnVolver = new JButton();
        btnBorrarC = new JButton();
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

                //======== scrollPane1 ========
                {

                    //---- taTextoConsulta ----
                    taTextoConsulta.setEditable(false);
                    scrollPane1.setViewportView(taTextoConsulta);
                }
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
                btnFiltrar.addActionListener(e -> buscarPiezas(e));
                panel2.add(btnFiltrar);
                btnFiltrar.setBounds(0, 280, 78, 30);

                //---- btnVolver ----
                btnVolver.setText("Volver");
                btnVolver.addActionListener(e -> cerrarVentana(e));
                panel2.add(btnVolver);
                btnVolver.setBounds(new Rectangle(new Point(520, 320), btnVolver.getPreferredSize()));

                //---- btnBorrarC ----
                btnBorrarC.setText("Borrar Todo");
                btnBorrarC.addActionListener(e -> eliminarBusquedas(e));
                panel2.add(btnBorrarC);
                btnBorrarC.setBounds(new Rectangle(new Point(255, 320), btnBorrarC.getPreferredSize()));

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
                btnInsertar.addActionListener(e -> insertarPieza(e));
                panel3.add(btnInsertar);
                btnInsertar.setBounds(220, 250, 78, 30);

                //---- btnModificar ----
                btnModificar.setText("Modificar");
                btnModificar.addActionListener(e -> updatePieza(e));
                panel3.add(btnModificar);
                btnModificar.setBounds(320, 250, 85, 30);

                //---- btnEliminar ----
                btnEliminar.setText("Eliminar");
                btnEliminar.addActionListener(e -> eliminarPieza(e));
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
    private JTextArea taTextoConsulta;
    private JTextField tfCNombre;
    private JLabel label6;
    private JLabel label5;
    private JTextField tfCCodigo;
    private JButton btnFiltrar;
    private JButton btnVolver;
    private JButton btnBorrarC;
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

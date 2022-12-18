/*
 * Created by JFormDesigner on Mon Dec 12 18:48:18 CET 2022
 */

package view;

import com.company.PiezasEntity;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;
import util.HibernateUtil;
import com.company.ProyectosEntity;
import org.hibernate.Session;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author Jon Maneiro García
 */
public class Proyectos extends JFrame {

    private static List<ProyectosEntity> listaProyectos;
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

    private void insertarProyecto(ActionEvent e) {
        if(checkCodeField() && checkNameField() && checkCityField()){
            turnCodeGUpp();
            String codigo = tfGCodProy.getText();
            String nombre = tfGNombre.getText();
            String ciudad = tfGCiudad.getText();

            //Empezamos la insercion
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();

            ProyectosEntity proy = new ProyectosEntity();
            proy.setCodigo(codigo);
            proy.setNombre(nombre);
            proy.setCiudad(ciudad);

            sesion.save(proy);

            sesion.getTransaction().commit();
            sesion.close();
            JOptionPane.showMessageDialog(this, "El Proyecto ha sido insertado","Insercion" , JOptionPane.INFORMATION_MESSAGE);

        }else{
            JOptionPane.showMessageDialog(this, "Los datos introducidos no son correctos","Error" , JOptionPane.ERROR_MESSAGE);
        }
    }


    private void updateProyecto(ActionEvent e) {
        if(checkCodeField() && (checkNameField() || checkCityField())){
           turnCodeGUpp();
           Session sesion = HibernateUtil.getSessionFactory().openSession();
           sesion.beginTransaction();
           try{
               ProyectosEntity po = (ProyectosEntity) sesion.load(ProyectosEntity.class , tfGCodProy.getText());

               if(checkNameField()){
                   po.setNombre(tfGNombre.getText());
               }
               if(checkCityField()){
                   po.setNombre(tfGNombre.getText());
               }

               sesion.update(po);
               sesion.getTransaction().commit();

               sesion.close();
               JOptionPane.showMessageDialog(this, "El Proyecto ha sido actualizado","Actualizacion" , JOptionPane.INFORMATION_MESSAGE);

           }catch(ObjectNotFoundException x){
               JOptionPane.showMessageDialog(this, "Ese proyecto no existe en la base de datos","Error" , JOptionPane.ERROR_MESSAGE);
           }

        }else{
            JOptionPane.showMessageDialog(this, "Para poder modificar un Proyecto, se necesita el codigo y otro de los campos como minimo","Error" , JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteProyecto(ActionEvent e) {
        if(checkCodeField()){
            turnCodeGUpp();
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            try{
                ProyectosEntity po = (ProyectosEntity) sesion.load(ProyectosEntity.class, tfGCodProy.getText());

                sesion.delete(po);
                sesion.getTransaction().commit();

                sesion.close();
                JOptionPane.showMessageDialog(this, "El Proyecto ha sido eliminado","Eliminacion" , JOptionPane.INFORMATION_MESSAGE);
            }catch(ObjectNotFoundException o){
                JOptionPane.showMessageDialog(this, "Ese Proyecto no existe en la BBDD","Error" , JOptionPane.ERROR_MESSAGE);
            }catch(ConstraintViolationException c){
                JOptionPane.showMessageDialog(this, "El Proyecto que deseas eliminar tiene relaciones, elimina primero esas relaciones","Error" , JOptionPane.ERROR_MESSAGE);
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
            for(ProyectosEntity p: listaProyectos){
                try{
                    sesion.delete(p);
                }catch(ConstraintViolationException c){
                    contador++;
                }
            }

            sesion.getTransaction().commit();//Esto podría ponerse dentro del bucle si da algun tipo de error
            sesion.close();
            JOptionPane.showMessageDialog(null, "Eliminacion Completada.\n Han quedado "+contador+" proyectos sin eliminar de la consulta");
        } else {
            JOptionPane.showMessageDialog(null, "No se ha borrado nada");
        }
    }

    private void buscarProyectos(ActionEvent e) {
        JTextArea ta = this.tatextoConsulta;
        //Se vacia el textArea para no liar datos
        ta.setText("");
        //Se comprueba que los campos tengan datos, y si esos datos cumplen las restricciones
        String codigo = "";
        String nombre = "";
        String ciudad = "";
        //select new list(codigo,nombre,ciudad)
        String hql = "from ProyectosEntity";
        if(hasDataCodeCField()){
            turnCodeCUpp();
            if(tfCCodigo.getText().length() > 6){
                JOptionPane.showMessageDialog(this, "El Codigo no puede tener mas de 6 caracteres","Error" , JOptionPane.ERROR_MESSAGE);
            }else{
                codigo = tfCCodigo.getText();
            }
        }
        if(hasDataNameCField()){
            if(tfCNombre.getText().length() > 40){
                JOptionPane.showMessageDialog(this, "El Nombre no puede tener mas de 40 caracteres","Error" , JOptionPane.ERROR_MESSAGE);
            }else{
                nombre = tfGNombre.getText();
            }
        }
        if(hasDataCityCField()){
            if(tfCCiudad.getText().length() > 40){
                JOptionPane.showMessageDialog(this, "La Ciudad no puede tener mas de 40 caracteres","Error" , JOptionPane.ERROR_MESSAGE);
            }else{
                ciudad = tfCCiudad.getText();
            }
        }

        Session sesion = HibernateUtil.getSessionFactory().openSession();

        if(!codigo.isBlank() || !nombre.isBlank() || !ciudad.isBlank()){
            hql = hql + " where ";
            if(!codigo.isBlank()){
                hql = hql + "codigo = '"+codigo+"' ";
            }
            if(!nombre.isBlank()){
                hql = hql + "nombre like '%"+nombre+"%' ";
            }
            if(!ciudad.isBlank()){
                hql = hql + "ciudad like '%"+ciudad+"%' ";
            }
        }

        Query query = sesion.createQuery(hql);
        List<ProyectosEntity> resultado = query.list();
        listaProyectos = resultado;

        sesion.close();

        for(ProyectosEntity p: resultado){
            ta.append(""+p.getCodigo()+" "+p.getNombre()+" "+p.getCiudad()+"\n");
        }

    }




    /*
    *
    *
    *
    * HAY QUE HACER COMPROBACION DE QUE LOS DATOS ESTAN BIEN INTRODUCIDOS
    *
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

    private boolean hasDataCityCField(){
        JTextField tfCity = this.tfCCiudad;
        if(tfCity.getText().isBlank()){
            return false;
        }else{
            return true;
        }
    }

    private void turnCodeGUpp(){
        tfGCodProy.setText(tfGCodProy.getText().toUpperCase());
    }
    private void turnCodeCUpp(){
        tfCCodigo.setText(tfCCodigo.getText().toUpperCase());
    }

    private boolean checkCodeField(){
        JTextField tfCod = this.tfGCodProy;
        if(tfCod.getText().isBlank()){
            return false;
        }else {
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

    private boolean checkCityField(){
        JTextField tfCiu = this.tfGCiudad;
        if(tfCiu.getText().isBlank()){
            return false;
        }else{
            return true;
        }
    }









    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        tabbedPane1 = new JTabbedPane();
        panel2 = new JPanel();
        scrollPane1 = new JScrollPane();
        tatextoConsulta = new JTextArea();
        label5 = new JLabel();
        label6 = new JLabel();
        label7 = new JLabel();
        tfCCodigo = new JTextField();
        tfCNombre = new JTextField();
        tfCCiudad = new JTextField();
        btnFiltrar = new JButton();
        btnVolver2 = new JButton();
        btnBorrarC = new JButton();
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

                        //======== scrollPane1 ========
                        {

                            //---- tatextoConsulta ----
                            tatextoConsulta.setEditable(false);
                            scrollPane1.setViewportView(tatextoConsulta);
                        }
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
                        btnFiltrar.addActionListener(e -> buscarProyectos(e));
                        panel2.add(btnFiltrar);
                        btnFiltrar.setBounds(new Rectangle(new Point(10, 325), btnFiltrar.getPreferredSize()));

                        //---- btnVolver2 ----
                        btnVolver2.setText("Volver");
                        btnVolver2.addActionListener(e -> cerrarVentana(e));
                        panel2.add(btnVolver2);
                        btnVolver2.setBounds(new Rectangle(new Point(525, 325), btnVolver2.getPreferredSize()));

                        //---- btnBorrarC ----
                        btnBorrarC.setText("Borrar Todo");
                        btnBorrarC.addActionListener(e -> eliminarBusquedas(e));
                        panel2.add(btnBorrarC);
                        btnBorrarC.setBounds(new Rectangle(new Point(255, 330), btnBorrarC.getPreferredSize()));

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
                        btnInsertar.addActionListener(e -> insertarProyecto(e));
                        panel1.add(btnInsertar);
                        btnInsertar.setBounds(new Rectangle(new Point(225, 250), btnInsertar.getPreferredSize()));

                        //---- btnModificar ----
                        btnModificar.setText("Modificar");
                        btnModificar.addActionListener(e -> updateProyecto(e));
                        panel1.add(btnModificar);
                        btnModificar.setBounds(new Rectangle(new Point(325, 250), btnModificar.getPreferredSize()));

                        //---- btnEliminar ----
                        btnEliminar.setText("Eliminar");
                        btnEliminar.addActionListener(e -> deleteProyecto(e));
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
    private JTextArea tatextoConsulta;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JTextField tfCCodigo;
    private JTextField tfCNombre;
    private JTextField tfCCiudad;
    private JButton btnFiltrar;
    private JButton btnVolver2;
    private JButton btnBorrarC;
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

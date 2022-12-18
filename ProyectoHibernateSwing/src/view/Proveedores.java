/*
 * Created by JFormDesigner on Mon Nov 28 20:37:59 CET 2022
 */

package view;

import com.company.PiezasEntity;
import com.company.ProyectosEntity;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;
import util.HibernateUtil;
import com.company.ProveedoresEntity;
import org.hibernate.Session;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author Jon Maneiro García
 */
public class Proveedores extends JFrame {

    private static List<ProveedoresEntity> listaProveedores;

    public Proveedores() {
        initComponents();
    }

    /**
     * Inicia la ventana en una de las dos pestañas disponibles
     * @param elegir
     */
    public void iniciarEnPestaña(boolean elegir){
        JTabbedPane tb = tabbedPane1;
        if(elegir == true){//Consultar
            tb.setSelectedIndex(0);
        }else{//gestionar
            tb.setSelectedIndex(1);
        }
    }

    /**
     * Cierra la ventana actual
     * @param e
     */
    private void cerrarVentana(ActionEvent e) {
        this.dispose();
    }

    /**
     * Vacía los campos de la pantalla
     * @param e
     */
    private void vaciarCampos(ActionEvent e) {
        JTextField tfCod = this.tfGCodProv;
        JTextField tfNom = this.tfGNombre;
        JTextField tfApe = this.tfGApellidos;
        JTextField tfDir = this.tfGDireccion;

        tfCod.setText("");
        tfNom.setText("");
        tfApe.setText("");
        tfDir.setText("");
    }

    /**
     * Inserta un proveedor en BBDD según los datos introducidos
     * @param e
     */
    private void insertarProveedor(ActionEvent e) {
        if(checkCodeField() && checkNameField() && checkLastNameField() && checkAddressField()){
            turnCodeGUpp();
            String codigo = tfGCodProv.getText();
            String nombre = tfGNombre.getText();
            String apellido = tfGApellidos.getText();
            String direccion = tfGDireccion.getText();
            //Empezamos la insercion
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();

            ProveedoresEntity prov = new ProveedoresEntity();
            prov.setCodigo(codigo);
            prov.setNombre(nombre);
            prov.setApellidos(apellido);
            prov.setDireccion(direccion);

            sesion.save(prov);

            sesion.getTransaction().commit();
            sesion.close();
            JOptionPane.showMessageDialog(this, "El Proveedor ha sido insertado","Insercion" , JOptionPane.INFORMATION_MESSAGE);

        }else{
            JOptionPane.showMessageDialog(this, "Los datos introducidos no son correctos","Error" , JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * Ejecuta el Update de un proveedor, por código, basado en los datos introducidos
     * @param e
     */
    private void updateProveedor(ActionEvent e) {
        if(checkCodeField() && (checkNameField() || checkLastNameField() || checkAddressField())){
            turnCodeGUpp();
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            try{
                ProveedoresEntity pr = (ProveedoresEntity) sesion.load(ProveedoresEntity.class, tfGCodProv.getText());

                if(checkNameField()){
                    pr.setNombre(tfGNombre.getText());
                }
                if(checkLastNameField()){
                    pr.setApellidos(tfGApellidos.getText());
                }
                if(checkAddressField()){
                    pr.setDireccion(tfGDireccion.getText());
                }

                sesion.update(pr);
                sesion.getTransaction().commit();

                sesion.close();
                JOptionPane.showMessageDialog(this, "El Proveedor ha sido actualizado","Actualizacion" , JOptionPane.INFORMATION_MESSAGE);

            }catch(ObjectNotFoundException o){
                JOptionPane.showMessageDialog(this, "Ese proveedor no existe en la BBDD","Error" , JOptionPane.ERROR_MESSAGE);

            }
        }else{
            JOptionPane.showMessageDialog(this, "Para poder modificar un objeto, se necesita el codigo y minimo otro campo a editar","Error" , JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * Elimina un proveedor, basandose en el codigo introducido
     * @param e
     */
    private void deleteProveedor(ActionEvent e) {
        if(checkCodeField()){
            turnCodeGUpp();
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            try{

                ProveedoresEntity pr = (ProveedoresEntity) sesion.load(ProveedoresEntity.class,tfGCodProv.getText());

                sesion.delete(pr);
                sesion.getTransaction().commit();

                sesion.close();
                JOptionPane.showMessageDialog(this, "El Proveedor ha sido eliminado","Eliminacion" , JOptionPane.INFORMATION_MESSAGE);
            }catch(ObjectNotFoundException o){
                JOptionPane.showMessageDialog(this, "Ese Proveedor no existe en la BBDD","Error" , JOptionPane.ERROR_MESSAGE);
            }catch(ConstraintViolationException c){
                JOptionPane.showMessageDialog(this, "El Proveedor que deseas eliminar tiene relaciones, elimina primero esas relaciones","Error" , JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(this, "Para poder eliminar un objeto hace falta su codigo)","Error" , JOptionPane.ERROR_MESSAGE);

        }
    }
    /**
     * Elimina todos los resultados que aparecen en pantalla, a excepcion de aquellos que tengan una relacion
     * @param e
     */
    private void eliminarBusquedas(ActionEvent e) {
        int reply = JOptionPane.showConfirmDialog(null, "¿Vas a proceder con la eliminacion de todos los objetos de la busqueda?\n Los objetos que tengan relaciones no se borrarán", "ELIMINACION MASIVA", JOptionPane.YES_NO_OPTION);
        int contador = 0;
        if (reply == JOptionPane.YES_OPTION) {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            for(ProveedoresEntity p: listaProveedores){
                try{
                    sesion.delete(p);
                }catch(ConstraintViolationException c){
                    contador++;
                }
            }

            sesion.getTransaction().commit();//Esto podría ponerse dentro del bucle si da algun tipo de error
            sesion.close();
            JOptionPane.showMessageDialog(null, "Eliminacion Completada.\n Han quedado "+contador+" proveedores sin eliminar de la consulta");
        } else {
            JOptionPane.showMessageDialog(null, "No se ha borrado nada");
        }
    }
    /**
     * Ejecuta la función de filtrado de Proveedores, buscando en funcion de los parámetros introducidos
     * @param e
     */
    private void buscarProveedores(ActionEvent e) {
        JTextArea ta = this.taTextoConsulta;
        //Se vacia el textArea para no liar datos
        ta.setText("");
        //Primero hay que mirar que campos tienen datos, y si esos datos son correctos
        String codigo = "";
        String nombre = "";
        String dir = "";
        //select new list(codigo, nombre, apellidos, direccion)
        String hql = "from ProveedoresEntity";

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

        if(hasDataDirCField()){
            if(tfCDireccion.getText().length() > 50){
                JOptionPane.showMessageDialog(this, "La Direccion no puede tener mas de 50 caracteres","Error" , JOptionPane.ERROR_MESSAGE);
            }else{
                dir = tfCDireccion.getText();
            }
        }

        Session sesion = HibernateUtil.getSessionFactory().openSession();

        if(!codigo.isBlank() || !nombre.isBlank() || !dir.isBlank()){

            hql = hql + " where ";

            if(!codigo.isBlank()){
                hql = hql + "codigo = '"+codigo+"' ";
            }
            if(!nombre.isBlank()){
                hql = hql + "nombre like '%"+nombre+"%' ";
            }
            if(!dir.isBlank()){
                hql = hql + "direccion like '%"+dir+"%' ";
            }

        }

        Query query = sesion.createQuery(hql);
        List<ProveedoresEntity> resultado = query.list();
        listaProveedores = resultado;
        sesion.close();

        for(ProveedoresEntity p: resultado){
            ta.append(""+p.getCodigo()+" "+p.getNombre()+" "+p.getApellidos()+" "+p.getDireccion()+"\n");
        }
    }
    /*
     *
     *HAY QUE COMPROBAR QUE LOS DATOS TENGAN SENTIDO
     *
     */
    /*
    * Consultas
    * */

    /**
     * Comprueba que el campo codigo de la pestaña de consultas tiene valores
     * @return true/false
     */
    private boolean hasDataCodeCField(){
        JTextField tfCod = this.tfCCodigo;
        if(tfCod.getText().isBlank()){
            return false;
        }else{
            return true;
        }
    }

    /**
     * Comprueba que el campo Nombre de la pestaña consultas tiene valores
     * @return true/false
     */
    private boolean hasDataNameCField(){
        JTextField tfName = this.tfCNombre;
        if(tfName.getText().isBlank()){
            return false;
        }else{
            return true;
        }
    }

    /**
     * Comprueba que el campo Direccion de la pestaña consultas tiene valores
     * @return true/false
     */
    private boolean hasDataDirCField(){
        JTextField tfDir = this.tfCDireccion;
        if(tfDir.getText().isBlank()){
            return false;
        }else{
            return true;
        }
    }

    /**
     * Convierte el campo Código a mayusculas, en caso de haberlo introducido en minusculas(Gestion)
     */
    private void turnCodeGUpp(){
        tfGCodProv.setText(tfGCodProv.getText().toUpperCase());
    }
    /**
     * Convierte el campo Código a mayusculas, en caso de haberlo introducido en minusculas(Consultas)
     */
    private void turnCodeCUpp(){
        tfCCodigo.setText(tfCCodigo.getText().toUpperCase());
    }

    /**
     * Comprueba que el campo Codigo es correcto
     * @return true/false
     */
    private boolean checkCodeField(){
        JTextField tfCod = this.tfGCodProv;
        if(tfCod.getText().isBlank()){
            return false;
        }else {
            return true;
        }
    }

    /**
     * Comprueba que el campo Nombre es correcto
     * @return true/false
     */
    private boolean checkNameField(){
        JTextField tfName = this.tfGNombre;
        if(tfName.getText().isBlank()){
            return false;
        }else{
            return true;
        }
    }
    /**
     * Comprueba que el campo Apellido es correcto
     * @return true/false
     */
    private boolean checkLastNameField(){
        JTextField tfApe = this.tfGApellidos;
        if(tfApe.getText().isBlank()){
            return false;
        }else {
            return true;
        }
    }
    /**
     * Comprueba que el campo Direccion es correcto
     * @return true/false
     */
    private boolean checkAddressField(){
        JTextField tfDir = this.tfGDireccion;
        if(tfDir.getText().isBlank()){
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
        taTextoConsulta = new JTextArea();
        label5 = new JLabel();
        label6 = new JLabel();
        label7 = new JLabel();
        tfCCodigo = new JTextField();
        tfCNombre = new JTextField();
        tfCDireccion = new JTextField();
        btnFiltrar = new JButton();
        btnVolver = new JButton();
        btnBorrarC = new JButton();
        panel1 = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        tfGCodProv = new JTextField();
        tfGNombre = new JTextField();
        tfGApellidos = new JTextField();
        tfGDireccion = new JTextField();
        btnLimpiar = new JButton();
        btnInsertar = new JButton();
        btnModificar = new JButton();
        btnEliminar = new JButton();
        btnVolver2 = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

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

                            //---- taTextoConsulta ----
                            taTextoConsulta.setEditable(false);
                            scrollPane1.setViewportView(taTextoConsulta);
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
                        label7.setText("Direccion");
                        panel2.add(label7);
                        label7.setBounds(new Rectangle(new Point(10, 280), label7.getPreferredSize()));
                        panel2.add(tfCCodigo);
                        tfCCodigo.setBounds(75, 205, 130, tfCCodigo.getPreferredSize().height);
                        panel2.add(tfCNombre);
                        tfCNombre.setBounds(75, 240, 440, 30);
                        panel2.add(tfCDireccion);
                        tfCDireccion.setBounds(75, 275, 440, 30);

                        //---- btnFiltrar ----
                        btnFiltrar.setText("Filtrar");
                        btnFiltrar.addActionListener(e -> buscarProveedores(e));
                        panel2.add(btnFiltrar);
                        btnFiltrar.setBounds(new Rectangle(new Point(10, 325), btnFiltrar.getPreferredSize()));

                        //---- btnVolver ----
                        btnVolver.setText("Volver");
                        btnVolver.addActionListener(e -> cerrarVentana(e));
                        panel2.add(btnVolver);
                        btnVolver.setBounds(new Rectangle(new Point(525, 325), btnVolver.getPreferredSize()));

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
                    tabbedPane1.addTab("Consultar Proveedores", panel2);

                    //======== panel1 ========
                    {
                        panel1.setLayout(null);

                        //---- label1 ----
                        label1.setText("Codigo Proveedor");
                        panel1.add(label1);
                        label1.setBounds(new Rectangle(new Point(10, 15), label1.getPreferredSize()));

                        //---- label2 ----
                        label2.setText("Nombre");
                        panel1.add(label2);
                        label2.setBounds(new Rectangle(new Point(10, 45), label2.getPreferredSize()));

                        //---- label3 ----
                        label3.setText("Apellidos");
                        panel1.add(label3);
                        label3.setBounds(new Rectangle(new Point(10, 75), label3.getPreferredSize()));

                        //---- label4 ----
                        label4.setText("Direccion");
                        panel1.add(label4);
                        label4.setBounds(new Rectangle(new Point(10, 105), label4.getPreferredSize()));
                        panel1.add(tfGCodProv);
                        tfGCodProv.setBounds(125, 10, 110, tfGCodProv.getPreferredSize().height);
                        panel1.add(tfGNombre);
                        tfGNombre.setBounds(125, 40, 440, 30);
                        panel1.add(tfGApellidos);
                        tfGApellidos.setBounds(125, 70, 440, 30);
                        panel1.add(tfGDireccion);
                        tfGDireccion.setBounds(125, 100, 440, 30);

                        //---- btnLimpiar ----
                        btnLimpiar.setText("Limpiar");
                        btnLimpiar.addActionListener(e -> vaciarCampos(e));
                        panel1.add(btnLimpiar);
                        btnLimpiar.setBounds(new Rectangle(new Point(125, 250), btnLimpiar.getPreferredSize()));

                        //---- btnInsertar ----
                        btnInsertar.setText("Insertar");
                        btnInsertar.addActionListener(e -> insertarProveedor(e));
                        panel1.add(btnInsertar);
                        btnInsertar.setBounds(new Rectangle(new Point(225, 250), btnInsertar.getPreferredSize()));

                        //---- btnModificar ----
                        btnModificar.setText("Modificar");
                        btnModificar.addActionListener(e -> updateProveedor(e));
                        panel1.add(btnModificar);
                        btnModificar.setBounds(new Rectangle(new Point(325, 250), btnModificar.getPreferredSize()));

                        //---- btnEliminar ----
                        btnEliminar.setText("Eliminar");
                        btnEliminar.addActionListener(e -> deleteProveedor(e));
                        panel1.add(btnEliminar);
                        btnEliminar.setBounds(new Rectangle(new Point(425, 250), btnEliminar.getPreferredSize()));

                        //---- btnVolver2 ----
                        btnVolver2.setText("Volver");
                        btnVolver2.addActionListener(e -> cerrarVentana(e));
                        panel1.add(btnVolver2);
                        btnVolver2.setBounds(new Rectangle(new Point(525, 325), btnVolver2.getPreferredSize()));

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
                    tabbedPane1.addTab("Gestion Proveedores", panel1);
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
        contentPane.add(dialogPane, BorderLayout.CENTER);
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
    private JTextArea taTextoConsulta;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JTextField tfCCodigo;
    private JTextField tfCNombre;
    private JTextField tfCDireccion;
    private JButton btnFiltrar;
    private JButton btnVolver;
    private JButton btnBorrarC;
    private JPanel panel1;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JTextField tfGCodProv;
    private JTextField tfGNombre;
    private JTextField tfGApellidos;
    private JTextField tfGDireccion;
    private JButton btnLimpiar;
    private JButton btnInsertar;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnVolver2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}

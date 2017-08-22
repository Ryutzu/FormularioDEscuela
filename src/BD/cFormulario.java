/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.SelectionMode;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Usuario
 */
public class cFormulario extends cBase implements ActionListener,KeyListener{
    public ArrayList<Object> objetos = new ArrayList<>();
    ArrayList<String> tempo=new ArrayList<>();
    public JFrame vent;
    JList lista,lista2;
    String cache;
    JScrollPane listTem;
    JScrollPane listTem2;
    DefaultListModel _dlm = new DefaultListModel();
    DefaultListModel _dlm2 = new DefaultListModel();
    public JTextField text = new JTextField();
    public JTextField Busca = new JTextField();
    public JTextField BuscaSel = new JTextField();
    public JTextField Aqui = new JTextField();
    public JComboBox combo = new JComboBox();
    public JLabel titu = new JLabel();
    public int wid,hei,proc;
    public JButton _agr = new JButton(">");
    public JButton _ret = new JButton("<");
    public JButton envio = new JButton("Enviar");
    public JButton Exi = new JButton("Cancelar");
    /**
     * Indica el nombre del frame del formulario, su ancho y largo
     * @param NomCuest
     * @param w
     * @param h 
     */
    public cFormulario(String NomCuest,int w,int h) {
        titu.setText(NomCuest);
        wid=w;
        hei=h;
    }
    /**
     * Establecer un cache para los parametros
     * @param cache 
     */
    public void setCache(String cache) {
        this.cache = cache;
    }
    /**
     * Establece el procedimiento que va a seguir el formulario
     * @param proc 
     */
    public void setProc(int proc) {
        this.proc = proc;
    }
    /**
     * Establece el ancho y e largo de la ventana
     * @param w
     * @param h 
     */
    public void setWH(int w,int h){
        wid=w;
        hei=h;
    }
    protected void renderV(){
        vent = new JFrame(titu.getText());
        vent.setVisible(true);
        vent.setSize(wid,hei);
        vent.setLayout(null);
        vent.setLocationRelativeTo(null);
        vent.setResizable(false);
    }
    /**
     * Agrega un nuevo cuadro de texto al formulario
     * @param titulo
     * Es el nombre del cuadro del texto
     * @param visible
     * Con 0 es visible
     */
    public void nText(String titulo,int visible){
        JLabel y = new JLabel(titulo);
        if(visible==0){
            y.setVisible(true);
        }else{
            y.setVisible(false);
        }
        y.setBounds(50, 410, 100, 50);
        objetos.add(y);
        JTextField x = new JTextField();
        objetos.add(x);
        if(visible==0){
            x.setVisible(true);
        }else{
            x.setVisible(false);
        }
        x.setBounds(200, 410, 100, 50);  
    }
    /**
     * Establece un texto por default en un cuadro de texto
     * @param index
     * Indice del cuadro de texto
     * @param valor 
     * Texto que se usará
     */
    public void sText(int index,String valor){
        JTextField x = (JTextField) objetos.get(index);
        objetos.remove(index);
        x.setText(valor);
        objetos.add(index,x);
    }
    /**
     * Agrega una nueva seleccion en lista
     * @param titulo
     * Nombre de la lista
     * @param visible
     * 0 es visible
     * @param opciones
     * Opciones de la lista
     * En un string, palabras separadas por comas (,)
     */
    public void nCombo(String titulo,int visible,String opciones){
        JLabel y = new JLabel(titulo);
        if(visible==0){
            y.setVisible(true);
        }else{
            y.setVisible(false);
        }
        objetos.add(y);
        String[] ops = opciones.split(",");
        JComboBox x = new JComboBox();
        if(visible==0){
            x.setVisible(true);
        }else{
            x.setVisible(false);
        }
        for(int j=0;j<ops.length;j++){
           x.addItem(ops[j]);
        }
        objetos.add(x);
    }
    /**
     * Selecciona una opcion de la lista usando un valor de texto
     * @param index
     * @param valor 
     */
    public void sCombo(int index,String valor){
        JComboBox x = (JComboBox) objetos.get(index);
        objetos.remove(index);
        x.setSelectedItem(valor);
        objetos.add(index, x);
    }
    /**
     * Selecciona una opcion de la lista usando el indice de la opcion
     * @param index
     * @param valor 
     */
    public void sCombo(int index,int valor){
        JComboBox x = (JComboBox) objetos.get(index);
        objetos.remove(index);
        x.setSelectedIndex(valor-1);
        objetos.add(index, x);
    }
    /**
     * Agrega una lista para seleccionar una o más cosas y agregarlos o quitarlos
     * Se limita una por cuestionario
     * El procedimiento a usar debe ser 5 u 11
     * @param titulo
     * Nombre de la lista
     * @param visible
     * 0 es visible
     * @param opciones
     * Opciones de la lista
     * En un string, palabras separadas por comas (,)
     */
    public void nSel(String titulo,int visible,String opciones){
        JLabel y = new JLabel(titulo);
        if(visible==0){
            y.setVisible(true);
        }else{
            y.setVisible(false);
        }
        objetos.add(y);
        JTextField z = new JTextField("BuscaSel");
        objetos.add(z);
        String[] ops = opciones.split(",");
        for(int j=0;j<ops.length;j++){
            _dlm.addElement(ops[j]);
        }
        JList x = new JList();
        x.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        x.setModel(_dlm);
        objetos.add(x);
        if(visible==0){
            x.setVisible(true);
        }else{
            x.setVisible(false);
        }
        
    }
    /**
     * Cuadro de texto que busca el texto escrito en base de datos y si existe aparece un texto correspondiente
     * @param titulo
     * Nombre para referenciar
     * @param visible 
     * 0 es visible
     */
    public void nBuscar(String titulo,int visible){
        JLabel y = new JLabel(titulo);
        if(visible==0){
            y.setVisible(true);
        }else{
            y.setVisible(false);
        }
        y.setBounds(50, 410, 100, 50);
        objetos.add(y);
        JTextField x = new JTextField("Busca");
        objetos.add(x);
        if(visible==0){
            x.setVisible(true);
        }else{
            x.setVisible(false);
        }
        x.setBounds(200, 410, 100, 50);
        JLabel z = new JLabel();
        if(visible==0){
            z.setVisible(true);
        }else{
            z.setVisible(false);
        }
        z.setBounds(50, 410, 100, 50);
        objetos.add(z);
        JTextField v = new JTextField("Aqui");
        objetos.add(v);
        if(visible==0){
            v.setVisible(true);
        }else{
            v.setVisible(false);
        }
        v.setBounds(200, 410, 100, 50);  
    }
    /**
     * Metodo que hace valido la configuración y despliega el formulario
     */
    public void RenderF(){
        renderV();
        int tam = 0;
        if(proc==5||proc==11){
            tam = objetos.size()+2;
        }else{
            tam = objetos.size();
        }
        titu.setBounds(wid/3,20, wid/3, hei/((tam/2+2)*2));
        vent.add(titu);
        int cont=1;
        for(Object num:objetos){
            if(num.getClass().toString().equalsIgnoreCase("class javax.swing.JTextField")){
                text=(JTextField) num;
                if(text.getText().equalsIgnoreCase("Busca")){
                    Busca.setBounds((wid*3)/5,(hei*cont)/((tam/2+2)),wid/5,hei/((tam/2+2)*2));
                    vent.add(Busca);
                    Busca.addKeyListener(this);
                    Busca.setVisible(true);
                }else if(text.getText().equalsIgnoreCase("Aqui")){
                    Aqui.setBounds((wid*3)/5,(hei*cont)/((tam/2+2)),wid/5,hei/((tam/2+2)*2));
                    vent.add(Aqui);
                    Aqui.setVisible(true);
                    Aqui.setEditable(false);
                }else if(text.getText().equalsIgnoreCase("BuscaSel")){
                    cont++;
                    BuscaSel.setBounds((wid)/5,(hei*cont)/((tam/2+2)),wid/5,hei/((tam/2+2)*2));
                    BuscaSel.setVisible(true);
                    BuscaSel.addKeyListener(this);
                    vent.add(BuscaSel);
                    cont-=2;
                }else{
                    text.setBounds((wid*3)/5,(hei*cont)/((tam/2+2)),wid/5,hei/((tam/2+2)*2));
                    vent.add(text);
                    text.setVisible(true);
                }
                cont++;
            }else if(num.getClass().toString().equalsIgnoreCase("class javax.swing.JLabel")){
                titu = (JLabel) num;
                titu.setBounds(wid/5,(hei*cont)/((tam/2+2)),wid/5,hei/((tam/2+2)*2));
                vent.add(titu);
                //cont++;
            }else if(num.getClass().toString().equalsIgnoreCase("class javax.swing.JComboBox")){
                combo= (JComboBox) num;
                combo.setBounds((wid*3)/5,(hei*cont)/((tam/2+2)),wid/5,hei/((tam/2+2)*2));
                vent.add(combo);
                cont++;
            }else if(num.getClass().toString().equalsIgnoreCase("class javax.swing.JList")){
                lista=(JList) num;               
                lista2=new JList();
                lista2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                lista2.setModel(_dlm2);
                //lista.setBounds((wid*3)/5,(hei*cont)/((tam/2+2)),wid/5,hei/((tam/2+2)*2));
                listTem = new JScrollPane();
                listTem2 = new JScrollPane();
                listTem.setBounds((int) ((wid*4.5)/10),(hei*cont)/((tam/2+2)),wid*2/10,hei*3/((tam/2+2)*2));
                listTem.setViewportView(lista);
                vent.add(listTem);
                _agr.setBounds((int) ((wid*6.5)/10), (int) ((hei*(cont+.25))/((tam/2+2))),wid/10,hei/((tam/2+2)*2));
                _agr.addActionListener(this);
                _ret.setBounds((int) ((wid*6.5)/10), (int) ((hei*(cont+.75))/((tam/2+2))),wid/10,hei/((tam/2+2)*2));
                _ret.addActionListener(this);
                vent.add(_agr);
                vent.add(_ret);
                listTem2.setBounds((int) ((wid*7.5)/10),(hei*cont)/((tam/2+2)),wid*2/10,hei*3/((tam/2+2)*2));
                listTem2.setViewportView(lista2);
                vent.add(listTem2);
                cont++;
            }
        }
        if(proc==5||proc==11){
            cont++;
        }
        envio.setBounds(wid/5, (hei*cont)/((tam/2+2)), wid/5, hei/((tam/2+2)*2));
        envio.setVisible(true);
        envio.addActionListener(this);
        vent.add(envio);
        Exi.setBounds((wid*3)/5, (hei*cont)/((tam/2+2)), wid/5, hei/((tam/2+2)*2));
        Exi.setVisible(true);
        Exi.addActionListener(this);
        vent.add(Exi);
    }
    protected String Cuestios(int op,String Datos) throws SQLException{
        String resp="";
        try{
            if(op==0){
                int cant = Datos.split(",").length;
                if(cant==4){
                    String[] dat = Datos.split(",");
                    cAlumno al = new cAlumno();
                    resp=al.RegAl(dat[0],dat[1],dat[2],Integer.parseInt(dat[3]));
                }else{
                    resp="Faltan Datos";
                }
            }
        }catch(Exception xD){
            resp=xD.getMessage();
        }
        return resp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            if(e.getSource()==envio){
                String resps = "",sep="";
                for(Object num:objetos){
                    if(num.getClass().toString().equalsIgnoreCase("class javax.swing.JTextField")){
                        text=(JTextField) num;
                        if(text.getText().equalsIgnoreCase("Busca")){
                            resps+=sep + Busca.getText();
                            sep=",";
                        }else if(text.getText().equalsIgnoreCase("Aqui")){

                        }else if(text.getText().equalsIgnoreCase("BuscaSel")){
                            
                        }else{
                            resps+=sep + text.getText();
                            sep=",";
                        }
                    }else if(num.getClass().toString().equalsIgnoreCase("class javax.swing.JLabel")){
                        titu = (JLabel) num;
                        //resps+=sep+titu.getText();
                        //sep=",";
                    }else if(num.getClass().toString().equalsIgnoreCase("class javax.swing.JComboBox")){
                        combo= (JComboBox) num;
                        resps+=sep+(combo.getSelectedIndex()+1);
                        sep=",";
                    }else if(num.getClass().toString().equalsIgnoreCase("class javax.swing.JList")){
                        //List l = (List) _dlm2;
                        String[] x;
                        String largo = "";
                        String yop="";
                        for(int j=0;j<_dlm2.size();j++){
                            x = _dlm2.get(j).toString().split("/");
                            largo+=yop+x[0]+"&"+x[2];
                            yop="/";
                        }
                        resps+=sep+largo;
                        sep=",";
                    }
                }
                if(proc==0){
                    JOptionPane.showMessageDialog(combo,Cuestios(proc, resps));
                    if(JOptionPane.showConfirmDialog(null,"¿Quieres registrar más?")!=0){
                        vent.dispose();
                    }
                }
            }else if(e.getSource()==Exi){
                vent.dispose();
            }else if(e.getSource()==_agr){
                if(!lista.isSelectionEmpty()){
                    String canti = JOptionPane.showInputDialog("¿Cuanto se va a usar?");
                    if(!canti.equals("")||canti!=null){
                        _dlm2.addElement(lista.getSelectedValue()+"/"+canti);
                    }else{
                        JOptionPane.showMessageDialog(null, "Ingresa una cantidad");
                    }
                }
            }else if(e.getSource()==_ret){
                if(!lista2.isSelectionEmpty()){
                    _dlm2.removeElement(lista2.getSelectedValue());
                }
            }
        }catch(Exception xD){
            System.out.println(xD.getMessage());
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
}

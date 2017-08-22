/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formulariodescuela;

import BD.cBase;
import BD.cFormulario;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Alumno
 */
public class FormularioDEscuela {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        cFormulario form = new cFormulario("Registro de alumno",400,400);
        form.nText("Nombre", 0);
        form.nText("Apellido Paterno", 0);
        form.nText("Apellido Materno", 0);
        String ops = GetEscuelas();
        form.nCombo("Escuela", 0,ops);
        form.setProc(0);
        form.RenderF();
    }
    protected static String GetEscuelas(){
        String Esc="";
        String sep="";
        try{
            cBase bd = new cBase();
            bd.conect();
            ResultSet ok = bd.consu("select * from verEscuela");
            while(ok.next()){
                Esc+=sep+ok.getString("nombre");
                sep=",";
            }
        }catch(Exception xD){
            JOptionPane.showMessageDialog(null,xD.getMessage());
        }
        return Esc;
    }
}

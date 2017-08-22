/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Profesor
 */
public class cAlumno extends cBase{
    public String RegAl(String Nom, String NomP, String NomM, int Escul) throws SQLException{
        Permiso();
        conect();
        String Exis="",sep="";
        String comando= "{call spGuardaAlumno(?,?,?,?)}";
        _sts= _con.prepareCall(comando);
        _sts.setString(1,Nom);
        _sts.setString(2,NomP);
        _sts.setString(3,NomM);
        _sts.setInt(4,Escul);
        _sts.execute();
        final ResultSet rs= _sts.getResultSet();
        if(rs.next()){
            Exis=rs.getString("mensaje");
        }
        cerrar();
        return Exis;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Usuario
 */
public class cBase {
    private String _user;
    private String _pass;
    private String _driver;
    private String _url;
    protected Connection _con = null;
    protected CallableStatement _sts = null;
    public ResultSet _rs = null;
    private Statement estancia;
    public cBase(){
        _user="root";
        _pass="123456a*";
        _driver="com.mysql.jdbc.Driver";
        _url="jdbc:mysql://localhost/alumno";
    }
    public void conect(){
        try{
            Class.forName(this._driver).newInstance();
            _con=DriverManager.getConnection(_url, _user, _pass);
        }catch(Exception e){
            System.out.println("error"+e.getMessage());
        }
    }
    public String Consu(int op) throws SQLException{
        conect();
        String Exis="",sep="";
        String comando= "{call spConsu(?)}";
        _sts= _con.prepareCall(comando);
        _sts.setInt(1,op);
        _sts.execute();
        final ResultSet rs= _sts.getResultSet();
        if(op==0){
            while(rs.next()){
                Exis+=sep+rs.getString("idMesa")+"/"+rs.getString("sts");
                sep="~";
            }
        }
        cerrar();
        return Exis;
    }
    public ResultSet consu(String consulta) throws SQLException {
        this.estancia = (Statement) _con.createStatement();
        return this.estancia.executeQuery(consulta);
    }
    //Metodos para ejecutar modif
    public void mod(String modif) throws SQLException {
        this.estancia = (Statement) _con.createStatement();
        this.estancia.executeUpdate(modif);
    } 
    public void cerrar() throws SQLException{
        _con.close();
    }
}

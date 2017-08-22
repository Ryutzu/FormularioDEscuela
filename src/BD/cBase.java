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
import javax.swing.JOptionPane;

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
    /**
     * Constructor de parametros para la base
     */
    public cBase(){
        _user="root";
        _driver="com.mysql.jdbc.Driver";
    }
    public  void Permiso(){
        _pass=JOptionPane.showInputDialog(null, "Ingrese la contraseña de mysql");
        _url="jdbc:mysql://localhost/"+JOptionPane.showInputDialog(null, "Ingrese la base de mysql");
    }
    /**
     * Metodo para conectar a la base
     */
    public void conect(){
        try{
            Class.forName(this._driver).newInstance();
            _con=DriverManager.getConnection(_url, _user, _pass);
        }catch(Exception e){
            System.out.println("error"+e.getMessage());
        }
    }
    /**
     * Consulta con un query
     * @param consulta
     * Codigo SQL para consulta
     * @return
     * ResultSet
     * @throws SQLException 
     */
    public ResultSet consu(String consulta) throws SQLException {
        this.estancia = (Statement) _con.createStatement();
        return this.estancia.executeQuery(consulta);
    }
    /**
     * Modifica la base de datos con un comando SQL
     * @param modif
     * @throws SQLException 
     */
    public void mod(String modif) throws SQLException {
        this.estancia = (Statement) _con.createStatement();
        this.estancia.executeUpdate(modif);
    } 
    /**
     * Cierra la conexion
     * @throws SQLException 
     */
    public void cerrar() throws SQLException{
        _con.close();
    }
}

package auth;

import java.sql.Connection;
import java.sql.DriverManager;

import com.master.util.ed.Parametro_FixoED;

import br.cte.model.Empresa;

public final class OracleConnection2 {
     private static   Parametro_FixoED parametro_FixoED  = new Parametro_FixoED();

     private final static String desenv =parametro_FixoED.getNM_Database_URL(); //"jdbc:postgresql://192.168.0.7:5432/sistema";
    
     private String url;
     private String user;
     private String senha;

     private OracleConnection2() {
         
     }

     public void setURL(String url) {
          this.url = url;
     }

     public void setUser(String user) {
          this.user = user;
     }

     public void setSenha(String senha) {
          this.senha = senha;
     }

     public static Connection getWEB() throws Exception {
          Class.forName(parametro_FixoED.getNM_Database_Driver());
          //// System.out.println("Usuario: " + parametro_FixoED.getNM_Database_Usuario() + " Empresa: " + parametro_FixoED.getNM_Empresa() );

          return DriverManager.getConnection(desenv, parametro_FixoED.getNM_Database_Usuario(), parametro_FixoED.getNM_Database_Pwd());
     }
     
     public static Connection getWEB(Empresa empresa) throws Exception {
         Class.forName(empresa.getDbDriver());
         //// System.out.println("Usuario: " + parametro_FixoED.getNM_Database_Usuario() + " Empresa: " + parametro_FixoED.getNM_Empresa() );

         return DriverManager.getConnection(empresa.getDbURL(), empresa.getDbUser(), empresa.getDbPass());
    }

     public static void main(String args[]) {
     }
}


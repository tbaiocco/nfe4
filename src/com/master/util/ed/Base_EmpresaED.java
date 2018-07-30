package com.master.util.ed;

public class Base_EmpresaED {

    private String NM_Empresa;
    private String NM_Base;
    private String NM_Base_da_Base2;
    private String NM_Database_Host;
    private String NM_Database_HostBase2;
    private int NM_Database_Port;
    private int NM_Database_PortBase2;
    private String NM_Database_URL;
    private String NM_Database_URLBase2;
    private String NM_Database_Usuario;
    private String NM_Database_UsuarioBase2;
    private String NM_Database_Driver;
    private String NM_Database_DriverBase2;
    private String NM_Database_Pwd;
    private String NM_Database_PwdBase2;
    private String NM_Databese_Sid;
    private String NM_Databese_SidBase2;

    public Base_EmpresaED() {

    	// *************   E M P R E S A  D E  A C E S S O   ***************
           NM_Empresa = "MIRO";
//           NM_Empresa = "TSG";
//    	NM_Empresa = "ATR";

      	// *************   B A S E  D E  A C E S S O   ***************
//      	 NM_Base = "CTE";
      	 NM_Base = "HOMOLOGA";

     	// *************   S E G U N D A  B A S E  D E  A C E S S O   ***************

     	NM_Base_da_Base2 = "SERVIDOR_BASE2";


    }

    public Base_EmpresaED(String cnpj) {

    	// *************   E M P R E S A  D E  A C E S S O   ***************
           NM_Empresa = "MIRO";

      	// *************   B A S E  D E  A C E S S O   ***************
      	 NM_Base = "CTE";

     	// *************   S E G U N D A  B A S E  D E  A C E S S O   ***************

     	NM_Base_da_Base2 = "SERVIDOR_BASE2";


    }

    public String getNM_Base() {
        return NM_Base;
    }

    public void setNM_Base(String base) {
        NM_Base = base;
    }

    public String getNM_Empresa() {
        return NM_Empresa;
    }

    public void setNM_Empresa(String empresa) {
        NM_Empresa = empresa;
    }

    public void setNM_Database_URL(String NM_Database_URL) {
        this.NM_Database_URL = NM_Database_URL;
    }

    public String getNM_Database_URL() {
        return NM_Database_URL;
    }

    public String getNM_Database_Pwd() {
        return NM_Database_Pwd;
    }

    public String getNM_Database_Usuario() {
        return NM_Database_Usuario;
    }

    public void setNM_Database_Pwd(String NM_Database_Pwd) {
        this.NM_Database_Pwd = NM_Database_Pwd;
    }

    public void setNM_Database_Usuario(String NM_Database_Usuario) {
        this.NM_Database_Usuario = NM_Database_Usuario;
    }

    public void setNM_Databese_Sid(String NM_Databese_Sid) {
        this.NM_Databese_Sid = NM_Databese_Sid;
    }

    public String getNM_Databese_Sid() {
        return NM_Databese_Sid;
    }

    public void setNM_Database_Host(String NM_Database_Host) {
        this.NM_Database_Host = NM_Database_Host;
    }

    public void setNM_Database_Port(int NM_Database_Port) {
        this.NM_Database_Port = NM_Database_Port;
    }

    public String getNM_Database_Host() {
        return NM_Database_Host;
    }

    public int getNM_Database_Port() {
        return NM_Database_Port;
    }

    public String getNM_Database_Driver() {
        return NM_Database_Driver;
    }

    public void setNM_Database_Driver(String NM_Database_Driver) {
        this.NM_Database_Driver = NM_Database_Driver;
    }

	public String getNM_Database_DriverBase2() {
		return NM_Database_DriverBase2;
	}

	public void setNM_Database_DriverBase2(String database_DriverBase2) {
		NM_Database_DriverBase2 = database_DriverBase2;
	}

	public String getNM_Database_PwdBase2() {
		return NM_Database_PwdBase2;
	}

	public void setNM_Database_PwdBase2(String database_PwdBase2) {
		NM_Database_PwdBase2 = database_PwdBase2;
	}

	public String getNM_Database_URLBase2() {
		return NM_Database_URLBase2;
	}

	public void setNM_Database_URLBase2(String database_URLBase2) {
		NM_Database_URLBase2 = database_URLBase2;
	}

	public String getNM_Database_UsuarioBase2() {
		return NM_Database_UsuarioBase2;
	}

	public void setNM_Database_UsuarioBase2(String database_UsuarioBase2) {
		NM_Database_UsuarioBase2 = database_UsuarioBase2;
	}

	public String getNM_Base_da_Base2() {
		return NM_Base_da_Base2;
	}

	public void setNM_Base_da_Base2(String base_da_Base2) {
		NM_Base_da_Base2 = base_da_Base2;
	}

	public String getNM_Database_HostBase2() {
		return NM_Database_HostBase2;
	}

	public void setNM_Database_HostBase2(String database_HostBase2) {
		NM_Database_HostBase2 = database_HostBase2;
	}

	public int getNM_Database_PortBase2() {
		return NM_Database_PortBase2;
	}

	public void setNM_Database_PortBase2(int database_PortBase2) {
		NM_Database_PortBase2 = database_PortBase2;
	}

	public String getNM_Databese_SidBase2() {
		return NM_Databese_SidBase2;
	}

	public void setNM_Databese_SidBase2(String databese_SidBase2) {
		NM_Databese_SidBase2 = databese_SidBase2;
	}
}

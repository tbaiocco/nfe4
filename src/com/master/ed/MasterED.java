package com.master.ed;

import javax.servlet.http.HttpServletRequest;

import com.master.root.UsuarioBean;
import com.master.util.Data;
import com.master.util.JavaUtil;
import com.master.util.Mensagens;

public class MasterED implements java.io.Serializable {


    //*** Parametros paginacao
    private int currPage = -1;
    private int numRecInPage = 10;//default
    private int totalRecords;

    private String dt_stamp;
    private String hr_stamp;
    private String dm_Stamp;
    private String usuario_Stamp;
    private String SO;
    private int user;
    private long time_millis;

    private String msg_Stamp;

    private String dm_Tipo_Consulta;

    public MasterED()
    {
        this.setDm_Stamp("S");
        this.setDt_stamp(Data.getDataDMY());
        this.setHr_stamp(Data.getHoraHM());
        this.setUsuario_Stamp("Master");
    }
    public MasterED(HttpServletRequest request) throws Mensagens
    {
        this.setMasterDetails(request);
    }

    public void setPaginacao(HttpServletRequest request)
    {
        String currPage = request.getParameter("currPage");
        if ("0".equals(currPage) || JavaUtil.doValida(currPage))
            this.currPage = Integer.parseInt(currPage);
        this.numRecInPage = JavaUtil.getValueDef(request.getParameter("numRecInPage"), 10);
        this.totalRecords = JavaUtil.getValueDef(request.getParameter("totalRecords"), 0);
    }
    public String getSQLPaginacao()
    {
        if (this.currPage > -1)
            return " OFFSET "+(this.currPage*this.numRecInPage)+" LIMIT "+this.numRecInPage;
        else return "";
    }
    public String getSQLSetMasterDetails()
    {
        return "    ,Usuario_Stamp="+JavaUtil.getSQLString(this.usuario_Stamp)+
               "    ,DM_Stamp="+JavaUtil.getSQLStringDef(this.dm_Stamp, "S")+
               "    ,DT_Stamp="+JavaUtil.getSQLDate(this.dt_stamp);
    }

    public void setMasterDetails(HttpServletRequest request) throws Mensagens
    {
        UsuarioED edUser = UsuarioBean.getUsuarioCorrente(request);
        this.usuario_Stamp = edUser.getNm_Usuario();
        this.dm_Stamp = JavaUtil.getValueDef(request.getParameter("acao"), "S");
    }

    public String getDt_stamp() {
        return dt_stamp;
    }
    public void setDt_stamp(String dt_stamp) {
        this.dt_stamp = dt_stamp;
    }
    public String getHr_stamp() {
        return hr_stamp;
    }
    public void setHr_stamp(String hr_stamp) {
        this.hr_stamp = hr_stamp;
    }
    public void setDm_Stamp(String dm_Stamp) {
        this.dm_Stamp = dm_Stamp;
    }
    public String getDm_Stamp() {
        return dm_Stamp;
    }
    public void setUsuario_Stamp(String usuario_Stamp) {
        this.usuario_Stamp = usuario_Stamp;
    }
    public String getUsuario_Stamp() {
        return usuario_Stamp;
    }
    public String getSO() {
        return SO;
    }
    public void setSO(String so) {
        SO = so;
    }
    public int getCurrPage() {
        return currPage;
    }
    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }
    public int getNumRecInPage() {
        return numRecInPage;
    }
    public void setNumRecInPage(int numRecInPage) {
        this.numRecInPage = numRecInPage;
    }
    public int getTotalRecords() {
        return totalRecords;
    }
    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }
	public int getUser() {
		return user;
	}
	public void setUser(int user) {
		this.user = user;
	}
	public String getMsg_Stamp() {
		return msg_Stamp;
	}
	public void setMsg_Stamp(String msg_Stamp) {
		this.msg_Stamp = msg_Stamp;
	}
	public long getTime_millis() {
		return time_millis;
	}
	public void setTime_millis(long time_millis) {
		this.time_millis = time_millis;
	}
	public String getDm_Tipo_Consulta() {
		return dm_Tipo_Consulta;
	}
	public void setDm_Tipo_Consulta(String dm_Tipo_Consulta) {
		this.dm_Tipo_Consulta = dm_Tipo_Consulta;
	}

}
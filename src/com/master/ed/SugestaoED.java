package com.master.ed;

import java.util.ArrayList;
/**
 * <p>Title: Sugestao Contábil</p>
 * <p>Description: Contém todos os eventos contabeis possiveis</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Delta Guia </p>
 * @author Claudia Galmarini Welter
 * @version 1.0
 */
public class SugestaoED extends MasterED{

 private long Oid_Sugestao;
 private String NM_Sugestao;
 private ArrayList ListaEventoED;

  public void setListaEventoED(ArrayList ListaEventoED) {
    this.ListaEventoED = ListaEventoED;
  }
  public void setNM_Sugestao(String NM_Sugestao) {
    this.NM_Sugestao = NM_Sugestao;
  }
  public void setOid_Sugestao(long Oid_Sugestao) {
    this.Oid_Sugestao = Oid_Sugestao;
  }
  public ArrayList getListaEventoED() {
    return ListaEventoED;
  }
  public String getNM_Sugestao() {
    return NM_Sugestao;
  }
  public long getOid_Sugestao() {
    return Oid_Sugestao;
  }


}
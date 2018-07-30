package com.master.root;
import java.text.SimpleDateFormat;

public class FormataDataBean_old
{
	private String DT_FormataData;

	public FormataDataBean_old()
	{
		DT_FormataData = "";
	}

	public void setDT_FormataData(String DT_FormataData)
	{
		this.DT_FormataData = DT_FormataData;
	}

	public String getDT_FormataData()
	{
		if (this.DT_FormataData != null) {
		try{
			SimpleDateFormat sdf;
			SimpleDateFormat sdfb;

			//a data que volta do banco
			String sData = DT_FormataData;
			//o formato que quero
			sdf = new SimpleDateFormat("dd/MM/yyyy");

			//a mascara do banco
			sdfb = new SimpleDateFormat("yyyy-MM-dd");

			if (sData != null)
			{
				DT_FormataData = sdf.format(sdfb.parse(sData));
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return DT_FormataData;

		}
		return "";
	}

	public static void main(String args[])
		throws Exception
	{
		FormataDataBean pp = new FormataDataBean();
		pp.setDT_FormataData("2002-10-20 00:00:00");
		// //// System.out.println(pp.getDT_FormataData());

	}
}



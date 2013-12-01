/**
 *
 * @author Aman
 */
package Util;

import Util.*;
import java.util.Vector;
import Session.GuiDComponent;

public class GuiDCodeGenerator 
{
	public static String getCode(String par_props[][],String par_type,Vector<GuiDComponent> v)
	{
		String code;
		String var_dec=" ";
		String setup_gui="";
		code = GuiDFileHandler.readFromFile("Templates/"+par_type+".template");
		if(v==null)
			return code;
	    
		for(int i=0;i<v.size();i++)
		{
			GuiDComponent gc = v.elementAt(i);
			var_dec += gc.getCast()+" "+gc.getVariableName()+";\n     ";
		}
		var_dec +="\n";
		setup_gui+="\t";
		for(int i=0;i<v.size();i++)
		{
			GuiDComponent gc = v.elementAt(i);
			String props[][] = gc.getPropertiesArray();
			if(gc.getCast().equals("JList")||gc.getCast().equals("JComboBox"))
			{
				int ti;
				if(gc.getCast().equals("JList"))
				{
					ti=9;
				}
				else
				{
					ti=8;
				}
				if(!props[ti][2].equals("$")&&!(props[ti][2].equals("")))
				{
					String tmp[]=props[ti][2].split(",");
					setup_gui+="String "+props[0][2]+"_tmp[]={";
					int k;
					for(k=0;k<tmp.length-1;k++)
						setup_gui+="\""+tmp[k]+"\",";
					setup_gui+="\""+tmp[k]+"\"};\n\t";
					setup_gui+=props[0][2]+" = new "+gc.getCast()+"("+props[0][2]+"_tmp);\n\t";
				}
				else
				{
					setup_gui+=props[0][2]+" = new "+gc.getCast()+"();\n\t";
				}
			}
			else
			  setup_gui+=props[0][2]+" = new "+gc.getCast()+"();\n\t";

			setup_gui+=props[0][2]+".setLocation("+props[1][2]+","+props[2][2]+");\n\t";

			setup_gui+=props[0][2]+".setSize("+props[3][2]+","+props[4][2]+");\n\t";
			
			if(Integer.parseInt(props[5][2])!=-13421773)
				setup_gui+=props[0][2]+".setForeground( new Color("+props[5][2]+") );\n\t";

			if(gc.getCast().contains("JText")||gc.getCast().equals("JPasswordField")||gc.getCast().equals("JList"))
			{
				if(!props[6][2].equals("-1"))
				{
					setup_gui+=props[0][2]+".setBackground( new Color("+props[6][2]+") );\n\t";
				}
			}
			else if(Integer.parseInt(props[6][2])!=-1118482)
				setup_gui+=props[0][2]+".setBackground( new Color("+props[6][2]+") );\n\t";

			if(props[7][2].equals("false"))
				setup_gui+=props[0][2]+".setVisible(false);\n\t";

			for(int j=8;props[j][0]!=null;j++)
			{
				if(props[j][1].equals("String"))
				{
					if(props[j][0].equals("ToolTipText"))
					{
						if(!(props[j][2].equals("$")))
					      setup_gui+=props[0][2]+".set"+props[j][0]+"(\""+props[j][2]+"\");\n\t";

					}
					else if(props[j][0].equals("Contents"))
					{
						if(!props[j][2].equals("$")&&!(props[j][2].equals("")))
						{
			
							String tmp[]=props[j][2].split(",");
							if(!(gc.getCast().startsWith("J")))
							{
								for(int k=0;k<tmp.length;k++)
									setup_gui+=props[0][2]+".add(\""+tmp[k]+"\");\n\t";
							}
						}
					}
					else if(gc.getCast().startsWith("J")&&props[j][0].equals("Label"))
						 setup_gui+=props[0][2]+".setText(\""+props[j][2]+"\");\n\t";
					else
					     setup_gui+=props[0][2]+".set"+props[j][0]+"(\""+props[j][2]+"\");\n\t";
				}
				else if(props[j][1].equals("char"))
				  setup_gui+=props[0][2]+".set"+props[j][0]+"('"+props[j][2]+"');\n\t";		
				else
				  setup_gui+=props[0][2]+".set"+props[j][0]+"("+props[j][2]+");\n\t";	
			}
			if(par_type.startsWith("J"))
			  setup_gui+="getContentPane().add("+props[0][2]+");\n\n\t";
			else
			  setup_gui+="add("+props[0][2]+");\n\n\t";	
		}

		if(!par_type.contains("Applet"))
		{
		  setup_gui+="setTitle(\""+par_props[0][2]+"\");\n\t";
		}
		setup_gui+="setSize("+par_props[1][2]+","+par_props[2][2]+");\n\t";
		if(Integer.parseInt(par_props[3][2])!=-13421773)
			setup_gui+="setForeground( new Color("+par_props[3][2]+") );\n\t";
		if(Integer.parseInt(par_props[4][2])!=-1118482)
			setup_gui+="setBackground( new Color("+par_props[4][2]+") );\n\t";
		if(par_props[5][2].equals("true"))
			setup_gui+="setVisible(true);\n\t";
		setup_gui+="setResizable("+par_props[6][2]+");\n\t\n\t";

		int tmp = code.indexOf("[Variables]");
		String tmp1 = code.substring(0,tmp);
		int tmp2 = code.indexOf("[Intialization]");
		String tmp3 = code.substring(tmp+12,tmp2);
		String tmp4 = code.substring(tmp2+15,code.length()-1);
	
		return tmp1+var_dec+tmp3+setup_gui+tmp4;
	}
}

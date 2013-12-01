package Util;

import Util.GuiDFileHandler;
import Session.GuiDComponent;
import java.io.*;
import java.util.Vector;

public class GuiDProjectFileHandler 
{
	Vector<GuiDComponent> v;
	String par_props[][];
	File f;
	String type;
	public GuiDProjectFileHandler(File f,String type,String props[][],Vector<GuiDComponent> v)
	{
		String path = f.getAbsolutePath();
		if(!(path.endsWith(".GuiD")))
		{
			path+=".GuiD";
			f = new File(path);
		}
		this.f = f;
		this.par_props = props;
		this.v= v;
		this.type = type;
	}
	public void generateProjectFile()
	{
		int i;
		String s=type+"\n";
		for(i=0;i<par_props.length;i++)
		{
			s+=par_props[i][0]+","+par_props[i][1]+","+par_props[i][2]+"\n";
		}
		s+="[END]\n";
		for(i=0;i<v.size();i++)
		{
			GuiDComponent gc = v.elementAt(i);
			String props[][] = gc.getPropertiesArray();
			String cast = gc.getCast();
			s+=cast+"\n";
			for(int j=0;j<props.length;j++)
			{
				if(props[j][0]==null)
					break;
			    s+=props[j][0]+","+props[j][1]+","+props[j][2]+"\n";
			}
			s+="[END]\n";
		}
		s+="[EOF]\n\n";
		GuiDFileHandler.writeToFile(f,s);
	}

	public void parseProjectFile(iGuiDPropMessenger gpm)
	{
		int i,j,st,ed;
		String s = GuiDFileHandler.readFromFile(f);

		par_props = new String[7][3];
		v = new Vector<GuiDComponent>();
		
		String slines[] = s.split("\n");
	    type = slines[1];
		for (i=2,j=0;!(slines[i].equals("[END]"));i++,j++)
		{
    		par_props[j] = slines[i].split(",");
		}
		for(i=i+1;!slines[i].equals("[EOF]");i++)
		{
			String props[][]=new String[19][4];
			String cast = slines[i];
			for (j=0,i=i+1;!(slines[i].equals("[END]"));j++,i++)
			{
				props[j] = slines[i].split(",");
			}
			GuiDComponent gc = new GuiDComponent(cast,props,-1,-1,-1,gpm);
			v.add(gc);
		}
	}

	public String getType()
	{
		return type;
	}
	public String[][] getParentProps()
	{
		return par_props;
	}
	public Vector<GuiDComponent> getComponentVector()
	{
		return v;
	}

}

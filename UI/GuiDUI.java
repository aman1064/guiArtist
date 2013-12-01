/**
 *
 * @author Aman
 */
package UI;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import Util.ChangeAdapter;
import javax.swing.event.ChangeEvent;

import UI.*;
import Session.*;

public class GuiDUI extends JFrame 
{
	GuiDPropertiesPanel curr_gpp=null;
	GuiDPropertiesPanel par_gpp=null;
	GuiDUserPanel gup;
	Container c;
	JTabbedPane tb;
	public GuiDUI(GuiDMenuBar mbr,GuiDToolBar tbr,GuiDToolBox gtb,GuiDUserPanel gp)
	{
		super("Java Gui Designer");
		this.gup= gp;
		c = new Container();
		c.setLayout(new BorderLayout());
		getContentPane().add(c);

		Image img = getToolkit().createImage("icons/GuiD_icon.png");
		this.setIconImage(img);
		setDefaultLookAndFeelDecorated(true);

		setJMenuBar(mbr);
		c.add(tbr,BorderLayout.NORTH);
		c.add(gtb,BorderLayout.WEST);
		c.add(new JScrollPane(gup),BorderLayout.CENTER);
	
		tb = new JTabbedPane();
		c.add(tb,BorderLayout.EAST);

		tb.addChangeListener(new ChangeAdapter()
		{
			public void stateChanged(ChangeEvent ce)
			{
				if(tb.getSelectedIndex()==0)
				{
					if(!gup.type.contains("Applet"))
					 gup.setUsrPanelTitle(((JTextField)par_gpp.getPropComponent(0)).getText());
				}
				else
				{
					gup.setCmpSelection(((JTextField)curr_gpp.getPropComponent(0)).getText());
				}
			}
		});

		setSize(1024,768);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	public void removePropertiesPanel()
	{
		if(curr_gpp!=null)
		{
			tb.removeTabAt(1);
			curr_gpp=null;
		}
		if(!gup.type.contains("Applet"))
			 gup.setUsrPanelTitle(((JTextField)par_gpp.getPropComponent(0)).getText());


	}
	public void addParentPropPanel(GuiDPropertiesPanel pp)
	{
		par_gpp=pp;
		tb.addTab(pp.getPropTitle(),pp);
	}
	public void updatePropertiesPanel(GuiDPropertiesPanel pp)
	{
		if(curr_gpp!=null)
		{
			tb.removeTabAt(1);
		}
		curr_gpp=pp;
		tb.addTab(curr_gpp.getPropTitle(),curr_gpp);
		tb.setSelectedIndex(1);
		tb.updateUI();
		curr_gpp.updatePanel();
	}
}

/**
 *
 * @author Aman
 */
package Session;

import UI.GuiDUserPanel;
import UI.GuiDPropertiesPanel;
import Session.*;
import Util.*;

import java.awt.event.*;
import java.awt.Color;
import javax.swing.*;
import java.awt.Container;


public class GuiDParentPropPanel
{
	private GuiDUserPanel gup; 
	private JInternalFrame frm;
	private String props[][];
	private GuiDPropertiesPanel gpp;
	private int width,height,min=0;
	public GuiDParentPropPanel(GuiDUserPanel gup)
	{
		props = new String[7][3];
		this.gup = gup;
		if(gup.type.contains("Applet"))
		  min=1;
		frm = gup.getUserPanel();
		initProperties();
		width =400;
		height=400;
		createPropPanel();
		addResizeListener();
	}
	public GuiDParentPropPanel(GuiDUserPanel gup,String props[][])
	{
		this.props=props;
		this.gup = gup;
		if(gup.type.contains("Applet"))
		  min=1;
		frm = gup.getUserPanel();
		width=Integer.parseInt(props[1][2]);
		height=Integer.parseInt(props[2][2]);
		setProperties();
		createPropPanel();
		addResizeListener();
	}
	private void addResizeListener()
	{
		frm.addComponentListener( new ComponentAdapter()
		{
			public void componentResized(ComponentEvent ce)
			{
				width=frm.getWidth();
				props[1][2]=String.valueOf(width);
				((JTextField)gpp.getPropComponent(1-min)).setText(String.valueOf(width));

				height=frm.getHeight();
				props[2][2]=String.valueOf(height);
				((JTextField)gpp.getPropComponent(2-min)).setText(String.valueOf(height));
			}
		});

	}
	private void setProperties()
	{
		gup.setUsrPanelTitle(props[0][2]);
		frm.setSize(width,height);
		frm.getContentPane().setForeground(new Color(Integer.parseInt(props[3][2])));
		frm.getContentPane().setBackground(new Color(Integer.parseInt(props[4][2])));
		frm.setResizable(Boolean.parseBoolean(props[6][2]));
	}
	public String[][] getPropertiesArray()
	{
		return props;
	}
	public GuiDPropertiesPanel getPropPanel()
	{
		return gpp;
	}
	public String getPropTitle()
	{
		return props[0][2];
	}
	private void initProperties()
	{
		props[0][0]="Title";
		props[0][1]="String";
		props[0][2]=gup.getUsrPanelTitle();

		props[1][0]="Width";
		props[1][1]="int";
		props[1][2]="400";

		props[2][0]="Height";
		props[2][1]="int";
		props[2][2]="400";

		props[3][0]="Foreground";
		props[3][1]="int";
		props[3][2]=String.valueOf(frm.getForeground().getRGB());

		props[4][0]="Background";
		props[4][1]="int";
		props[4][2]=String.valueOf(frm.getBackground().getRGB());

		props[5][0]="Visible";
		props[5][1]="boolean";
		props[5][2]="true";

		props[6][0]="Resizable";
		props[6][1]="boolean";
		props[6][2]="true";

	}

	private void createPropPanel()
	{
		gpp = new GuiDPropertiesPanel();
		gpp.setPropTitle(gup.type);

		if(!(gup.type.contains("Applet")))
		{
			JTextField p1 = new JTextField(props[0][2],10);
			p1.addKeyListener(new KeyAdapter()
			{
				public void keyTyped(KeyEvent ke)
				{
					JTextField tm = (JTextField)ke.getSource();
					props[0][2] = tm.getText();
					gup.setUsrPanelTitle(props[0][2]);
					frm.updateUI();
				}
			});
			gpp.addProperty(new JLabel("Title"),p1);
		}

		JTextField p2 = new JTextField(props[1][2],10);
		p2.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent ke)
			{
				JTextField tm = (JTextField)ke.getSource();
				if(!tm.getText().equals(""))
				{
					props[1][2] = tm.getText();
					frm.setSize(Integer.parseInt(props[1][2]),Integer.parseInt(props[2][2]));
					frm.updateUI();
				}
			}
		});
		gpp.addProperty(new JLabel("Width"),p2);

		JTextField p3 = new JTextField(props[2][2],10);
		p3.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent ke)
			{
				JTextField tm = (JTextField)ke.getSource();
				if(!tm.getText().equals(""))
				{
					props[2][2] = tm.getText();
					frm.setSize(Integer.parseInt(props[1][2]),Integer.parseInt(props[2][2]));
					frm.updateUI();
				}
			}
		});
		gpp.addProperty(new JLabel("Height"),p3);

		JButton p4 = new JButton("Frg Color");
		p4.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent ke)
			{
				Color col = JColorChooser.showDialog(null,"Foreground Color Chooser",frm.getForeground());
				if(col!=null)
				{
					props[3][2]=String.valueOf(col.getRGB());
					frm.getContentPane().setForeground(col);
					frm.updateUI();
				}
			}
		});
		gpp.addProperty(new JLabel("Foreground Color"),p4);

		JButton p5 = new JButton("Bck Color");
		p5.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent ke)
			{
				Color col = JColorChooser.showDialog(null,"Background Color Chooser",frm.getBackground());
				if(col!=null)
				{
					props[4][2]=String.valueOf(col.getRGB());
					frm.getContentPane().setBackground(col);
					frm.updateUI();
				}
			}
		});
		gpp.addProperty(new JLabel("Background Color"),p5);


		String bl[]={"true","false"};
		JComboBox p6 = new JComboBox(bl);
		p6.addItemListener(new ItemAdapter()
		{
			public void itemStateChanged(ItemEvent e)
			{
				JComboBox tm = (JComboBox)e.getSource();
				props[5][2] = String.valueOf(tm.getSelectedItem());
			}
		});
		gpp.addProperty(new JLabel("Visible"),p6);

		JComboBox p7 = new JComboBox(bl);
		p7.addItemListener(new ItemAdapter()
		{
			public void itemStateChanged(ItemEvent e)
			{
				JComboBox tm = (JComboBox)e.getSource();
				props[6][2] = String.valueOf(tm.getSelectedItem());
				frm.setResizable(Boolean.parseBoolean(props[6][2]));
			}
		});
		gpp.addProperty(new JLabel("Resizable"),p7);
		
	}
}

/**
 *
 * @author Aman
 */
package Session;
import Util.*;
import UI.GuiDPropertiesPanel;

import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.text.JTextComponent;
public class GuiDComponent
{
	private String props[][];
	private String cast;
	private int x,y;
	GuiDPropertiesPanel gpp;
	JComponent comp;
	private int i;
	MouseEvent pressed;
	Point location;
	private int wi,hi;
	boolean set;
	iGuiDPropMessenger igpm;
	private int re_cur;
	public GuiDComponent(String cast,int no,int x,int y,iGuiDPropMessenger gpm)
	{
		this.cast = cast;
		this.x = x;
		this.y = y;
		igpm=gpm;
		set=false;

		props = GuiDPropReader.getProperties(cast,no,x,y);
		if(cast.contains("Text")||cast.equals("JPasswordField")||cast.contains("List"))
		{
			props[6][2]="-1";
		}
		createComponent();
		createPropertiesPanel();
		makeMovable();
	}
	public GuiDComponent(String cast,String props[][],int no,int x,int y,iGuiDPropMessenger gpm)
	{
		this.cast = cast;
		this.x = x;
		this.y = y;
		igpm=gpm;
		this.props = props;
		if(no!=-1)
		{
		  props[0][2] += "_"+String.valueOf(no);
		  props[1][2] = String.valueOf(x);
		  props[2][2] = String.valueOf(y);
		}
		set=false;
		createComponent();
		createPropertiesPanel();
		makeMovable();
	}
	public void finalize()
	{
		try
		{
		   super.finalize();	
		}
		catch (Throwable e)
		{
			System.out.println(e);
		}
		
	}
	private void makeMovable()
	{
		comp.addMouseListener(new MouseAdapter()
		{
				public void mousePressed(MouseEvent me)
				{
					pressed = me;
					wi = comp.getWidth();
					hi = comp.getHeight();

				}
			});
		comp.addMouseMotionListener(new MouseMotionAdapter(){
			
			public void mouseMoved(MouseEvent e) 
			{
					int xh= comp.getX()+comp.getWidth();
					int yh = comp.getY()+comp.getHeight();
					set=false;
					if(e.getX()<=(xh-re_cur)&&(e.getX()>=comp.getWidth()-(10+re_cur))&&e.getY()<=yh&&(e.getY()>=comp.getHeight()-10))
					{
						comp.setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
						set=true;
					}
					else
					{
						comp.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					}

			}
			public void mouseDragged(MouseEvent me)
			{

				if(!set)
				{
					location = comp.getLocation(location);
					int x = location.x - pressed.getX() + me.getX();
					int y = location.y - pressed.getY() + me.getY();
					if(x<0)
						x=0;
					if(y<0)
						y=0;
					if(x>(comp.getParent().getWidth()-comp.getWidth()))
						x=comp.getParent().getWidth()-comp.getWidth();
					if(y>(comp.getParent().getHeight()-comp.getHeight()))
						y=comp.getParent().getHeight()-comp.getHeight();
					comp.setLocation(x, y);
					if(!(comp instanceof JTextComponent))
						 ((JComponent)comp).updateUI();
					Component tmp[] = gpp.getPropComponents();
					((JTextField)tmp[3]).setText(String.valueOf(x));
					((JTextField)tmp[5]).setText(String.valueOf(y));
					props[1][2]=String.valueOf(x);
					props[2][2]=String.valueOf(y);
				}
				else
				{
					int w = wi+me.getX()-pressed.getX();
					int h = hi+me.getY()-pressed.getY();
					if(w<25)
					{
						w=25;
					}
					if(h<25)
					{
						h=25;
					}
					comp.setSize(w,h);
					if(!(comp instanceof JTextComponent))
					 ((JComponent)comp).updateUI();
					Component tmp[] = gpp.getPropComponents();
					((JTextField)tmp[7]).setText(String.valueOf(w));
					((JTextField)tmp[9]).setText(String.valueOf(h));
					props[3][2]=String.valueOf(w);
					props[4][2]=String.valueOf(h);
				}
			 }
		});
	}
	public String[][] getPropertiesArray()
	{
		return props;
	}
	public String getCast()
	{
		return cast;
	}
	public String getVariableName()
	{
		return props[0][2];
	}
	public String getSelString()
	{
		return props[0][2]+"( "+cast+" )";
	}
	public GuiDPropertiesPanel getPropertiesPanel()
	{
		return gpp;
	}
	public JComponent getDispComponent()
	{
		return comp;
	}
	private void createComponent()
	{
		if(cast.equals("Button")||cast.equals("JButton"))
		{
				comp = new JButton(props[8][2]);
		}
		else if(cast.equals("Label")||cast.equals("JLabel"))
		{
				comp = new JLabel(props[8][2]);
		}
		else if(cast.equals("CheckBox")||cast.equals("JCheckBox"))
		{
				comp = new JCheckBox(props[8][2]);
				((JCheckBox)comp).setBorderPainted(true);
		}
		else if(cast.equals("TextField")||cast.equals("JTextField"))
		{
				comp = new JTextField(props[8][2],Integer.parseInt(props[9][2]));
		}
		else if(cast.equals("TextArea")||cast.equals("JTextArea"))
		{
				comp = new JTextArea(props[8][2],Integer.parseInt(props[9][2]),Integer.parseInt(props[10][2]));
		}
		else if(cast.equals("List")||cast.equals("JList"))
		{
				comp = new JList();
		}
		else if(cast.equals("Choice")||cast.equals("JComboBox"))
		{
				comp = new JComboBox();
		}
		else if(cast.equals("JRadioButton"))
		{
				comp = new JRadioButton(props[8][2]);
				((JRadioButton)comp).setBorderPainted(true);
		}
		else if(cast.equals("JPasswordField"))
		{
				comp = new JPasswordField(props[8][2],Integer.parseInt(props[9][2]));

				((JPasswordField)comp).setEchoChar(props[10][2].charAt(0));
		}
		/*else
		{//For Further Enchancement
			try
			{
				Class Txt = Class.forName("javax.swing."+cast);
				comp = (JComponent)Txt.newInstance();
			}
			catch (Exception e)
			{
				System.out.println(e);
			}
		}*/
		if(comp instanceof JComboBox)
		{
			re_cur =20;
		}
		else
		{
			re_cur = 0;
		}
		comp.setLocation(Integer.parseInt(props[1][2]),Integer.parseInt(props[2][2]));
		comp.setSize(Integer.parseInt(props[3][2]),Integer.parseInt(props[4][2]));
		((JComponent)comp).setOpaque(true);
		if(cast.startsWith("J"))
		  ((JComponent)comp).setBorder(BorderFactory.createLineBorder(Color.black));
		else
		  ((JComponent)comp).setBorder(BorderFactory.createLineBorder(Color.yellow));	

		comp.setForeground(new Color(Integer.parseInt(props[5][2])));
		comp.setBackground(new Color(Integer.parseInt(props[6][2])));

		comp.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent cme)
			{
				requestPanelChange();
			}
			public void mouseExited(MouseEvent me)
			{
				if(me.getSource() instanceof JToggleButton)
				    mouseClicked(me);
			}
			public void mouseClicked(MouseEvent cme)
			{
				Component tmp = (Component)cme.getSource();
				if(tmp instanceof JToggleButton)
				{
					JComboBox tm=(JComboBox)gpp.getPropComponent(9);
					tm.setSelectedItem(String.valueOf(((JToggleButton)comp).isSelected()));
				}

			}
		});
		if(comp instanceof JTextComponent)
			((JTextComponent)comp).setEditable(false);
	}
	public void requestPanelChange()
	{
		igpm.changePropPanel(this);
	}
	private void createPropertiesPanel()
	{
		gpp = new GuiDPropertiesPanel();
		gpp.setPropTitle(cast);

		JTextField p1 = new JTextField(props[0][2],10);
		
		p1.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent ke)
			{
				JTextField tm = (JTextField)ke.getSource();
				props[0][2] = tm.getText();
				igpm.updateCompSel(props[0][2]+"( "+cast+" )");
			}
		});
		gpp.addProperty(new JLabel("Variable Name"),p1);

		JTextField p2 = new JTextField(props[1][2],10);
		p2.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent ke)
			{
				JTextField tm = (JTextField)ke.getSource();
				if(!tm.getText().equals(""))
				{
					props[1][2] = tm.getText();
					comp.setLocation(Integer.parseInt(props[1][2]),Integer.parseInt(props[2][2]));
					comp.updateUI();
					comp.validate();
				}
			}
		});
		gpp.addProperty(new JLabel("Location X"),p2);

		JTextField p3 = new JTextField(props[2][2],10);
		p3.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent ke)
			{
				JTextField tm = (JTextField)ke.getSource();
				if(!tm.getText().equals(""))
				{
					props[2][2] = tm.getText();
					comp.setLocation(Integer.parseInt(props[1][2]),Integer.parseInt(props[2][2]));
					comp.updateUI();
					comp.validate();
				}
			}
		});
		gpp.addProperty(new JLabel("Location Y"),p3);

		JTextField p4 = new JTextField(props[3][2],10);
		p4.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent ke)
			{
				JTextField tm = (JTextField)ke.getSource();
				if(!tm.getText().equals(""))
				{
					props[3][2] = tm.getText();
					comp.setSize(Integer.parseInt(props[3][2]),Integer.parseInt(props[4][2]));
					comp.updateUI();
					comp.validate();
				}
			}
		});
		gpp.addProperty(new JLabel("Width"),p4);

		JTextField p5 = new JTextField(props[4][2],10);
		p5.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent ke)
			{
				JTextField tm = (JTextField)ke.getSource();
				if(!tm.getText().equals(""))
				{
					props[4][2] = tm.getText();
					comp.setSize(Integer.parseInt(props[3][2]),Integer.parseInt(props[4][2]));
					comp.updateUI();
					comp.validate();
				}
			}
		});
		gpp.addProperty(new JLabel("Height"),p5);

		JButton p6 = new JButton("Frg Color");
		p6.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent ke)
			{
				Color col = JColorChooser.showDialog(null,"Foreground Color Chooser",new Color(Integer.parseInt(props[5][2])));
				if(col!=null)
				{
					props[5][2]=String.valueOf(col.getRGB());
					comp.setForeground(col);
					comp.updateUI();
					comp.validate();
				}
			}
		});
		gpp.addProperty(new JLabel("Foreground Color"),p6);

		JButton p7 = new JButton("Bck Color");
		p7.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent ke)
			{
				Color col = JColorChooser.showDialog(null,"Background Color Chooser",new Color(Integer.parseInt(props[6][2])));
				if(col!=null)
				{
					props[6][2]=String.valueOf(col.getRGB());
					comp.setBackground(col);
					comp.updateUI();
					comp.validate();
				}
			}
		});
		gpp.addProperty(new JLabel("Background Color"),p7);



		String bl[]={"true","false"};
		JComboBox p8 = new JComboBox(bl);
		p8.addItemListener(new ItemAdapter()
		{
			public void itemStateChanged(ItemEvent e)
			{
				JComboBox tm = (JComboBox)e.getSource();
				props[7][2] = String.valueOf(tm.getSelectedItem());
			}
		});
		gpp.addProperty(new JLabel("Visible"),p8);
		
		for(i=8;props[i][0]!=null;i++)
		{
			if(props[i][1].equals("Boolean"))
			{
					String bi[]={"false","true"};
					JComboBox pi = new JComboBox(bi);
					pi.addItemListener(new ItemAdapter()
					{
						public void itemStateChanged(ItemEvent e)
						{
							JComboBox tm = (JComboBox)e.getSource();
							int j=gpp.getCompIndex(tm);
							props[j][2] = String.valueOf(tm.getSelectedItem());
							if(comp instanceof JToggleButton)
							{
								((JToggleButton)comp).setSelected(Boolean.parseBoolean(props[j][2]));
							}
							else if (cast.equals("JComboBox"))
							{
								((JComboBox)comp).setEditable(Boolean.parseBoolean(props[j][2]));
								if(!Boolean.parseBoolean(props[j][2]))
									if (((JComboBox)comp).getItemCount()!=0 )
									{
									   ((JComboBox)comp).setSelectedIndex(0);
									}
									
							}
						}
					});
					gpp.addProperty(new JLabel(props[i][0]),pi);
			}
			else if(props[i][1].equals("int")||props[i][1].equals("char")||props[i][1].equals("String"))
			{
				JTextField pi1 = new JTextField(props[i][2],10);
				pi1.addKeyListener(new KeyAdapter()
				{
					public void keyTyped(KeyEvent ke)
					{
						JTextField tm = (JTextField)ke.getSource();
						int j=gpp.getCompIndex(tm);
						props[j][2] = tm.getText();
						if(props[j][0].equals("Label")||props[j][0].equals("Text"))
						{
							if(comp instanceof AbstractButton)
							{
								 ((AbstractButton)comp).setText(props[j][2]);
							}
							else if(comp instanceof JLabel)
							{
								((JLabel)comp).setText(props[j][2]);
							}
							else if(comp instanceof JTextComponent)
							{
								((JTextComponent)comp).setText(props[j][2]);
							}
						}
						else if (props[j][0].equals("EchoChar"))
						{
							if(!props[j][2].equals(""))
							 ((JPasswordField)comp).setEchoChar(props[j][2].charAt(0));
							else
							{
								props[j][2]=String.valueOf(((JPasswordField)comp).getEchoChar());
							}
						}
						else if (props[j][0].equals("ToolTipText"))
						{
							((JComponent)comp).setToolTipText(props[j][2]);
						}
						else if(props[j][0].equals("Contents"))
						{
							if(!props[j][2].equals("$")&&!props[j][2].equals(""))
							{
								String tmp[]=props[j][2].split(",");
								if(cast.contains("List"))
								{
									((JList)comp).setListData(tmp);
								}
								else
								{
									((JComboBox)comp).removeAllItems();
									for(int ti=0;ti<tmp.length;ti++)
									{
										((JComboBox)comp).addItem(tmp[ti]);
									}
								}
							}
						}
					}
				});
				gpp.addProperty(new JLabel(props[i][0]),pi1);
			}
		}
	}
}

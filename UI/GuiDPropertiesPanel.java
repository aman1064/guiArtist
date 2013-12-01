package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import UI.*;

public class GuiDPropertiesPanel extends JDesktopPane 
{
	private JInternalFrame propfrm;
	private Container propcont;
	private GridBagConstraints gbc;
	private int x,y;
	private String title;
	public GuiDPropertiesPanel()
	{
		setPreferredSize(new Dimension(260,668));
		setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
		setAutoscrolls(true);
		propfrm = new JInternalFrame("",false,false,false,false);
		title="";
		propfrm.setFrameIcon(new ImageIcon("icons/GuiD_icon1.png"));
		propfrm.setSize(260,668);
		propfrm.setVisible(true);
		propfrm.addComponentListener(new ComponentAdapter(){
	    public void componentMoved(ComponentEvent ce) 
		  {
		          propfrm.setLocation(new Point(0,0));
		  }
		});
		propcont = new Container();
		gbc = new GridBagConstraints();
		gbc.insets=new Insets(5,5,5,5);
		propcont.setLayout(new GridBagLayout());
		x=1;
		y=1;
		super.add(propfrm);
    	propfrm.getContentPane().add(propcont);
		propfrm.toFront();
	}
	public void setPropTitle(String title)
	{
		this.title=title;
		propfrm.setTitle(title);
	}
	public String getPropTitle()
	{
		return title;
	}
    public void addProperty(JLabel lbl,JComponent cmp)
	{
		gbc.gridx=x;
		gbc.gridy=y;
		propcont.add(lbl,gbc);
		gbc.gridx=++x;
		propcont.add(cmp,gbc);
		y++;
		x--;
		propcont.invalidate();
	}
	public void updatePanel()
	{
		propfrm.toFront();
		propfrm.updateUI();
	}
	public int getCompIndex(Component cmp)
	{
		Component c[]=getPropComponents();
		for(int i=16;c[i]!=null;i++)
		{
			if(c[i].equals(cmp))
				return i/2;
		}
		return -1;
	}
	public Component getPropComponent(int index)
	{
		int t=(index+1)*2-1;
		return propcont.getComponent(t);
	}
	public Component[] getPropComponents()
	{
		return propcont.getComponents();
	}
}

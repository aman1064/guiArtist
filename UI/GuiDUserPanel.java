package UI;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class GuiDUserPanel extends JDesktopPane
{
	 private JInternalFrame usr_frm;
	 public String type;
	 private String title;
	 public GuiDUserPanel(String type)
	{
		this.type = type;
		setPreferredSize(new Dimension(2000,2000));
	    setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
		usr_frm = new JInternalFrame("",true,false,false,false);
		usr_frm.setFrameIcon(new ImageIcon("icons/GuiD_icon1.png"));
		if(type.equals("Frame")||type.equals("JFrame"))
		{
			 title = "untitled ( "+type+" )";
		}
		else
		{
			title = type;
		}
		 usr_frm.setTitle(title);
		usr_frm.setSize(400,400);
		usr_frm.setVisible(true);
		usr_frm.addComponentListener(new ComponentAdapter(){
			
		   public void componentMoved(ComponentEvent ce) 
			  {
			          usr_frm.setLocation(new Point(0,0));
			  }
		});
		usr_frm.setLayout(null);
		super.add(usr_frm);
		this.setSelectedFrame(usr_frm);
	}
	public JInternalFrame getUserPanel()
	{
		return usr_frm;
	}
	public String getUsrPanelTitle()
	{
		return title;
	}
	public void setCmpSelection(String s)
	{
		usr_frm.setTitle(title+"- Selection : "+s);
	}
	public void setUsrPanelTitle(String t)
	{
		title =t;
		usr_frm.setTitle(title);
	}
	public void addCmp(Component cmp)
	{
		usr_frm.getContentPane().add(cmp);
		usr_frm.getContentPane().validate();
		((JComponent)cmp).updateUI();
	}
	public void removeCmp(Component cmp)
	{
		usr_frm.getContentPane().remove(cmp);
		usr_frm.updateUI();
		usr_frm.getContentPane().validate();
	}
	public void removeAllCmp()
	{
		usr_frm.getContentPane().removeAll();
		usr_frm.getContentPane().validate();
	}
}
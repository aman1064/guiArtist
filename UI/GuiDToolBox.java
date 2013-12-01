package UI;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.*;

public class GuiDToolBox extends JDesktopPane 
{
	JInternalFrame toolfrm;
	Container toolcont,toolcont1;
	JButton but,lbl,radbut,chk,txtfld,txtarea,lst,cmb,passfld;
	JButton but1,lbl1,chk1,txtfld1,txtarea1,lst1,cmb1;
	JTabbedPane tb;

	public GuiDToolBox()
	{
		setPreferredSize(new Dimension(165,668));
		setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
		tb = new JTabbedPane();
		toolfrm = new JInternalFrame("ToolBox",false,false,false,false);
		toolfrm.setFrameIcon(new ImageIcon("icons/GuiD_icon1.png"));
		toolfrm.setSize(165,670);
		toolfrm.setVisible(true);
		toolfrm.addComponentListener(new ComponentAdapter(){
		  public void componentMoved(ComponentEvent ce) 
		  {
		          toolfrm.setLocation(new Point(0,0));
		  }
		});

		toolcont = new Container();
		toolcont.setLayout(new FlowLayout(FlowLayout.CENTER,0,20));
		toolcont1 = new Container();
		toolcont1.setLayout(new FlowLayout(FlowLayout.CENTER,0,20));

		but = new JButton(new ImageIcon("component_icons/JButton.gif"));
		lbl = new JButton(new ImageIcon("component_icons/JLabel.gif"));
		radbut = new JButton(new ImageIcon("component_icons/JRadioButton.gif"));
		chk = new JButton(new ImageIcon("component_icons/JCheckBox.gif"));
		txtfld = new JButton(new ImageIcon("component_icons/JTextField.gif"));
		passfld = new JButton(new ImageIcon("component_icons/JPasswordField.gif"));
		txtarea = new JButton(new ImageIcon("component_icons/JTextArea.gif"));
		lst = new JButton(new ImageIcon("component_icons/JList.gif"));
		cmb = new JButton(new ImageIcon("component_icons/JComboBox.gif"));

		but1 = new JButton(new ImageIcon("component_icons/Button.gif"));
		lbl1 = new JButton(new ImageIcon("component_icons/Label.gif"));
		chk1 = new JButton(new ImageIcon("component_icons/CheckBox.gif"));
		txtfld1 = new JButton(new ImageIcon("component_icons/TextField.gif"));
		txtarea1 = new JButton(new ImageIcon("component_icons/TextArea.gif"));
		lst1 = new JButton(new ImageIcon("component_icons/List.gif"));
		cmb1 = new JButton(new ImageIcon("component_icons/Choice.gif"));

		setBorders();
		setActionCommands();
		addTools();
		tb.addTab("Swing ToolBox",toolcont);
		tb.addTab("AWT ToolBox",toolcont1);
		toolfrm.getContentPane().add(tb);
		super.add(toolfrm);
		toolfrm.show();
	}
	public void setToolboxSelection(String s)
	{
		toolfrm.setTitle("ToolBox : "+s);
	}
	public void clearToolboxSelection()
	{
		toolfrm.setTitle("ToolBox");
	}
	private void setBorders()
	{
		but.setBorderPainted(false);
		lbl.setBorderPainted(false);
		chk.setBorderPainted(false);
		txtfld.setBorderPainted(false);
		txtarea.setBorderPainted(false);
		lst.setBorderPainted(false);
		cmb.setBorderPainted(false);
		radbut.setBorderPainted(false);
		passfld.setBorderPainted(false);

		but1.setBorderPainted(false);
		lbl1.setBorderPainted(false);
		chk1.setBorderPainted(false);
		txtfld1.setBorderPainted(false);
		txtarea1.setBorderPainted(false);
		lst1.setBorderPainted(false);
		cmb1.setBorderPainted(false);
	}
	private void addTools()
	{
		toolcont.add(but);
		toolcont.add(lbl);
		toolcont.add(chk);
		toolcont.add(txtfld);
		toolcont.add(radbut);
		toolcont.add(passfld);
		toolcont.add(txtarea);
		toolcont.add(lst);
		toolcont.add(cmb);

		toolcont1.add(but1);
		toolcont1.add(lbl1);
		toolcont1.add(chk1);
		toolcont1.add(txtfld1);
		toolcont1.add(txtarea1);
		toolcont1.add(lst1);
		toolcont1.add(cmb1);

	}
	private void setActionCommands()
	{
		but.setActionCommand("JButton");
		lbl.setActionCommand("JLabel");
		chk.setActionCommand("JCheckBox");
		txtfld.setActionCommand("JTextField");
		txtarea.setActionCommand("JTextArea");
		lst.setActionCommand("JList");
		cmb.setActionCommand("JComboBox");
		radbut.setActionCommand("JRadioButton");
		passfld.setActionCommand("JPasswordField");

		but1.setActionCommand("Button");
		lbl1.setActionCommand("Label");
		chk1.setActionCommand("CheckBox");
		txtfld1.setActionCommand("TextField");
		txtarea1.setActionCommand("TextArea");
		lst1.setActionCommand("List");
		cmb1.setActionCommand("Choice");

	}
	public void addListeners(ActionListener al)
	{
		but.addActionListener(al);
		lbl.addActionListener(al);	
		chk.addActionListener(al);
		txtfld.addActionListener(al);
		txtarea.addActionListener(al);
		lst.addActionListener(al);
		cmb.addActionListener(al);
		radbut.addActionListener(al);
		passfld.addActionListener(al);

		but1.addActionListener(al);
		lbl1.addActionListener(al);	
		chk1.addActionListener(al);
		txtfld1.addActionListener(al);
		txtarea1.addActionListener(al);
		lst1.addActionListener(al);
		cmb1.addActionListener(al);

	}
}

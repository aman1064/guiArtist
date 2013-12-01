package UI;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Color;

public class GuiDToolBar extends JToolBar
{
	JButton tb_close,tb_save,tb_saveas;
	JButton	tb_cut,tb_copy,tb_paste,tb_delete;
	JButton tb_code,tb_help;
	JButton tb_exit;
	public GuiDToolBar()
	{
		makeToolBar();
		setFloatable(false);
		setBackground(Color.lightGray);
	}
	void makeToolBar()
	{
		tb_close = new JButton(new ImageIcon("icons/close.png"));
		tb_close.setToolTipText("Close Project");
		tb_close.setActionCommand("close");

		tb_save = new JButton(new ImageIcon("icons/save.png"));
		tb_save.setToolTipText("Save Project");
		tb_save.setActionCommand("save");

		tb_saveas = new JButton(new ImageIcon("icons/saveas.png"));
		tb_saveas.setToolTipText("Save Java File");
		tb_saveas.setActionCommand("scode");


		tb_cut = new JButton(new ImageIcon("icons/cut.png"));
		tb_cut.setToolTipText("Cut");
		tb_cut.setActionCommand("cut");

		tb_copy = new JButton(new ImageIcon("icons/copy.png"));
		tb_copy.setToolTipText("Copy");
		tb_copy.setActionCommand("copy");

		tb_paste = new JButton(new ImageIcon("icons/paste.png"));
		tb_paste.setToolTipText("Paste");
		tb_paste.setActionCommand("paste");

		tb_delete = new JButton(new ImageIcon("icons/delete.png"));
		tb_delete.setToolTipText("Delete");
		tb_delete.setActionCommand("delete");

		tb_code = new JButton(new ImageIcon("icons/java.png"));
		tb_code.setToolTipText("View Java Code");
		tb_code.setActionCommand("code");

		tb_help = new JButton(new ImageIcon("icons/help.png"));
		tb_help.setToolTipText("View Help");
		tb_help.setActionCommand("help");

		tb_exit = new JButton(new ImageIcon("icons/exit.png"));
		tb_exit.setToolTipText("Exit JGuiD");
		tb_exit.setActionCommand("exit");

		addSeparator(new Dimension(30,35));
		add(tb_save);
		add(tb_saveas);
		add(tb_close);
		addSeparator(new Dimension(30,35));
		add(tb_cut);
		add(tb_copy);
		add(tb_paste);
		add(tb_delete);
		addSeparator(new Dimension(30,35));
		add(tb_code);
		addSeparator(new Dimension(30,35));
		add(tb_help);
		addSeparator(new Dimension(30,35));
		add(tb_exit);

	}
	public void addListeners(ActionListener al)
	{
		tb_close.addActionListener(al);
		tb_save.addActionListener(al);
		tb_saveas.addActionListener(al);

		tb_cut.addActionListener(al);
		tb_copy.addActionListener(al);
		tb_paste.addActionListener(al);
		tb_delete.addActionListener(al);

		tb_code.addActionListener(al);
		tb_help.addActionListener(al);
		tb_exit.addActionListener(al);
	}
}

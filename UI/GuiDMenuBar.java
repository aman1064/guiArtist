package UI;

import javax.swing.*;
import java.awt.event.*;

// This class control the entire menu bar
public class GuiDMenuBar extends JMenuBar
{
	JMenu m_file,m_edit,m_view,m_help,mi_v_LnF;
	JMenuItem mi_f_new,mi_f_open,mi_f_close,mi_f_save,mi_f_saveas,mi_f_exit;
	JMenuItem mi_e_cut,mi_e_copy,mi_e_paste,mi_e_delete;
	JMenuItem mi_v_motif,mi_v_java,mi_v_windows,mi_v_code;
	JMenuItem mi_h_help,mi_h_about;
	public GuiDMenuBar()
	{
		setBorderPainted(true);
		makeFileMenu();
		makeEditMenu();
		makeViewMenu();
		makeHelpMenu();
	}
	
	void makeFileMenu()
	{
		m_file = new JMenu("File");
		m_file.setMnemonic('F');

		mi_f_new = new JMenuItem("New",new ImageIcon("icons/new_project.png"));
		mi_f_new.setMnemonic('N');
		mi_f_open = new JMenuItem("Open",new ImageIcon("icons/open_project.png"));
		mi_f_open.setMnemonic('O');
		mi_f_close = new JMenuItem("Close",new ImageIcon("icons/close.png"));
		mi_f_close.setMnemonic('C');
		mi_f_save = new JMenuItem("Save",new ImageIcon("icons/save.png"));
		mi_f_save.setMnemonic('S');
		mi_f_saveas = new JMenuItem("Save Java File",new ImageIcon("icons/saveas.png"));
		mi_f_saveas.setMnemonic('J');
		mi_f_exit = new JMenuItem("Exit",new ImageIcon("icons/exit.png"));
		mi_f_exit.setMnemonic('X');


		mi_f_new.setAccelerator(KeyStroke.getKeyStroke("control N"));
		mi_f_open.setAccelerator(KeyStroke.getKeyStroke("control O"));
		mi_f_close.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3,InputEvent.ALT_MASK));
		mi_f_save.setAccelerator(KeyStroke.getKeyStroke("control S"));
		mi_f_saveas.setAccelerator(KeyStroke.getKeyStroke("control J"));
		mi_f_exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4,InputEvent.ALT_MASK));

		mi_f_new.setActionCommand("new");
		mi_f_open.setActionCommand("open");
		mi_f_close.setActionCommand("close");
		mi_f_save.setActionCommand("save");
		mi_f_saveas.setActionCommand("scode");
		mi_f_exit.setActionCommand("exit");
		
		m_file.add(mi_f_new);
		m_file.add(mi_f_open);
		m_file.add(mi_f_close);
		m_file.addSeparator();
		m_file.add(mi_f_save);
		m_file.add(mi_f_saveas);
		m_file.addSeparator();
		m_file.add(mi_f_exit);

		add(m_file);
	}
	void makeEditMenu()
	{
		m_edit = new JMenu("Edit");
		m_edit.setMnemonic('E');

		mi_e_cut = new JMenuItem("Cut",new ImageIcon("icons/cut.png"));
		mi_e_cut.setMnemonic('X');
		mi_e_copy = new JMenuItem("copy",new ImageIcon("icons/copy.png"));
		mi_e_copy.setMnemonic('C');
		mi_e_paste = new JMenuItem("Paste",new ImageIcon("icons/paste.png"));
		mi_e_paste.setMnemonic('P');
		mi_e_delete = new JMenuItem("Delete",new ImageIcon("icons/delete.png"));
		mi_e_delete.setMnemonic('D');


		mi_e_cut.setAccelerator(KeyStroke.getKeyStroke("control X"));
		mi_e_copy.setAccelerator(KeyStroke.getKeyStroke("control C"));
		mi_e_paste.setAccelerator(KeyStroke.getKeyStroke("control V"));
		mi_e_delete.setAccelerator(KeyStroke.getKeyStroke("control D"));

		mi_e_cut.setActionCommand("cut");
		mi_e_copy.setActionCommand("copy");
		mi_e_paste.setActionCommand("paste");
		mi_e_delete.setActionCommand("delete");
		
		m_edit.add(mi_e_cut);
		m_edit.add(mi_e_copy);
		m_edit.add(mi_e_paste);
		m_edit.addSeparator();
		m_edit.add(mi_e_delete);

		add(m_edit);
	}
	void makeViewMenu()
	{
		m_view = new JMenu("View");
		m_view.setMnemonic('V');
		
		mi_v_LnF = new JMenu("Look and Feel");
		mi_v_LnF.setMnemonic('L');

		mi_v_motif = new JMenuItem("Motif");
		mi_v_motif.setMnemonic('M');
		mi_v_java = new JMenuItem("Java");
		mi_v_java.setMnemonic('J');
		mi_v_windows = new JMenuItem("Windows");
		mi_v_windows.setMnemonic('W');
		mi_v_code = new JMenuItem("View Code");
		mi_v_code.setMnemonic('C');
		
		mi_v_code.setAccelerator(KeyStroke.getKeyStroke("control alt C"));

		mi_v_motif.setActionCommand("look_motif");
		mi_v_java.setActionCommand("look_java");
		mi_v_windows.setActionCommand("look_windows");
		mi_v_code.setActionCommand("code");

		mi_v_LnF.add(mi_v_motif);
		mi_v_LnF.add(mi_v_java);
		mi_v_LnF.add(mi_v_windows);
			
		m_view.add(mi_v_LnF);
		m_view.addSeparator();
		m_view.add(mi_v_code);

		add(m_view);
	}
	void makeHelpMenu()
	{
		m_help = new JMenu("Help");
		m_help.setMnemonic('H');

		mi_h_help = new JMenuItem("Help",new ImageIcon("icons/help.png"));
		mi_h_help.setMnemonic('H');
		mi_h_about = new JMenuItem("About");
		mi_h_about.setMnemonic('A');
		
		mi_h_help.setAccelerator(KeyStroke.getKeyStroke("F1"));
		mi_h_about.setAccelerator(KeyStroke.getKeyStroke("control A"));

		mi_h_help.setActionCommand("help");
		mi_h_about.setActionCommand("about");

		m_help.add(mi_h_help);
		m_help.addSeparator();
		m_help.add(mi_h_about);

		add(m_help);
	}

	public void addListeners(ActionListener al)
	{
		mi_f_new.addActionListener(al);
		mi_f_open.addActionListener(al);
		mi_f_close.addActionListener(al);
		mi_f_save.addActionListener(al);
		mi_f_saveas.addActionListener(al);
		mi_f_exit.addActionListener(al);

		mi_e_cut.addActionListener(al);
		mi_e_copy.addActionListener(al);
		mi_e_paste.addActionListener(al);
		mi_e_delete.addActionListener(al);

		mi_v_motif.addActionListener(al);
		mi_v_java.addActionListener(al);
		mi_v_windows.addActionListener(al);
		mi_v_code.addActionListener(al);

		mi_h_help.addActionListener(al);
		mi_h_about.addActionListener(al);

	}

}

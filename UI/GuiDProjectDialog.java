/**
 *
 * @author Aman
 */
package UI;

import UI.*;
import Util.iJGuiD;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;


public class GuiDProjectDialog extends JFrame implements ActionListener
{
	JLabel lblHeader;
	JButton app,japp,frm,jfrm,cmdExistingProject,cmdQuit;
	JFileChooser filechooser;
	iJGuiD msg;
    public GuiDProjectDialog(iJGuiD m)
	{
		super("Project Dialog");
		msg = m;
	    Image myIcon = getToolkit().createImage("icons/GuiD_icon.png");
        this.setIconImage(myIcon);
	    int x = 100;
        int y = 150;
        int width = 130; 
        int height = 100;	
		Container c = getContentPane();
        c.setLayout( null );
        c.setBackground( Color.black );
        c.setForeground( Color.white );


		lblHeader = new JLabel( new ImageIcon("images/JGuiD.gif"));
	    lblHeader.setBackground( Color.black );
		lblHeader.setForeground( Color.white );
        lblHeader.setFont( new Font( "Helvetica", 0, 40 ) );
        lblHeader.setBounds( 80, 50, 312, 82 );

        c.add( lblHeader );
	
		 app = new JButton(new ImageIcon("images/applet.gif"));
         app.setBackground( Color.black );
         app.setBounds( x, y, width, height );
         app.addActionListener( this );
         app.setToolTipText("Applet");
         c.add(app);

		  x += 150;
		 japp = new JButton(new ImageIcon("images/japplet.gif"));
         japp.setBackground( Color.black );
         japp.setBounds( x, y, width, height );
         japp.addActionListener( this );
         japp.setToolTipText("JApplet");
         c.add(japp);

		 x -= 150;
         y += 150;

		 frm = new JButton(new ImageIcon("images/frame.gif"));
         frm.setBackground( Color.black );
         frm.setBounds( x, y, width, height );
         frm.addActionListener( this );
         frm.setToolTipText("Frame");
         c.add(frm);

		  x += 150;
		 jfrm = new JButton(new ImageIcon("images/jframe.gif"));
         jfrm.setBackground( Color.black );
         jfrm.setBounds( x, y, width, height );
         jfrm.addActionListener( this );
         jfrm.setToolTipText("JFrame");
         c.add(jfrm);

		  x-=150;	
	     cmdExistingProject = new JButton( "Existing project" );
         cmdExistingProject.setBounds( x, y + 150, width, 30 );
         cmdExistingProject.setBackground( Color.black );
         cmdExistingProject.setForeground( Color.white );
         cmdExistingProject.setFont( new Font( "Helvetica", 0, 10 ) );
         cmdExistingProject.setToolTipText( "Open an existing GuiD Project" );
         cmdExistingProject.addActionListener( this );

         cmdQuit = new JButton( "Quit" );
         cmdQuit.setBounds( x + 150, y+150, width, 30 );
         cmdQuit.setBackground( Color.black );
         cmdQuit.setForeground( Color.white );
         cmdQuit.setFont( new Font( "Helvetica", 0, 10 ) );
         cmdQuit.addActionListener( this );


         c.add( cmdExistingProject );
         c.add( cmdQuit );

         this.pack();
		 this.setSize(500,600);
         this.setVisible(true);
		 this.setResizable(false);
		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

   public void actionPerformed( ActionEvent e )
   {
      int answer = -1;
      String type = null;
      /*
        The user is promptet on exit if confirmdialogs are
        turned on in the GuiDconfig.xml file.
       */
      if( e.getSource() == cmdQuit )
      {
            answer = JOptionPane.showConfirmDialog( null, "Leaving The GuiD so soon?", 
                                                    "GuiD Confirmation", JOptionPane.YES_NO_OPTION ); 

         if( answer == JOptionPane.YES_OPTION)
         {
	System.exit( 0 );
         }
      }
      /*
        Opens existing project.
        Starts the filedialog in the user's "GuiD files path" 
        if so is specified in the GuiDconfig file.
        If not, the dialog is opened in the user home directory.
       */
      else if( e.getSource() == cmdExistingProject )
      {
			JFileChooser jfc = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("GuiD Project File", "GuiD");
			jfc.setFileFilter(filter);
			if(jfc.showOpenDialog(this)==JFileChooser.APPROVE_OPTION)
			{
     		    this.setCursor( new Cursor( Cursor.WAIT_CURSOR ) );
				msg.openExistingProject(jfc.getSelectedFile());
				this.dispose();
			}
      }
      else if( e.getSource() == app )
      {
         type = "Applet";
      }
      else if( e.getSource() == japp )
      {
         type = "JApplet";
      }
      else if( e.getSource() == frm )
      {
         type = "Frame";

      }
      else if( e.getSource() == jfrm )
      {
         type = "JFrame";
      }
      /*
        Sets the type of application in the JGuiD and disposes itself.
        The startNewProject() method in JGuiD then starts the show.
       */
	   if(type!=null)
	   {
		 this.setCursor( new Cursor( Cursor.WAIT_CURSOR ) );
		 msg.startNewProject( type );
         this.dispose();
	   }
   }

}
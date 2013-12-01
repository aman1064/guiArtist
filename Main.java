import UI.*;
import Util.iJGuiD;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Main extends JFrame implements ActionListener
{
	JLabel lblHeader;
	JButton paint,editor,guid,quit;
    public Main()
	{
	super("GUI ARTIST AND JEDITOR");
	Image myIcon = getToolkit().createImage("icons/GuiD_icon.png");
	this.setIconImage(myIcon);
	int x = 20;
	int y = 150;
                 int width = 130; 
                 int height = 100;	
	Container c = getContentPane();
                 c.setLayout( null );
                 c.setBackground( Color.black );
                 c.setForeground( Color.white );
	
	lblHeader = new JLabel( );
	lblHeader = new JLabel( new ImageIcon("images/JDEP.gif"));
	lblHeader.setBackground( Color.black );
	lblHeader.setForeground( Color.white );
                 lblHeader.setFont( new Font( "Helvetica", 0, 40 ) );
                 lblHeader.setBounds( 80, 50, 312, 82 );
                 c.add( lblHeader );
	
	paint = new JButton();
	paint= new JButton(new ImageIcon("images/PAINT.gif"));	
	paint.setBackground( Color.black );
                 paint.setBounds( x, y, width, height );
                 paint.addActionListener( this );
                 paint.setToolTipText("PAINT");
                 c.add(paint);
x += 150;
         editor = new JButton();
         editor= new JButton(new ImageIcon("images/EDITOR.gif"));
         editor.setBackground( Color.black );
         editor.setBounds( x, y, width, height );
         editor.addActionListener( this );
         editor.setToolTipText("EDITOR");
         c.add(editor);
         x += 150;
         //y += 150;
        
        guid= new JButton();
        guid= new JButton(new ImageIcon("images/GUID.gif"));
        guid.setBackground( Color.black );
        guid.setBounds( x, y, width, height );
        guid.addActionListener( this );
        guid.setToolTipText("GUID");
        c.add(guid);
        //x += 150;

         quit = new JButton( "Quit" );
         quit.setBounds( x-150 , y+150, width, 30 );
         quit.setBackground( Color.black );
         quit.setForeground( Color.white );
         quit.setFont( new Font( "Helvetica", 0, 10 ) );
         quit.addActionListener( this );
         quit.setToolTipText("QUIT");
         c.add( quit );

         this.pack();
         this.setSize(500,450);
         this.setVisible(true);
         this.setResizable(false);
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         }

   public void actionPerformed( ActionEvent e )
   {
      int answer = -1;
      String type = null;

      if( e.getSource() == quit )
      {
            answer = JOptionPane.showConfirmDialog( null, "Leaving The GUI Designer so soon?", 
                                                    "GuiD Confirmation", JOptionPane.YES_NO_OPTION ); 

         if( answer == JOptionPane.YES_OPTION)
         {
            System.exit( 0 );
         }
      }
      else if( e.getSource() == paint )
      {
      this.setVisible(false);
      Painter pp=new Painter();
	pp.show();
      }
      else if( e.getSource() == editor )
      {
        this.setVisible(false);
        SimpleTextEditor ste=new SimpleTextEditor();
         ste.init();
      }
      else if( e.getSource() == guid )
      {
        this.setVisible(false);
        JGuiD gui=new JGuiD();
        
      }
      }
public static void main(String[] args) 
	{
		Main tg = new Main();
	}
}

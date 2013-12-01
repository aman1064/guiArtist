package UI;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.html.*;
import java.io.File;
import java.awt.Image;
public class GuiDInfoViewer extends JFrame implements HyperlinkListener
{
	public GuiDInfoViewer(String title,String page,String type)
	{
		super(title);
		Image img = getToolkit().createImage("icons/GuiD_icon.png");
		this.setIconImage(img);
		JEditorPane ep = new JEditorPane();
		ep.setEditable(false);
		ep.setContentType("text/"+type);
		if(type.equals("html"))
		{
			try
			{
			   ep.setPage(new File(page).toURI().toURL());	
			}
			catch (Exception e)
			{
				JOptionPane.showMessageDialog(this," Sorry Cannot Load Page . . .\n Page may be deleted ","Error",0);
			}
		}
		else
		{
			ep.setText(page);
		}
		ep.addHyperlinkListener(this);
		getContentPane().add(new JScrollPane(ep));
		setSize(640,480);
		setVisible(true);
		setResizable(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
   // This part code is taken from Sun Microsystems java 1.4.2 Documentation page of JEditorPane
	public void hyperlinkUpdate(HyperlinkEvent e) 
	{
 	     if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
		 {
 		      JEditorPane pane = (JEditorPane) e.getSource();
 		      if (e instanceof HTMLFrameHyperlinkEvent) 
			  {
 		          HTMLFrameHyperlinkEvent  evt = (HTMLFrameHyperlinkEvent)e;
 		          HTMLDocument doc = (HTMLDocument)pane.getDocument();
 		          doc.processHTMLFrameHyperlinkEvent(evt);
 		      } 
			  else 
			  {
 		          try 
				  {
 				      pane.setPage(e.getURL());
 		          } 
				  catch (Exception ex) 
				  {
  	      			JOptionPane.showMessageDialog(this," Sorry Cannot Load Page . . .\n Page may be deleted ","Error",0);
 		          }
 		      }
 	     }
    }
}

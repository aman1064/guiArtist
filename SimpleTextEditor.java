import java.lang.*;
import java.awt.*;
import java.awt.Component;
import java.awt.event.*;
import java.awt.datatransfer.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import javax.swing.undo.*;
import javax.swing.event.*;
import javax.swing.text.*;

import java.util.Properties;
import java.lang.System;
import java.io.IOException;
import java.lang.Runtime;
import java.lang.Process;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import javax.swing.border.*;


/**  SimpleTextEditor is a very basic text editor application which supports
*  File Open, File Save, and the basic text editor functions for cut, copy 
*  and paste and interfaces with the system clipboard.
*/

// start of class

public class SimpleTextEditor extends Frame implements ActionListener,KeyListener
{
// variables used
JFrame frame;
public JTextPane pane;
TextArea ta = new TextArea(10,60);
int i=0;
TextField t1 = new TextField();
JTextArea editor;  
String unif; 
int flag = 0;
int flag2;
int j = 0;
String msgkey = "";  
String[] iconFiles = {"new.gif","open.gif","save.gif","cut.gif","copy.gif","paste.gif","compile.gif","execute.gif"};
String[] buttonLables = {"New","Open","Save","Cut","Copy","Paste","Compile","Execute"};
ImageIcon[] icons= new ImageIcon[iconFiles.length];
JButton[] buttons = new JButton[buttonLables.length];
String[ ] fileItems = new String[ ] { "New", "Open", "Save","Print","Exit" };
String[ ] editItems = new String[ ] { "Cut", "Copy", "Paste" };
char[ ] fileShortcuts = { 'N','O','S','P','X' };
char[ ] editShortcuts = {'X','C','V' };
ColorDialog cd;
Color currentColor = new Color(255,255,255);
FontDialog fd;
Font currentFont=new Font("Monospaced",Font.PLAIN,12);
//clipboard 8*/
Clipboard clipboard;
// clipboardstring is the Transferrable object used to store
// String text data for clear, cut, copy, and paste operations
StringSelection clipboardstring; 
boolean debug = false; //set to true for debugging messages
Font editorfont = new Font("Monospaced",Font.PLAIN,12);
int tabsize = 5; 
public void init(  ) 
{
SecurityManager securitymanager = System.getSecurityManager();
    	if (securitymanager != null)
	{
      	try{
        		securitymanager.checkSystemClipboardAccess();
        		clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        		if (debug) System.out.println("Using system clipboard");
      	    }
	catch (SecurityException se)
		{
        		clipboard = new Clipboard("SimpleTextEditor");
        		System.out.println("SystemClipboardAccess is denied.");
		System.out.println("SecurityException: " + se.getMessage());
	                if (debug) System.out.println("Using application clipboard");
                                }
    	 }
	else{
      		clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    		if (debug) System.out.println("Using system clipboard");
    	        }
    
	frame = new JFrame("SimpleTextEditor");
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	//button controls
               JMenuBar menuBar = new JMenuBar();
               JMenu fileMenu = new JMenu("File");
               JMenu editMenu = new JMenu("Edit");
               JMenu formatMenu = new JMenu("Format");
               JMenu compileMenu = new JMenu("Compile");
               JMenu helpMenu = new JMenu("Help");
               menuBar.add(fileMenu);
               menuBar.add(editMenu);
               menuBar.add(formatMenu);
               menuBar.add(compileMenu);
              JMenuItem formatFont=new JMenuItem("Font");
              JMenuItem formatColor=new JMenuItem("Color");
        //compile menu item
             JMenuItem compileCompile= new JMenuItem("Compile");
             JMenuItem compileExecute= new JMenuItem("Execute");
	formatMenu.add(formatFont);
	formatMenu.addSeparator();
	formatMenu.add(formatColor);
   //add complie menuitem to compile item
	compileMenu.add(compileCompile);
	compileMenu.addSeparator();
	compileMenu.add(compileExecute);
         //help action
    compileCompile.addActionListener(this);
    compileExecute.addActionListener(this);
    formatColor.addActionListener(this);
    formatFont.addActionListener(this);  
    for (int i=0; i < fileItems.length; i++) {
   JMenuItem item = new JMenuItem(fileItems[i]);
   item.setAccelerator(KeyStroke.getKeyStroke(fileShortcuts[i],Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(), false));
   item.addActionListener(this);
   fileMenu.add(item);
        }
    // Assemble the File menus with keyboard accelerators.
       for (int i=0; i < editItems.length; i++) {
            JMenuItem item = new JMenuItem(editItems[i]);
            item.setAccelerator(KeyStroke.getKeyStroke(editShortcuts[i],Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(), false));
            item.addActionListener(this);
            editMenu.add(item);
        }
        frame.getContentPane().add(menuBar,"North");
	for(int i=0;i<buttonLables.length;++i) 
	{
	icons[i] = new ImageIcon(iconFiles[i]);
	buttons[i] = new JButton(icons[i]);
	buttons[i].setToolTipText(buttonLables[i]);
	buttons[i].addActionListener(this);
	menuBar.add(buttons[i]);
	}
	 //editor
    	editor = new JTextArea(new PlainDocument(),"",60,60); //initializes a JTextArea with width 60& height 30 
    	editor.setFont(editorfont);
         editor.setTabSize(tabsize);
	ta.setEditable(false);
	frame.getContentPane().add(ta,"South");
	JScrollPane scrollpane = new JScrollPane(editor);
    	frame.getContentPane().add(scrollpane,"Center");
    	frame.pack();
    	frame.show();
	addKeyListener(this);
	requestFocus();
 }
public void keyPressed(KeyEvent ke)
{ 
int key = ke.getKeyCode();
switch(key)
{
case KeyEvent.VK_ENTER:
msgkey = "Enter";
break;	
}
if (msgkey.equals("Enter"))
{
j++;
System.out.println(j);
}	
}
public void keyReleased(KeyEvent ke)
{
}
public void keyTyped(KeyEvent ke)
{ 
msgkey += ke.getKeyChar();
System.out.println(msgkey); 
}

public void actionPerformed(ActionEvent actionevent) 
{
String actioncommand = actionevent.getActionCommand();
if (actioncommand.equals(fileItems[0]) || actionevent.getSource() == buttons[0])
{
editor.setText("");
ta.setText(""); 
}
else if(actioncommand.equals(fileItems[1])||actionevent.getSource() == buttons[1])
{
flag2 = 1;
File f = getAFileToOpen();
writeFileToEditor(f);
}
else
if (actioncommand.equals(fileItems[2]) || actionevent.getSource() == buttons[2]) 
{
flag2 = 0;
String s = editor.getText();
File f = getAFileForSave();
writeStringToFile(f,s);
unif=f.getName();
showMessage("Contents of the Text Editor saved to file " + f.getName());
}
else if (actioncommand.compareTo(fileItems[3]) == 0)
{     
print();     
}
else if(actioncommand.compareTo(fileItems[4]) == 0)
{
this.setVisible(false);
Main mm=new Main();
mm.show();
//System.exit(0);
}    	
else if (actioncommand.compareTo("Clear") == 0)	
{
clear();
}
else
if (actioncommand.equals(editItems[0]) || actionevent.getSource() == buttons[3]) 
{
cut();
}
else 
if (actioncommand.equals(editItems[1]) || actionevent.getSource() == buttons[4]) 
{
copy();
}
else
if (actioncommand.equals(editItems[2]) || actionevent.getSource() == buttons[5]) 
{
paste();
}
else 
if (actioncommand.compareTo("Font") == 0)
{
fd=new FontDialog(SimpleTextEditor.this,currentFont,new FontSelectHandler());
fd.show();
}
else
if (actioncommand.compareTo("Color") == 0)
{
cd=new ColorDialog(SimpleTextEditor.this,currentColor,new ColorSelectHandler());
cd.show();
}
else if (actioncommand.compareTo("Compile") == 0 || actionevent.getSource() == buttons[6]) 
{
if (flag2==1) showMessage("Please Save The File First");
else
{
System.out.println("name : -" +  unif);	
 try
{
Runtime r = Runtime.getRuntime();
Process p = r.exec("javac "+ unif );
ta.setText("");
BufferedReader kbdInput = new BufferedReader(new InputStreamReader(p.getErrorStream()));
int line;
while((line = kbdInput.read())!=-1)
{
StringBuffer sb= new StringBuffer("");
sb.append((char)line);
String ss= sb.toString();
ta.append(ss);
}
System.out.println(ta.getText());
flag = 1; 
}
catch(Exception ioe) 
{
showMessage("Error in Compiling");
System.out.println("Error");	
}
}	            
}
else 
if (actioncommand.compareTo("Execute") == 0 || actionevent.getSource() == buttons[7]) 
{
if(flag==1)
{
StringBuffer sb = new StringBuffer(unif);
StringBuffer s1= sb.delete(sb.length()-5, sb.length());
System.out.println("name : -" +  s1);
try
{
Runtime r = Runtime.getRuntime();
Process p = r.exec("java " + s1);
BufferedReader kbdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
String line;
while((line = kbdInput.readLine())!=null)
{
StringBuffer sb1= new StringBuffer("");
sb1.append(line);
String ss= sb1.toString();
ta.append(ss+"\n");
}
System.out.println(ta.getText());
System.out.println(line); 
}
catch(IOException ioe) 
{
showMessage("Error in Execution");
System.out.println("Error");	
}
}
else 
{
showMessage("Please Compile First");             
}
}
}
/**  Conveniently displays a message to the user and waits for 
  *  confirmation.
  */
public void showMessage(String s)
{
JOptionPane.showMessageDialog(frame,s);
}
/**  Conveniently displays an exception error message to the user 
  *  and waits for confirmation.
  */
public void showExceptionErrorMessage(String s)
{
JOptionPane.showMessageDialog(frame,s,s,JOptionPane.ERROR_MESSAGE);
}
class FontSelectHandler implements ActionListener
{
public void actionPerformed(ActionEvent e)
{
currentFont=fd.getFont();
fd.dispose();
editor.setFont(currentFont);
}
}
class FontDialog extends Dialog
{
String fontName;
int fontStyle;
int fontSize;
String fontNames[];
String styleNames[]={"Plain","Bold","Italic","Bold Italic"};
String sizeNames[]={"10","12","14","18","24","36","72"};
int styles[]={Font.PLAIN,Font.BOLD,Font.ITALIC,Font.BOLD+Font.ITALIC};
int sizes[]={10,12,14,18,24,36,72};
MyList fontList;
MyList styleList=new MyList(5,false,styleNames);
MyList sizeList = new MyList(5,false,sizeNames);	
Toolkit toolkit;
Font newFont;
boolean fontChanged;
ActionListener ah;
public FontDialog(Frame parent,Font currentFont,ActionListener ah)
{
super(parent,"Select a font:",true);
toolkit=parent.getToolkit();
newFont=currentFont;
setupFonts();
setupPanels();
setBackground(Color.lightGray);
setForeground(Color.black);
this.ah=ah;
pack();
addWindowListener(new WindowEventHandler());
}
void setupFonts()
{
fontName=newFont.getName();
fontStyle=newFont.getStyle();
fontSize=newFont.getSize();
fontNames=toolkit.getFontList();
fontList=new MyList(5,false,fontNames);
}
void setupPanels()
{
Panel mainPanel=new Panel();
mainPanel.setLayout(new GridLayout(1,3));
Panel fontPanel = new Panel();
fontPanel.setLayout(new BorderLayout());
Label fontLabel=new Label("Font:");
fontPanel.add("North",fontLabel);
fontPanel.add("Center",fontList);
Panel stylePanel=new Panel();
stylePanel=new Panel();
stylePanel.setLayout(new BorderLayout());
Label styleLabel=new Label("Style:");
stylePanel.add("North",styleLabel);
stylePanel.add("Center",styleList);
Panel sizePanel=new Panel();
sizePanel.setLayout(new BorderLayout());
Label sizeLabel=new Label("Size:");
sizePanel.add("North",sizeLabel);
sizePanel.add("Center",sizeList);
mainPanel.add(fontPanel);
mainPanel.add(stylePanel);
mainPanel.add(sizePanel);
Font plainFont=new Font("Helvetica",Font.PLAIN,12);
Font boldFont=new Font("Helvetica",Font.BOLD,12);
mainPanel.setFont(plainFont);
fontLabel.setFont(boldFont);
styleLabel.setFont(boldFont);
sizeLabel.setFont(boldFont);
Panel buttonPanel=new Panel();
buttonPanel.setLayout(new FlowLayout());
Button selectButton=new Button("Select");
Button cancelButton = new Button("Cancel");
ButtonHandler bh=new ButtonHandler();
selectButton.addActionListener(bh);
cancelButton.addActionListener(bh);
buttonPanel.add(selectButton);
buttonPanel.add(cancelButton);
buttonPanel.setFont(boldFont);
add("Center",mainPanel);
add("South",buttonPanel);
}
public boolean isChanged()	
{
return fontChanged;
}
public Font getFont()
{
return newFont;
}
void updatenewFont()
{
if(fontList.getSelectedIndex()!=-1) 
fontName=fontList.getSelectedItem();
if(styleList.getSelectedIndex()!=-1)
fontStyle=styles[styleList.getSelectedIndex()];
if(sizeList.getSelectedIndex()!=-1)
fontSize=sizes[sizeList.getSelectedIndex()];
newFont=new Font(fontName,fontStyle,fontSize);
fontChanged=true;
}
class ButtonHandler implements ActionListener
{
public void actionPerformed(ActionEvent e)
{
String s=e.getActionCommand();
if("Select".equals(s))
{
updatenewFont();
ah.actionPerformed(new ActionEvent(FontDialog.this,ActionEvent.ACTION_PERFORMED,"Select"));	
FontDialog.this.setVisible(false);
}
else if("Cancel".equals(s))
{
FontDialog.this.dispose();
}
}
}
class WindowEventHandler extends WindowAdapter
{
public void windowClosing(WindowEvent e)
{
FontDialog.this.dispose();
}
}
}	
class MyList extends List
{
public MyList(int rows,boolean multiple,String labels[])
{
super(rows,multiple);
int length=labels.length;
for(int i=0;i<length;++i)
{
try
{
addItem(labels[i]);
}
catch (NullPointerException ex)
{
addItem("");
}
}
}
}
class ColorSelectHandler implements ActionListener
{
public void actionPerformed(ActionEvent e)
{
currentColor=cd.getColor();
cd.dispose();
editor.setForeground(currentColor);
}
  }

 class ColorDialog extends Dialog 
 {
 	Color colors[] = {Color.black,Color.blue,Color.cyan,Color.darkGray,Color.gray,
 	Color.green,Color.lightGray,Color.magenta,Color.orange,Color.pink,Color.red,
	Color.white,Color.yellow};
	String colorNames[] = {"black","blue","cyan","darkGray","gray","green",
	"lightGray","magenta","orange","pink","red",
	"white","yellow"};
	 MyList colorList = new MyList(5,false,colorNames);
	 Color newColor;
	 boolean colorChanged;
	 ActionListener ah;

 public ColorDialog(Frame parent,Color currentColor,ActionListener ah) 
 {
	super(parent,"Select a color:",true);
	setupPanels();
	setBackground(Color.lightGray);
	setForeground(Color.black);
	this.ah=ah;
	pack();
	addWindowListener(new WindowEventHandler());
  }
 void setupPanels() 
 {
	Panel colorPanel = new Panel();
	colorPanel.setLayout(new BorderLayout());
	Label colorLabel = new Label("Color:");
	colorPanel.add("North",colorLabel);
	colorPanel.add("Center",colorList);
	Font plainFont = new Font("Helvetica",Font.PLAIN,12);
	Font boldFont = new Font("Helvetica",Font.BOLD,12);
	colorLabel.setFont(boldFont);
	colorList.setFont(plainFont);
	Panel buttonPanel = new Panel();
	buttonPanel.setLayout(new FlowLayout());
	Button selectButton = new Button("Select");
	Button cancelButton = new Button("Cancel");
	ButtonHandler bh = new ButtonHandler();
	selectButton.addActionListener(bh);
	cancelButton.addActionListener(bh);
	buttonPanel.add(selectButton);
	buttonPanel.add(cancelButton);
	buttonPanel.setFont(boldFont);
	add("Center",colorPanel);
	add("South",buttonPanel);
 }
 public boolean isChanged() 
 {
	return colorChanged;
 }
 public Color getColor() 
 {
	return newColor;
 }
 class ButtonHandler implements ActionListener 
 {
	public void actionPerformed(ActionEvent e)
	{
	String s = e.getActionCommand();
	if("Select".equals(s)) 
	{
	if(colorList.getSelectedIndex() != -1)
	newColor = colors[colorList.getSelectedIndex()];
	colorChanged = true;
	ah.actionPerformed(new ActionEvent(ColorDialog.this,
	ActionEvent.ACTION_PERFORMED,"Select"));
	ColorDialog.this.setVisible(false);
 	}else if("Cancel".equals(s)) 
	{
    	ColorDialog.this.dispose();
   	}
  	}
 }
 class WindowEventHandler extends WindowAdapter 
	 {
	public void windowClosing(WindowEvent e){
	ColorDialog.this.dispose();
	 }
      }
  }	  
    
	 public File getAFileForSave()
	{
    	File file = null;
    	File currentdirectory = new File(".");
    	JFileChooser filechooser = new JFileChooser(currentdirectory);
    	int replycode = filechooser.showSaveDialog(frame);
    	if (replycode == JFileChooser.APPROVE_OPTION)
	    {
      	file = filechooser.getSelectedFile();
    	}
    	return file;

  	}
 /* public File getAFileForSaveas()
	{
    	File file = null;
    	File currentdirectory = new File(".");
    	JFileChooser filechooser = new JFileChooser(currentdirectory);
    	int replycode = filechooser.showSaveDialog(frame);
    	if (replycode == JFileChooser.APPROVE_OPTION)
	    {
      	file = filechooser.getSelectedFile();
    	}
    	return file;

  	}
*/	
	public void writeStringToFile(File file, String s)
  {
    try{
	FileWriter filewriter = new FileWriter(file);
      	StringReader stringreader = new StringReader(s);
      	BufferedReader bufferedreader = new BufferedReader(stringreader);
      	String lineread = "";
      	while ((lineread = bufferedreader.readLine()) != null){
        	filewriter.write(lineread + "\r\n");
        }
      filewriter.close();
  }catch (FileNotFoundException fnfe)
        {
      	System.err.println("FileNotFoundException: " + fnfe.getMessage());
      	showExceptionErrorMessage("FileNotFoundException: " + fnfe.getMessage());
        }catch (IOException ioe)
        {
      	System.err.println("IOException: " + ioe.getMessage());
      	showExceptionErrorMessage("IOException: " + ioe.getMessage());
        }
    
 }
 
      public void writeFileToEditor(File file)
   {
    try{
	FileReader filereader = new FileReader(file);
      	BufferedReader bufferedreader = new BufferedReader(filereader);
      	String lineread = "";
      	while ((lineread = bufferedreader.readLine()) != null)
	{
        	editor.append(lineread + "\n");
        	}
      	filereader.close();
          }catch (FileNotFoundException fnfe)
	{
      	System.err.println("FileNotFoundException: " + fnfe.getMessage());
      	showExceptionErrorMessage("FileNotFoundException: " + fnfe.getMessage());
    	}catch (IOException ioe)
	{
      	System.err.println("IOException: " + ioe.getMessage());
      	showExceptionErrorMessage("IOException: " + ioe.getMessage());
    	}
 }



void print()
	 {
	Toolkit toolkit;
	toolkit=getToolkit();
	String name="Test print job";
	Properties properties=new Properties();
	PrintJob pj=toolkit.getPrintJob(SimpleTextEditor.this, name,properties);
   	if(pj==null) showMessage("Print Cancelled"); 
  	else	
		{
     		String output="Name: "+name+"\nProperties: "+properties.toString();
     		Dimension pageDim=pj.getPageDimension();
    		int resolution=pj.getPageResolution();
	    	boolean lastPageFirst=pj.lastPageFirst();
    		output+="\nPage dimension (in pixels):";
	     	output+="\n height: "+String.valueOf(pageDim.height);
    		output+="\n width: "+String.valueOf(pageDim.width);
	    	output+="\nResolution (pixels/inch): "+String.valueOf(resolution);
     		output+="\nLast Page First: "+String.valueOf(lastPageFirst);
	     	//editor.setText(output);
     		Graphics g = pj.getGraphics();
	     	g.dispose();
     		pj.end();
   		}
   	}




  /**  clear() puts all editor text into the clipboardstring Transferrable 
  *  object and into the clipboard in case the user wants to paste it 
  *  back later in, then clears the editor. Text is also placed in the 
  *  system clipboard so it can be pasted into another application.
  */

  private void clear()
  {

	    String selectedtext = editor.getText();
	    if (selectedtext != null)
	{
	    clipboardstring = new StringSelection(selectedtext);
	    clipboard.setContents(clipboardstring,clipboardstring);
    	}
    	editor.setText("");
  }


  /**   cut deletes selected text and stores the text into the clipboardstring 
  *  Transferrable object and into the clipboard.
  */

  private void cut()
  {
  	  String selectedtext = editor.getSelectedText();
    	  if (selectedtext != null)
		{
	     	  clipboardstring = new StringSelection(selectedtext);
	    	  clipboard.setContents(clipboardstring,clipboardstring);
	 	  int startmark = editor.getSelectionStart();    
	     	  int endmark = editor.getSelectionEnd();
	    	  editor.replaceRange("",startmark,endmark);
		}    
  }


  /**  copy() stores the selected text from the editor into the 
  *  clipboardstring Transferrable object and into the clipboard.
  */


  private void copy()
  {
  	 String selectedtext = editor.getSelectedText();
   	 if (selectedtext != null)
		{
   		 clipboardstring = new StringSelection(selectedtext);
	    	 clipboard.setContents(clipboardstring,clipboardstring);
		}
  }
  

  /**  paste() inserts the string stored from clear, copy or cut at
  *  the current caret position.
  */


  private void paste()
  {
  	  Transferable clipboarddata = clipboard.getContents(clipboardstring);
    	try{
      		String text =  (String) clipboarddata .getTransferData(DataFlavor.stringFlavor);
      		editor.insert(text,editor.getCaretPosition());
    	     }catch(Exception e)
		{
     		System.err.println("Exception: " + e.getMessage());  
		showExceptionErrorMessage("Exception: " + e.getMessage());    
    		}
  }


 	
	public File getAFileToOpen()
	{
    	File file = null;
    	File currentdirectory = new File(".");
    	JFileChooser filechooser = new JFileChooser(currentdirectory);
  	int replycode = filechooser.showOpenDialog(frame);
    	if (replycode == JFileChooser.APPROVE_OPTION)
        {
      	file = filechooser.getSelectedFile();
    	}
    	return file;
  	}
	 
 
      public static void main(String[] args)
	{
        
	    SimpleTextEditor simpletexteditor = new SimpleTextEditor();
	   simpletexteditor.init();
		
     }
	}
	
  /**  Initializes the button controls, a textarea for an editor to display 
  *  or enter text, and adds the components to a frame window and displays
  *  it. A clipboard object is set up so that this application can interface
  *  with other applications for cut copy and paste operations provided this
  *  is allowed by the SecuityManager if active. If the security manager
  *  does not allow it, it creates a clipboard object which will only support
  *  cut, copy,and paste operations from this application.
  *  
  */

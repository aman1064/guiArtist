/**
 *
 * @author Aman
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;
import java.io.*;
import javax.swing.*;
import javax.imageio.*;

public class CanvasPanel extends JPanel implements MouseListener,MouseMotionListener, Serializable
{
	protected final static int LINE=1,SQUARE=2,OVAL=3,POLYGON=4,ROUND_RECT=5,FREE_HAND=6,
								SOLID_SQUARE=22, SOLID_OVAL=33, SOLID_POLYGON=44,
								SOLID_ROUND_RECT=55;
	protected static Vector vLine,vSquare,vOval,vPolygon,vRoundRect,vFreeHand,
						 	vSolidSquare,vSolidOval,vSolidPolygon,vSolidRoundRect,vFile,
						 	xPolygon, yPolygon;						 	
	
	private Stack undoStack, redoStack;
	
	private Color foreGroundColor, backGroundColor; 
	
	private int x1,y1,x2,y2,linex1,linex2,liney1,liney2, drawMode=0;
	private boolean solidMode, polygonBuffer;
	
	private File fileName;
					    
	public CanvasPanel()
	{
		vLine 			= new Vector();
		vSquare 		= new Vector();
		vOval			= new Vector();
		vPolygon		= new Vector();
		vRoundRect		= new Vector();
		vFreeHand		= new Vector();
		vSolidSquare	= new Vector();
		vSolidOval		= new Vector();
		vSolidPolygon	= new Vector();
		vSolidRoundRect	= new Vector();
		vFile			= new Vector();
		xPolygon		= new Vector();
		yPolygon		= new Vector();
		
		addMouseListener(this);
		addMouseMotionListener(this);
		
		solidMode 		= false;
		polygonBuffer 	= false; 
		
		foreGroundColor = Color.WHITE;
		backGroundColor = Color.BLACK;
		setBackground(backGroundColor);
		
		undoStack = new Stack();
		redoStack = new Stack();
		
		repaint();		
	}
/*----------------------------------------------------------------------------*/		
	public void mousePressed(MouseEvent event)
	{
		x1 = linex1 = linex2 = event.getX();
        y1 = liney1 = liney2 = event.getY();
	}
/*----------------------------------------------------------------------------*/
	public void mouseClicked(MouseEvent event){}
	public void mouseMoved(MouseEvent event){}
/*----------------------------------------------------------------------------*/
	public void mouseReleased(MouseEvent event)
	{
		if (drawMode == LINE)
        {	
           	vLine.add(new Coordinate(x1,y1,event.getX(),event.getY(),foreGroundColor));
           	undoStack.push(new StepInfo(LINE ,new Coordinate(x1,y1,event.getX(),event.getY(),foreGroundColor)));
        }
        if (drawMode == SQUARE) 
        {
            if(solidMode)
           	{
           		if(x1 > event.getX() || y1 > event.getY())
				{
           			vSolidSquare.add(new Coordinate(event.getX(),event.getY(),x1,y1,foreGroundColor));
           			undoStack.push(new StepInfo(SOLID_SQUARE, new Coordinate(event.getX(),event.getY(),x1,y1,foreGroundColor)));
           		}
           		else
           		{
           			vSolidSquare.add(new Coordinate(x1,y1,event.getX(),event.getY(),foreGroundColor));
           			undoStack.push(new StepInfo(SOLID_SQUARE, new Coordinate(x1,y1,event.getX(),event.getY(),foreGroundColor)));
           		}
           	}
            else
            {
           		if(x1 > event.getX() || y1 > event.getY())
           		{
           			vSquare.add(new Coordinate(event.getX(),event.getY(),x1,y1,foreGroundColor));
           			undoStack.push(new StepInfo(SQUARE, new Coordinate(event.getX(),event.getY(),x1,y1,foreGroundColor)));
           		}
           		else
           		{
           			vSquare.add(new Coordinate(x1,y1,event.getX(),event.getY(),foreGroundColor));
           			undoStack.push(new StepInfo(SQUARE, new Coordinate(x1,y1,event.getX(),event.getY(),foreGroundColor)));
           		}
           	}
        }
        if (drawMode == this.OVAL) 
        {
          	if(solidMode)
          	{
          		if(x1 > event.getX() || y1 > event.getY())
          		{
          			vSolidOval.add(new Coordinate(event.getX(),event.getY(),x1,y1,foreGroundColor));
          			undoStack.push(new StepInfo(SOLID_OVAL, new Coordinate(event.getX(),event.getY(),x1,y1,foreGroundColor)));
          		}
          		else
          		{
          			vSolidOval.add(new Coordinate(x1,y1,event.getX(),event.getY(),foreGroundColor));
          			undoStack.push(new StepInfo(SOLID_OVAL, new Coordinate(x1,y1,event.getX(),event.getY(),foreGroundColor)));
          		}
           	}
           	else
           	{
           		if(x1 > event.getX() || y1 > event.getY())
           		{
           			vOval.add(new Coordinate(event.getX(),event.getY(),x1,y1,foreGroundColor));
           			undoStack.push(new StepInfo(OVAL, new Coordinate(event.getX(),event.getY(),x1,y1,foreGroundColor)));
           		}
           		else	
           		{
           			vOval.add(new Coordinate(x1,y1,event.getX(),event.getY(),foreGroundColor));
           			undoStack.push(new StepInfo(OVAL, new Coordinate(x1,y1,event.getX(),event.getY(),foreGroundColor)));
           		}
           	}
        }
        if (drawMode == this.POLYGON || drawMode == this.SOLID_POLYGON) 
        {
        	xPolygon.add(new Integer(event.getX()));
        	yPolygon.add(new Integer(event.getY()));
        	polygonBuffer = true;
        	repaint();       	      
        }
        if (drawMode == this.ROUND_RECT) 
        {
          	if(solidMode)
          	{
          		if(x1 > event.getX() || y1 > event.getY())
          		{
          			vSolidRoundRect.add(new Coordinate(event.getX(),event.getY(),x1,y1,foreGroundColor));
          			undoStack.push(new StepInfo(SOLID_ROUND_RECT, new Coordinate(event.getX(),event.getY(),x1,y1,foreGroundColor)));
          		}
          		else
          		{
           			vSolidRoundRect.add(new Coordinate(x1,y1,event.getX(),event.getY(),foreGroundColor));
           			undoStack.push(new StepInfo(SOLID_ROUND_RECT, new Coordinate(x1,y1,event.getX(),event.getY(),foreGroundColor)));
           		}
           	}
           	else
           	{
           		if(x1 > event.getX() || y1 > event.getY())
           		{
           			vRoundRect.add(new Coordinate(event.getX(),event.getY(),x1,y1,foreGroundColor));
           			undoStack.push(new StepInfo(ROUND_RECT, new Coordinate(event.getX(),event.getY(),x1,y1,foreGroundColor)));
           		}
           		else
           		{
           			vRoundRect.add(new Coordinate(x1,y1,event.getX(),event.getY(),foreGroundColor));
           			undoStack.push(new StepInfo(ROUND_RECT, new Coordinate(x1,y1,event.getX(),event.getY(),foreGroundColor)));
           		}
           	}
        }           
        x1=linex1=x2=linex2=0;
        y1=liney1=y2=liney2=0;
	}
/*----------------------------------------------------------------------------*/
	public void mouseEntered(MouseEvent event)
	{
		setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
	}
/*----------------------------------------------------------------------------*/
	public void mouseExited(MouseEvent event)
	{
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
/*----------------------------------------------------------------------------*/
	public void mouseDragged(MouseEvent event)
	{
        x2 = event.getX();
        y2 = event.getY();
                        
        if (drawMode == this.FREE_HAND) 
        {
            linex1 = linex2;
            liney1 = liney2;           
            linex2 = x2;
            liney2 = y2;
               
            vFreeHand.add(new Coordinate(linex1,liney1,linex2,liney2,foreGroundColor));
         	undoStack.push(new StepInfo(FREE_HAND, new Coordinate(linex1,liney1,linex2,liney2,foreGroundColor)));
         }
         repaint();
	}
/*----------------------------------------------------------------------------*/
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
      	redrawVectorBuffer(g);

	  	g.setColor(foreGroundColor);
      
      	if (drawMode == LINE) 
      	{
        	g.drawLine(x1,y1,x2,y2);
      	}
      	if (drawMode == OVAL) 
      	{
      	 	if(solidMode)
      	 	{
         		if(x1 > x2 || y1 > y2)
         			g.fillOval(x2,y2,x1-x2,y1-y2);
         		else	
         			g.fillOval(x1,y1,x2-x1,y2-y1);
      	 	}
         	else
         	{
         		if(x1 > x2 || y1 > y2)
         			g.drawOval (x2,y2,x1-x2,y1-y2);
         		else
         			g.drawOval (x1,y1,x2-x1,y2-y1);
         	}
      	}
      	if (drawMode == ROUND_RECT) 
      	{
         	if(solidMode)
         	{
         		if(x1 > x2 || y1 > y2)
         			g.fillRoundRect(x2,y2,x1-x2,y1-y2,25,25);
         		else
         			g.fillRoundRect(x1,y1,x2-x1,y2-y1,25,25);
         	}
         	else
         	{
         		if(x1 > x2 || y1 > y2)
         			g.drawRoundRect(x2,y2,x1-x2,y1-y2,25,25);
         		else
         			g.drawRoundRect(x1,y1,x2-x1,y2-y1,25,25);
         	}
      	}
      	if (drawMode == SQUARE) 
      	{
      	 	if(solidMode)
      	 	{
      	 		if(x1 > x2 || y1 > y2)
      	 			g.fillRect (x2,y2,x1-x2,y1-y2);
      	 		else
      	 			g.fillRect (x1,y1,x2-x1,y2-y1);
      	 	}
         	else
         	{
         		if(x1 > x2 || y1 > y2)
         			g.drawRect (x2,y2,x1-x2,y1-y2);
         		else
         			g.drawRect (x1,y1,x2-x1,y2-y1);
         	}
      	}
      	if (drawMode == POLYGON || drawMode == SOLID_POLYGON)
      	{
      		int xPos[] = new int[xPolygon.size()];
      	 	int yPos[] = new int[yPolygon.size()];
      	 
      	 	for(int count=0;count<xPos.length;count++)
      	 	{
      	 		xPos[count] = ((Integer)(xPolygon.elementAt(count))).intValue();
      	 		yPos[count] = ((Integer)(yPolygon.elementAt(count))).intValue();
      	 	}
      	 	g.drawPolyline(xPos,yPos,xPos.length);
      	 	polygonBuffer = true;
	  	}
      	if (drawMode == FREE_HAND) 
      	{
         	g.drawLine(linex1,liney1,linex2,liney2);
      	}
	}
/*----------------------------------------------------------------------------*/
	public void setDrawMode(int mode)
	{
		drawMode = mode;
	}
	public int getDrawMode()
	{	
		return drawMode;	
	}
/*----------------------------------------------------------------------------*/
	public void setSolidMode(Boolean inSolidMode)
	{
		solidMode = inSolidMode.booleanValue();
	}
	public Boolean getSolidMode()
	{
		return Boolean.valueOf(solidMode);
	}
/*----------------------------------------------------------------------------*/
	public void setForeGroundColor(Color inputColor)
	{
		foreGroundColor = inputColor;
	}
	public Color getForeGroundColor()
	{
		return foreGroundColor;
	}
/*----------------------------------------------------------------------------*/
	public void setBackGroundColor(Color inputColor)
	{
		backGroundColor = inputColor;
		this.setBackground(backGroundColor);
	}
	public Color getBackGroundColor()
	{
		return backGroundColor;
	}
/*----------------------------------------------------------------------------*/
	public void undo()
	{
		StepInfo tempInfo;
		
		if(undoStack.isEmpty())
			JOptionPane.showMessageDialog(null, "Can't Undo","Painter", JOptionPane.INFORMATION_MESSAGE);
		else
		{
			tempInfo = (StepInfo)undoStack.pop();

			switch(tempInfo.getStepType())
			{
				case 1:	vLine.remove(vLine.size()-1);
					break;
				case 2:	vSquare.remove(vSquare.size()-1);
					break;
				case 3:	vOval.remove(vOval.size()-1);
					break;
				case 4:	vPolygon.remove(vPolygon.size()-1);
					break;	
				case 5:	vRoundRect.remove(vRoundRect.size()-1);
					break;
				case 6:	vFreeHand.remove(vFreeHand.size()-1);
					break;
				case 22:vSolidSquare.remove(vSolidSquare.size()-1);
					break;
				case 33:vSolidOval.remove(vSolidOval.size()-1);
					break;
				case 44:vSolidPolygon.remove(vSolidPolygon.size()-1);
					break;
				case 55:vSolidRoundRect.remove(vSolidRoundRect.size()-1);
					break;
			}
			redoStack.push(tempInfo);
		}
		repaint();
	}
/*----------------------------------------------------------------------------*/
	public void redo()
	{
		StepInfo tempInfo;
		
		if(redoStack.isEmpty())
			JOptionPane.showMessageDialog(null,"Can't Redo","Painter",JOptionPane.INFORMATION_MESSAGE);
		else
		{
			tempInfo = (StepInfo)redoStack.pop();
			
			switch(tempInfo.getStepType())
			{
				case 1:	vLine.add(tempInfo.getStepCoordinate());
					break;
				case 2:	vSquare.add(tempInfo.getStepCoordinate());
					break;
				case 3:	vOval.add(tempInfo.getStepCoordinate());
					break;
				case 4:	vPolygon.add(tempInfo.getStepCoordinate());
					break;	
				case 5:	vRoundRect.add(tempInfo.getStepCoordinate());
					break;
				case 6:	vFreeHand.add(tempInfo.getStepCoordinate());
					break;
				case 22:vSolidSquare.add(tempInfo.getStepCoordinate());
					break;
				case 33:vSolidOval.add(tempInfo.getStepCoordinate());
					break;
				case 44:vSolidPolygon.add(tempInfo.getStepCoordinate());
					break;
				case 55:vSolidRoundRect.add(tempInfo.getStepCoordinate());
					break;
			}
			undoStack.push(tempInfo);
		}
		repaint();
	}
/*----------------------------------------------------------------------------*/
	public void clearCanvas()
	{
		vFreeHand.removeAllElements();
		vLine.removeAllElements();
		vOval.removeAllElements();
		vPolygon.removeAllElements();
		vRoundRect.removeAllElements();
		vSolidOval.removeAllElements();
		vSolidPolygon.removeAllElements();
		vSolidRoundRect.removeAllElements();
		vSolidSquare.removeAllElements();
		vSquare.removeAllElements();
		undoStack.clear();
		redoStack.clear();
		repaint();
	}
/*----------------------------------------------------------------------------*/	
	public void SaveCanvasToFile()
	{
		if(fileName != null)
		{
			vFile.removeAllElements();
			vFile.addElement(vFreeHand);
			vFile.addElement(vLine);
			vFile.addElement(vOval);
			vFile.addElement(vPolygon);
			vFile.addElement(vRoundRect);
			vFile.addElement(vSolidOval);
			vFile.addElement(vSolidPolygon);
			vFile.addElement(vSolidRoundRect);
			vFile.addElement(vSolidSquare);
			vFile.addElement(vSquare);
			vFile.addElement(new Color(backGroundColor.getRGB()));
			RenderedImage rendImage = myCreateImage();
			
			try
			{
				FileOutputStream fos = new FileOutputStream(fileName);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(vFile);
				JOptionPane.showMessageDialog(null,"File Saved","Painter",JOptionPane.INFORMATION_MESSAGE);
			}catch(Exception exp){}
			
			try 
			{
        		File file = new File(fileName.toString() + ".jpg");        		
        		ImageIO.write(rendImage, "jpg", file);
    		}catch (IOException e) {}
		}
		else
		{
			SaveAsCanvasToFile();
		}
		repaint();
	}
/*----------------------------------------------------------------------------*/
	public void SaveAsCanvasToFile()
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);	
		int result = fileChooser.showSaveDialog(null);
	
		if(result == JFileChooser.CANCEL_OPTION) return;
			
		fileName = fileChooser.getSelectedFile();

		if(fileName == null || fileName.getName().equals(""))
			JOptionPane.showMessageDialog(null,"Invalid File Name","Painter",JOptionPane.ERROR_MESSAGE);
		else
		{
			vFile.removeAllElements();
			vFile.addElement(vFreeHand);
			vFile.addElement(vLine);
			vFile.addElement(vOval);
			vFile.addElement(vPolygon);
			vFile.addElement(vRoundRect);
			vFile.addElement(vSolidOval);
			vFile.addElement(vSolidPolygon);
			vFile.addElement(vSolidRoundRect);
			vFile.addElement(vSolidSquare);
			vFile.addElement(vSquare);	
			vFile.addElement(new Color(backGroundColor.getRGB()));
			
			RenderedImage rendImage = myCreateImage();
			
			try
			{
				FileOutputStream fos = new FileOutputStream(fileName);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(vFile);
				JOptionPane.showMessageDialog(null,"File Saved","Painter",JOptionPane.INFORMATION_MESSAGE);
			}catch(Exception exp){}
			
			try {
        		File file = new File(fileName.toString() + ".jpg");        		
        		ImageIO.write(rendImage, "jpg", file);
    		}catch (IOException e) {}
		}		    
	repaint();
	}
/*----------------------------------------------------------------------------*/
	public void OpenCanvasFile()
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			
		int result = fileChooser.showOpenDialog(null);
		if(result == JFileChooser.CANCEL_OPTION) return;
			
		fileName = fileChooser.getSelectedFile();
		
		if(fileName != null)
		{
			try{
				FileInputStream fis = new FileInputStream(fileName);
				ObjectInputStream ois = new ObjectInputStream(fis);
				vFile = (Vector) ois.readObject();
				
				this.clearCanvas();
				vFreeHand 		= (Vector)vFile.elementAt(0);
				vLine 			= (Vector)vFile.elementAt(1);
				vOval			= (Vector)vFile.elementAt(2);
				vPolygon		= (Vector)vFile.elementAt(3);
				vRoundRect		= (Vector)vFile.elementAt(4);
				vSolidOval		= (Vector)vFile.elementAt(5);
				vSolidPolygon	= (Vector)vFile.elementAt(6);
				vSolidRoundRect	= (Vector)vFile.elementAt(7);
				vSolidSquare	= (Vector)vFile.elementAt(8);
				vSquare			= (Vector)vFile.elementAt(9);
				backGroundColor = (Color)vFile.elementAt(10);
				
				this.setBackground(backGroundColor);
			}
			catch(Exception exp){
				JOptionPane.showMessageDialog(null,"Can't Open File","Painter",JOptionPane.INFORMATION_MESSAGE);
			}	
		}
		else{
			fileName = null;
		}
		repaint();
	}
/*----------------------------------------------------------------------------*/
	public boolean isExistPolygonBuffer()
	{
		return polygonBuffer;
	} 
/*----------------------------------------------------------------------------*/	
	public void flushPolygonBuffer()
	{
		if(!solidMode)
		{
			vPolygon.add(new Coordinate(xPolygon, yPolygon, foreGroundColor));
			undoStack.push(new StepInfo(POLYGON,new Coordinate(xPolygon, yPolygon, foreGroundColor)));
		}
		else
		{
			vSolidPolygon.add(new Coordinate(xPolygon, yPolygon, foreGroundColor));
			undoStack.push(new StepInfo(SOLID_POLYGON,new Coordinate(xPolygon, yPolygon, foreGroundColor)));
		}
		
		xPolygon.removeAllElements();
		yPolygon.removeAllElements();
			
		polygonBuffer = false;
		repaint();
	}
/*----------------------------------------------------------------------------*/
	private class Coordinate implements Serializable
	{
		private int x1,y1,x2,y2;
		private Color foreColor;
		private Vector xPoly, yPoly;
		
		public Coordinate (int inx1,int iny1,int inx2, int iny2, Color color) 
		{
        	x1 = inx1;
         	y1 = iny1;
         	x2 = inx2;
         	y2 = iny2;
         	foreColor = color;
      	}
      	public Coordinate(Vector inXPolygon, Vector inYPolygon, Color color)
      	{
      		xPoly = (Vector)inXPolygon.clone();
      		yPoly = (Vector)inYPolygon.clone();
      		foreColor = color;
      	}
      	public Color colour()
      	{
        	return foreColor;
      	}
      	public int getX1 () 
      	{
        	return x1;
      	}
      	public int getX2 () 
      	{
        	return x2;
      	}
      	public int getY1 () 
      	{
        	return y1;
      	}
      	public int getY2 () 
      	{
        	return y2;
      	}
      	public Vector getXPolygon()
      	{
      		return xPoly;
      	}
      	public Vector getYPolygon()
      	{
      		return yPoly;
      	}
	}		
/*----------------------------------------------------------------------------*/	
	private class StepInfo implements Serializable
	{
		private int stepType;
		private Coordinate stepCoordinate;
		
		public StepInfo(int inStepType, Coordinate inStepCoordinate)
		{
			stepType = inStepType;
			stepCoordinate = inStepCoordinate;
		}
		public int getStepType()
		{
			return stepType;
		}
		public Coordinate getStepCoordinate()
		{
			return stepCoordinate;
		}
	}
/*----------------------------------------------------------------------------*/	
	private RenderedImage myCreateImage() 
	{
        BufferedImage bufferedImage = new BufferedImage(600,390, BufferedImage.TYPE_INT_RGB);

        Graphics g = bufferedImage.createGraphics();
    	redrawVectorBuffer(g);

      	g.dispose();
      	return bufferedImage;
    }
/*----------------------------------------------------------------------------*/	
    private void redrawVectorBuffer(Graphics g)
    {
    	for (int i=0;i<vFreeHand.size();i++){
        	g.setColor(((Coordinate)vFreeHand.elementAt(i)).colour());
         	g.drawLine(((Coordinate)vFreeHand.elementAt(i)).getX1(),((Coordinate)vFreeHand.elementAt(i)).getY1(),((Coordinate)vFreeHand.elementAt(i)).getX2(),((Coordinate)vFreeHand.elementAt(i)).getY2());
      	}
      	for (int i=0;i<vLine.size();i++){
         	g.setColor(((Coordinate)vLine.elementAt(i)).colour());
         	g.drawLine(((Coordinate)vLine.elementAt(i)).getX1(),((Coordinate)vLine.elementAt(i)).getY1(),((Coordinate)vLine.elementAt(i)).getX2(),((Coordinate)vLine.elementAt(i)).getY2());
      	}
	  	for (int i=0;i<vOval.size();i++){	
         	g.setColor(((Coordinate)vOval.elementAt(i)).colour());
         	g.drawOval(((Coordinate)vOval.elementAt(i)).getX1(),((Coordinate)vOval.elementAt(i)).getY1(),((Coordinate)vOval.elementAt(i)).getX2()-((Coordinate)vOval.elementAt(i)).getX1(),((Coordinate)vOval.elementAt(i)).getY2()-((Coordinate)vOval.elementAt(i)).getY1());
      	}
      	for (int i=0;i<vRoundRect.size();i++){
         	g.setColor(((Coordinate)vRoundRect.elementAt(i)).colour());
         	g.drawRoundRect(((Coordinate)vRoundRect.elementAt(i)).getX1(),((Coordinate)vRoundRect.elementAt(i)).getY1(),((Coordinate)vRoundRect.elementAt(i)).getX2()-((Coordinate)vRoundRect.elementAt(i)).getX1(),((Coordinate)vRoundRect.elementAt(i)).getY2()-((Coordinate)vRoundRect.elementAt(i)).getY1(),25,25);
      	}
      	for (int i=0;i<vSolidOval.size();i++){
         	g.setColor(((Coordinate)vSolidOval.elementAt(i)).colour());
         	g.fillOval(((Coordinate)vSolidOval.elementAt(i)).getX1(),((Coordinate)vSolidOval.elementAt(i)).getY1(),((Coordinate)vSolidOval.elementAt(i)).getX2()-((Coordinate)vSolidOval.elementAt(i)).getX1(),((Coordinate)vSolidOval.elementAt(i)).getY2()-((Coordinate)vSolidOval.elementAt(i)).getY1());
      	}
      	for (int i=0;i<vSolidRoundRect.size();i++){
         	g.setColor(((Coordinate)vSolidRoundRect.elementAt(i)).colour());
       	 	g.fillRoundRect(((Coordinate)vSolidRoundRect.elementAt(i)).getX1(),((Coordinate)vSolidRoundRect.elementAt(i)).getY1(),((Coordinate)vSolidRoundRect.elementAt(i)).getX2()-((Coordinate)vSolidRoundRect.elementAt(i)).getX1(),((Coordinate)vSolidRoundRect.elementAt(i)).getY2()-((Coordinate)vSolidRoundRect.elementAt(i)).getY1(),25,25);
      	}
      	for (int i=0;i<vSquare.size();i++){
         	g.setColor(((Coordinate)vSquare.elementAt(i)).colour());
         	g.drawRect(((Coordinate)vSquare.elementAt(i)).getX1(),((Coordinate)vSquare.elementAt(i)).getY1(),((Coordinate)vSquare.elementAt(i)).getX2()-((Coordinate)vSquare.elementAt(i)).getX1(),((Coordinate)vSquare.elementAt(i)).getY2()-((Coordinate)vSquare.elementAt(i)).getY1());
      	}
      	for (int i=0;i<vSolidSquare.size();i++){
         	g.setColor(((Coordinate)vSolidSquare.elementAt(i)).colour());
         	g.fillRect(((Coordinate)vSolidSquare.elementAt(i)).getX1(),((Coordinate)vSolidSquare.elementAt(i)).getY1(),((Coordinate)vSolidSquare.elementAt(i)).getX2()-((Coordinate)vSolidSquare.elementAt(i)).getX1(),((Coordinate)vSolidSquare.elementAt(i)).getY2()-((Coordinate)vSolidSquare.elementAt(i)).getY1());
      	}
      	for(int i=0;i<vPolygon.size();i++){
      	 	int xPos[] = new int[((Coordinate)vPolygon.elementAt(i)).getXPolygon().size()];
      	 	int yPos[] = new int[((Coordinate)vPolygon.elementAt(i)).getYPolygon().size()];
      	 
      	 	for(int count=0;count<xPos.length;count++)
      	 	{
      	 		xPos[count] = ((Integer)((Coordinate)vPolygon.elementAt(i)).getXPolygon().elementAt(count)).intValue();
      	 		yPos[count] = ((Integer)((Coordinate)vPolygon.elementAt(i)).getYPolygon().elementAt(count)).intValue();
      	 	}     	 
      	 	g.setColor(((Coordinate)vPolygon.elementAt(i)).colour());
      	 	g.drawPolygon(xPos,yPos,xPos.length);
	  	}
	  	for(int i=0;i<vSolidPolygon.size();i++){
      	 	int xPos[] = new int[((Coordinate)vSolidPolygon.elementAt(i)).getXPolygon().size()];
      	 	int yPos[] = new int[((Coordinate)vSolidPolygon.elementAt(i)).getYPolygon().size()];
      	 
      	 	for(int count=0;count<xPos.length;count++)
      	 	{
      	 		xPos[count] = ((Integer)((Coordinate)vSolidPolygon.elementAt(i)).getXPolygon().elementAt(count)).intValue();
      	 		yPos[count] = ((Integer)((Coordinate)vSolidPolygon.elementAt(i)).getYPolygon().elementAt(count)).intValue();
      	 	}
      	 	g.setColor(((Coordinate)vSolidPolygon.elementAt(i)).colour());
      	 	g.fillPolygon(xPos,yPos,xPos.length);
      	}	
    }
    
}

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class ToolButtonPanel extends JPanel
{
	private JButton lineBtn, squareBtn, ovalBtn, polygonBtn, roundRectBtn, freeHandBtn, undoBtn, redoBtn, clearBtn;		
	
	private JCheckBox fullChk;
	private CanvasPanel canvasPanel;
	
	public ToolButtonPanel(CanvasPanel inCanvasPanel)
	{
		canvasPanel = inCanvasPanel;
/*----------------------------------------------------------------------------*/		 
		lineBtn			= new JButton("",new ImageIcon("lineBtn.gif"));
		squareBtn		= new JButton("",new ImageIcon("squareBtn.gif"));
		ovalBtn	 		= new JButton("",new ImageIcon("ovalBtn.gif"));
		polygonBtn		= new JButton("",new ImageIcon("polygonBtn.gif"));
		roundRectBtn	= new JButton("",new ImageIcon("roundRectBtn.gif"));
		freeHandBtn		= new JButton("",new ImageIcon("freeHandBtn.gif"));
		undoBtn			= new JButton("",new ImageIcon("undoBtn.gif"));
		redoBtn			= new JButton("",new ImageIcon("redoBtn.gif"));
		clearBtn		= new JButton("",new ImageIcon("clearBtn.gif"));
		
		lineBtn.addActionListener(new ToolButtonListener());
		lineBtn.setToolTipText("Line");
		squareBtn.addActionListener(new ToolButtonListener());
		squareBtn.setToolTipText("Retangle");
		ovalBtn.addActionListener(new ToolButtonListener());
		ovalBtn.setToolTipText("Oval");
		polygonBtn.addActionListener(new ToolButtonListener());
		polygonBtn.setToolTipText("Polygon");
		roundRectBtn.addActionListener(new ToolButtonListener());
		roundRectBtn.setToolTipText("Rectangle");
		freeHandBtn.addActionListener(new ToolButtonListener());
		freeHandBtn.setToolTipText("Free Hand");
		undoBtn.addActionListener(new ToolButtonListener());
		undoBtn.setToolTipText("Undo");
		redoBtn.addActionListener(new ToolButtonListener());
		redoBtn.setToolTipText("Redo");
		clearBtn.addActionListener(new ToolButtonListener());
		clearBtn.setToolTipText("Clear Canvas");
/*----------------------------------------------------------------------------*/		
		fullChk = new JCheckBox("Full");
		fullChk.addItemListener(
			new ItemListener()
			{
				public void itemStateChanged(ItemEvent event)
				{
					if(fullChk.isSelected())
						canvasPanel.setSolidMode(Boolean.TRUE);
					else
						canvasPanel.setSolidMode(Boolean.FALSE);
				}	
			}
		);
/*----------------------------------------------------------------------------*/		
		this.setLayout(new GridLayout(1,9)); // 8 Buttons & 1 CheckBox
		this.add(lineBtn);
		this.add(squareBtn);
		this.add(ovalBtn);
		this.add(polygonBtn);
		this.add(roundRectBtn);
		this.add(freeHandBtn);
		this.add(undoBtn);
		this.add(redoBtn);
		this.add(clearBtn);
		this.add(fullChk);				
	}
/*----------------------------------------------------------------------------*/
	private class ToolButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{	
			if(canvasPanel.isExistPolygonBuffer()!= false)
			{
				canvasPanel.flushPolygonBuffer();
			}
			if(event.getSource() == lineBtn)
			{
				canvasPanel.setDrawMode(canvasPanel.LINE);		
			}
			if(event.getSource() == squareBtn)
			{
				canvasPanel.setDrawMode(canvasPanel.SQUARE);
			}
			if(event.getSource() == ovalBtn)
			{
				canvasPanel.setDrawMode(canvasPanel.OVAL);
			}
			if(event.getSource() == polygonBtn)
			{
				canvasPanel.setDrawMode(canvasPanel.POLYGON);
			}
			if(event.getSource() == roundRectBtn)
			{
				canvasPanel.setDrawMode(canvasPanel.ROUND_RECT);
			}
			if(event.getSource() == freeHandBtn)
			{
				canvasPanel.setDrawMode(canvasPanel.FREE_HAND);
			}
			if(event.getSource() == undoBtn)
			{
				canvasPanel.undo();
			}
			if(event.getSource() == redoBtn)
			{
				canvasPanel.redo();
			}
			if(event.getSource() == clearBtn)
			{
				canvasPanel.clearCanvas();
			}
		}
	}
}

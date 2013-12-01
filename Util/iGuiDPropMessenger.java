package Util;

import Session.GuiDComponent;
import UI.GuiDPropertiesPanel;

public interface iGuiDPropMessenger
{
	public void changePropPanel(GuiDComponent gcmp);
	public void updateCompSel(String s);
}
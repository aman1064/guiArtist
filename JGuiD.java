import UI.*;
import Util.*;
import Session.*;

import java.io.File;
/**
* This is the mainclass were the show starts this class calls
* the Project Dialog and waits for user interaction 
*/
public class JGuiD implements iJGuiD
{
   /**
         * This Constructor just calls the showProjectDialog() function
        */
	public JGuiD()
	{
		showProjectDialog();
	}
	/**
	*This creates an instance for the class GuiDProjectDialog
	* Which show the Project Dialog
	*/
	public void showProjectDialog()
	{
	   GuiDProjectDialog gpd = new GuiDProjectDialog(this);
	}
	/**
	*function starts a new Project by sending the type of project to GuiDSession Object
	*which Then start a session for new project
	*/
	public void startNewProject(String type)
	{
		GuiDSession prim = new GuiDSession(type,this);
	}
	/**
	*function starts a existing Project by sending the project file  to GuiDSession Object
	*which Then start a session for specified project file
	*/
	public void openExistingProject(File f)
	{
		GuiDSession prim = new GuiDSession(f,this);
	}
	public static void main(String[] args) 
	{
		JGuiD tg = new JGuiD();
	}
}

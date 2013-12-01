package Util; 
import java.io.*; 
import javax.swing.JOptionPane;


/**
 * Class for reading writing to and from file
 * and other filehandling issues such as OS independent Path.
 */ 
public abstract class GuiDFileHandler
{
  
   /**
    * Reads a file from disk sequentially and returns it as a String.
    */
   public static String readFromFile( String sFilename )
   {
       FileReader file = null;

  
      try
      {                  
         file = new FileReader( sFilename.trim() );
      }
      catch( FileNotFoundException fnfe  )
      {
		  JOptionPane.showMessageDialog( null, sFilename + " could not be found ","File Not Found",JOptionPane.ERROR_MESSAGE);
      }
	  return read(file); 	
   }
   private static String read(FileReader file)
	{
      String sLine = "";
      String str = "";    
      boolean eof = false;     

	   if( file != null )
      {
         try 
         {
            BufferedReader buff = new BufferedReader( file );
            int i = 0;          

            while( !eof )
            {             
               sLine = buff.readLine();             

               if( sLine == null )
               {
                  eof = true;
               }
               else
               {
                  str = str + "\n" + sLine;
               }
            }
         }
         catch( IOException ioe )
         {
            JOptionPane.showMessageDialog( null,ioe,"Error",JOptionPane.ERROR_MESSAGE);
         }     
      }
      return str;

	}
    public static String readFromFile(File f)
	{
       FileReader file = null;

	  try
      {                  
         file = new FileReader(f);
      }
      catch( FileNotFoundException fnfe  )
      {
		  JOptionPane.showMessageDialog( null, f.getName() + " could not be found ","File Not Found",JOptionPane.ERROR_MESSAGE);
      }
		
       return read(file);
	}

   /**
    * Writes the String s to file filename.
    
    */
   public static void writeToFile( File filename, String s )
   {
      try 
      {
         filename.createNewFile();
      }
      catch( IOException ioe )
      {
         JOptionPane.showMessageDialog( null, 
                                        "Could not create \"" + filename 
                                        + "\" are you sure you have write permission to this path ?",
                                        "Could not create file",
                                        JOptionPane.ERROR_MESSAGE );
         return;
      }

      try 
      {
         PrintWriter out = new PrintWriter( new FileWriter( filename ) );
         out.print( s );
         out.close();
      }
      catch( Exception e )
      {
		  JOptionPane.showMessageDialog( null,"could not Write File :"+e,"File Cannot be Written",JOptionPane.ERROR_MESSAGE);
      }
   }


}



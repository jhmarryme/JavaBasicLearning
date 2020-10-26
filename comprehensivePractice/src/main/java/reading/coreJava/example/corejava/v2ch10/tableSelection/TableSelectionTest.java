package coreJava.example.corejava.v2ch10.tableSelection;

/**
 @version 1.05 2018-05-01
 @author Cay Horstmann
 */

import java.awt.*;
import javax.swing.*;

/**
 * This program demonstrates selection, addition, and removal of rows and columns.
 */
public class TableSelectionTest
{
   public static void main(String[] args)
   {
      EventQueue.invokeLater(() ->
         {
            var frame = new TableSelectionFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
      });
   }
}

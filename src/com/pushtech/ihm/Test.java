package com.pushtech.ihm;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Test extends JFrame {

   public Test(String strTitle) {
      this.setTitle(strTitle);
      this.setSize(1000, 250);
      this.setLocationRelativeTo(null);
      this.setResizable(false);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      JLabel title = new JLabel(" ");
      this.getContentPane().setLayout(new BorderLayout());
      this.add(title, BorderLayout.NORTH);
      this.add(new JLabel("               "), BorderLayout.WEST);
      this.add(new JLabel("               "), BorderLayout.EAST);
      this.getContentPane().add(new CentralPanel(), BorderLayout.CENTER);
      this.setVisible(true);
      this.setVisible(true);
   }

   public static void main(String[] args) {
      try {
         UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      } catch (ClassNotFoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (InstantiationException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (IllegalAccessException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (UnsupportedLookAndFeelException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      new Test("Alco Distribution");
   }

}
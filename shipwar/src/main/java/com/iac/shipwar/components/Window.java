package com.iac.shipwar.components;

import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.iac.shipwar.components.util.BackgroundPanel;
import com.iac.shipwar.interfaces.IProperties;

public class Window implements IProperties {
  protected int width;
  protected int height;
  protected JPanel panelMain;
  protected JFrame frame;
  protected String img = "b.jpg";

  public Window(String title, int w, int h) {
    this.width = w;
    this.height = h;
    this.frame = new JFrame(title);
    this.panelMain = new BackgroundPanel("/com/iac/shipwar/img/"+img);
    this.generateWindow();
  }

  private void generateWindow() {
    this.size_(this.frame, this.width, this.height);
    this.size_(this.panelMain, this.width, this.height);
    this.background_(this.panelMain, "#353940");
    this.frame.add(this.panelMain);
    this.frame.setResizable(false);
    this.frame.setVisible(true);
    this.frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.panelMain.setLayout(new GridBagLayout());
  }

  public JPanel getPanel() {
    return this.panelMain;
  }

  public JFrame getFrame() {
    return this.frame;
  }

}
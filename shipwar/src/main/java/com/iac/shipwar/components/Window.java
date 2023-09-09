package com.iac.shipwar.components;

import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.iac.shipwar.interfaces.IProperties;

public class Window implements IProperties {
  protected final int width = 1700;
  protected final int height = 1000;
  protected JPanel panelMain;
  protected JFrame frame;

  public Window(String title) {
    this.frame = new JFrame(title);
    this.panelMain = new BackgroundPanel("/com/iac/shipwar/img/b.jpg");
    this.generateWindow();
  }

  private void generateWindow() {
    this.size_(this.frame, this.width, this.height);
    this.size_(this.panelMain, this.width, this.height);
    this.background_(this.panelMain, "#202124");

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
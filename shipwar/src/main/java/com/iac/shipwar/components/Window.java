package com.iac.shipwar.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.iac.shipwar.data.ElementPosition;
import com.iac.shipwar.data.PanelCharacteristic;
import com.iac.shipwar.interfaces.IProperties;

public class Window implements IProperties {
  protected final int width = 1750;
  protected final int height = 1000;
  protected JPanel panelMain;
  protected JFrame frame;
  protected Panel_ panelInformation;
  protected Panel_ panelBoard;
  protected Panel_ panelEnemyBoard;

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

    final PanelCharacteristic characteristicPanelInfo = new PanelCharacteristic(
        300,
        900,
        20,
        128,
        60,
        10,
        0,
        0,
        "#000000");

    final PanelCharacteristic characteristicPanelMyBoard = new PanelCharacteristic(
        650,
        650,
        10,
        128,
        60,
        10,
        1,
        0,
        "#000000");

    final PanelCharacteristic characteristicPanelEnemyBoard = new PanelCharacteristic(
        650,
        650,
        10,
        128,
        60,
        10,
        2,
        0, "#000000");

    this.panelInformation = layout(characteristicPanelInfo);
    this.panelBoard = layout(characteristicPanelMyBoard);
    this.panelEnemyBoard = layout(characteristicPanelEnemyBoard);

    Text_ title = new Text_("Estado", this.panelInformation.getSizeWidthComponent(), 30);
    title.setSize(25);
    title.setColor("#FFFFFF");
    title.setType(Font.BOLD);
    title.setAling(ElementPosition.CENTER);
    this.panelInformation.addComponent(title.getLabel());
  }

  private Panel_ layout(PanelCharacteristic characteristic) {
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = characteristic.Xposition();
    gbc.gridy = characteristic.Yposition();
    gbc.insets = new Insets(characteristic.gap(), characteristic.gap(), characteristic.gap(), characteristic.gap());
    final Panel_ buildPanel = new Panel_(characteristic.width(), characteristic.height(), characteristic.padding(),
        characteristic.colorHex(), characteristic.transparency());
    buildPanel.radiusBorder(characteristic.rounded());
    this.panelMain.add(buildPanel.getPanel(), gbc);
    return buildPanel;
  }

  /* */

}
package components;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Window implements IProperties {
  protected final int width = 1750;
  protected final int height = 1000;
  protected JPanel panelMain;
  protected JFrame frame;

  public Window(String title) {
    this.frame = new JFrame(title);
    this.panelMain = new BackgroundPanel("/img/b.jpg");
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

    // Crear las restricciones
    GridBagConstraints gbcInfo = new GridBagConstraints();
    gbcInfo.gridx = 0; // Posición en la cuadrícula (columna 0)
    gbcInfo.gridy = 0; // Posición en la cuadrícula (fila 0)
    gbcInfo.insets = new Insets(10, 10, 10, 10); // Separación entre componentes

    GridBagConstraints gbcMyBoard = new GridBagConstraints();
    gbcMyBoard.gridx = 1; // Posición en la cuadrícula (columna 1)
    gbcMyBoard.gridy = 0; // Posición en la cuadrícula (fila 0)
    gbcMyBoard.insets = new Insets(10, 10, 10, 10); // Separación entre componentes

    GridBagConstraints gbcEnemyBoard = new GridBagConstraints();
    gbcEnemyBoard.gridx = 2; // Posición en la cuadrícula (columna 2)
    gbcEnemyBoard.gridy = 0; // Posición en la cuadrícula (fila 0)
    gbcEnemyBoard.insets = new Insets(10, 10, 10, 10);

    final Panel_ info = new Panel_(300, 900, 20, "#FFFFFF", 128);

    final Panel_ myBoard = new Panel_(650, 650, 10, "#000000",128);

    final Panel_ enemyBoard = new Panel_(650, 650, 10, "#202124",128);

    info.radiusBorder(60);
    myBoard.radiusBorder(60);
    enemyBoard.radiusBorder(60);

    element(info);

    this.panelMain.add(info.getPanel(), gbcInfo);
    this.panelMain.add(myBoard.getPanel(), gbcMyBoard);
    this.panelMain.add(enemyBoard.getPanel(), gbcEnemyBoard);

  }
  // componente JFrame

  public void element(Panel_ m) {
    JLabel label_ = new JLabel();
    label_.setText("Puntuacion:");
    this.font_(label_, new HashMap<String, String>() {
      {
        put("color", "#000000");
        put("set", "false");
      }
    });
    // JTextArea miJTextArea = new JTextArea(5, 20);
    label_.setBackground(Color.BLUE);
    label_.setPreferredSize(m.getSizeWidthComponent(50));
    m.addComponent(label_);
    // m.add(miJTextArea);

  }

  /* */

}
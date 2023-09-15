package com.iac.shipwar.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.iac.shipwar.components.layout.UiDashboard;
import com.iac.shipwar.components.widgets.Button_;
import com.iac.shipwar.components.widgets.Options_;
import com.iac.shipwar.components.widgets.Panel_;
import com.iac.shipwar.components.widgets.Text_;
import com.iac.shipwar.models.dataclass.AttackCoordinates;
import com.iac.shipwar.models.dataclass.PanelCharacteristic;
import com.iac.shipwar.models.enums.AlingText;
import com.iac.shipwar.models.enums.Column;
import com.iac.shipwar.models.enums.Dashboard;
import com.iac.shipwar.models.enums.MainPanels;
import com.iac.shipwar.models.enums.Row;
import com.iac.shipwar.models.enums.Ship;
import com.iac.shipwar.models.enums.SubcomponentOrientation;

import java.awt.event.ActionListener;

public class Coordinates {
    protected Map<MainPanels, Panel_> mainPanels;
    protected UiDashboard dashboard;
    protected ArrayList<ArrayList<Column>> coordinateMatrix = new ArrayList<ArrayList<Column>>();;
    protected Map<String, Panel_> panels = new HashMap<String, Panel_>();
    protected int shipCount = Ship.BIG.getNumber() + Ship.MEDIUM.getNumber() + Ship.SMALL.getNumber();
    protected Map<String, Map<Ship, ArrayList<ArrayList<Integer>>>> coordinatesMyShip = new HashMap<String, Map<Ship, ArrayList<ArrayList<Integer>>>>();

    public Coordinates(Map<MainPanels, Panel_> mp, UiDashboard ud) {
        this.mainPanels = mp;
        this.dashboard = ud;
        this.coordinatesMyShip.put("initial", new HashMap<>());
        this.coordinatesMyShip.put("final", new HashMap<>());
        initialMap(Ship.SMALL);
        initialMap(Ship.MEDIUM);
        initialMap(Ship.BIG);

        buildArray();
        assembleComponent();
    }

    private void assembleComponent() {
        Options_ shipSize = boxedOptionsShip("Barco", Ship.class, "shipSize");
        createText("_______Posicion inicial_______", "initialSubtitle");
        Options_ iROw = boxedOptions("Fila", Row.class, "iROw");
        Options_ iColumn = boxedOptions("Columna", Column.class, "iColumn");
        continueButtonAction(createButton("Continuar", "continueBtn"), shipSize, iROw, iColumn);
        createText("_______Posicion final_______", "finalSubtitle");
        boxedOptions("Fila", Row.class, "eROw");
        boxedOptions("Columna", Column.class, "eColumn");
        keepButtonAction(createButton("Guardar", "saveBtn"));
        visibleComponents(false);
        this.dashboard.getBox(Dashboard.COORDINATES).activateGrip(SubcomponentOrientation.VERTICAL_LEFT);

    }

    private <T extends Enum<T>> Options_<T> boxedOptions(String name, Class<T> enumClass, String id) {
        Panel_ panel = createDefaultPanel(this.dashboard.getBox(Dashboard.COORDINATES), 35);
        final Options_<T> options = new Options_<>(enumClass);
        panel.addComponent(new Text_(name, 100).getLabel());
        options.addPanel(panel);
        this.panels.put(id, panel);
        this.dashboard.getBox(Dashboard.COORDINATES).addComponent(panel.getPanel());
        return options;
    }

    private <T extends Enum<T>> Options_<T> boxedOptionsShip(String name, Class<T> enumClass, String id) {
        Panel_ panel = createDefaultPanel(this.dashboard.getBox(Dashboard.COORDINATES), 35);
        final Options_<T> options = new Options_<>(enumClass);
        options.setWidth(100);
        panel.addComponent(new Text_(name, 60).getLabel());
        options.addPanel(panel);
        this.panels.put(id, panel);
        this.dashboard.getBox(Dashboard.COORDINATES).addComponent(panel.getPanel());
        return options;
    }

    private Text_ createText(String content, String id) {
        Panel_ panel = createDefaultPanel(this.dashboard.getBox(Dashboard.COORDINATES), 30);
        Text_ text = new Text_(content,
                this.dashboard.getBox(Dashboard.COORDINATES).getSizeWidthComponent());
        text.setAling(AlingText.CENTER);
        text.setColor("#154eb7");
        panel.addComponent(text.getLabel());
        this.panels.put(id, panel);
        this.dashboard.getBox(Dashboard.COORDINATES).addComponent(panel.getPanel());
        return text;
    }

    private Panel_ createDefaultPanel(Panel_ box, int h) {
        return new Panel_(
                new PanelCharacteristic(box.getSizeWidthComponent(), h, 0,
                        0, 40, 10,
                        0, 0,
                        "#000000", box.getPanel()));
    }

    private Button_ createButton(String Text, String id) {
        Panel_ panel = createDefaultPanel(this.dashboard.getBox(Dashboard.COORDINATES), 60);
        Button_ b = new Button_();
        Text_ t = new Text_(Text, this.dashboard.getBox(Dashboard.COORDINATES).getSizeWidthComponent());
        b.add(t.getLabel());
        b.setRounded(50);
        b.setWidth(120);
        b.setHeight(40);
        b.setColorBg("#154eb7");
        t.setColor("#FFFFFF");
        panel.addComponent(b.geButton());
        this.panels.put(id, panel);
        this.dashboard.getBox(Dashboard.COORDINATES).addComponent(panel.getPanel());
        return b;
    }

    private void visibleComponents(boolean b) {
        panels.get("continueBtn").visible(!false);
        this.panels.get("finalSubtitle").visible(b);
        this.panels.get("eROw").visible(b);
        this.panels.get("eColumn").visible(b);
        this.panels.get("saveBtn").visible(b);
    }

    private void continueButtonAction(Button_ btn, Options_ ship, Options_ row, Options_ column) {
        JButton button = btn.geButton();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Row selectedRow = (Row) row.getComboBox().getSelectedItem();
                Column selectedColumn = (Column) column.getComboBox().getSelectedItem();
                Ship selectShip = (Ship) ship.getComboBox().getSelectedItem();
                ArrayList<Integer> newData = new ArrayList<>();
                newData.add(selectedRow.getIndex());
                newData.add(selectedColumn.getIndex());

                if (coordinatesMyShip.get("initial").get(selectShip).size() == (selectShip.getNumber() - 1)) {
                    int selectedIndex = ship.getComboBox().getSelectedIndex();
                    ship.getComboBox().removeItemAt(selectedIndex);
                }
                //comprobar si las coordenadas enviadas no se repiten
                coordinatesMyShip.get("initial").get(selectShip)
                        .add(newData);

                visibleComponents(true);

                System.out.println(coordinatesMyShip.toString());

            }
        });
    }

    private void initialMap(Ship key) {
        this.coordinatesMyShip.get("initial").put(key, new ArrayList<>());
        this.coordinatesMyShip.get("final").put(key, new ArrayList<>());
    }

    private void keepButtonAction(Button_ btn) {
        JButton button = btn.geButton();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                visibleComponents(false);

            }
        });
    }

    private void buildArray() {
        ArrayList<Column> arrayL = new ArrayList<Column>();
        Column[] columns = Column.values();
        for (Column c : columns) {
            arrayL.add(c);
        }
        for (int i = 0; i <= arrayL.size(); i++) {
            this.coordinateMatrix.add(arrayL);
        }
    }

    private void availableOptions(Row rInit, Column cInit) {
        int rIndex = rInit.getIndex();
        int cIndex = cInit.getIndex();
        // swtich case de rindex, case == 0 incrementa +1 case case == 7 -1, default
        // incrementa y tambien resta
        ArrayList<Integer> rowResult = possibleRowMovements(rIndex);

        Column cPOsicion = this.coordinateMatrix.get(rIndex).get(cIndex);

        System.out
                .println("coordenada: " + rInit + "=" + rIndex + " || " + cInit + "=" + cIndex + "\nresultado de las ");

    }

    private ArrayList<Integer> possibleRowMovements(int row) {
        ArrayList<Integer> movements = new ArrayList<Integer>();
        switch (row) {
            case 0:
                movements.add((row + 1));
                break;
            case 7:
                movements.add((row - 1));
                break;
            default:
                movements.add((row + 1));
                movements.add((row - 1));
                break;
        }
        return movements;
    }

    private ArrayList<Integer> possibleColumnMovements(int column) {
        ArrayList<Integer> movements = new ArrayList<Integer>();
        switch (column) {
            case 0:
                movements.add((column + 1));

                break;
            case 7:
                movements.add((column - 1));
                break;
            default:
                movements.add((column + 1));
                movements.add((column - 1));
                break;
        }
        return movements;
    }

}

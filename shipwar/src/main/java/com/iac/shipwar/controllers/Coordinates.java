package com.iac.shipwar.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;

import javax.swing.JButton;

import com.iac.shipwar.components.layout.UiBoard;
import com.iac.shipwar.components.layout.UiDashboard;
import com.iac.shipwar.components.util.EnumList;
import com.iac.shipwar.components.widgets.Button_;
import com.iac.shipwar.components.widgets.Options_;
import com.iac.shipwar.components.widgets.Panel_;
import com.iac.shipwar.components.widgets.Text_;
import com.iac.shipwar.models.dataclass.PanelCharacteristic;
import com.iac.shipwar.models.enums.AlingText;
import com.iac.shipwar.models.enums.Column;
import com.iac.shipwar.models.enums.Dashboard;
import com.iac.shipwar.models.enums.Row;
import com.iac.shipwar.models.enums.Ship;
import com.iac.shipwar.models.enums.SubcomponentOrientation;

import java.awt.event.ActionListener;

public class Coordinates {
    protected UiDashboard dashboard;
    protected UiBoard myBoard;
    protected Panel_ enemyPanel;
    protected ArrayList<ArrayList<Column>> coordinateMatrix = new ArrayList<ArrayList<Column>>();;
    protected Map<String, Panel_> panels = new HashMap<String, Panel_>();
    protected int shipCount = Ship.BIG.getNumber() + Ship.MEDIUM.getNumber() + Ship.SMALL.getNumber();
    protected Map<String, Map<Ship, ArrayList<ArrayList<Integer>>>> coordinatesMyShip = new HashMap<String, Map<Ship, ArrayList<ArrayList<Integer>>>>();

    private Options_<Ship> shipSize;
    protected Options_<Row> iROw;
    protected Options_<Column> iColumn;
    protected Options_<Row> fRow;
    protected Options_<Column> fColumn;

    public Coordinates(UiDashboard ud, UiBoard myBoard, Panel_ enemyPanel) {
        this.myBoard = myBoard;
        this.enemyPanel = enemyPanel;
        this.dashboard = ud;
        this.coordinatesMyShip.put("initial", new HashMap<>());
        this.coordinatesMyShip.put("middle", new HashMap<>());
        this.coordinatesMyShip.put("final", new HashMap<>());
        initialMap(Ship.SMALL);
        initialMap(Ship.MEDIUM);
        initialMap(Ship.BIG);
        buildArray();
        assembleComponent();
    }

    private void assembleComponent() {
        createText("Error: Coordenada incorrecta", "alertError", "#Be2528");
        this.shipSize = boxedOptionsShip("Barco", Ship.class, "shipSize");
        createText("_______Posicion inicial_______", "initialSubtitle", "#154eb7");
        this.iROw = boxedOptions("Fila", Row.class, "iROw");
        this.iColumn = boxedOptions("Columna", Column.class, "iColumn");
        continueButtonAction(createButton("Continuar", "continueBtn", "#154eb7"));
        createText("_______Posicion final_______", "finalSubtitle", "#154eb7");
        this.fRow = boxedOptions("Fila", Row.class, "eROw");
        this.fColumn = boxedOptions("Columna", Column.class, "eColumn");
        saveButtonAction(createButton("Guardar", "saveBtn", "#64ae4d"));
        panels.get("alertError").visible(false);
        visibleComponents(false);
        this.dashboard.getBox(Dashboard.COORDINATES).activateGrip(SubcomponentOrientation.VERTICAL_LEFT);
    }

    private void continueButtonAction(Button_ btn) {
        JButton button = btn.geButton();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                availableOptions();
                Ship selectShip = (Ship) shipSize.getComboBox().getSelectedItem();
                if (selectShip != Ship.SMALL) {
                    visibleComponents(true);
                } else {
                    panels.get("continueBtn").visible(false);
                    panels.get("finalSubtitle").visible(false);
                    panels.get("eROw").visible(false);
                    panels.get("eColumn").visible(false);
                    panels.get("saveBtn").visible(true);

                }

            }
        });
    }

    private void saveButtonAction(Button_ btn) {
        JButton button = btn.geButton();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Integer> newInitialCoordinate = new ArrayList<>();
                ArrayList<Integer> newFinalCoordinate = new ArrayList<>();

                Ship selectShip = (Ship) shipSize.getComboBox().getSelectedItem();

                Row initialRowSelection = (Row) iROw.getComboBox().getSelectedItem();
                Column initialColumnSelection = (Column) iColumn.getComboBox().getSelectedItem();

                newInitialCoordinate.add(initialRowSelection.getIndex());
                newInitialCoordinate.add(initialColumnSelection.getIndex());
                coordinatesMyShip.get("initial").get(selectShip)
                        .add(newInitialCoordinate);
                if (selectShip != Ship.SMALL) {
                    Row finalRowSelection = (Row) fRow.getComboBox().getSelectedItem();
                    Column finalColumnSelection = (Column) fColumn.getComboBox().getSelectedItem();
                    if (initialRowSelection != finalRowSelection || initialColumnSelection != finalColumnSelection) {
                        newFinalCoordinate.add(finalRowSelection.getIndex());
                        newFinalCoordinate.add(finalColumnSelection.getIndex());
                        coordinatesMyShip.get("final").get(selectShip)
                                .add(newFinalCoordinate);
                        myBoard.changeColor(finalRowSelection, finalColumnSelection, selectShip.getColorHex());
                        visibleComponents(false);
                        iROw.setEnabled(true);
                        iColumn.setEnabled(true);
                        shipSize.setEnabled(true);
                        panels.get("alertError").visible(false);
                    } else {

                        panels.get("alertError").visible(true);
                        if (coordinatesMyShip.get("initial").get(selectShip).size() == 1) {
                            coordinatesMyShip.get("initial").get(selectShip).remove(0);
                        } else {
                            coordinatesMyShip.get("initial").get(selectShip)
                                    .remove(coordinatesMyShip.get("initial").get(selectShip).size() - 1);
                        }

                    }

                } else {
                    iROw.setEnabled(true);
                    iColumn.setEnabled(true);
                    shipSize.setEnabled(true);

                    visibleComponents(false);
                }

                if (coordinatesMyShip.get("initial").get(selectShip).size() == selectShip.getNumber()) {
                    int selectedIndex = shipSize.getComboBox().getSelectedIndex();
                    shipSize.getComboBox().removeItemAt(selectedIndex);
                }

                System.out.println(coordinatesMyShip.toString());

            }
        });
    }

    private boolean coordinateVerification() {
        // ArrayList<String> keys
        // recore todos los arrays y verifica si las posicones no se repiten, si se
        // repiten no se guarda ysale un mensaje de alerta
        return true;
    }

    private void availableOptions() {
        Ship selectShip = (Ship) shipSize.getComboBox().getSelectedItem();
        Row rInit = (Row) this.iROw.getComboBox().getSelectedItem();
        Column cInit = (Column) this.iColumn.getComboBox().getSelectedItem();
        this.shipSize.setEnabled(false);

        // los irow y icolumn de abajo pueden cambiar de acuerdo a la verificacion, para que lo
        // pueda cambiar para que se haga valido
        this.iROw.setEnabled(false);
        this.iColumn.setEnabled(false);

        final EnumList<Row> horizontal = (EnumList<Row>) possibleMovements(rInit);
        final EnumList<Column> vertical = (EnumList<Column>) possibleMovements(cInit);

        myBoard.changeColor(rInit, cInit, selectShip.getColorHex());
        this.fRow.setOptions(horizontal.getArrayList());
        this.fColumn.setOptions(vertical.getArrayList());

        System.out.println("posible horizontal: " + horizontal.getArrayList().toString());
        System.out.println("posible vertical: " + vertical.getArrayList().toString());
    }

    private EnumList<? extends Enum<?>> possibleMovements(Enum<?> rowOrColumn) {
        EnumList movements = new EnumList<>();

        int index = (rowOrColumn instanceof Column) ? ((Column) rowOrColumn).getIndex()
                : ((Row) rowOrColumn).getIndex();

        switch (index) {
            case 7:
                movements.addEnum(getByIndex(rowOrColumn, (index - 1)));
                movements.addEnum(getByIndex(rowOrColumn, index));
                break;
            case 0:
                movements.addEnum(getByIndex(rowOrColumn, index));
                movements.addEnum(getByIndex(rowOrColumn, (index + 1)));
                break;
            default:
                movements.addEnum(getByIndex(rowOrColumn, (index - 1)));
                movements.addEnum(getByIndex(rowOrColumn, index));
                movements.addEnum(getByIndex(rowOrColumn, (index + 1)));
                break;
        }
        return movements;
    }

    private Enum<?> getByIndex(Enum<?> rowOrColumn, int index) {
        if (rowOrColumn instanceof Column) {
            return Column.getByIndex(index);
        } else if (rowOrColumn instanceof Row) {
            return Row.getByIndex(index);
        } else {
            return null;
        }
    }

    private void visibleComponents(boolean b) {
        panels.get("continueBtn").visible(!b);
        this.panels.get("finalSubtitle").visible(b);
        this.panels.get("eROw").visible(b);
        this.panels.get("eColumn").visible(b);
        this.panels.get("saveBtn").visible(b);
    }

    private void initialMap(Ship key) {
        this.coordinatesMyShip.get("initial").put(key, new ArrayList<>());
        this.coordinatesMyShip.get("middle").put(key, new ArrayList<>());
        this.coordinatesMyShip.get("final").put(key, new ArrayList<>());
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

    private <T extends Enum<T>> Options_<T> boxedOptions(String name, Class<T> enumClass, String id) {
        Panel_ panel = createDefaultPanel(this.dashboard.getBox(Dashboard.COORDINATES), 35);
        final Options_<T> options = new Options_<>();
        options.pureEnum(enumClass);
        panel.addComponent(new Text_(name, 100).getLabel());
        options.addPanel(panel);
        this.panels.put(id, panel);
        this.dashboard.getBox(Dashboard.COORDINATES).addComponent(panel.getPanel());
        return options;
    }

    private <T extends Enum<T>> Options_<T> boxedOptionsShip(String name, Class<T> enumClass, String id) {
        Panel_ panel = createDefaultPanel(this.dashboard.getBox(Dashboard.COORDINATES), 35);
        final Options_<T> options = new Options_<>();
        options.pureEnum(enumClass);
        options.setWidth(100);
        panel.addComponent(new Text_(name, 60).getLabel());
        options.addPanel(panel);
        this.panels.put(id, panel);
        this.dashboard.getBox(Dashboard.COORDINATES).addComponent(panel.getPanel());
        return options;
    }

    private Text_ createText(String content, String id, String colorHex) {
        Panel_ panel = createDefaultPanel(this.dashboard.getBox(Dashboard.COORDINATES), 30);
        Text_ text = new Text_(content,
                this.dashboard.getBox(Dashboard.COORDINATES).getSizeWidthComponent());
        text.setAling(AlingText.CENTER);
        text.setColor(colorHex);
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

    private Button_ createButton(String Text, String id, String colorHex) {
        Panel_ panel = createDefaultPanel(this.dashboard.getBox(Dashboard.COORDINATES), 60);
        Button_ b = new Button_();
        Text_ t = new Text_(Text, this.dashboard.getBox(Dashboard.COORDINATES).getSizeWidthComponent());
        b.add(t.getLabel());
        b.setRounded(50);
        b.setWidth(120);
        b.setHeight(40);
        b.setColorBg(colorHex);
        t.setColor("#FFFFFF");
        panel.addComponent(b.geButton());
        this.panels.put(id, panel);
        this.dashboard.getBox(Dashboard.COORDINATES).addComponent(panel.getPanel());
        return b;
    }

}

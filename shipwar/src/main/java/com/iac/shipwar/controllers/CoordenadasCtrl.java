package com.iac.shipwar.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.Timer;

import javax.swing.JButton;

import com.iac.shipwar.UI.components.Coordinates;
import com.iac.shipwar.UI.layout.UiBoard;
import com.iac.shipwar.UI.layout.UiDashboard;
import com.iac.shipwar.UI.util.EnumList;
import com.iac.shipwar.UI.widgets.Button_;
import com.iac.shipwar.UI.widgets.Panel_;
import com.iac.shipwar.models.enums.Column;
import com.iac.shipwar.models.enums.Dashboard;
import com.iac.shipwar.models.enums.Row;
import com.iac.shipwar.models.enums.Ship;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CoordenadasCtrl extends Coordinates {
    protected Map<String, Map<Ship, ArrayList<ArrayList<Integer>>>> coordinatesMyShip = new HashMap<String, Map<Ship, ArrayList<ArrayList<Integer>>>>();
    protected ArrayList<ArrayList<Column>> coordinateMatrix = new ArrayList<ArrayList<Column>>();;
    boolean available = false;
    protected Timer timer;

    public CoordenadasCtrl(UiDashboard ud, UiBoard myBoard, Panel_ enemyPanel) {
        super(ud, myBoard, enemyPanel);
        continueButtonAction(continueBtn);
        saveButtonAction(saveBtn);
        this.coordinatesMyShip.put("initial", new HashMap<>());
        this.coordinatesMyShip.put("middle", new HashMap<>());
        this.coordinatesMyShip.put("final", new HashMap<>());
        initialMap(Ship.SMALL);
        initialMap(Ship.MEDIUM);
        initialMap(Ship.BIG);
        buildArray();
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

    private void continueButtonAction(Button_ btn) {
        JButton button = btn.geButton();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                availableOptions();
                Ship selectShip = (Ship) shipSize.getComboBox().getSelectedItem();
                if (selectShip != Ship.SMALL) {
                    dashboard.getBox(Dashboard.COORDINATES).setHeight(395);
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

                if (!verificationPosition(initialRowSelection, initialColumnSelection)) {

                    if (selectShip != Ship.SMALL) {
                        Row finalRowSelection = (Row) fRow.getComboBox().getSelectedItem();
                        Column finalColumnSelection = (Column) fColumn.getComboBox().getSelectedItem();
                        if (!verificationPosition(finalRowSelection, finalColumnSelection)) {
                            myBoard.changeColor(initialRowSelection, initialColumnSelection, selectShip.getColorHex());
                            newInitialCoordinate.add(initialRowSelection.getIndex());
                            newInitialCoordinate.add(initialColumnSelection.getIndex());
                            coordinatesMyShip.get("initial").get(selectShip)
                                    .add(newInitialCoordinate);
                            differentPosition(initialRowSelection, finalRowSelection, initialColumnSelection,
                                    finalColumnSelection, newFinalCoordinate, selectShip);

                        } else {
                            System.out.println("if de adentor");
                            // panels.get("alertError").visible(true);
                            // alertCompletion("alertError", 395);
                            // dashboard.getBox(Dashboard.COORDINATES).setHeight(500);

                            available = false;
                        }

                    } else {
                        myBoard.changeColor(initialRowSelection, initialColumnSelection, selectShip.getColorHex());

                        newInitialCoordinate.add(initialRowSelection.getIndex());
                        newInitialCoordinate.add(initialColumnSelection.getIndex());
                        coordinatesMyShip.get("initial").get(selectShip)
                                .add(newInitialCoordinate);

                        iROw.setEnabled(true);
                        iColumn.setEnabled(true);
                        shipSize.setEnabled(true);
                        visibleComponents(false);
                    }

                    if (coordinatesMyShip.get("initial").get(selectShip).size() == selectShip.getNumber()) {
                        int selectedIndex = shipSize.getComboBox().getSelectedIndex();
                        shipSize.getComboBox().removeItemAt(selectedIndex);
                        if (shipSize.getComboBox().getItemCount() == 0) {
                            dashboard.getBox(Dashboard.COORDINATES).visible(false);
                            dashboard.getBox(Dashboard.ATTACK).visible(true);
                            dashboard.getBox(Dashboard.FAILED).visible(true);
                            enemyPanel.visible(true);
                            dashboard.getBox(Dashboard.DESTROYED).visible(true);
                        }
                    }
                } else {
                    panels.get("alertError").visible(true);
                    iROw.setEnabled(true);
                    iColumn.setEnabled(true);
                    if (selectShip == Ship.SMALL) {
                        alertCompletion("alertError", 250);
                        dashboard.getBox(Dashboard.COORDINATES).setHeight(300);
                    } else {
                        System.out.println(">>> if de afuera");
                        alertCompletion("alertError", 395);
                        visibleComponents(false);
                        dashboard.getBox(Dashboard.COORDINATES).setHeight(400);
                    }
                    available = false;
                }

                System.out.println(coordinatesMyShip.toString());
            }
        });
    }

    private void differentPosition(Row initialRowSelection, Row finalRowSelection, Column initialColumnSelection,
            Column finalColumnSelection, ArrayList<Integer> newFinalCoordinate, Ship selectShip) {
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
            dashboard.getBox(Dashboard.COORDINATES).setHeight(250);
        } else {
            panels.get("alertError").visible(true);
            available = false;
            System.out.println("estoy en diffrenpostion");
            if (coordinatesMyShip.get("initial").get(selectShip).size() == 1) {
                coordinatesMyShip.get("initial").get(selectShip).remove(0);
            } else {
                coordinatesMyShip.get("initial").get(selectShip)
                        .remove(coordinatesMyShip.get("initial").get(selectShip).size() - 1);
            }
        }
    }

    private boolean verificationPosition(Row iRowSelection, Column iColumnSelection) {
        Iterator<Map.Entry<String, Map<Ship, ArrayList<ArrayList<Integer>>>>> coordinateIterator = coordinatesMyShip
                .entrySet().iterator();
        while (coordinateIterator.hasNext() && !available) {
            Map.Entry<String, Map<Ship, ArrayList<ArrayList<Integer>>>> coordinateOne = coordinateIterator.next();
            Map<Ship, ArrayList<ArrayList<Integer>>> subCoordinate = coordinateOne.getValue();
            Iterator<Map.Entry<Ship, ArrayList<ArrayList<Integer>>>> subCoordinateIterator = subCoordinate.entrySet()
                    .iterator();
            while (subCoordinateIterator.hasNext() && !available) {
                Map.Entry<Ship, ArrayList<ArrayList<Integer>>> subcoordinateIteration = subCoordinateIterator.next();
                ArrayList<ArrayList<Integer>> arrayList = subcoordinateIteration.getValue();
                if (arrayList.isEmpty()) {
                    available = false;
                } else {
                    for (ArrayList<Integer> subArrayList : arrayList) {
                        if (subArrayList.isEmpty()) {
                            available = false;
                        } else {
                            if (subArrayList.get(0) != iRowSelection.getIndex()
                                    || subArrayList.get(1) != iColumnSelection.getIndex()) {
                                available = false;
                            } else {
                                available = true;
                            }
                        }
                    }
                }
            }
        }
        System.out.println("row: " + iRowSelection.getIndex() + " column: " + iColumnSelection.getIndex());
        System.out.println("Existe : " + available);
        return available;
    }

    private void availableOptions() {
        Row rInit = (Row) this.iROw.getComboBox().getSelectedItem();
        Column cInit = (Column) this.iColumn.getComboBox().getSelectedItem();
        this.shipSize.setEnabled(false);
        this.iROw.setEnabled(false);
        this.iColumn.setEnabled(false);
        final EnumList<Row> horizontal = (EnumList<Row>) possibleMovements(rInit);
        final EnumList<Column> vertical = (EnumList<Column>) possibleMovements(cInit);
        this.fRow.setOptions(horizontal.getArrayList());
        this.fColumn.setOptions(vertical.getArrayList());
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

    private void alertCompletion(String alertName, int heightContainer) {
        this.timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panels.get(alertName).visible(false);
                dashboard.getBox(Dashboard.COORDINATES).setHeight(heightContainer);

            }
        });
        this.timer.start();

    }

}

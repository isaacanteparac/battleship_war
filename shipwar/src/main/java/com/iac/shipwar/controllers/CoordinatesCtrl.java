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
import com.iac.shipwar.models.enums.ShipStructure;
import com.iac.shipwar.models.enums.TypeMarineElement;
import com.iac.shipwar.models.enums.VitalConditions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CoordinatesCtrl extends Coordinates {
    protected Map<ShipStructure, Map<Ship, ArrayList<ArrayList<Integer>>>> coordinatesMyShip = new HashMap<ShipStructure, Map<Ship, ArrayList<ArrayList<Integer>>>>();
    protected ArrayList<ArrayList<Column>> coordinateMatrix = new ArrayList<ArrayList<Column>>();;
    protected boolean available = false;
    protected Singleton singleton = Singleton.getInstance();
    protected Timer timer;

    public CoordinatesCtrl(UiDashboard ud, UiBoard myBoard, Panel_ enemyPanel) {
        super(ud, myBoard, enemyPanel);
        continueButtonAction(continueBtn);
        saveButtonAction(saveBtn);
        this.coordinatesMyShip.put(ShipStructure.BOW, new HashMap<>());
        this.coordinatesMyShip.put(ShipStructure.CENTER, new HashMap<>());
        this.coordinatesMyShip.put(ShipStructure.STERN, new HashMap<>());
        initialMap(Ship.MINI);
        initialMap(Ship.SMALL);
        initialMap(Ship.MEDIUM);
        initialMap(Ship.BIG);
        buildArray();
    }

    private void initialMap(Ship key) {
        this.coordinatesMyShip.get(ShipStructure.BOW).put(key, new ArrayList<>());
        this.coordinatesMyShip.get(ShipStructure.CENTER).put(key, new ArrayList<>());
        this.coordinatesMyShip.get(ShipStructure.STERN).put(key, new ArrayList<>());
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

    // NOTE: BOTON DE CONTINUAR, VERIFICA LOS POSIBLES MOVIMIENTOS
    private void continueButtonAction(Button_ btn) {
        JButton button = btn.geButton();

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                availableOptions();
                Ship selectShip = (Ship) shipSize.getComboBox().getSelectedItem();
                if (selectShip != Ship.MINI) {
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
                Ship selectShip = (Ship) shipSize.getComboBox().getSelectedItem();
                Row initialRowSelection = (Row) iROw.getComboBox().getSelectedItem();
                Column initialColumnSelection = (Column) iColumn.getComboBox().getSelectedItem();

                if (!verificationPosition(initialRowSelection, initialColumnSelection)) {
                    if (selectShip != Ship.MINI) {
                        Row finalRowSelection = (Row) fRow.getComboBox().getSelectedItem();
                        Column finalColumnSelection = (Column) fColumn.getComboBox().getSelectedItem();
                        if (!verificationPosition(finalRowSelection, finalColumnSelection)) {
                            add(ShipStructure.BOW, initialRowSelection, initialColumnSelection, selectShip);
                            differentPosition(initialRowSelection, finalRowSelection, initialColumnSelection,
                                    finalColumnSelection, selectShip);
                        } else {
                            available = false;
                        }
                    } else {
                        add(ShipStructure.BOW, initialRowSelection, initialColumnSelection, selectShip);
                        iROw.setEnabled(true);
                        iColumn.setEnabled(true);
                        shipSize.setEnabled(true);
                        visibleComponents(false);
                        saveData(initialRowSelection, initialColumnSelection, selectShip);
                    }
                    enableEnemyBoard(selectShip);
                } else {
                    enableAlert(selectShip);
                }
            }
        });
    }

    private void saveData(Row row, Column column, Ship ship) {
        ShipDeployed deployed = singleton.getShipGood(row, column);
        deployed.setMarineElement(TypeMarineElement.SHIP);
        deployed.setVital(VitalConditions.ALIVE);
        deployed.setShip(ship);
    }

    private void add(ShipStructure shipStructure, Row iRowSelection, Column iColumnSelection, Ship selectShip) {
        ArrayList<Integer> newCoordinate = new ArrayList<>();
        myBoard.changeColor(iRowSelection, iColumnSelection, selectShip.getColorHex());
        newCoordinate.add(iRowSelection.getIndex());
        newCoordinate.add(iColumnSelection.getIndex());
        coordinatesMyShip.get(shipStructure).get(selectShip)
                .add(newCoordinate);
        saveData(iRowSelection, iColumnSelection, selectShip);

    }

    private void enableAlert(Ship selectShip) {
        panels.get("alertError").visible(true);
        iROw.setEnabled(true);
        iColumn.setEnabled(true);
        if (selectShip == Ship.MINI) {
            alertCompletion("alertError", 250);
            dashboard.getBox(Dashboard.COORDINATES).setHeight(300);
        } else {
            alertCompletion("alertError", 395);
            visibleComponents(false);
            dashboard.getBox(Dashboard.COORDINATES).setHeight(400);
        }
        available = false;
    }

    private void enableEnemyBoard(Ship selectShip) {
        if (coordinatesMyShip.get(ShipStructure.BOW).get(selectShip).size() == selectShip.getNumber()) {
            int selectedIndex = shipSize.getComboBox().getSelectedIndex();
            shipSize.getComboBox().removeItemAt(selectedIndex);
            if (shipSize.getComboBox().getItemCount() == 0) {
                dashboard.getBox(Dashboard.COORDINATES).visible(false);
                //dashboard.getBox(Dashboard.ATTACK).visible(this.singleton.getGameInstance().getAttackComponent());
                dashboard.getBox(Dashboard.SHOOTINGLOG).visible(true);
                dashboard.getBox(Dashboard.SCORE).visible(true);
                enemyPanel.visible(true);

            }
        }
    }

    private void differentPosition(Row initialRowSelection, Row finalRowSelection, Column initialColumnSelection,
            Column finalColumnSelection, Ship selectShip) {

        if (initialRowSelection != finalRowSelection || initialColumnSelection != finalColumnSelection) {
            if (selectShip == Ship.MEDIUM) {
                int middleRowIndex = (initialRowSelection.getIndex() + finalRowSelection.getIndex()) / 2;
                int middleColumnIndex = (initialColumnSelection.getIndex() + finalColumnSelection.getIndex()) / 2;
                Row middleRow = Row.getByIndex(middleRowIndex);
                Column middleColumn = Column.getByIndex(middleColumnIndex);
                boolean indexValidationMiddle = middleRowIndex >= 0 && middleRowIndex < Row.values().length &&
                        middleColumnIndex >= 0 && middleColumnIndex < Column.values().length;
                if (!verificationPosition(middleRow, middleColumn)) {
                    if (indexValidationMiddle) {
                        add(ShipStructure.CENTER, middleRow, middleColumn, selectShip);
                    }
                } else {
                    System.out.println("no posicionar en mitad - medium");
                }

            } else if (selectShip == Ship.BIG) {
                int middleRowIndex = (initialRowSelection.getIndex() + finalRowSelection.getIndex()) / 2;
                int middleColumnIndex = (initialColumnSelection.getIndex() + finalColumnSelection.getIndex()) / 2;

                int penultimateRowIndex;
                int penultimateColumnIndex;
                if (middleColumnIndex == initialColumnSelection.getIndex()) {
                    penultimateRowIndex = (initialRowSelection.getIndex() - 1);
                    penultimateColumnIndex = middleColumnIndex;

                } else {
                    penultimateRowIndex = middleRowIndex;
                    penultimateColumnIndex = (middleColumnIndex + 1);

                }

                Row middleRow = Row.getByIndex(middleRowIndex);
                Column middleColumn = Column.getByIndex(middleColumnIndex);
                Row penultimateRow = Row.getByIndex(penultimateRowIndex);
                Column penultimateColumn = Column.getByIndex(penultimateColumnIndex);

                boolean validIndicesM = middleRowIndex >= 0 && middleRowIndex < Row.values().length &&
                        middleColumnIndex >= 0 && middleColumnIndex < Column.values().length;
                boolean validIndicesP = penultimateRowIndex >= 0 && penultimateRowIndex < Row.values().length &&
                        penultimateColumnIndex >= 0 && penultimateColumnIndex < Column.values().length;

                if (!verificationPosition(middleRow, middleColumn)
                        && !verificationPosition(penultimateRow, penultimateColumn)) {
                    if (validIndicesM && validIndicesP) {
                        add(ShipStructure.CENTER, middleRow, middleColumn, selectShip);
                        add(ShipStructure.CENTER, penultimateRow, penultimateColumn, selectShip);
                    }
                } else {
                    System.out.println("no posicionar en mitad o penultimo - big");
                }
            }
            add(ShipStructure.STERN, finalRowSelection, finalColumnSelection, selectShip);
            visibleComponents(false);
            iROw.setEnabled(true);
            iColumn.setEnabled(true);
            shipSize.setEnabled(true);
            dashboard.getBox(Dashboard.COORDINATES).setHeight(250);
        } else {
            panels.get("alertError").visible(true);
            available = false;
            if (coordinatesMyShip.get(ShipStructure.BOW).get(selectShip).size() == 1) {
                coordinatesMyShip.get(ShipStructure.BOW).get(selectShip).remove(0);
            } else {
                coordinatesMyShip.get(ShipStructure.BOW).get(selectShip)
                        .remove(coordinatesMyShip.get(ShipStructure.BOW).get(selectShip).size() - 1);
            }
        }
    }

    private boolean verificationPosition(Row iRowSelection, Column iColumnSelection) {
        Iterator<Map.Entry<ShipStructure, Map<Ship, ArrayList<ArrayList<Integer>>>>> coordinateIterator = coordinatesMyShip
                .entrySet().iterator();
        while (coordinateIterator.hasNext() && !available) {
            Map.Entry<ShipStructure, Map<Ship, ArrayList<ArrayList<Integer>>>> coordinateOne = coordinateIterator
                    .next();
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
        return available;
    }

    private void availableOptions() {
        Row rInit = (Row) this.iROw.getComboBox().getSelectedItem();
        Column cInit = (Column) this.iColumn.getComboBox().getSelectedItem();
        Ship selectShip = (Ship) shipSize.getComboBox().getSelectedItem();
        this.shipSize.setEnabled(false);
        this.iROw.setEnabled(false);
        this.iColumn.setEnabled(false);
        final EnumList<Row> horizontal = (EnumList<Row>) possibleMovements(rInit, selectShip);
        final EnumList<Column> vertical = (EnumList<Column>) possibleMovements(cInit, selectShip);
        this.fRow.setOptions(horizontal.getArrayList());
        this.fColumn.setOptions(vertical.getArrayList());

    }

    private EnumList<? extends Enum<?>> possibleMovements(Enum<?> rowOrColumn, Ship shipType) {
        EnumList movements = new EnumList<>();
        int index = (rowOrColumn instanceof Column) ? ((Column) rowOrColumn).getIndex()
                : ((Row) rowOrColumn).getIndex();

        // negative
        int bigSizeL = (index - (Ship.BIG.getSize() - 1));
        int mediumSizeL = (index - (Ship.MEDIUM.getSize() - 1));
        int smallSizeL = (index - (Ship.SMALL.getSize() - 1));
        // positive
        int bigSizeP = (index + (Ship.BIG.getSize() - 1));
        int mediumSizeP = (index + (Ship.MEDIUM.getSize() - 1));
        int smallSizeP = (index + (Ship.SMALL.getSize() - 1));

        switch (index) {
            case 9:
                if (shipType == Ship.BIG) {
                    movements.addEnum(getByIndex(rowOrColumn, bigSizeL));
                } else if (shipType == Ship.MEDIUM) {
                    movements.addEnum(getByIndex(rowOrColumn, mediumSizeL));
                } else {
                    movements.addEnum(getByIndex(rowOrColumn, smallSizeL));
                }
                movements.addEnum(getByIndex(rowOrColumn, index));
                break;
            case 0:
                movements.addEnum(getByIndex(rowOrColumn, index));
                if (shipType == Ship.BIG) {
                    movements.addEnum(getByIndex(rowOrColumn, bigSizeP));
                } else if (shipType == Ship.MEDIUM) {
                    movements.addEnum(getByIndex(rowOrColumn, mediumSizeP));
                } else {
                    movements.addEnum(getByIndex(rowOrColumn, smallSizeP));
                }
                break;
            default:
                if (shipType == Ship.BIG) {
                    if (index == 1 || index == 2) {
                        movements.addEnum(getByIndex(rowOrColumn, index));
                        movements.addEnum(getByIndex(rowOrColumn, bigSizeP));

                    } else if (index == 7 || index == 8) {
                        movements.addEnum(getByIndex(rowOrColumn, bigSizeL));
                        movements.addEnum(getByIndex(rowOrColumn, index));

                    } else {
                        movements.addEnum(getByIndex(rowOrColumn, bigSizeL));
                        movements.addEnum(getByIndex(rowOrColumn, index));
                        movements.addEnum(getByIndex(rowOrColumn, bigSizeP));
                    }

                } else if (shipType == Ship.MEDIUM) {
                    if (index == 1) {
                        movements.addEnum(getByIndex(rowOrColumn, index));
                        movements.addEnum(getByIndex(rowOrColumn, mediumSizeP));

                    } else if (index == 8) {
                        movements.addEnum(getByIndex(rowOrColumn, mediumSizeL));
                        movements.addEnum(getByIndex(rowOrColumn, index));

                    } else {
                        movements.addEnum(getByIndex(rowOrColumn, mediumSizeL));
                        movements.addEnum(getByIndex(rowOrColumn, index));
                        movements.addEnum(getByIndex(rowOrColumn, mediumSizeP));

                    }

                } else {
                    movements.addEnum(getByIndex(rowOrColumn, smallSizeL));
                    movements.addEnum(getByIndex(rowOrColumn, index));
                    movements.addEnum(getByIndex(rowOrColumn, smallSizeP));
                }
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

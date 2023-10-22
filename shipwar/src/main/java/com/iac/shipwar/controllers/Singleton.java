package com.iac.shipwar.controllers;

import java.util.HashMap;
import java.util.Map;

import com.iac.shipwar.UI.layout.UiBoard;
import com.iac.shipwar.UI.layout.UiDashboard;
import com.iac.shipwar.UI.widgets.Text_;
import com.iac.shipwar.interfaces.IGame;
import com.iac.shipwar.models.enums.Column;
import com.iac.shipwar.models.enums.Dashboard;
import com.iac.shipwar.models.enums.Row;
import com.iac.shipwar.models.enums.TypeMarineElement;
import com.iac.shipwar.server.CreateGame;
import com.iac.shipwar.server.JoinGame;

public class Singleton {

    private static Singleton instance;
    private IGame gameInstance;
    private int score = 200;
    private Map<Row, Map<Column, ShipDeployed>> board = new HashMap<Row, Map<Column, ShipDeployed>>();
    private UiBoard myBoard;
    private UiBoard enemyBoard;
    private UiDashboard dashboard;
    private Boolean successfulAttack = false;
    private Text_ winnertext;

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public int getScore() {
        return this.score;
    }

    private void decreaseScore() {
        if (this.score > 0) {
            this.score -= 10;
            this.dashboard.updateTextsModifier(Dashboard.SCORE, this.score + " de 200");
            if (this.score == 0) {
                String loser = "Perdiste manc@ :(";
                System.out.println(loser);
                this.winnertext.setText(loser);
                this.enemyBoard.getContainer().visible(false);
                this.myBoard.getContainer().visible(false);
                this.winnertext.getLabel().setVisible(true);
            }
        }
    }

    public void setDashboard(UiDashboard d) {
        this.dashboard = d;
    }

    public void setMyBoard(UiBoard b) {
        this.myBoard = b;
    }

    public void setEnemyBoard(UiBoard b) {
        this.enemyBoard = b;

    }

    public void setWinnertext(Text_ t) {
        this.winnertext = t;
    }

    public void serverActive(boolean create, String http) {
        try {
            this.gameInstance = ((create) ? new CreateGame(this.dashboard)
                    : new JoinGame(http, this.dashboard));
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public void createRowsAndColumns() {
        if (board.isEmpty()) {
            for (Row r : Row.values()) {
                Map<Column, ShipDeployed> mDefault = new HashMap<>();
                for (Column c : Column.values()) {
                    mDefault.put(c, new ShipDeployed(TypeMarineElement.OCEAN, r, c));
                }
                this.board.put(r, mDefault);
            }
        }
    }

    public Map<Row, Map<Column, ShipDeployed>> getboard() {
        return this.board;
    }

    public ShipDeployed getShipGood(Row row, Column column) {
        return this.board.get(row).get(column);
    }

    public IGame getGameInstance() {
        return this.gameInstance;
    }

    public void getBoardRowColum(Row targetRow, Column targetColumn) {
        ShipDeployed ship = board.get(targetRow).get(targetColumn);

        if (ship != null) {
            System.out.println("\nShip Details: ");
            System.out.println(" - Marine Element: " + ship.getMarineElement());
            System.out.println(" - Vital Conditions: " + ship.getVital());
            System.out.println(" - Ship Type: " + ship.getShip());
            System.out.println(" - Row: " + ship.getRow());
            System.out.println(" - Column: " + ship.getColumn());
            System.out.println(" - Position Map: " + ship.getPosition().toString());
            System.out.println("--------------------------");
        } else {
            System.out.println("No hay barco en la fila " + targetRow + ", columna " + targetColumn);
        }
    }

    public void receiveAttack(ShipDeployed sd) {
        System.out.println("\nrecibiste ataque del enenemigo");
        if (this.board.get(sd.getRow()).get(sd.getColumn()).getMarineElement() == TypeMarineElement.SHIP) {
            replaceShipDeployed(sd);
            decreaseScore();
            System.out.println("puntuacion: " + this.score + "/200");
            getBoardRowColum(sd.getRow(), sd.getColumn());
            this.myBoard.changeColor(sd.getRow(), sd.getColumn(), "#Fc045b");
            this.successfulAttack = true;
        } else {
            System.out.println("fallo la bomba");
            this.successfulAttack = false;
        }
        this.enemyBoard.getContainer().changeColorBorder("#55de0a");

    }

    public boolean getSuccessfulAttack() {
        return successfulAttack;
    }

    public void replaceShipDeployed(ShipDeployed newShipDeploy) {
        this.board.get(newShipDeploy.getRow()).put(newShipDeploy.getColumn(), newShipDeploy);
    }
}

package com.iac.shipwar;

import java.util.Map;

import com.iac.shipwar.components.Panel_;
import com.iac.shipwar.components.layout.UiBoard;
import com.iac.shipwar.components.layout.UiInformation;
import com.iac.shipwar.components.layout.UiMain;
import com.iac.shipwar.data.enums.StatusBoard;

/**
 *
 * @author isaac
 */
public class App {

    public static void main(String[] args) {
        final UiMain ui = new UiMain();
        Map<String, Panel_> uiPanels = ui.getMapPanels();

        final UiInformation uiInformation = new UiInformation(uiPanels.get("information"));
        final UiBoard uiBoard = new UiBoard(uiPanels.get("myboard"), "Yo","#99ccff");
        final UiBoard uiBoardEnemy = new UiBoard(uiPanels.get("enemyBoard"), "Enemigo", "#ffcc99");

        uiInformation.updateTextsModifier(StatusBoard.SCORE, "modificado");

    }
}

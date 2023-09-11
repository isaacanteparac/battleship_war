package com.iac.shipwar.components.layout;

import java.util.HashMap;
import java.util.Map;

import com.iac.shipwar.components.Panel_;
import com.iac.shipwar.components.Window;
import com.iac.shipwar.data.PanelCharacteristic;
import com.iac.shipwar.data.enums.MainPanels;

public class UiMain {
    protected Window windows;
    protected final int sizeBoardWH = 750;
    protected Map<MainPanels, Panel_> panels = new HashMap<MainPanels, Panel_>();
    protected final Map<MainPanels, PanelCharacteristic> panelProperties = new HashMap<MainPanels, PanelCharacteristic>();
    protected final int transparency = 125;
    protected final String bgPanels = "#eff3f7";//#eff3f7

    public UiMain() {

        this.windows = new Window("Barquitos Peleadores", 1905, 1000);
        createdPanel(MainPanels.INFORMATION,
                new PanelCharacteristic(300, 900, 20,
                        this.transparency, 60, 10,
                        0, 0,
                        this.bgPanels, this.windows.getPanel()));

        createdPanel(MainPanels.MYBOARD,
                new PanelCharacteristic(this.sizeBoardWH, this.sizeBoardWH, 20,
                        this.transparency, 60, 15,
                        1, 0,
                        this.bgPanels, this.windows.getPanel()));

        createdPanel(MainPanels.ENEMYBOARD,
                new PanelCharacteristic(this.sizeBoardWH, this.sizeBoardWH, 20, 
                this.transparency, 60, 10, 
                2, 0, 
                this.bgPanels, this.windows.getPanel()));

    }

    public Map<MainPanels, Panel_> getMapPanels(){
        return this.panels;
    }

    private void createdPanel(MainPanels name, PanelCharacteristic properties) {
        this.panelProperties.put(name, properties);
        final Panel_ p = new Panel_(this.panelProperties.get(name));
        p.spacer();
        this.panels.put(name, p);
    }

}

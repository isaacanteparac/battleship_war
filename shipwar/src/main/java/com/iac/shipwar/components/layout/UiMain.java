package com.iac.shipwar.components.layout;

import java.util.HashMap;
import java.util.Map;

import com.iac.shipwar.components.Panel_;
import com.iac.shipwar.components.Window;
import com.iac.shipwar.data.PanelCharacteristic;

public class UiMain {
    protected Window windows;
    protected final int sizeBoardWH = 750;
    protected Map<String, Panel_> panels = new HashMap<String, Panel_>();
    protected final Map<String, PanelCharacteristic> panelProperties = new HashMap<String, PanelCharacteristic>();

    public UiMain() {

        this.windows = new Window("BARQUITOS", 1920, 1080);
        createdPanel("information",
                new PanelCharacteristic(300, 900, 20,
                        200, 60, 10,
                        0, 0,
                        "#eff3f7", this.windows.getPanel()));
        createdPanel("myboard",
                new PanelCharacteristic(this.sizeBoardWH, this.sizeBoardWH, 20,
                        210, 60, 15,
                        1, 0,
                        "#eff3f7", this.windows.getPanel()));
        createdPanel("enemyBoard",
                new PanelCharacteristic(this.sizeBoardWH, this.sizeBoardWH, 20, 
                210, 60, 10, 
                2, 0, 
                "#eff3f7", this.windows.getPanel()));

    }

    public Map<String, Panel_> getMapPanels(){
        return this.panels;
    }

    private void createdPanel(String name, PanelCharacteristic properties) {
        this.panelProperties.put(name, properties);
        final Panel_ p = new Panel_(this.panelProperties.get(name));
        p.spacer();
        this.panels.put(name, p);
    }

}

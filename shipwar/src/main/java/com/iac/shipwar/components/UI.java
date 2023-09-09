package com.iac.shipwar.components;

import java.util.HashMap;
import java.util.Map;

import com.iac.shipwar.data.PanelCharacteristic;

public class UI {
    protected Window windows;
    protected Map<String, Panel_> panels = new HashMap<String, Panel_>();
    protected final Map<String, PanelCharacteristic> panelProperties = new HashMap<String, PanelCharacteristic>();

    public UI() {

        this.windows = new Window("BARQUITOS");
        createdPanel("information",
                new PanelCharacteristic(300, 900, 20,
                        128, 60, 10,
                        0, 0,
                        "#000000", this.windows.getPanel()));
        createdPanel("myboard",
                new PanelCharacteristic(650, 650, 10,
                        128, 60, 10,
                        1, 0,
                        "#000000", this.windows.getPanel()));
        createdPanel("myboard",
                new PanelCharacteristic(650, 650, 10, 
                128, 60, 10, 
                2, 0, 
                "#000000", this.windows.getPanel()));

    }

    private void createdPanel(String name, PanelCharacteristic properties) {
        this.panelProperties.put(name, properties);
        final Panel_ p = new Panel_(this.panelProperties.get(name));
        p.spacer();
        this.panels.put(name, p);
    }

}

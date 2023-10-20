package com.iac.shipwar.UI.components;

import java.util.HashMap;
import java.util.Map;

import com.iac.shipwar.UI.layout.UiBoard;
import com.iac.shipwar.UI.layout.UiDashboard;
import com.iac.shipwar.UI.widgets.Button_;
import com.iac.shipwar.UI.widgets.Options_;
import com.iac.shipwar.UI.widgets.Panel_;
import com.iac.shipwar.UI.widgets.Text_;
import com.iac.shipwar.models.dataclass.PanelCharacteristic;
import com.iac.shipwar.models.enums.AlingText;
import com.iac.shipwar.models.enums.Column;
import com.iac.shipwar.models.enums.Dashboard;
import com.iac.shipwar.models.enums.Row;
import com.iac.shipwar.models.enums.Ship;
import com.iac.shipwar.models.enums.ShipStructure;
import com.iac.shipwar.models.enums.SubcomponentOrientation;

public class Coordinates {
    protected UiDashboard dashboard;
    protected UiBoard myBoard;
    protected Panel_ enemyPanel;
    protected Map<String, Panel_> panels = new HashMap<String, Panel_>();
    protected int shipCount = Ship.BIG.getNumber() + Ship.MEDIUM.getNumber() + Ship.SMALL.getNumber();
    protected final String fontColor = "#FFFFFF";
    protected Options_<Ship> shipSize;
    protected Options_<Row> iROw;
    protected Options_<Column> iColumn;
    protected Options_<Row> fRow;
    protected Options_<Column> fColumn;
    protected Button_ continueBtn;
    protected Button_ saveBtn;


    public Coordinates(UiDashboard ud, UiBoard myBoard, Panel_ enemyPanel) {
        this.myBoard = myBoard;
        this.enemyPanel = enemyPanel;
        this.dashboard = ud;
        assembleComponent();
    }

    private void assembleComponent() {
        this.shipSize = boxedOptions("Barco", Ship.class, "shipSize", 100, 60);
        createText(ShipStructure.BOW.getDisplayName(), "initialSubtitle", "#2872f0");
        this.iROw = boxedOptions("Fila", Row.class, "iROw", 60, 100);
        this.iColumn = boxedOptions("Columna", Column.class, "iColumn", 60, 100);
        this.continueBtn = createButton("Continuar", "continueBtn", "#Fcb8f4");
        createText(ShipStructure.STERN.getDisplayName(), "finalSubtitle", "#2872f0");
        this.fRow = boxedOptions("Fila", Row.class, "eROw", 60, 100);
        this.fColumn = boxedOptions("Columna", Column.class, "eColumn", 60, 100);
        this.saveBtn = createButton("Guardar", "saveBtn", "#87fa94");
        createText("Error: Ya existe coordenada", "alertError", "#Be2528");
        panels.get("alertError").visible(false);
        visibleComponents(false);
        this.dashboard.getBox(Dashboard.COORDINATES).activateGrip(SubcomponentOrientation.VERTICAL_LEFT);

    }

    public void visibleComponents(boolean b) {
        panels.get("continueBtn").visible(!b);
        this.panels.get("finalSubtitle").visible(b);
        this.panels.get("eROw").visible(b);
        this.panels.get("eColumn").visible(b);
        this.panels.get("saveBtn").visible(b);
    }

    private <T extends Enum<T>> Options_<T> boxedOptions(String name, Class<T> enumClass, String id, int oWidth,
            int tWidth) {
        Panel_ panel = createDefaultPanel(this.dashboard.getBox(Dashboard.COORDINATES), 35);
        final Options_<T> options = new Options_<>();
        options.pureEnum(enumClass);
        options.setWidth(oWidth);
        Text_ t = new Text_(name, tWidth);
        t.setColor(this.fontColor);
        panel.addComponent(t.getLabel());
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
        Panel_ panel = createDefaultPanel(this.dashboard.getBox(Dashboard.COORDINATES), 45);
        Button_ b = new Button_();
        Text_ t = new Text_(Text, this.dashboard.getBox(Dashboard.COORDINATES).getSizeWidthComponent());
        b.add(t.getLabel());
        b.setRounded(50);
        b.setWidth(120);
        b.setHeight(40);
        b.setColorBg(colorHex);
        t.setColor("#000000");
        panel.addComponent(b.geButton());
        this.panels.put(id, panel);
        this.dashboard.getBox(Dashboard.COORDINATES).addComponent(panel.getPanel());
        return b;
    }

}

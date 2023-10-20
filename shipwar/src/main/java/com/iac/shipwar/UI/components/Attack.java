package com.iac.shipwar.UI.components;

import com.iac.shipwar.UI.layout.UiBoard;
import com.iac.shipwar.UI.layout.UiDashboard;
import com.iac.shipwar.UI.widgets.Button_;
import com.iac.shipwar.UI.widgets.Options_;
import com.iac.shipwar.UI.widgets.Panel_;
import com.iac.shipwar.UI.widgets.Text_;
import com.iac.shipwar.models.dataclass.PanelCharacteristic;
import com.iac.shipwar.models.enums.Column;
import com.iac.shipwar.models.enums.Dashboard;
import com.iac.shipwar.models.enums.Row;

public class Attack {

    protected Panel_ container;
    protected String route;
    protected UiBoard enemyBoard;
    protected UiDashboard dashboard;
    protected Options_<Row> rowOption;
    protected Options_<Column> columnOption;
    protected Button_ fireButton;
    protected String colorText = "#FFFFFF";

    public Attack(UiBoard eb, UiDashboard ud) {
        this.enemyBoard = eb;
        this.dashboard = ud;
        assembleComponent();
    }

    public void assembleComponent() {
        Panel_ panelRow = createDefaultPanel(this.dashboard.getBox(Dashboard.ATTACK), 35);
        Panel_ panelColumn = createDefaultPanel(this.dashboard.getBox(Dashboard.ATTACK), 35);

        this.rowOption = new Options_<>();
        this.columnOption = new Options_<>();
        this.rowOption.pureEnum(Row.class);
        this.columnOption.pureEnum(Column.class);

        Text_ tRow = new Text_("Fila", 100);
        Text_ tColumn = new Text_("Columna", 100);
        tRow.setColor(this.colorText);
        tColumn.setColor(this.colorText);
        panelRow.addComponent(tRow.getLabel());
        panelColumn.addComponent(tColumn.getLabel());
        this.rowOption.addPanel(panelRow);
        this.columnOption.addPanel(panelColumn);

        this.fireButton = new Button_();
        Text_ t = new Text_("\u1F52 Fuego \u1F52", this.dashboard.getBox(Dashboard.ATTACK).getSizeWidthComponent());
        this.fireButton.add(t.getLabel());
        this.fireButton.setRounded(50);
        this.fireButton.setWidth(120);
        this.fireButton.setHeight(40);
        this.fireButton.setColorBg("#Fc045b");
        t.setColor("#FFFFFF");

        this.dashboard.getBox(Dashboard.ATTACK).addComponent(panelRow.getPanel());
        this.dashboard.getBox(Dashboard.ATTACK).addComponent(panelColumn.getPanel());
        this.dashboard.getBox(Dashboard.ATTACK).addComponent(this.fireButton.geButton());

    }

    private Panel_ createDefaultPanel(Panel_ box, int h) {
        return new Panel_(
                new PanelCharacteristic(box.getSizeWidthComponent(), h, 0,
                        0, 40, 10,
                        0, 0,
                        "#000000", box.getPanel()));
    }

}

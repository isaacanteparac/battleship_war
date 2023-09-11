package com.iac.shipwar.components.layout;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import com.iac.shipwar.components.Button_;
import com.iac.shipwar.components.Options_;
import com.iac.shipwar.components.Panel_;
import com.iac.shipwar.components.Text_;
import com.iac.shipwar.data.PanelCharacteristic;
import com.iac.shipwar.data.enums.Column;
import com.iac.shipwar.data.enums.Orientation;
import com.iac.shipwar.data.enums.Row;

import java.awt.event.ActionListener;


public class UiInput {

    protected Panel_ container;
    protected String route;

    public void mountComponentRoute(Panel_ pRoute) {
        JTextField inputRoute = new JTextField();
        inputRoute.setPreferredSize(new Dimension(pRoute.getSizeWidthComponent(), 30));
        Button_ b = new Button_();
        Text_ t = new Text_("Conectar", 90);
        b.setWidth(100);
        b.setHeight(40);
        b.setColorBg("#000000");
        t.setColor("#FFFFFF");
        b.add(t.getLabel());
        actionButtonRoute(b.geButton(), inputRoute);
        pRoute.addComponent(inputRoute);
        pRoute.addComponent(b.geButton());
    }


    private void actionButtonRoute(JButton b, JTextField input){
         b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                route = input.getText();
                System.out.println("Texto ingresado: " + route);
                //hacer una alerta que fue conectado correctamente al server
            }
        });
    }
    

    public void mountComponentAttack(Panel_ pAttack){
        Panel_ moment = new Panel_(
                new PanelCharacteristic(pAttack.getSizeWidthComponent(), 35, 0,
                        0, 40, 10,
                        0, 0,
                        "#000000", pAttack.getPanel()));

        Options_ rowOpt = new Options_<>(Row.class);
        Options_ columnOpt = new Options_<>(Column.class);
        moment.addComponent(rowOpt.getComboBox());
        moment.addComponent(columnOpt.getComboBox());
        Button_ b = new Button_();
        Text_ t = new Text_("Fuego", moment.getSizeWidthComponent());
        b.add(t.getLabel());
        b.setRounded(50);
        b.setWidth(100);
        b.setHeight(40);
        b.setColorBg("#Fc045b");
        t.setColor("#FFFFFF");


        actionButtonAttack(b.geButton(),rowOpt.getComboBox(), columnOpt.getComboBox());


        moment.activateGrip(Orientation.CENTER);
        pAttack.addComponent(moment.getPanel());
        pAttack.addComponent(b.geButton());
    }

    private void actionButtonAttack(JButton button, JComboBox row, JComboBox column){
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener la selección de los JComboBox
                Row selectedRow = (Row) row.getSelectedItem();
                Column selectedColumn = (Column) column.getSelectedItem();
        
                // Hacer algo con los elementos seleccionados
                System.out.println("Fila seleccionada: " + selectedRow);
                System.out.println("Columna seleccionada: " + selectedColumn);
            }
        });
    }


}

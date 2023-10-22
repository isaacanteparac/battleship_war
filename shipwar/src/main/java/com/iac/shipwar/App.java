package com.iac.shipwar;

import com.iac.shipwar.UI.layout.UiMain;
import com.iac.shipwar.controllers.Singleton;

/**
 *
 * @author isaac
 */
public class App {

    public static void main(String[] args) {

        Singleton singleton = Singleton.getInstance();
        singleton.createRowsAndColumns();
        new UiMain();

    }
}

package com.iac.shipwar.controllers;

public class Board {

    protected String text;

    public Board(String s){
        this.text = s;
    }


    public void getText(){
        System.out.println(text);
    }
    
}

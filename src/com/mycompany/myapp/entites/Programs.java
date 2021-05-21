/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entites;

/**
 *
 * @author bhk
 */
public class Programs {
    private int id;
    private String program;

    public Programs(int id, String program) {
        this.id = id;
        this.program = program;
    }

    public Programs() {
    }

    public Programs(String program) {
        this.program = program;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    @Override
    public String toString() {
        return "Programs{" + "id=" + id + ", program=" + program + '}';
    }
    
   
    
    
}
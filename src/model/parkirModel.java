/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


public class parkirModel {
    private int id ;
    private String namaPemilik;
    private String platNomor;
    private String merk;
    private int jam;

    public parkirModel(int id, String namaPemilik, String platNomor, String merk, int jam) {
        this.id = id;
        this.namaPemilik = namaPemilik;
        this.platNomor = platNomor;
        this.merk = merk;
        this.jam = jam;
    }

    public int getId() {
        return id;
    }

    public String getNamaPemilik() {
        return namaPemilik;
    }

    public String getPlatNomor() {
        return platNomor;
    }

    public String getMerk() {
        return merk;
    }

    public int getJam() {
        return jam;
    }
    
    
}

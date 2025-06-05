/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.parkirDAO;
import View.ViewParkir;
import java.util.List;
import model.parkirModel;


public class parkirController {
    private ViewParkir view;
    private parkirDAO DAO_parkir = new parkirDAO();
    private List<parkirModel> data;
    private parkirModel selectedParkir;

    public parkirController(ViewParkir view){
        this.view = view;
        refresh();
    }
    
    
    private void refresh(){
        data = DAO_parkir.getAllParkir();
        view.setTabel(data);
        updateSelected(-1);
    }
    
    public void insertParkir(String namaPemilik, String platNomor, String merk, int jam){
        var parkir = new parkirModel(0, namaPemilik, platNomor, merk, jam);
        if(DAO_parkir.isDuplicate(namaPemilik, platNomor)){
            view.showError("Data Duplikat, Sudah Pernah Dimasukkan");
            return;
        }
        if(DAO_parkir.insertParkir(parkir)){
            refresh();
        }else{
            view.showError("Data Gagal Ditambahkan");
        }
    }
    
    public void updateSelected(int index){
        if(index == -1){
            selectedParkir = null;
        }else{
            selectedParkir = data.get(index);
        }
        view.setSelected(selectedParkir);
    }
    
    public void edit(String namaPemilik, String platNomor, String merk, int jam ){
        if(selectedParkir == null){
            view.showError("Belum Ada yang dipilih");
            return;
        }
        var parkir = new parkirModel(selectedParkir.getId(), namaPemilik, platNomor, merk, jam);
        if(DAO_parkir.updateParkir(parkir)){
            refresh();
        }else{
            view.showError("Data gagal Ditambahkan");
        }
    }
    
    public void delete(){
        if(selectedParkir == null){
            view.showError("Belum Ada yang dipilih");
            return;
        }
        if(DAO_parkir.deleteParkir(selectedParkir.getId())){
            refresh();
        }else{
            view.showError("Data gagal Ditambahkan");
        }
    }
    
    public void showTotalBayar(int index){
        if(index <0 || index >= data.size()){
            view.showError("Index Tidak Valid");
            return;
        }
        parkirModel parkir = data.get(index);
        if(parkir.getJam() < 4){
            int total = parkir.getJam() * 5000;
             String message = "Jam Parkir : " + parkir.getJam() + " Jam" +
               "\ntotal Bayar = Rp " + total;
             view.showMessage(message);
        }else{
            int total = (parkir.getJam() * 5000 )+ (parkir.getJam() * 2000 );
            String message = "Jam Parkir : " + parkir.getJam() + " Jam" +
               "\ntotal Bayar = Rp " + total;
             view.showMessage(message);
        }   
    }
    
    
}

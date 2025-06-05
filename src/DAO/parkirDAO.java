/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.ArrayList;
import java.util.List;
import model.ConnectDB;
import model.parkirModel;

public class parkirDAO {
    public boolean insertParkir(parkirModel t){
        var conn = ConnectDB.geConnection();
        try(var stmt = conn.prepareStatement("INSERT INTO parkir (nama_pemilik, plat_nomor, merk, durasi) VALUES (?,?,?,?)");){
            stmt.setString(1, t.getNamaPemilik());
            stmt.setString(2, t.getPlatNomor());
            stmt.setString(3, t.getMerk());
            stmt.setInt(4, t.getJam());
            stmt.execute();
        }catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }
    
    public List<parkirModel> getAllParkir(){
        var conn = ConnectDB.geConnection();
        var result = new ArrayList<parkirModel>();
        try(var stmt = conn.prepareStatement("SELECT * FROM parkir");){
            stmt.execute();
            var rs = stmt.getResultSet();
            while(rs.next()){
                parkirModel parkir = new parkirModel(
                rs.getInt("id"),
                rs.getString("nama_pemilik"),
                rs.getString("plat_nomor"),
                rs.getString("merk"),
                rs.getInt("durasi")
                );
                result.add(parkir);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
    
    public boolean updateParkir(parkirModel t){
        var conn = ConnectDB.geConnection();
        try(var stmt = conn.prepareStatement("UPDATE parkir SET nama_pemilik =?, plat_nomor =?, merk =?, durasi =?, WHERE id=?");){
            stmt.setString(1, t.getNamaPemilik());
            stmt.setString(2, t.getPlatNomor());
            stmt.setString(3, t.getMerk());
            stmt.setInt(4, t.getJam());
            stmt.setInt(5, t.getId());
            stmt.execute();
        }catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }
    
    public boolean deleteParkir(int id){
        var conn = ConnectDB.geConnection();
        try(var stmt = conn.prepareStatement("DELETE FROM parkir WHERE id=?");){
            stmt.setInt(1, id);
            stmt.execute();
        }catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }
    
    public boolean isDuplicate(String namaPemilik, String platNomor){
        var conn = ConnectDB.geConnection();
        try(var stmt = conn.prepareStatement("SELECT COUNT(*) FROM parkir WHERE nama_pemilik = ? AND plat_nomor=?");){
            stmt.setString(1, namaPemilik);
            stmt.setString(2, platNomor);
            var rs = stmt.executeQuery();
            if(rs.next()){
                return rs.getInt(1) > 0;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }
}

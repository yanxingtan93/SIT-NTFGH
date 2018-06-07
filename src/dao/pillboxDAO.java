package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import DBUtils.DBConn;
import model.pillbox;


public class pillboxDAO {

    private Connection conn;

    public pillboxDAO(){
        try {
            conn = DBConn.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<pillbox> getList(){
        List<pillbox> pillboxList = new ArrayList<pillbox>();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM INVENTORY WHERE drug_ID = 1");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                pillbox pbox = new pillbox();
                pbox.setInventory_id(resultSet.getString(1));
                pbox.setDrugintake_id(resultSet.getString(2));
                pbox.setDrugphase_id(resultSet.getString(3));
                pbox.setInventory_balance(resultSet.getString(4));
                pbox.setInventory_days(resultSet.getString(5));
                pbox.setInventory_startdate(resultSet.getString(6));
                pbox.setInventory_status(resultSet.getString(7));
                pbox.setDrug_ID(resultSet.getString(8));
                pillboxList.add(pbox);

                //resultSet.getString(1),resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8));
                //pillbox pbox = new pillbox(resultSet.getString(1),resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pillboxList;
    }

}

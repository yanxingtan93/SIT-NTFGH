package DatabaseConnector;

import model.Medicine;

import java.util.ArrayList;
import java.util.List;

public interface DrugsDao {
    public ArrayList<Medicine> getAllDrugs();
    public ArrayList<Medicine> getAllDrugEdit(String medicineID);
    public void addNewDrug(Medicine med);
    public Medicine getDrug(int id);
    public void updateDrug(Medicine med);
    public void deleteDrug(int id);
    public void deleteAllMedicine();
}

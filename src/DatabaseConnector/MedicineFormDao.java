package DatabaseConnector;

import model.MedicineForm;

import java.util.List;

public interface MedicineFormDao {
    public List<MedicineForm> getAllMedicineForm();
    public void addNewMedicineForm(MedicineForm medicineForm);
    public MedicineForm getMedicineForm(int id);
    public void updateMedicineForm(MedicineForm medicineForm);
    public void deleteForm(int id);
    public void deleteAllForm();

}

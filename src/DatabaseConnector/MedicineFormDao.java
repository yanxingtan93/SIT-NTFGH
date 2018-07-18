package DatabaseConnector;

import model.MedicineForm;

import java.util.List;

public interface MedicineFormDao {
    public List<MedicineForm> getAllMedicineForm();
    public void addNewMedicineForm(MedicineForm medicineForm);
    public MedicineForm getMedicineForm(int id);
    public void updateMedicineForm(MedicineForm medicineForm, int type);
    public void deleteForm(int id,int type);
    public void deleteAllForm();
    public String getMedicineFormName(int id);
    public String getMedicinePhaseName(int id);
    public String getMedicineIntakeName(int id);

}

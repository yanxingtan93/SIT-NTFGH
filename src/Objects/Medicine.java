package Objects;

public class Medicine {
    private int id;
    private String medicineName;
    private String brand;
    private float price;
    private int medicineFormId;
    private String description;
    private String sideEffect;

    //Default Constructor
    public Medicine(){

    }
    //Constructor with ID
    public Medicine(int id, String medicineName, String brand, float price, int medicineFormId, String description, String sideEffect) {
        this.id = id;
        this.medicineName = medicineName;
        this.brand = brand;
        this.price = price;
        this.medicineFormId = medicineFormId;
        this.description = description;
        this.sideEffect = sideEffect;
    }
    //Constructor without ID
    public Medicine(String medicineName, String brand, float price, int medicineFormId, String description, String sideEffect) {
        this.medicineName = medicineName;
        this.brand = brand;
        this.price = price;
        this.medicineFormId = medicineFormId;
        this.description = description;
        this.sideEffect = sideEffect;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getMedicineFormId() {
        return medicineFormId;
    }

    public void setMedicineFormId(int medicineFormId) {
        this.medicineFormId = medicineFormId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSideEffect() {
        return sideEffect;
    }

    public void setSideEffect(String sideEffect) {
        this.sideEffect = sideEffect;
    }
}

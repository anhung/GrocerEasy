package annabel.grocereasy;

public class FoodItem {

    private String m_name;          // Name of the food item
    private double m_quantity;      // Quantity of item to buy
    private String m_measurement;   // Measurement associated with quantity
    private String m_notes;
    
    /**
     * Default constructor. Just for testing.
     * TODO: Get rid of this for release!
     */
    public FoodItem() {
        m_name = new String("banana");
        m_quantity = 2;
        m_measurement = new String("pieces");
        m_notes = new String("Lalalala these are some notes!");
    }
    
    /**
     * Create a new food item.
     * 
     * @param name name of the food item
     * @param quantity how much the user wants to buy
     * @param measurement unit of measurement associated with quantity
     */
    public FoodItem(String name, double quantity, String measurement, String notes) {
        m_name = new String(name);
        m_quantity = quantity;
        m_measurement = new String(measurement);
        m_notes = new String(notes);
    }
    
    /**
     * Returns this food item's name.
     * 
     * @return this food item's name
     */
    public String getName() {
        return m_name;
    }
    
    /**
     * Returns this food item's quantity.
     * 
     * @return this food item's quantity
     */
    public double getQuantity() {
        return m_quantity;
    }
    
    /**
     * Returns this food item's unit of measurement.
     * 
     * @return this food item's unit of measurement
     */
    public String getMeasurement() {
        return m_measurement;
    }
    
    /**
     * Returns this food item's notes.
     * 
     * @return the notes
     */
    public String getNotes() {
        return m_notes;
    }
    
    /**
     * Set this food item's name.
     * 
     * @param newName the new name
     */
    public void setName(String newName) {
        m_name = new String(newName);
    }
    
    /**
     * Set this food item's new quantity.
     * 
     * @param newQuantity the new quantity
     */
    public void setQuantity(double newQuantity) {
        m_quantity = newQuantity;
    }
    
    /**
     * Set this food item's new unit of measurement.
     * 
     * @param newMeasurement the new unit of measurement
     */
    public void setMeasurement(String newMeasurement) {
        m_measurement = newMeasurement;
    }
    
    /**
     * Set this food item's new notes.
     * 
     * @param notes the new notes
     */
    public void setNotes(String notes) {
        m_notes = new String(notes);
    }
    
    public String getDisplayString() {
        return new String(m_quantity + " " + m_measurement + " " + m_name);
    }
    
    @Override
    public String toString() {
        return getName();
    }
    
    @Override
    public boolean equals(Object o) {
        return ((FoodItem)o).toString().equals(toString());
    }    
}

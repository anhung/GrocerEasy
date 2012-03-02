package annabel.grocereasy;

public class FoodItem {

    private String m_name;          // Name of the food item
    private double m_quantity;      // Quantity of item to buy
    private String m_measurement;   // Measurement associated with quantity
    
    /**
     * Default constructor. Just for testing.
     * TODO: Get rid of this for release!
     */
    public FoodItem() {
        m_name = new String("Banana");
        m_quantity = 2;
        m_measurement = new String("piece");
    }
    
    /**
     * Create a new food item.
     * 
     * @param name name of the food item
     * @param quantity how much the user wants to buy
     * @param measurement unit of measurement associated with quantity
     */
    public FoodItem(String name, double quantity, String measurement) {
        m_name = new String(name);
        m_quantity = quantity;
        m_measurement = new String(measurement);
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
}

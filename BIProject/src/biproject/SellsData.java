
package biproject;

import javafx.beans.property.SimpleStringProperty;


public class SellsData {

    /**
     * @return the year
     */
    public Integer getYear() {
        return year;
    }

    /**
     * @return the region
     */
    public String getRegion() {
        return region;
    }

    /**
     * @return the vehicle
     */
    public String getVehicle() {
        return vehicle;
    }

    /**
     * @return the quantity
     */
    public Integer getQuantity() {
        return quantity;
    }
    private final Integer year;
    private final String region;
    private final String vehicle;
    private final Integer quantity;

    public SellsData(
            Integer year,
            String region,
            String vehicle,
            Integer quantity
    ) {
        this.year = year;
        this.region = region;
        this.vehicle = vehicle;
        this.quantity = quantity;
    }
    
    @Override
    public SellsData clone(){
     SellsData res=new SellsData(this.year, this.region, this.vehicle, this.quantity);
     return res;
    }

}

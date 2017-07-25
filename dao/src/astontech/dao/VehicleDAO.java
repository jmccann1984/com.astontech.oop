package astontech.dao;

import com.astontech.bo.Vehicle;

import java.util.List;

/**
 * Created by Joshua.McCann on 7/14/2017.
 */
public interface VehicleDAO {
    //GET METHODS
    public Vehicle getVehicleById(int vehicleId);
    public List<Vehicle> getVehicleList();

    //EXECUTE METHODS
    public int insertVehicle(Vehicle vehicle);
    public boolean updateVehicle(Vehicle vehicle);
    public boolean deleteVehicle(int vehicleId);
}

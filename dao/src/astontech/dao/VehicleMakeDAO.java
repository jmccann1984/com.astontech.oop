package astontech.dao;

import com.astontech.bo.VehicleMake;

import java.util.List;

/**
 * Created by Joshua.McCann on 7/14/2017.
 */
public interface VehicleMakeDAO {
    //GET METHODS
    public VehicleMake getVehicleMakeById(int vehicleMakeId);
    public List<VehicleMake> getVehicleMakeList();

    //EXECUTE METHODS
    public int insertVehicleMake(VehicleMake vehicleMake);
    public boolean updateVehicleMake(VehicleMake vehicleMake);
    public boolean deleteVehicleMake(int vehicleMakeId);
}

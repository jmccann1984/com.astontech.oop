package astontech.dao;

import com.astontech.bo.VehicleModel;

import java.util.List;

/**
 * Created by Joshua.McCann on 7/14/2017.
 */
public interface VehicleModelDAO {

    //GET METHODS
    public VehicleModel getVehicleModelById(int vehicleModelId);
    public List<VehicleModel> getVehicleModelList();

    //EXECUTE METHODS
    public int insertVehicleModel(VehicleModel vehicleModel);
    public boolean updateVehicleModel(VehicleModel vehicleModel);
    public boolean deleteVehicleModel(int vehicleModelId);
}

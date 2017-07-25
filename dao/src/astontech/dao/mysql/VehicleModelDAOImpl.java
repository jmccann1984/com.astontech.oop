package astontech.dao.mysql;

import astontech.dao.VehicleModelDAO;
import com.astontech.bo.VehicleModel;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joshua.McCann on 7/17/2017.
 */
public class VehicleModelDAOImpl extends MySQL implements VehicleModelDAO {

    @Override
    public VehicleModel getVehicleModelById(int vehicleModelId) {
        Connect();
        VehicleModel vehicleModel = null;
        try{
            CallableStatement cStmnt = conn.prepareCall(Procedures.GetVehicleModel);

            cStmnt.setInt(1, GET_BY_ID);
            cStmnt.setInt(2, vehicleModelId);

            ResultSet rs = cStmnt.executeQuery();

            if(rs.next()){
                vehicleModel = new VehicleModel();
                vehicleModel = HydrateVehicleModel(rs);
            }

        } catch (SQLException SqlEx) {
            logger.error(SqlEx);
        }
        return vehicleModel;
    }

    @Override
    public List<VehicleModel> getVehicleModelList() {
        Connect();
        List<VehicleModel> vehicleModelList = new ArrayList<>();
        try{
            CallableStatement cStmnt = conn.prepareCall(Procedures.GetVehicleModel);

            cStmnt.setInt(1, GET_COLLECTION);
            cStmnt.setNull(2, Types.INTEGER);

            ResultSet rs = cStmnt.executeQuery();

            while(rs.next()){
                vehicleModelList.add(HydrateVehicleModel(rs));
            }

        } catch (SQLException SqlEx) {
            logger.error(SqlEx);
        }

        return vehicleModelList;
    }

    @Override
    public int insertVehicleModel(VehicleModel vehicleModel) {
        int id = 0;
        try{
            id = ModifyVehicleModel(DELETE, vehicleModel);
        } catch (SQLException SqlEx) {
            logger.error(SqlEx);
        }
        return id;
    }

    @Override
    public boolean updateVehicleModel(VehicleModel vehicleModel) {
        int id = 0;
        try{
            id = ModifyVehicleModel(UPDATE, vehicleModel);
        } catch (SQLException SqlEx) {
            logger.error(SqlEx);
        }
        return id>0;
    }

    @Override
    public boolean deleteVehicleModel(int vehicleModelId) {
        int id = 0;
        VehicleModel vehicleModel = new VehicleModel();
        vehicleModel.setVehicleModelId(vehicleModelId);
        try{
            id = ModifyVehicleModel(DELETE, vehicleModel);
        } catch (SQLException SqlEx) {
            logger.error(SqlEx);
        }
        return id>0;
    }

    private static VehicleModel HydrateVehicleModel(ResultSet rs) throws SQLException {
        //modelId       int         index 1
        //modelName     varchar     index 2
        //makeId        int         index 3

        VehicleModel vehicleModel = new VehicleModel(new VehicleMakeDAOImpl().getVehicleMakeById(rs.getInt(3)));
        vehicleModel.setVehicleModelId(rs.getInt(1));
        vehicleModel.setVehicleModelName(rs.getString(2));

        return vehicleModel;
    }

    private static int ModifyVehicleModel(int key, VehicleModel vehicleModel) throws SQLException {
        //queryId       int         index 1
        //modelId       int         index 2
        //modelName     varchar     index 3
        //makeId        int         index 4

        Connect();
        CallableStatement cStmnt = conn.prepareCall(Procedures.ExecVehicleModel);

        cStmnt.setInt(1, key);
        if(vehicleModel.getVehicleModelId()!=0){
            cStmnt.setInt(2, vehicleModel.getVehicleModelId());
        } else {
            cStmnt.setNull(2, Types.INTEGER);
        }

        if(vehicleModel.getVehicleModelName()!=null){
            cStmnt.setString(3, vehicleModel.getVehicleModelName());
        } else {
            cStmnt.setNull(3, Types.VARCHAR);
        }

        if(vehicleModel.getVehicleMakeId()!=0){
            cStmnt.setInt(4, vehicleModel.getVehicleMakeId());
        } else {
            cStmnt.setNull(4, Types.INTEGER);
        }
        ResultSet rs = cStmnt.executeQuery();
        if(rs.next()){
            return rs.getInt(1);
        } else {
            return 0;
        }
    }
}

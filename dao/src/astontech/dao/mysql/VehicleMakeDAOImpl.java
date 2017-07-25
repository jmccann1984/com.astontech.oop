package astontech.dao.mysql;

import astontech.dao.VehicleMakeDAO;
import com.astontech.bo.VehicleMake;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joshua.McCann on 7/17/2017.
 */
public class VehicleMakeDAOImpl extends MySQL implements VehicleMakeDAO {

    @Override
    public VehicleMake getVehicleMakeById(int vehicleMakeId) {
        Connect();
        VehicleMake vehicleMake = null;
        try{
            CallableStatement cStmnt = conn.prepareCall(Procedures.GetVehicleMake);

            cStmnt.setInt(1, GET_BY_ID);
            cStmnt.setInt(2, vehicleMakeId);

            ResultSet rs = cStmnt.executeQuery();

            if(rs.next()){
                vehicleMake = new VehicleMake();
                vehicleMake = HydrateVehicleMake(rs);
            }

        } catch (SQLException SqlEx) {
            logger.error(SqlEx);
        }
        return vehicleMake;
    }

    @Override
    public List<VehicleMake> getVehicleMakeList() {
        Connect();
        List<VehicleMake> vehicleMakeList = new ArrayList<>();
        try{
            CallableStatement cStmnt = conn.prepareCall(Procedures.GetVehicleMake);

            cStmnt.setInt(1, GET_COLLECTION);
            cStmnt.setNull(2, Types.INTEGER);

            ResultSet rs = cStmnt.executeQuery();

            while(rs.next()){
                vehicleMakeList.add(HydrateVehicleMake(rs));
            }

        } catch (SQLException SqlEx) {
            logger.error(SqlEx);
        }

        return vehicleMakeList;
    }

    @Override
    public int insertVehicleMake(VehicleMake vehicleMake) {
        int id = 0;
        try {
            id = ModifyVehicleMake(INSERT, vehicleMake);
        } catch (SQLException SqlEx) {
            logger.error(SqlEx);
        }
        return id;
    }

    @Override
    public boolean updateVehicleMake(VehicleMake vehicleMake) {
        int id = 0;
        try{
            id = ModifyVehicleMake(UPDATE, vehicleMake);
        } catch (SQLException SqlEx) {
            logger.error(SqlEx);
        }
        return id>0;
    }

    @Override
    public boolean deleteVehicleMake(int vehicleMakeId) {
        int id = 0;
        VehicleMake vehicleMake = new VehicleMake();
        vehicleMake.setVehicleMakeId(vehicleMakeId);
        try{
            id = ModifyVehicleMake(DELETE, vehicleMake);
        } catch (SQLException SqlEx) {
            logger.error(SqlEx);
        }
        return id>0;
    }

    private static VehicleMake HydrateVehicleMake(ResultSet rs) throws SQLException{
        //makeId        int         index 1
        //makeName      varchar     index 2

        VehicleMake vehicleMake = new VehicleMake();
        vehicleMake.setVehicleMakeId(rs.getInt(1));
        vehicleMake.setVehicleMakeName(rs.getString(2));

        return vehicleMake;

    }

    private static int ModifyVehicleMake(int key, VehicleMake vehicleMake) throws SQLException {
        //queryId       int         index 1
        //makeId        int         index 2
        //makeName      varchar     index 3

        Connect();
        CallableStatement cStmnt = conn.prepareCall(Procedures.ExecVehicleMake);

        cStmnt.setInt(1, key);
        if(vehicleMake.getVehicleMakeId()!=0){
            cStmnt.setInt(2, vehicleMake.getVehicleMakeId());
        } else {
            cStmnt.setNull(2, Types.INTEGER);
        }

        if(vehicleMake.getVehicleMakeName()!=null){
            cStmnt.setString(3, vehicleMake.getVehicleMakeName());
        } else {
            cStmnt.setNull(3, Types.VARCHAR);
        }
        ResultSet rs = cStmnt.executeQuery();
        if(rs.next()){
            return rs.getInt(1);
        } else {
            return 0;
        }
    }
}

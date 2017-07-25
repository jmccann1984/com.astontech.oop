package astontech.dao.mysql;

import astontech.dao.VehicleDAO;
import com.astontech.bo.Vehicle;
import common.helpers.DateHelper;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joshua.McCann on 7/17/2017.
 */
public class VehicleDAOImpl extends MySQL implements VehicleDAO {

    @Override
    public Vehicle getVehicleById(int vehicleId) {
        Connect();
        Vehicle vehicle = null;
        try{
            CallableStatement cStmnt = conn.prepareCall(Procedures.GetVehicle);

            cStmnt.setInt(1, GET_BY_ID);
            cStmnt.setInt(2, vehicleId);

            ResultSet rs = cStmnt.executeQuery();

            if(rs.next()){
                vehicle = new Vehicle();
                vehicle = HydrateVehicle(rs);
            }

        } catch (SQLException SqlEx) {
            logger.error(SqlEx);
        }
        return vehicle;
    }

    @Override
    public List<Vehicle> getVehicleList() {
        Connect();
        List<Vehicle> vehicleList = new ArrayList<>();
        try{
            CallableStatement cStmnt = conn.prepareCall(Procedures.GetVehicle);

            cStmnt.setInt(1, GET_COLLECTION);
            cStmnt.setNull(2, Types.INTEGER);

            ResultSet rs = cStmnt.executeQuery();

            while(rs.next()){
                vehicleList.add(HydrateVehicle(rs));
            }

        } catch (SQLException SqlEx) {
            logger.error(SqlEx);
        }

        return vehicleList;
    }

    @Override
    public int insertVehicle(Vehicle vehicle) {
        int id = 0;
        try{
            id = ModifyVehicle(INSERT, vehicle);
        } catch (SQLException SqlEx) {
            logger.error(SqlEx);
        }
        return id;
    }

    @Override
    public boolean updateVehicle(Vehicle vehicle) {
        int id = 0;
        try{
            id = ModifyVehicle(UPDATE, vehicle);
        } catch (SQLException SqlEx) {
            logger.error(SqlEx);
        }
        return id>0;
    }

    @Override
    public boolean deleteVehicle(int vehicleId) {
        int id = 0;
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId(vehicleId);
        try{
            id = ModifyVehicle(DELETE, vehicle);
        } catch (SQLException SqlEx) {
            logger.error(SqlEx);
        }
        return id>0;
    }

    private static Vehicle HydrateVehicle(ResultSet rs) throws SQLException {
        //vehicleId         int         index 1
        //year              int         index 2
        //licensePlate      varchar     index 3
        //vin               varchar     index 4
        //color             varchar     index 5
        //isPurchase        tinyint     index 6
        //purchasePrice     int         index 7
        //purchaseDate      datetime    index 8
        //vehicleModelId    int         index 9

        Vehicle vehicle = new Vehicle(new VehicleModelDAOImpl().getVehicleModelById(rs.getInt(9)));
        vehicle.setVehicleId(rs.getInt(1));
        vehicle.setYear(rs.getInt(2));
        vehicle.setLicensePlate(rs.getString(3));
        vehicle.setVIN(rs.getString(4));
        vehicle.setColor(rs.getString(5));
        //vehicle.setIsPurchase(rs.getInt(6)==1);
        //vehicle.setPurchasePrice(rs.getInt(7));
        //vehicle.setPurchaseDate(DateHelper.sqlDateToUtilDate(rs.getDate(8)));

        return vehicle;
    }

    private static int ModifyVehicle(int key, Vehicle vehicle) throws SQLException {
        //queryId           int         index 1
        //vehicleId         int         index 2
        //year              int         index 3
        //licensePlate      varchar     index 4
        //vin               varchar     index 5
        //color             varchar     index 6
        //isPurchase        tinyint     index 7
        //purchasePrice     int         index 8
        //purchaseDate      datetime    index 9
        //vehicleModelId    int         index 10

        Connect();
        CallableStatement cStmnt = conn.prepareCall(Procedures.ExecVehicle);

        cStmnt.setInt(1, key);
        if(vehicle.getVehicleId()!=0){
            cStmnt.setInt(2, vehicle.getVehicleId());
        } else {
            cStmnt.setNull(2, Types.INTEGER);
        }

        if(vehicle.getYear()!=0){
            cStmnt.setInt(3, vehicle.getYear());
        } else {
            cStmnt.setNull(3, Types.INTEGER);
        }

        if(vehicle.getLicensePlate()!=null){
            cStmnt.setString(4, vehicle.getLicensePlate());
        } else {
            cStmnt.setNull(4, Types.VARCHAR);
        }

        if(vehicle.getVIN()!=null){
            cStmnt.setString(5, vehicle.getVIN());
        } else {
            cStmnt.setNull(5, Types.VARCHAR);
        }

        if(vehicle.getColor()!=null){
            cStmnt.setString(6, vehicle.getColor());
        } else {
            cStmnt.setNull(6, Types.VARCHAR);
        }
        if (vehicle.getIsPurchase()){
            cStmnt.setInt(7, 1);
        } else {
            cStmnt.setNull(7, 0);
        }

        if(vehicle.getPurchasePrice()!=0){
            cStmnt.setInt(8, vehicle.getPurchasePrice());
        } else {
            cStmnt.setNull(8, Types.INTEGER);
        }

        if(vehicle.getPurchaseDate()!=null){
            cStmnt.setDate(9, DateHelper.utilDateToSqlDate(vehicle.getPurchaseDate()));
        } else {
            cStmnt.setNull(9, Types.DATE);
        }

        if(vehicle.getVehicleModelId()!=0){
            cStmnt.setInt(10, vehicle.getVehicleModelId());
        } else {
            cStmnt.setNull(10, Types.INTEGER);
        }


        ResultSet rs = cStmnt.executeQuery();
        if(rs.next()){
            return rs.getInt(1);
        } else {
            return 0;
        }
    }
}

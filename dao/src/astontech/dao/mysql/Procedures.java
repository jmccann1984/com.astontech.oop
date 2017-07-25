package astontech.dao.mysql;

/**
 * Created by Joshua.McCann on 6/29/2017.
 */
public abstract class Procedures {
    final static String GetClient = "{ call usp_GetClient(?,?) }";
    final static String GetEmail = "{ call usp_GetEmail(?,?) }";
    final static String GetEmployee = "{ call usp_GetEmployee(?,?,?) }";
    final static String GetPerson = "{call usp_GetPerson(?,?)}";
    final static String GetPhone = "{ call usp_GetPhone(?,?) }";
    final static String GetVehicle = "{ call usp_GetVehicle(?,?) }";
    final static String GetVehicleMake = "{ call usp_GetVehicleMake(?,?) }";
    final static String GetVehicleModel = "{ call usp_GetVehicleModel(?,?) }";
    final static String ExecClient = "{ call usp_ExecuteClient(?,?,?) }";
    final static String ExecEmail = "{call usp_ExecuteEmail(?,?,?,?,?)}";
    final static String ExecEmployee = "{ call usp_ExecuteEmployee(?,?,?,?,?,?) }";
    final static String ExecPerson = "{call usp_ExecutePerson(?,?,?,?,?,?,?)}";
    final static String ExecPhone = "{ call usp_ExecutePhone(?,?,?,?,?,?,?,?) }";
    final static String ExecVehicle = "{ call usp_ExecuteVehicle(?,?,?,?,?,?,?,?,?,?) }";
    //queryId,  vehicleId,  year,   licensePlate,   vin,        color,      isPurchase,     purchasePrice,  purchaseDate,   vehicleModelId
    //1.int,    2.int     3.int     4.varchar       5.varchar   6.varchar   7.tinyint       8.int           9.datetime      10.int
    final static String ExecVehicleMake = "{ call usp_ExecuteVehicleMake(?,?,?) }";
    //queryId,  makeId, makeName
    //1.int     2.int   3.varchar
    final static String ExecVehicleModel = "{ call usp_ExecuteVehicleModel(?,?,?,?) }";
    //queryId,  modelId modelName,  makeId
    //1.int     2.int   3.varchar   4.int
}

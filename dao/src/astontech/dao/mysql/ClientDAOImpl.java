package astontech.dao.mysql;

import astontech.dao.ClientDAO;
import com.astontech.bo.Client;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joshua.McCann on 6/28/2017.
 */
public class ClientDAOImpl extends MySQL implements ClientDAO {

    @Override
    public Client getClientById(int clientId) {
        Connect();
        Client client = null;
        try{
            CallableStatement cStmnt = conn.prepareCall(Procedures.GetClient);

            cStmnt.setInt(1, GET_BY_ID);
            cStmnt.setInt(2, clientId);

            ResultSet rs = cStmnt.executeQuery();

            while(rs.next()){
                client = new Client();
                client = HydrateClient(rs);
            }
        } catch (SQLException SqlEx){
            logger.error(SqlEx);
        }

        return client;
    }

    @Override
    public List<Client> getClientList() {
        Connect();
        List<Client> clientList = new ArrayList<Client>();
        try {
            CallableStatement cStmnt = conn.prepareCall(Procedures.GetClient);

            cStmnt.setInt(1, GET_COLLECTION);
            cStmnt.setInt(2, 0);

            ResultSet rs = cStmnt.executeQuery();

            while(rs.next()){
                clientList.add(HydrateClient(rs));
            }

        } catch (SQLException SqlEx){
            logger.error(SqlEx);
        }

        return clientList;
    }

    @Override
    public int insertClient(Client client) {
        int id = 0;
        try {
            id = ModifyClient(INSERT, client);

        } catch (SQLException SqlEx) {
            logger.error(SqlEx);
        }

        return id;
    }

    @Override
    public boolean updateClient(Client client) {
        int id = 0;
        try {
            id = ModifyClient(UPDATE, client);

        } catch (SQLException SqlEx) {
            logger.error(SqlEx);
        }

        return id>0;
    }

    @Override
    public boolean deleteClient(int clientId) {
        int id = 0;
        Client client = new Client();
        client.setClientId(clientId);
        try {
            id = ModifyClient(DELETE, client);

        } catch (SQLException SqlEx) {
            logger.error(SqlEx);
        }

        return id>0;
    }

    private static Client HydrateClient(ResultSet rs) throws SQLException{
        /*
        *   ClientId    int     index 1
        *   ClientName  varchar index 2
        *   CreateDate  date    index 3
        */

        Client client = new Client();
        client.setClientId(rs.getInt(1));
        client.setClientName(rs.getString(2));
        client.setCreateDate(rs.getDate(3));

        return client;
    }

    private static int ModifyClient(int key, Client client) throws SQLException{
        Connect();
        //`usp_ExecuteClient`(queryId int, clientId int, clientName varchar(100))

        CallableStatement cStmnt = conn.prepareCall(Procedures.ExecClient);

        cStmnt.setInt(1, key);
        if(client.getClientId()==0)
            cStmnt.setNull(2, Types.INTEGER);
        else
            cStmnt.setInt(2, client.getClientId());
        cStmnt.setString(3, client.getClientName());

        ResultSet rs = cStmnt.executeQuery();

        if(rs.next())
            return rs.getInt(1);
        else
            return 0;
    }
}

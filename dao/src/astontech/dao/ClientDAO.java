package astontech.dao;

import com.astontech.bo.Client;

import java.util.List;

/**
 * Created by Joshua.McCann on 6/28/2017.
 */
public interface ClientDAO {

    //GET METHODS
    public Client getClientById(int clientId);
    public List<Client> getClientList();

    //EXECUTE METHODS
    public int insertClient(Client client);
    public boolean updateClient(Client client);
    public boolean deleteClient(int clientId);
}

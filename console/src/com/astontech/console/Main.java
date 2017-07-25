package com.astontech.console;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.Date;

import astontech.dao.*;
import astontech.dao.mysql.*;
import com.astontech.bo.*;
import com.astontech.bo.interfaces.Home;
import com.astontech.bo.interfaces.IGetFullName;
import com.astontech.bo.interfaces.ILocation;
import com.astontech.bo.interfaces.Site;
import common.helpers.*;
import org.apache.log4j.Logger;


public class Main {

    final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {

        //Notes:    private static method for the lesson
        LessonRecursionComplex(new File("C:\\Users\\Joshua.McCann\\Desktop\\"));
    }

    private static void LessonRecursionComplex(File dir){

        try{
//            int dirLength = dir.getCanonicalPath().length() - dir.getParent().length();
            File[] files = dir.listFiles();
            DecimalFormat df = new DecimalFormat("#.##");
            for(File file : files){
                if(file.isDirectory()){
                    //Notes:    recursion happens here
                    double folderLength;
                    logger.info("Parent File: " + file.getParentFile());
                    if(file.listFiles()!=null) {
                        folderLength = folderSize(file) / 1024.0;
                        logger.info("Folder     - ParentFolder: " + file.getParent().subSequence(file.getParent().lastIndexOf("\\") + 1, file.getParent().length()) + " FolderName: " + file.getName() + " Size: " + df.format(folderLength/1024.0) + " MB #Files: " + file.listFiles().length);
                        LessonRecursionComplex(file);
                    } else
                        logger.info("Folder     - ParentFolder: " + file.getParent().subSequence(file.getParent().lastIndexOf("\\") + 1, file.getParent().length()) + " FolderName: " + file.getName() + " Size: 0 MB #Files: 0");
                } else {
                    if(file.getName().contains(".")) {
                        if(file.length()<1024)
                            logger.info("File       - ParentFolder: " + file.getParent().subSequence(file.getParent().lastIndexOf("\\") + 1, file.getParent().length()) + " FileName: " + file.getName().subSequence(0, file.getName().lastIndexOf(".")) + " FileType: " + file.getName().subSequence(file.getName().lastIndexOf(".") + 1, file.getName().length()) + " Size: " + file.length() + " bytes");
                        else
                            logger.info("File       - ParentFolder: " + file.getParent().subSequence(file.getParent().lastIndexOf("\\") + 1, file.getParent().length()) + " FileName: " + file.getName().subSequence(0, file.getName().lastIndexOf(".")) + " FileType: " + file.getName().subSequence(file.getName().lastIndexOf(".") + 1, file.getName().length()) + " Size: " + df.format(file.length()/1024.0) + " KB");
                    }else {
                        if(file.length()<1024)
                            logger.info("File       - ParentFolder: " + file.getParent().subSequence(file.getParent().lastIndexOf("\\") + 1, file.getParent().length()) + " FileName: " + file.getName() + " FileType: none Size: " + file.length() + " bytes");
                        else
                            logger.info("File       - ParentFolder: " + file.getParent().subSequence(file.getParent().lastIndexOf("\\") + 1, file.getParent().length()) + " FileName: " + file.getName() + " FileType: none Size: " + df.format(file.length()/1024.0) + " KB");
                    }
                }

            }
        } catch (Exception ioEx){
            logger.error(ioEx);
        }

    }

    private static long folderSize(File dir){
        long length = 0;
        for (File file : dir.listFiles()){
            if (file.isFile())
                length += file.length();
            else if (file.listFiles()!=null)
                length += folderSize(file);
        }
        return length;
    }

    private static void LessonRecursion(int recursionCount) {
        logger.info("Recursive Count = " + recursionCount);
        if(recursionCount > 0)
            LessonRecursion(recursionCount - 1);
    }

    private static void LessonSerialization() {
        //Notes:    Get an object from db.
        PersonDAO personDAO = new PersonDAOImpl();
        Person person = personDAO.getPersonById(1);

        //Notes:    Serialize to a text file
        try {
            FileOutputStream fileOut = new FileOutputStream("C:\\Users\\Joshua.McCann\\Desktop\\OOP\\Logs\\ser_person.text");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(person);
            out.close();
            logger.info("Object serialization and writen to file: C:\\Users\\Joshua.McCann\\Desktop\\OOP\\Logs\\ser_person.text");
            logger.info("Serialized object: " + person.ToString());

        } catch (IOException ioEx) {
            logger.error(ioEx);
        }

    }

    private static void LessonDeserialization() {
        Person person = null;
        try {
            FileInputStream fileIn = new FileInputStream("C:\\Users\\Joshua.McCann\\Desktop\\OOP\\Logs\\ser_person.text");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            person = (Person) in.readObject();
            fileIn.close();

        } catch(FileNotFoundException fileEx){
            logger.error(fileEx);
        } catch(IOException ioEx) {
            logger.error(ioEx);
        } catch(ClassNotFoundException clzEx){
            logger.error(clzEx);
        }

        logger.info("Deseralized object: " + person.ToString());
    }

    private static void LessonBoxUnboxCast(){
        //notes:    Boxing:     act of converting a value type to a reference type
        //          Unboxing:   converting a reference type to a value type

        //Notes:    Boxing
        int x = 10;
        Object o = x;

        LessonReflection(o.getClass());

        //Notes:    Unboxing (this is casting, specifically explicit casting
        int y = (int)o;
        logger.info(y);

        //Notes:    implicit casting
        int i = 100;
        double d = i;

        double db = 1.92;
        //int in = db;  this fails

        //notes:    explicit casting
        int in = (int) db;
        logger.info(in);
    }

    private static <T> void LessonReflection(Class<T> genericClass){
        Class obj = genericClass;

        logger.info("Full Name: " + obj.getName());
        logger.info("Simple Name: " + obj.getSimpleName());

        for(Field field : obj.getDeclaredFields()){
            logger.info("Field: " + field.getName() + " - Type: " + field.getType());
        }
        for(Method method : obj.getDeclaredMethods()){
            logger.info("Method: " + method.getName() + " - ReturnType: " + method.getReturnType());
        }
    }

    private static void PhoneDAODelete() {
        PhoneDAO phoneDAO = new PhoneDAOImpl();
        if(phoneDAO.deletePhone(18))
            logger.info("Phone Deleted Successfully!");
        else
            logger.info("Phone Delete Failed!");


    }

    private static void PhoneDAOUpdate() {
        PhoneDAO phoneDAO = new PhoneDAOImpl();
        Phone phone = phoneDAO.getPhoneById(18);

        phone.setAreaCode(612);

        if(phoneDAO.updatePhone(phone))
            logger.info("Updated Successfully!");
        else
            logger.info("Update Not Successful!");
    }

    private static void PhoneDAOInsert(){
        Phone phone = new Phone();
        EntityType entityType = new EntityType();
        entityType.setEntityTypeId(1);

        phone.setPhoneId(0);
        phone.setPhoneType(entityType);
        phone.setPhonePerson(new PersonDAOImpl().getPersonById(4));
        phone.setPhoneNumber(5554545);


        PhoneDAO phoneDAO = new PhoneDAOImpl();
        int id = phoneDAO.insertPhone(phone);

        logger.info("New Phone Record Inserted.  ID=" + id);


    }

    private static void EmployeeDAODelete() {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        if(employeeDAO.deleteEmployee(7))
            logger.info("Employee Deleted Successfully!");
        else
            logger.info("Employee Delete Failed!");


    }

    private static void EmployeeDAOUpdate() {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        Employee employee = employeeDAO.getEmployeeById(6);

        employee.setPersonId(7);

        if(employeeDAO.updateEmployee(employee))
            logger.info("Updated Successfully!");
        else
            logger.info("Update Not Successful!");
    }

    private static void EmployeeDAOInsert(){
        Employee employee = new Employee();
        DateFormat df = new SimpleDateFormat("mm dd yyyy");

        try {
            employee.setEmployeeId(0);
            employee.setBirthDate(null);
            employee.setCreateDate(null);
            employee.setTermDate(null);
            employee.setHireDate(df.parse("06 01 2000"));
            employee.setPersonId(6);
        }catch (Exception ex){
            logger.error("Problem with the date");
        }

        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        int id = employeeDAO.insertEmployee(employee);

        logger.info("New Employee Record Inserted.  ID=" + id);


    }

    private static void EmailDAODelete() {
        EmailDAO emailDAO = new EmailDAOImpl();
        if(emailDAO.deleteEmail(10))
            logger.info("Email Deleted Successfully!");
        else
            logger.info("Email Delete Failed!");


    }

    private static void EmailDAOUpdate() {
        EmailDAO emailDAO = new EmailDAOImpl();
        Email email = emailDAO.getEmailById(10);

        email.setEmailAddress("Bob@bob.com");

        if(emailDAO.updateEmail(email))
            logger.info("Updated Successfully!");
        else
            logger.info("Update Not Successful!");
    }

    private static void EmailDAOInsert(){
        Email email = new Email();

        EntityType entityType = new EntityType();
        entityType.setEntityTypeId(2);

        email.setEmailAddress("SNilsen@gmail.com");
        email.setEmailId(0);
        email.setEmailType(entityType);
        email.setEmailEmployee(new EmployeeDAOImpl().getEmployeeById(4));

        EmailDAO emailDAO = new EmailDAOImpl();
        int id = emailDAO.insertEmail(email);

        logger.info("New Person Record Inserted.  ID=" + id);


    }

    private static void ClientDAODelete() {
        ClientDAO clientDAO = new ClientDAOImpl();
        if(clientDAO.deleteClient(11))
            logger.info("Client Deleted Successfully!");
        else
            logger.info("Client Delete Failed!");


    }

    private static void ClientDAOUpdate() {
        ClientDAO clientDAO = new ClientDAOImpl();
        Client client = clientDAO.getClientById(11);

        client.setClientName("Ubisoft");

        if(clientDAO.updateClient(client))
            logger.info("Updated Successfully!");
        else
            logger.info("Update Not Successful!");
    }

    private static void ClientDAOInsert(){
        Client client = new Client();
        client.setClientId(0);
        client.setClientName("Konami");
        client.setCreateDate(null);

        ClientDAO clientDAO = new ClientDAOImpl();
        int id = clientDAO.insertClient(client);

        logger.info("New Client Record Inserted.  ID=" + id);


    }

    private static void LessonDAODelete() {
        PersonDAO personDAO = new PersonDAOImpl();
        if(personDAO.deletePerson(20))
            logger.info("Person Deleted Successfully!");
        else
            logger.info("Person Delete Failed!");


    }

    private static void LessonDAOUpdate() {
        PersonDAO personDAO = new PersonDAOImpl();
        Person person = personDAO.getPersonById(2);

        person.setTitle("Mr.");

        if(personDAO.updatePerson(person))
            logger.info("Updated Successfully!");
        else
            logger.info("Update Not Successful!");
    }

    private static void LessonDAOInsert(){
        Person person = new Person();
        person.setFirstName("Anthony");
        person.setLastName("Stark");
        person.setGender('M');
        person.setDisplayFirstName("Tony");
        person.setTitle("Mr.");

        PersonDAO personDAO = new PersonDAOImpl();
        int id = personDAO.insertPerson(person);

        logger.info("New Person Record Inserted.  ID=" + id);


    }

    private static void LessonDAO() {
        //region CREATE MENU
        PersonDAO personDAO = new PersonDAOImpl();
        List<Person> personlist = personDAO.getPersonList();


        System.out.println("==========================");

        for(Person person : personlist){
            System.out.println(person.getPersonId() + " " + person.getLastName() + ", " + person.getFirstName());
        }

        System.out.println("==========================");


        //endregion

        //region PROMPT USER

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please Select a Person from list: ");
        String personId = scanner.nextLine();

        //endregion

        //region GET PERSON DETAILS

        Person personDetail = personDAO.getPersonById(Integer.parseInt(personId));

        System.out.println("------- PERSON DETAILS ------");
        System.out.println("First Name: " + personDetail.getFullName());
        System.out.println("Gender: " + personDetail.getGender());
        System.out.println("Create Date: " + personDetail.getCreateDate());

        //endregion
    }

    private static void ClientDAOTest() {
        //region CREATE MENU
        ClientDAO clientDAO = new ClientDAOImpl();
        List<Client> clientList = clientDAO.getClientList();


        System.out.println("==========================");

        for(Client client : clientList){
            System.out.println(client.getClientId() + " " + client.getClientName());
        }

        System.out.println("==========================");


        //endregion

        //region PROMPT USER

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please Select a Client from list: ");
        String clientId = scanner.nextLine();

        //endregion

        //region GET DETAILS

        Client clientDetail = clientDAO.getClientById(Integer.parseInt(clientId));

        System.out.println("------- CLIENT DETAILS ------");
        System.out.println("Client Name: " + clientDetail.getClientName());
        System.out.println("Create Date: " + clientDetail.getCreateDate());


        //endregion
    }

    private static void EmailDAOTest() {
        //region CREATE MENU
        EmailDAO emailDAO = new EmailDAOImpl();
        List<Email> emailList = emailDAO.getEmailList();

        System.out.println("==========================");

        for(Email email : emailList){
            System.out.println(email.getEmailId() + ": " + email.getEmailAddress());
        }

        System.out.println("==========================");

        //endregion

        //region PROMPT USER
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select an Email");
        String emailId = scanner.nextLine();

        //endregion

        //region GET DETAILS
        Email emailDetail = emailDAO.getEmailById(Integer.parseInt(emailId));

        System.out.println("----- EMAIL DETAILS -----");
        System.out.println("Email Address:  " + emailDetail.getEmailAddress());
        System.out.println("Employee Owner: " + emailDetail.getEmailEmployee().getFullName());
        //endregion

    }

    private static void EmployeeDAOTest() {
        //region CREATE MENU
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        List<Employee> employeeList = employeeDAO.getEmployeeList();

        System.out.println("==========================");

        for(Employee employee : employeeList){
            System.out.println(employee.getEmployeeId() + ": " + employee.getLastName() + ", " + employee.getFirstName());
        }

        System.out.println("==========================");

        //endregion

        //region PROMPT USER
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select an Employee:");
        String employeeId = scanner.nextLine();

        //endregion

        //region GET DETAILS
        Employee employeeDetail = employeeDAO.getEmployeeById(Integer.parseInt(employeeId));
        System.out.println("----- EMPLOYEE DETAILS -----");
        System.out.println("Full Name:  " + employeeDetail.getFullName());
        System.out.println("Hire Date:  " + employeeDetail.getHireDate());
        System.out.println("Term Date:  " + employeeDetail.getTermDate());
        System.out.println("Birth Date: " + employeeDetail.getBirthDate());

        //endregion

    }

    private static void PhoneDAOTest() {
        //region CREATE MENU
        PhoneDAO phoneDAO = new PhoneDAOImpl();
        List<Phone> phoneList = phoneDAO.getPhoneList();

        System.out.println("==========================");

        for(Phone phone : phoneList){
            System.out.println(phone.getPhoneId() + ": (" + phone.getAreaCode() + ") " + phone.getPhoneNumber());
        }

        System.out.println("==========================");

        //endregion

        //region PROMPT USER
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select a Phone number");
        String phoneId = scanner.nextLine();

        //endregion

        //region GET DETAILS
        Phone phoneDetail = phoneDAO.getPhoneById(Integer.parseInt(phoneId));

        System.out.println("----- PHONE DETAILS -----");
        System.out.println("Phone Number: " + phoneDetail.getAreaCodeString());
        if(phoneDetail.getPhonePerson() != null)
            System.out.println("Person Name:  " + phoneDetail.getPhonePerson().getFullName());
        if(phoneDetail.getPhoneClient() != null)
            System.out.println("Client Name:  " + phoneDetail.getPhoneClient().getClientName());

        //endregion

    }


    private static void SqlServerQuery(){
        Connection conn = SqlServerDBConnection();
        try{
            Statement statement = conn.createStatement();
            String sql = "select PersonId, FirstName, LastName from dbo.Person";

            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                int personId = rs.getInt(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);

                logger.info(personId + ": " + firstName + " " + lastName);
            }
            conn.close();
        } catch (Exception ex) {
            logger.error(ex);
        }
    }

    private static void LessonGetStoredProc() {
        Connection conn = LessonDBConnection();
        try {
            String sp = "{call usp_GetPerson(?,?)}";
            CallableStatement cStmt = conn.prepareCall(sp);

            cStmt.setInt(1, 20);
            cStmt.setInt(2, 0);
            ResultSet rs = cStmt.executeQuery();

            while(rs.next()){
                /*Title             varchar     index 1
                * FirstName         varchar     index 2
                * LastName          varchar     index 3
                * DisplayFirstName  varchar     index 4
                * Gender            char        index 5
                */

                String title = rs.getString(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                String displayFirstName = rs.getString(4);
                char gender = rs.getString(5).charAt(0);

                logger.info(title + " " + firstName + " " + lastName + " \"" + displayFirstName + "\" " + gender);

            }


            conn.close();
        } catch (SQLException SqlEx){
            logger.error(SqlEx);
        }
    }

    private static void LessonExecQuery() {
        Connection conn = LessonDBConnection();
        try {
            Statement statement = conn.createStatement();
            String sql = "select PersonId, FirstName, LastName from person";

            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                int personId = rs.getInt(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);

                logger.info(personId + ": " + firstName + " " + lastName);
            }

            conn.close();


        } catch (SQLException SqlEx ) {
            logger.error(SqlEx);
        }
    }

    private static Connection LessonDBConnection() {
        String dbHost = "localhost";
        String dbName = "astonengineer";
        String dbUser = "consoleUser";
        String dbPass = "qwe123$!";
        String useSSL = "false";
        String procBod = "true";

        try{
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex){
            logger.error("MySQL Driver not found! " + ex.getMessage());
            return null;
        }

        logger.info("MySQL Driver Registered.");
        Connection connection = null;

        try{
            connection = DriverManager.getConnection("jdbc:mysql://" + dbHost + ":3306/" +dbName + "?useSSL=" + useSSL + "&noAccessToProcedureBodies=" + procBod,dbUser,dbPass);
        } catch (SQLException ex){
            logger.error("Connect Failed! :" + ex.getMessage());
            return null;
        }

        if(connection != null){
            logger.info("Successfully connected to MySQL database");
            return connection;
        } else {
            logger.info("Connection failed!");
            return null;
        }
    }

    private static Connection SqlServerDBConnection() {
        String dbHost = "MNLT180";
        String dbName = "AstonEngineer";
        String dbUser = "sa";
        String dbPass = "saPassword";

        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://" + dbHost + ";databaseName=" + dbName + ";user=" + dbUser + ";password=" + dbPass);
            return conn;

        } catch (Exception ex){
            logger.error(ex);
            return null;
        }
    }

    private static void LessonLogging() {
        //Notes:    Level of logging.
        logger.debug("This is a DEBUG log message");
        logger.info("This is an INFO log message");

        //Notes:    Production Levels
        logger.warn("This is a WARN log message");
        logger.error("This is an ERROR log message");
        logger.fatal("This is a FATAL log message");

        //Notes:    log an exception
        try {
            int i = 10 / 0;
        }catch(ArithmeticException ex){
                logger.error("An exception occurred: " + ex.getMessage());
        }
    }

    public static void InterfacesLab(){
        List<Vehicle> vehicleTestList = new ArrayList<>();
        List<Employee> employeeTestList = new ArrayList<>();
        vehicleTestList.add(new Vehicle("Toyota", "Carola"));
        vehicleTestList.add(new Vehicle("Toyota", "Camry"));
        vehicleTestList.add(new Vehicle("Ford", "Prefect"));

        employeeTestList.add(new Employee("Joshua", "McCann"));
        employeeTestList.add(new Employee("Bob", "Dole"));
        employeeTestList.add(new Employee("Smith"));
        employeeTestList.add(new Employee(1, "Janice"));

        for(Employee singleEmployee : employeeTestList){
            fullNameInterface(singleEmployee, "Employee Name");
        }

        for(Vehicle singleVehicle : vehicleTestList){
            fullNameInterface(singleVehicle, "Vehicle Type");
        }

        OopPrinciplesLab5 testString = new OopPrinciplesLab5("Print me backward");
        OopPrinciplesLab5 testString2 = new OopPrinciplesLab5("Race car");
        OopPrinciplesLab5 testString3 = new OopPrinciplesLab5("otto");

        printBackward(testString);
        printBackward(testString2);
        printBackward(testString3);

    }

    public static void fullNameInterface(IGetFullName IgetFullName, String s){
        System.out.println(s + ": " + IgetFullName.getFullName());

    }

    public static void printBackward(CharSequence charSequence){
        System.out.println(charSequence.subSequence(charSequence.length()-1, 0));
    }

    private static void LessonInterfacesTest() {
        Site MN010 = new Site();
        MN010.setSiteName("MN010");
        MN010.setCoffeeMachines(2);
        MN010.setConferenceRooms(1);
        MN010.setCubicles(8);
        MN010.setOffices(6);
        MN010.setTrainingDesks(36);

        Home BipsHouse = new Home();
        BipsHouse.setAddress("1 Main St.");
        BipsHouse.setOwner(new Employee("Bipin", "Butala"));

        LessonInterfaces(MN010);
        LessonInterfaces(BipsHouse);
    }

    private static void LessonInterfaces(ILocation Ilocation) {
        System.out.println("========================");
        System.out.println("Location Name: " + Ilocation.getLocationNames());
        System.out.println("Can Have Meetings: " + Ilocation.canHaveMeetings());
        System.out.println("Number of Workspaces: " + Ilocation.numberOfWorkspaces());
        System.out.println("Has Coffee: " + Ilocation.hasCoffee());
        System.out.println("========================");

    }

    private static void LessonValueVsRef(){
        //Notes:    references type
        Employee firstEmp = new Employee();
        firstEmp.setFirstName("Bipin");

        Employee secondEmp = firstEmp;
        firstEmp.setFirstName("Dean");
        secondEmp.setFirstName("Bob");

        System.out.println("SecondEmp: " + secondEmp.getFirstName());
        System.out.println("FirstEmp: " + firstEmp.getFirstName());

        int firstInt = 10;
        int secondInt = firstInt;

        firstInt = 20;

        System.out.println("\nFirst num: " + firstInt);
        System.out.println("second num: " + secondInt);
    }

    private static void LessonHash() {
        //Notes:    key-value pairs / value list

        //todo:     HashTable
        /*
        *   1) does NOT allow null for either key or value
        *   2) synchronized, thread safe, but performance is decreased
        */

        System.out.println("---HASH TABLE---");

        Hashtable<Integer, String> firstHashTable = new Hashtable<>();
        firstHashTable.put(1, "Inheritance");
        firstHashTable.put(2, "Encapsulation");
        firstHashTable.put(3, "Abstraction");
        firstHashTable.put(4, "Polymorphism");
//        hashTable.put(5, null); //throws NPE

        System.out.println("Value from given key: " + firstHashTable.get(3));
        for(Integer key : firstHashTable.keySet()){
            System.out.println("key: " + key + " - value: " + firstHashTable.get(key));
        }

        //todo:     HashMap
        /*
        *   1) Does allow null for either key or value
        *   2) un-synchronized, not thread safe, better performance
        */

        System.out.println("----------------");
        System.out.println("----------------");

        System.out.println("----HASH MAP----");

        HashMap<Integer, String> firstHashMap = new HashMap<>();
        firstHashMap.put(1, "Inheritance");
        firstHashMap.put(2, "Encapsulation");
        firstHashMap.put(3, "Abstraction");
        firstHashMap.put(4, "Polymorphism");
        firstHashMap.put(5, null); //does not throw NPE
        firstHashMap.put(6, "Encapsulation");


        for(Integer key : firstHashMap.keySet()){
            System.out.println("key: " + key + " - value: " + firstHashMap.get(key));
        }

        System.out.println("----------------");
        System.out.println("----------------");

        //todo:     HashSet
        /*
        *   1) built in mechanism for duplicates
        *   2) used for where you want to mantain a unique list
        */

        System.out.println("----HASH SET----");
        HashSet<String> oopPrincip = new HashSet<>();
        oopPrincip.add("Inheritance");
        oopPrincip.add("Encapsulation");
        oopPrincip.add("Abstraction");
        oopPrincip.add("Polymorphism");
        //Will not take multiple entries

        if(oopPrincip.contains("Encapsulation"))
            System.out.println("Value exists");
        else
            System.out.println("Values does not exist");

        oopPrincip.add("Encapsulation");
        oopPrincip.add("Encapsulation");
        oopPrincip.add("Encapsulation");
        oopPrincip.add("Encapsulation");



        for(String key : oopPrincip){
            System.out.println("value: " + key);
        }

        System.out.println("\n----HASH SET----");


        int[] intArray;
        intArray = new int[10];
        intArray[0] = 6;
        intArray[1] = 1;
        intArray[2] = 2;
        intArray[3] = 4;
        intArray[4] = 5;
        intArray[5] = 6;
        intArray[6] = 6;
        intArray[7] = 5;
        intArray[8] = 3;
        intArray[9] = 2;



        HashSet<Integer> numberComparison = new HashSet<>();
        for (int integerArray : intArray){
            numberComparison.add(integerArray);
        }

        for (Integer key : numberComparison){
            System.out.println("Numbers are: " + key.toString());
        }

        System.out.println("Greatest value: " + numberComparison);



    }

    private static void LessonPolymorphism() {
        //Notes:    compile time polymorphism - overloaded

        //Notes:    run-time polymorphism - over-rid
        BaseBO baseBO = new BaseBO();
        System.out.println(baseBO.test_method());

        EntityType entityType = new EntityType();
        System.out.println(entityType.test_method());
    }

    private static void LessonInstanceVsStatic() {
//        Math InstanceOfMathClass = new Math();

        System.out.println(MathHelper.E);
        System.out.println(MathHelper.PI);

        System.out.println(MathHelper.square(4));

    }

    private static void LessonCollectionsLAB() {
        List<Vehicle> VehicleCollection = new ArrayList<>();

        VehicleCollection.add(new Vehicle(2016, null, "HGBH41JXQE109186","Red",false,0));
        VehicleCollection.add(new Vehicle(2016, "AB9-75D", "1GHBN95GXQR895187","Blue",true,18963));
        VehicleCollection.add(new Vehicle(2017, "FW9-DQ7", "1RNKQ28AXMN914863","Green",true,504932));
        VehicleCollection.add(new Vehicle(2016, null, "1ADXY18VXQR781496","Purple",false,0));
        VehicleCollection.add(new Vehicle(2016, "F87-FQ6", "1JNRD63FXQR678126","White",true,49763));
        VehicleCollection.add(new Vehicle(2017, null, "1HGBH41JXMN985237","Tan",false,0));
        VehicleCollection.add(new Vehicle(2017, "FD9-DA9", "1GHBN95GXMN184384","Yellow",true,47954));
        VehicleCollection.add(new Vehicle(2017, "VB7-897", "1JNRD63FXMN198465","Pink",true,19843));
        VehicleCollection.add(new Vehicle(2016, "X47-7GA", "1RNKQ28AXQR175863","Cyan",true,19876));
        VehicleCollection.add(new Vehicle(2017, null, "1ADXY18VXMN183549","Magenta",false,0));

        VehicleCollection.get(0).setVehicleModelName("Vanquish");
        VehicleCollection.get(0).setVehicleMakeName("Aston Martin");
        VehicleCollection.get(1).setVehicleModelName("Accent");
        VehicleCollection.get(1).setVehicleMakeName("Hyundai");
        VehicleCollection.get(2).setVehicleModelName("Chevrolet Corvette");
        VehicleCollection.get(2).setVehicleMakeName("General Motors");
        VehicleCollection.get(3).setVehicleModelName("Beetle");
        VehicleCollection.get(3).setVehicleMakeName("Volkswagen");
        VehicleCollection.get(4).setVehicleModelName("Camry");
        VehicleCollection.get(4).setVehicleMakeName("Toyota");
        VehicleCollection.get(5).setVehicleModelName("Rapide S");
        VehicleCollection.get(5).setVehicleMakeName("Aston Martin");
        VehicleCollection.get(6).setVehicleModelName("Equus");
        VehicleCollection.get(6).setVehicleMakeName("Hyundai");
        VehicleCollection.get(7).setVehicleModelName("Corolla");
        VehicleCollection.get(7).setVehicleMakeName("Toyota");
        VehicleCollection.get(8).setVehicleModelName("Hummber H3");
        VehicleCollection.get(8).setVehicleMakeName("General Motors");
        VehicleCollection.get(9).setVehicleModelName("Jetta");
        VehicleCollection.get(9).setVehicleMakeName("Volkswagen");

        for(Vehicle singleVehicle : VehicleCollection){
            System.out.println(singleVehicle.ToString());
        }

    }

    private static void LessonComplexProperties(){

        EntityType emailPersonalType = new EntityType("Personal");

        Email myEmail = new Email("joshua.c.mccann@gmail.com");
        myEmail.setEmailType(emailPersonalType);

        System.out.println(myEmail.getEmailAddress() + " Type: " + myEmail.getEmailType().getEntityTypeName());

        //notes:    collection/list of complex(nested) objects as a property.
        Employee myEmployee = new Employee();
        myEmployee.getEmails().add(new Email("test@test.com"));
        myEmployee.getEmails().add(new Email("dan@test.com"));
        myEmployee.getEmails().add(new Email("jason@test.com"));

        for(Email email : myEmployee.getEmails()){
            System.out.println(email.getEmailAddress());
        }
    }

    private static void LessonCollections(){
        //notes:    List<T> - generic type 'T'
        List<Employee> employeeList = new ArrayList<>();

        Employee emp1 = new Employee("Dan", "Simmer");
        Employee emp2 = new Employee("James", "McRoberts");
        Employee emp3 = new Employee("Sean", "Nilsen");

        employeeList.add(emp1);
        employeeList.add(emp2);
        employeeList.add(emp3);

        employeeList.add(new Employee("Adrian", "Ratanyake"));
        employeeList.add(new Employee("John", "Doe"));

        for(Employee e : employeeList){
            System.out.println(e.getFullName());
        }
    }

    private static void LessonObjectsLAB(){
        Address joshAddress = new Address("326", "George Street West", "#3", "St. Paul");
        Client bestBuyClient = new Client("BestBuy");
        ClientContact bestBuyClientContact = new ClientContact();
        Email joshEmail = new Email("jmcann@place.com");
        EmployeeProject testEmployeeProject = new EmployeeProject("Test place holder for testEmployeeProject");
        Entity testEntity = new Entity("email");
        EntityType testEntityType = new EntityType("Personal");
        LoyaltyAccount testLoyaltyAccount = new LoyaltyAccount("xyznrp");
        LoyaltyCompany testLoyaltyCompany = new LoyaltyCompany("Experis");
        Phone joshPhone = new Phone(262, 3390817, 1);
        Project testProject = new Project(50, "Lab 3");
        ProjectStatus testProjectStatus = new ProjectStatus("Test place holder for testProjectStatus", 50);
        Review testReview = new Review("This is just a test place holder for testReview.");
        ReviewData testReviewData = new ReviewData("This is just a test place holder for testReviewData");
        Training testTraining = new Training("MN010 Java");
        TrainingData testTrainingData = new TrainingData("06-12-2017");
        Vehicle joshVehicle = new Vehicle(2007,"132-HVG","1HG9854XVC98","Silver",true,10000);
        VehicleMake Honda = new VehicleMake("Honda");
        VehicleModel Civic = new VehicleModel("Civic");
        VehicleStatus joshVehicleStatus = new VehicleStatus("Vehicle driven by employee");

        bestBuyClientContact.setFirstName("Joshua");
        bestBuyClientContact.setLastName("McCann");


        System.out.println(joshAddress.getFullAddress());
        System.out.println(bestBuyClient.getClientName());
        System.out.println(bestBuyClientContact.getFullName());
        System.out.println(joshEmail.getEmailAddress());
        System.out.println(testEmployeeProject.getNotes());
        System.out.println(testEntity.getEntityName());
        System.out.println(testEntityType.getEntityTypeName());
        System.out.println(testLoyaltyAccount.getMemberNumber());
        System.out.println(testLoyaltyCompany.getCompanyName());
        System.out.println(joshPhone.getFullPhoneNumber());
        System.out.println(testProject.getProjectName());
        System.out.println(Integer.toString(testProjectStatus.getPercentComplete()));
        System.out.println(testReview.getReviewName());
        System.out.println(testReviewData.getReviewDataValue());
        System.out.println(testTraining.getTrainingName());
        System.out.println(testTrainingData.getTrainingDataValue());
        System.out.println(joshVehicle.getLicensePlate());
        System.out.println(Honda.getVehicleMakeName());
        System.out.println(Civic.getVehicleModelName());
        System.out.println(joshVehicleStatus.getNotes());

        //Testing to see if algorithm appropriately adds spaces to getFullAddress
        //System.out.println("\nTest Address to see if function getFullAddress Works.");

        Address testAddress = new Address();
        Address testAddress2 = new Address();
        Address blankAddress = new Address();

        testAddress.setCity("St. Paul");
        testAddress.setStreet("University Ave");
        testAddress2.setAddressNumber("5596");
        testAddress2.setStreet02("University Ave");

        System.out.println("TestAddress (no street number or street 2): " + testAddress.getFullAddress());
        System.out.println("TestAddress2 (no city or street 1): " + testAddress2.getFullAddress());
        System.out.println("Blank Address: " + blankAddress.getFullAddress());

        testAddress2.setStreet("West");

        System.out.println("TestAddress2 (no city, street 1 added): " + testAddress2.getFullAddress());
    }

    private static void LessonMethods() {
        //notes:    method signature (or declaration)
        /*
            <access modifier> <instance/static> <return data type> <method name> (<data type> <param name>, <data type> <param name>, ...) { body }
            private            static             void              LessonMethods   ( nothing passed in)

            public              --                int               getPersonId     ( nothing passed in )

            public              --                void              setPersonId     ( int       PersonId)
         */

        //notes:    constructors are special methods with same name as class

//        Employee constructorEmployee = new Employee("Joshua", "McCann");
//        System.out.println(constructorEmployee.getFirstName() + " " + constructorEmployee.getLastName());
//
//        Employee const2Employee = new Employee("Simmer");
//        System.out.println(const2Employee.getLastName());

        Employee employeeJames = new Employee("James", "McRoberts");
        System.out.println(employeeJames.getFullName());
    }

    private static void LessonInheritance(){
        //notes:    Create Employee

        Employee employeeBip = new Employee();
        employeeBip.setFirstName("Bipin");
        employeeBip.setLastName("Butala");
        employeeBip.setId(3);

        System.out.println(employeeBip.getId() + ": " + employeeBip.getFirstName() + " " + employeeBip.getLastName());

        Person personBip = new Person();

        personBip.setFirstName("Bipin");
        personBip.setLastName("Butala");
        personBip.setId(3);

        System.out.println(personBip.getId() + ": " + personBip.getFirstName() + " " + personBip.getLastName());
    }

    private static void LessonClassObjects(){
        //notes:    instantiating a new object
        Person myFirstPerson = new Person();
        myFirstPerson.setFirstName("Bipin");
        myFirstPerson.setLastName("Butala");
        myFirstPerson.setTitle("Mr.");

        Person mySecondPerson = new Person();
        mySecondPerson.setFirstName("Sarah");
        mySecondPerson.setLastName("Butala");
        mySecondPerson.setTitle("Mrs.");

        System.out.print(myFirstPerson.getTitle());
        System.out.print(" ");
        System.out.print(myFirstPerson.getFirstName());
        System.out.print(" ");
        System.out.println(myFirstPerson.getLastName());

        System.out.print(mySecondPerson.getTitle());
        System.out.print(" ");
        System.out.print(mySecondPerson.getFirstName());
        System.out.print(" ");
        System.out.println(mySecondPerson.getLastName());

        //Notes:    Setting value for super (inherited) class
        myFirstPerson.setId(3);
        mySecondPerson.setId(4);

        System.out.print(myFirstPerson.getId());
        System.out.print(" ");
        System.out.print(myFirstPerson.getTitle());
        System.out.print(" ");
        System.out.print(myFirstPerson.getFirstName());
        System.out.print(" ");
        System.out.println(myFirstPerson.getLastName());

        System.out.print(mySecondPerson.getId());
        System.out.print(" ");
        System.out.print(mySecondPerson.getTitle());
        System.out.print(" ");
        System.out.print(mySecondPerson.getFirstName());
        System.out.print(" ");
        System.out.println(mySecondPerson.getLastName());
    }

    private static void LessonFizzBuzzLAB(){
        for(int i=1; i<=100; i++){
            if(i%3==0||i%5==0) {
                if (i%3 == 0) {
                    System.out.print("Fizz");
                }
                if (i%5 == 0) {
                    System.out.print("Buzz");
                }
                System.out.print("\n");
            }
            else{
                System.out.println(Integer.toString(i));
            }
        }
    }

    private static void LessonCalculatorLAB() throws Exception{
        int num1, num2;
        double total;

        num1 = PromptForNumber("Input first number (0-9): ");
        num2 = PromptForNumber("Input second number (0-9): ");
        total = PromptForOperator("Choose an operator (+, -, /, *): ", num1, num2);

        System.out.println("Total: " + Double.toString(total));
    }

    private static int PromptForNumber(String s) throws Exception {
        Scanner inputScanner = new Scanner(System.in);
        int number = 12;
        boolean whileSwitch = false;

        while(!whileSwitch) {
            System.out.print(s);
            try {
                number = inputScanner.nextInt();
            } catch (Exception e) {
                throw new Exception("Exception thrown from PromptForNumber", e);
            }
            finally {
                if (number > 9 || number < 0) {
                    System.out.println("INVALID");
                } else {
                    whileSwitch = true;
                }
            }
        }
        return number;
    }

    private static double PromptForOperator(String s, int num1, int num2) throws Exception {
        Scanner inputScanner = new Scanner(System.in);
        double total = 0;
        char operator = 0;
        boolean whileSwitch = false;

        while(!whileSwitch) {
            System.out.print(s);
            try {
                operator = inputScanner.next().charAt(0);

            } catch (Exception e) {
                throw new Exception("Exception thrown from PromptForNumber", e);
            }
            switch(operator){
                case '+':
                    total = num1+num2;
                    whileSwitch = true;
                    break;
                case '-':
                    total = num1-num2;
                    whileSwitch = true;
                    break;
                case '*':
                    total = num1*num2;
                    whileSwitch = true;
                    break;
                case '/':
                    total = num1/num2;
                    whileSwitch = true;
                    break;
                default:
                    System.out.println("INVALID");
                    break;
            }
        }
        return total;
    }

    private static void LessonExceptions() throws Exception{
        //todo:    simple unhandled exception
//        String firstName = "Joshua";
//        int x = Integer.parseInt(firstName);
//
//        System.out.print("Integer value: ");
//        System.out.println(x);

        //todo:     try catch block
//        String firstName = "Joshua";
//        try {
//            int x = Integer.parseInt(firstName);
//
//            System.out.print("Integer value: ");
//            System.out.println(x);
//        }
//        catch (NumberFormatException e){
//            System.out.println("Exception:  Invalid Number");
//        }

        //todo:    try catch with multiple catch blocks
//        String firstName = "Joshua";
//        try {
//            int x = Integer.parseInt(firstName);
//
//            System.out.print("Integer value: ");
//            System.out.println(x);
//        }
//        catch (NumberFormatException e){
//            System.out.println("Exception:  Number Format Error.");
//        }
//        catch (IllegalArgumentException e){
//            System.out.println("Exception:  String was null or empty");
//        }
//        catch (Exception e){
//            System.out.println("Exception:  Generic Exception");
//        }
        //todo: try catch with multiple tach and finally block
//        String firstName = "Joshua";
//        try {
//            int x = Integer.parseInt(firstName);
//
//            System.out.print("Integer value: ");
//            System.out.println(x);
//        }
//        catch (NumberFormatException e){
//            System.out.println("Exception:  Number Format Error.");
//        }
//        catch (IllegalArgumentException e){
//            System.out.println("Exception:  String was null or empty");
//        }
//        catch (Exception e){
//            System.out.println("Exception:  Generic Exception");
//        }
//        finally{
//            System.out.println("Program has been completed regardless of exceptions.");
//        }
        //todo:     exception object
//      //notes:    base exception class
//      String firstName = "Joshua";
//      try {
//          int x = Integer.parseInt(firstName);
//
//          System.out.print("Integer value: ");
//          System.out.println(x);
//      }
//      catch (IllegalArgumentException e){
//          System.out.println("Exception: " + e.toString());
//      }
//      catch (NullPointerException e){
//          System.out.println("Exception: " + e.toString());
//      }
//      catch (Exception e){
//          System.out.println("Exception: " + e.toString());
//      }
//      finally{
//          System.out.println("Program has been completed regardless of exceptions.");
//      }

        //todo:     throwing an exception
        String firstName = "Joshua";
        try {
            int x = Integer.parseInt(firstName);

            System.out.print("Integer value: ");
            System.out.println(x);
        }
        catch (Exception e){
            throw new Exception("A custom excpetion from LessonExceptions method.", e);
        }
    }


    private static void LessonFlowControl(){
        //notes:    if/else
        String name = "danny";

        if(name.equals("joshuaa")){
            System.out.println("Correct first name.");
            System.out.println("Another line");
        }
        else {
            System.out.println("Incorrect first name.");

            if(name.length() > 10){
                System.out.println("very long first name.");
            }
            else if(name.length() > 5){
                System.out.println("long first name.");
            }
            else{
                System.out.println("short first name.");
            }
        }

        //notes:    case switch
        switch(name){
            case "joshua":
                System.out.println("first name is joshua");
                break;
            case "dan":
            case "danny":
            case "daniel":
                System.out.println("First name is dan");
                break;
            default:
                System.out.println("some other first name");
                break;
        }
    }

    private static void LessonFundamentalsLAB(){
        //2.    A variable is a piece of memory allocated to store data values.
        int qTwoInt;
        Scanner inputScanner = new Scanner(System.in);
        
        System.out.println("Please enter a number between 1 and 10: ");
        qTwoInt = inputScanner.nextInt();
        System.out.println("1.75 added to " + qTwoInt + " is " + Double.toString(1.75 + qTwoInt));

        //3.    The eight data types are boolean, byte, char, short, int, long, float, and double

        boolean qThreeBoolean = false;
        byte qThreeByte = 0;
        char qThreeChar = ' ';
        short qThreeShort = 0;
        int qThreeInt = 0;
        long qThreeLong = 0;
        float qThreeFloat = 0;
        double qThreeDouble = 0;

        System.out.println("\nBoolean\nDefault: " + qThreeBoolean);
        System.out.println("\nByte\nDefault: " + Byte.toString(qThreeByte) + "\nMax: " + Byte.toString(Byte.MAX_VALUE) + "\nMin: " + Byte.toString(Byte.MIN_VALUE));
        System.out.println("\nChar\nDefault: " + Character.toString(qThreeChar) + "\nMax: " + Character.toString(Character.MAX_VALUE) + "\nMin: " + Character.toString(Character.MIN_VALUE));
        System.out.println("\nShort\nDefault: " + Short.toString(qThreeShort) + "\nMax: " + Short.toString(Short.MAX_VALUE) + "\nMin: " + Short.toString(Short.MIN_VALUE));
        System.out.println("\nInt\nDefault: " + Integer.toString(qThreeInt) + "\nMax: " + Integer.toString(Integer.MAX_VALUE) + "\nMin: " + Integer.toString(Integer.MIN_VALUE));
        System.out.println("\nLong\nDefault: " + Long.toString(qThreeLong) + "\nMax: " + Long.toString(Long.MAX_VALUE) + "\nMin: " + Long.toString(Long.MIN_VALUE));
        System.out.println("\nFloat\nDefault: " + Float.toString(qThreeFloat) + "\nMax: " + Float.toString(Float.MAX_VALUE) + "\nMin: " + Float.toString(Float.MIN_VALUE));
        System.out.println("\nDouble\nDefault: " + Double.toString(qThreeDouble) + "\nMax: " + Double.toString(Double.MAX_VALUE) + "\nMin: " + Double.toString(Double.MIN_VALUE));

        //4.    Strings are actually character arrays.
        String qFourString = new String();
        char qFourChar, qFourTestChar;
        char qFourCharArray[];

        //Set variables for testing purposes
        qFourString = "Random Letters";
        qFourChar = qFourString.charAt(2);
        qFourTestChar = 'n';
        qFourCharArray = qFourString.toCharArray();

        //Compare outputs to see if they are the same
        if(qFourChar == qFourTestChar) System.out.println("The characters are the same");
        else System.out.println("The characters are not the same");

        //Print out all characters in order to both the array and string
        for(int i=0; i < qFourString.length(); i++){
            System.out.print("\nString: Character at location " + i + ": " + qFourString.charAt(i));
            System.out.print("\nArray:  Character at location " + i + ": " + qFourCharArray[i]);

        }

        /*5.    A list is a sub-interface for the class Collection and receives inheritance from the Collection.
                Collection is expecting a group of class objects.  Int and char are primitive data types and not objects.
                As a result, a list can be composed of the wrapper for the primitive data types to be treated as an object,
                but a list cannot be created for primitive data types.*/
        List<String> qFiveList = new ArrayList<String>();

        System.out.println("\n");
        for(int i = 0; i<10; i++){
            qFiveList.add("String for question five in place " + Integer.toString(i + 1));
        }

        for(String printString : qFiveList){
            System.out.println(printString);
        }

        //6.  Lottery
        String gameName = new String("Gopher 5"), winningNumbers = new String("09 29 34 38 45");
        int costToPlay = 2, jackpot = 510000;
        DateFormat stringFormater = new SimpleDateFormat("MM/dd/yyyy");
        Date drawingDate = new Date();

        try {
            drawingDate = stringFormater.parse("06/19/2017");
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("\nGame: " + gameName);
        System.out.println("Cost to Play: $" + Integer.toString(costToPlay));
        System.out.println("Drawing Date: " + stringFormater.format(drawingDate));
        System.out.println("Winning Numbers: " + winningNumbers);
        System.out.println("Prize: $" + Integer.toString(jackpot));



    }

    private static void LessonLists(){
        //notes:    collections / Lists
        List<String> myStringCollection = new ArrayList<String>();

        myStringCollection.add("1st String");
        myStringCollection.add("2nd String");
        myStringCollection.add("3rd String");
        myStringCollection.add("4th String");
        myStringCollection.add("5th String");

        System.out.println(myStringCollection);

        for(String singleString : myStringCollection){
            System.out.println(singleString);
        }

        List<Integer> myIntCollection = new ArrayList<Integer>();

        myIntCollection.add(10);
        myIntCollection.add(20);
        myIntCollection.add(30);
        myIntCollection.add(40);
        myIntCollection.add(50);

        System.out.println(myIntCollection);

        for(int singleInt : myIntCollection){
            System.out.println(singleInt);
        }

        //Notes:     arrays
        String[] myStringArray = new String[5];

        myStringArray[0] = "1st";
        myStringArray[1] = "2nd";
        myStringArray[2] = "3rd";
        myStringArray[3] = "4th";
        myStringArray[4] = "5th";

        System.out.println(myStringArray);

        for(String singleString : myStringArray){
            System.out.println(singleString);
        }


    }

    private static void LessonStrings(){

        //Notes:    strings have a value or not.
        String firstString = "";
        //firstString = "Something";
        firstString = null;

        if(firstString == null || firstString.isEmpty()){
            System.out.println("String is empty");
        } else {
            System.out.println("String has a value");
        }

        //Notes:    immutable - unable to be changed...
        for(int x = 0; x<=100; x++){
            firstString = "new value: " + Integer.toString(x);
            System.out.println(firstString);
        }

        //Notes:    StringBuilder()
        StringBuilder myStringBuilder = new StringBuilder();

        for(int x = 0; x<=100; x++){
            myStringBuilder.append("New value with string builder: ")
                            .append(Integer.toString(x))
                            .append("\n");
        }

        System.out.println(myStringBuilder);

        //Notes:    searching strings (indexOf, lastIndexOf)
        String myName = "Joshua McCann";

        int indexOf = myName.indexOf("a");
        System.out.println(indexOf);

        int lastIndexOf = myName.lastIndexOf("a");
        System.out.println(lastIndexOf);


        //Notes:    enumerating a string
        String largeString = "This is a longer string than the previous one.";
        for(char c : largeString.toCharArray()){
            System.out.println(c);
        }

        //Notes:    substring(beginning index) or substring(beginning index, and end index)
        String partOfLargerString = largeString.substring(largeString.indexOf("p"), largeString.lastIndexOf("s")+1);
        System.out.println(partOfLargerString);
    }

    private static void LessonDataTypes(){
        //notes:    primitive data types
        //          int (integer number)
        //          float (floating point number)
        //          double (larger number)
        //          boolean (true or false)
        //          char (character)

        //notes:    common data types
        boolean myBool = true;
        int myInt = 4;
        String myString = "Words Go Here";
        Date myDate = new Date();

        System.out.println(myBool);
        System.out.println(myInt);
        System.out.println(myString);
        System.out.println(myDate);

        //Notes:    parsing / converting data types
        //Notes:    String -> int
        String numberString = "341";
        int intFromString = Integer.parseInt(numberString);

        System.out.println(intFromString);

        //Notes:    int -> String
        int i = 312;
        String stringFromInt = Integer.toString(i);

        System.out.println(stringFromInt);


        //Notes:    date -> String
        String stringFromDate = myDate.toString();
        System.out.println(stringFromDate);
    }

    private static void LessonVariables(){


        //Notes:    declare variable and set the string
        String lastName = "McCann", firstName = "Joshua";

        //Notes:    setting value of variables
        //lastName = "McCann";

        //Notes:    output to the console
        System.out.println (firstName + " " + lastName);

        //Notes:    create a Scanner object
        Scanner reader = new Scanner(System.in);

        //Notes:    Prompt the user
        System.out.print("Enter your name: ");

        //Notes:    Read input from the user and store in a variable
        String input = reader.nextLine();

        //Notes:    Print the value back to the screen
        System.out.println("Your Name is: " + input);
    }
}

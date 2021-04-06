package Model;

public class Client {
    private int idclient;
    private String name;
    private String location;

    /**
     * This is the client class constructor
     * @param ID
     * @param name
     * @param location
     */

    public Client(int ID, String name, String location)
    {
        this.idclient = ID;
        this.name = name;
        this.location = location;
    }

    /**
     * ID setter
     * @param ID
     */

    public void setIdclient (int ID) {this.idclient = ID;}

    /**
     * ID getter
     * @return
     */

    public int getIdclient() {return this.idclient;}

    /**
     * Name setter
     * @param name
     */

    public void setName(String name) {this.name = name;}

    /**
     * Name getter
     * @return
     */

    public String getName() {return this.name;}

    /**
     * Location setter
     * @param location
     */

    public void setLocation(String location) {this.location = location;}

    /**
     * Location getter
     * @return
     */

    public String getLocation() {return this.location;}
}

package app.edufindermadrid.entities;


import com.google.gson.annotations.SerializedName;

public class EduCenter {
    @SerializedName("@id")
    private String id;
    @SerializedName("@type")
    private String type;
    private String tittle;
    private String relation;
    private Address address;
    private Location location;
    private Organization organization;

   // Constructor test
    public EduCenter(String id, String type, String tittle) {
        this.id = id;
        this.type = type;
        this.tittle = tittle;
    }

    // TODO: Getters and Setters

    public void setId(String id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getTittle() {
        return tittle;
    }

    public String getRelation() {
        return relation;
    }

    public Address getAddress() {
        return address;
    }

    public Location getLocation() {
        return location;
    }

    public Organization getOrganization() {
        return organization;
    }
}

package com.example.app;

public class adventureAdd {


    private String ImageID;
    private String adventureId;
    private String adventureName;
    private  String adventureDis;
    private  String adventureLoc;

    public adventureAdd(){

    }

    public String getAdventureLoc() {
        return adventureLoc;
    }

    public void setAdventureLoc(String adventureLoc) {
        this.adventureLoc = adventureLoc;
    }

    public String getImageID() {
        return ImageID;
    }

    public void setImageID(String imageID) {
        ImageID = imageID;
    }

    public String getAdventureId() {
        return adventureId;
    }

    public void setAdventureId(String adventureId) {
        this.adventureId = adventureId;
    }

    public String getAdventureName() {
        return adventureName;
    }

    public void setAdventureName(String adventureName) {
        this.adventureName = adventureName;
    }

    public String getAdventureDis() {
        return adventureDis;
    }

    public void setAdventureDis(String adventureDis) {
        this.adventureDis = adventureDis;
    }
}

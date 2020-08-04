package com.example.fisiovideos.video;

public class Video {
    private String id;
    private String name;
    private String description;

    public Video(){

    }

    public Video(String name, String description){
        this.name = name;
        this.description = description;
    }

    public Video(String id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // equals and hasCode unicamente con el ID
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Video comida = (Video) o;

        return id != null ? id.equals(comida.id) : comida.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return name;
    }
}

package org.example;

import lombok.Data;

@Data
public class Client {
    private long id;
    private String name;

    public Client(String name){
      this.name = name;
    }

    public Client(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

package com.kauabiscotto.HotelSystem;

public class Guest {

    private String name;
    private String cpf;
    private String cellphone;
    private String email;


    public Guest(String name, String cpf, String cellphone, String email) {
        this.name = name;
        this.cpf = cpf;
        this.cellphone = cellphone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public String getCellphone() {
        return cellphone;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void displayInfo() {
        System.out.println("=== Guest Information ===");
        System.out.println("Name: " + getName());
        System.out.println("CPF: " + getCpf());
        System.out.println("Cellphone: " + getCellphone());
        System.out.println("Email: " + getEmail());
        System.out.println("=========================");
    }
}

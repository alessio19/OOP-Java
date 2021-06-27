/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employee;

/**
 *
 * @author Zenkh
 */
public class Employee {
    private int id;
    private String mail;
    private String password;
    private String name;
    private String lastName;

    public Employee(int id, String mail, String password, String name, String lastName) {
        this.id = id;
        this.mail = mail;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
    }
}

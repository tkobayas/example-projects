package org.example;

import lombok.Data;

@Data
public class User {

    private int id;
    private String name;
    private String email;

    public static void main(String[] args) {
        User user = new User();
        user.setId(1);
        user.setName("John");
        user.setEmail("john@example.org");

        System.out.println(user);
        // --> User(id=1, name=oinume, email=oinume@example.com)
    }
}
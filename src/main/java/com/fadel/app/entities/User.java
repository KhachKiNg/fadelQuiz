package com.fadel.app.entities;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class User {
	private int id;
    private String username;
    private String password;
    private String email;
}
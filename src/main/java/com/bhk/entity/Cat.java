package com.bhk.entity;

import javax.persistence.Entity;

@Entity
public class Cat extends Animal {

	@Override
	public String makeNoise() {
		// TODO Auto-generated method stub
		return "meow meow";
	}

}

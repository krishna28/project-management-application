package com.krishna.example.auth

class Role {

	String authority

	static mapping = {
        table 'table_role'
		cache true
	}

	static constraints = {
		authority blank: false, unique: true
	}
}

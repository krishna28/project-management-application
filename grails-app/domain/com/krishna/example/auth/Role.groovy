package com.krishna.example.auth

class Role {

	String authority

	static mapping = {
		cache true
	}

	static constraints = {
        table 'SEC_ROLE'
		authority blank: false, unique: true
	}
}

package com.krishna.example.auth

class AuthToken {

    String username
    String token

    static constraints = {
    }
    static mapping = {
        id name: 'token_id'
    }
}

package project.management.example

import com.krishna.example.auth.User

class Task {

    String description
    static belongsTo = [project:Project,user:User]
    static hasMany = [comments:Comment]

    static constraints = {
    }

}

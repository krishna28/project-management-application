package project.management.example

class Task {

    String description
    static belongsTo = [project:Project]
    static hasMany = [comments:Comment]
    static constraints = {
    }

}

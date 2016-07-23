package project.management.example

class Project {

    String projectName
    String description
//    Date expectedStartDate
//    Date expectedEndDate
//    Date actualStartDate
//    Date actualCompletionDate

    static hasMany = [tasks:Task]

    static constraints = {
        projectName(blank: false,nullable: false,unique: true)
    }
}

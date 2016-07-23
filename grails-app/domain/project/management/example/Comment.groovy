package project.management.example

class Comment {

   String comment
   static belongsTo = [task:Task]

    static constraints = {
    }
}




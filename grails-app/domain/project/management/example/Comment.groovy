package project.management.example

import com.krishna.example.auth.User

class Comment {

   User user
   String commentNote
   static belongsTo = [task:Task,project:Project]

    static constraints = {
        user(nullable: false, blank:false)
        commentNote(nullable: false,blank:false)
        task(nullable: false,blank:false)
    }
    static mapping ={
        table 'table_comment'
        id name: 'comment_id'
    }
}




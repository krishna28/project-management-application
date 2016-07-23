package project.management.example

import com.krishna.example.auth.User
import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class UserTask implements Serializable {

    Task task
    User user


    static UserTask create(User user, Task task, boolean flush = false) {
        def instance = new UserTask(user: user, task: task)
        instance.save(flush: flush, insert: true)
        instance
    }

    static constraints = {
    }

    static mapping = {
        id composite: ['task', 'user']
        version false
    }
}

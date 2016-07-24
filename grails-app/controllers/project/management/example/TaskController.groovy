package project.management.example

import grails.plugin.springsecurity.annotation.Secured
import grails.rest.RestfulController

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TaskController extends RestfulController {
//    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    static responseFormats = ['json', 'xml']
    TaskController(){
        super(TaskController)
    }

    @Secured(['ROLE_USER','ROLE_MANAGER'])
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Task.list(params), model: [taskInstanceCount: Task.count()]
    }
}

package project.management.example

import com.krishna.example.auth.User
import grails.plugin.springsecurity.annotation.Secured
import grails.rest.RestfulController

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = false)
@Secured(['IS_AUTHENTICATED_FULLY'])
class TaskController extends RestfulController {
//    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    static responseFormats = ['json', 'xml']
    TaskController(){
        super(Task)
    }

    @Secured(['ROLE_USER','ROLE_MANAGER'])
    def index(Integer max) {
        def responseObject = [:]
        params.max = params?.maxResultSet?:Math.min(max ?: 10, 100)
        def projectId = params.projectId
        responseObject['taskList'] = Task.where {
            project.id == projectId
        }.list(params)
        responseObject['taskCount'] = Task.count();
        respond responseObject

    }


    @Secured(['ROLE_MANAGER','ROLE_USER'])
    def save(){
        def responseObject = [:]
        def task =  new Task();
        def user = User.get(params.userId);
        def project = Project.get(params.projectId);
        task.description = params.description;
        user.addToTasks(task);
        project.addToTasks(task);
        task.save(flush: true,failOnError: true)
        if(task.save()){
            responseObject['status'] = 200;
            responseObject['message'] = "ok";
            responseObject['task'] = task;
        }
        respond responseObject;

    }

    @Secured(['ROLE_MANAGER'])
    def create() {
        respond new Task(params)
    }


    @Secured(['ROLE_USER','ROLE_MANAGER'])
    def show(){
        def id = params.id;
        def taskInstance = Task.get(id);
        respond taskInstance
    }

    @Secured(['ROLE_MANAGER','ROLE_USER'])
    def edit(){
        def id = params.id;
        def taskInstance = Task.get(id);
        bindData(taskInstance, params, [exclude: ['id']])
        respond taskInstance
    }


    @Secured(['ROLE_MANAGER','ROLE_USER'])
    def update(){
        def id = params.id;
        def taskInstance = Task.get(id);
        bindData(taskInstance, params, [exclude: ['id']])
        respond taskInstance
    }


    @Secured(['ROLE_MANAGER'])
    def delete(){
        def id = params.id;
        def taskInstance = Task.get(id);
        taskInstance.delete()
    }



}

package project.management.example

import grails.rest.RestfulController

import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Transactional(readOnly = false)
@Secured(['IS_AUTHENTICATED_FULLY'])
class ProjectController extends RestfulController {

//    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    static responseFormats = ['json', 'xml']
    ProjectController(){
        super(Project)
    }

    @Secured(['ROLE_USER','ROLE_MANAGER'])
    def index(Integer max) {
        def responseObject = [:]
        params.max = params?.maxResultSet?:Math.min(max ?: 10, 100)
        responseObject['projectList'] = Project.list(params);
        responseObject['projectCount'] = Project.count();
        respond responseObject
    }

    @Secured(['ROLE_MANAGER'])
    def create() {
        respond new Project(params)
    }
    @Secured(['ROLE_MANAGER'])
    def save(){
        def responseObject = [:]
        def project =  new Project();
        project.projectName = params.projectName
        project.description = params.description
        if(project.save()){
            responseObject['status'] = 200;
            responseObject['message'] = "ok";
        }
        respond responseObject

    }
    @Secured(['ROLE_USER','ROLE_MANAGER'])
    def show(){
        def id = params.id;
        def projectInstance = Project.get(id);
        respond projectInstance
    }

    @Secured(['ROLE_MANAGER'])
    def edit(){
        def id = params.id;
        def projectInstance = Project.get(id);
        bindData(projectInstance, params, [exclude: ['id']])
        respond projectInstance
    }

    @Secured(['ROLE_MANAGER'])
    def update(){
        def id = params.id;
        def projectInstance = Project.get(id);
        bindData(projectInstance, params, [exclude: ['id']])
        respond projectInstance
    }


    @Secured(['ROLE_MANAGER'])
    def delete(){
        def id = params.id;
        def projectInstance = Project.get(id);
        projectInstance.delete()
    }


}

package project.management.example

import grails.rest.RestfulController

import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Transactional(readOnly = false)
class ProjectController extends RestfulController {

//    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    static responseFormats = ['json', 'xml']
    ProjectController(){
        super(ProjectController)
    }

    @Secured(['ROLE_MANAGER'])
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Project.list(params), model: [projectInstanceCount: Project.count()]
    }

    def show(Project projectInstance) {
        respond projectInstance
    }

    def create() {
        respond new Project(params)
    }
    @Secured(['ROLE_MANAGER'])
    def save(){

        def project =  new Project();
        project.projectName = params.projectName
        project.description = params.description
        project.save()
        respond project


    }


}

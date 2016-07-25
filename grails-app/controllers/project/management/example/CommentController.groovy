package project.management.example

import com.krishna.example.auth.User
import grails.plugin.springsecurity.annotation.Secured
import grails.rest.RestfulController
import jline.internal.Log

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = false)
@Secured(['IS_AUTHENTICATED_FULLY'])
class CommentController extends RestfulController  {

//    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    static responseFormats = ['json', 'xml']
    CommentController(){
        super(Comment)
    }

    @Secured(['ROLE_USER','ROLE_MANAGER'])
    def index(Integer max) {
        def responseObject = [:]
        params.max = params?.maxResultSet?:Math.min(max ?: 10, 100)
        def projectId = params.projectId
        def taskId = params.taskId
        responseObject['commentList'] = Comment.where {
            task.id == taskId && project.id == projectId
        }.list(params)
        responseObject['commentCount'] = Comment.count();
        respond responseObject;
    }

    @Secured(['ROLE_MANAGER','ROLE_USER'])
    def save(){
        def responseObject = [:]
        def comment =  new Comment();
        def taskInstance = Task.get(params.taskId);

        //we can inject spring security here and double check if logged in user is posting the commment
        def user = User.findByUsername(params.userId);
        def projectInstance = Project.get(params.projectId);
        comment.commentNote = params.commentNote;
        comment.project = projectInstance;
        comment.user = user;
        taskInstance.addToComments(comment);
        taskInstance.save(flush: true,failOnError: true);
        if(comment.save()){
            responseObject['status'] = 200;
            responseObject['message'] = "ok";
            responseObject['comment'] = comment;
        }
        respond responseObject

    }

    @Secured(['ROLE_MANAGER'])
    def create() {
        respond new Comment(params)
    }


    @Secured(['ROLE_USER','ROLE_MANAGER'])
    def show(){
        def id = params.id;
        def commentInstance = Comment.get(id);
        respond commentInstance
    }

    @Secured(['ROLE_MANAGER','ROLE_USER'])
    def edit(){
        def id = params.id;
        def commentInstance = Comment.get(id);
        bindData(commentInstance, params, [exclude: ['id']])
        respond commentInstance
    }


    @Secured(['ROLE_MANAGER','ROLE_USER'])
    def update(){
        def id = params.id;
        def commentInstance = Comment.get(id);
        bindData(commentInstance, params, [exclude: ['id']])
        commentInstance.save(flush:true);
        respond commentInstance
    }


    @Secured(['ROLE_MANAGER'])
    def delete(){
        try {
            def responseObject = [:]
            def id = params.id;
            def commentInstance = Comment.get(id);
            commentInstance.delete()
            responseObject['status'] = 200;
            responseObject['message'] = "ok";
            respond commentInstance
        }catch(Exception ex){
          Log.error(ex)
        }
    }

}

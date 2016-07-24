package project.management

import com.krishna.example.auth.Role
import com.krishna.example.auth.User
import com.krishna.example.auth.UserRole
import grails.plugin.springsecurity.annotation.Secured
import grails.rest.RestfulController
import org.springframework.transaction.annotation.Transactional

class RegisterController extends RestfulController {
    static responseFormats = ['json', 'xml']
    def springSecurityService

    RegisterController() {
        super(RegisterController)
    }

    @Override
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond User.list(params), model: [userInstanceCount: User.count()]
    }

    @Override
    @Transactional
    def save() {
        try {
            def saltString = getGrailsApplication().config.custom.security.saltString
            def responseObject = [:]
            String encodedPassword = springSecurityService.encodePassword(params.password, saltString)
            def user = new User(password: encodedPassword,
                    accountLocked: false,
                    enabled: true,
                    accountExpired: false,
                    passwordExpired: false,
                    username: params.username,
            )
            user.save(failOnError: true)
            def role = new UserRole(user: user, role: Role.findWhere(authority: 'ROLE_USER')).save(failOnError: true);
            if (user.save()) {

                responseObject['status'] = 200;
                responseObject['message'] = "ok";
                respond responseObject
            }
        } catch (Exception e) {
            println e;
        }

    }

    @Override
    def delete() {
        def userInstance = User.findById(params.id);
        def instances = UserRole.findByUser(userInstance);

        instances.each { user ->
            user.delete()
        }

        if (userInstance == null) {
            notFound()
            return
        }

        userInstance.delete flush: true
    }

    @Override
    def show() {
        respond User.get(params.id)
    }
}

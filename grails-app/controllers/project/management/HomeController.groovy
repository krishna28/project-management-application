package project.management

import com.krishna.example.auth.Role
import com.krishna.example.auth.User
import com.krishna.example.auth.UserRole
import grails.rest.RestfulController

class HomeController extends RestfulController{
    static responseFormats = ['json', 'xml']
    def springSecurityService
    HomeController() {
        super(HomeController)
    }
    def index() {


    }

    def save(){
        try {
            def saltString = getGrailsApplication().config.custom.security.saltString
            println saltString
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
        }catch(Exception e){
            println e;
        }

    }


}

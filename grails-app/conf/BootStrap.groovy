import com.krishna.example.auth.Role
import com.krishna.example.auth.User
import com.krishna.example.auth.UserRole
import grails.plugin.springsecurity.authentication.dao.NullSaltSource
import grails.plugin.heroku.PostgresqlServiceInfo;
class BootStrap {

    def saltSource
    def springSecurityService
    def grailsApplication

    def init = { servletContext ->

        String DATABASE_URL = System.getenv('DATABASE_URL')
        if (DATABASE_URL) {
            try {
                PostgresqlServiceInfo info = new PostgresqlServiceInfo()
                println "\nPostgreSQL service ($DATABASE_URL): url='$info.url', " +
                        "user='$info.username', password='$info.password'\n"
            }
            catch (e) {
                println "Error occurred parsing DATABASE_URL: $e.message"
            }
        }


        def saltString = grailsApplication.config.custom.security.saltString

        if (Role.list().size() == 0) {
            new Role(authority: "ROLE_MANAGER").save()
            new Role(authority: "ROLE_USER").save()
        }

        if (User.list().size() == 0) {
            String salt = saltSource instanceof NullSaltSource ? null : saltString
            // spring security use username as salt for password encryption
            String encodedPassword = springSecurityService.encodePassword('root', salt)
            def manager = new User(password: encodedPassword,
                    accountLocked: false,
                    enabled: true,
                    accountExpired: false,
                    passwordExpired: false,
                    username: "admin",
            )
            def user = new User(password: encodedPassword,
                    accountLocked: false,
                    enabled: true,
                    accountExpired: false,
                    passwordExpired: false,
                    username: "test",
            )

            manager.save(failOnError: true)
            user.save(failOnError: true)

            def role1 = new UserRole(user: manager, role: Role.findWhere(authority: 'ROLE_MANAGER')).save(failOnError: true);
            def role2 = new UserRole(user: user, role: Role.findWhere(authority: 'ROLE_USER')).save(failOnError: true);


        }
        def destroy = {
        }
    }
}

// Place your Spring DSL code here
import grails.rest.render.json.JsonCollectionRenderer
import grails.rest.render.json.JsonRenderer
import com.krishna.example.auth.User
import project.management.example.Project

beans = {

    userRender(JsonRenderer, User) {
        excludes = ['password']
    }

    usersRender(JsonCollectionRenderer, User) {
        excludes = ['password']
    }

    userRender(JsonRenderer, Project) {
        excludes = ['class']
    }

    usersRender(JsonCollectionRenderer, Project) {
        excludes = ['class']
    }
}

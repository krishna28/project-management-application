class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
        "/api/user"(resources:'home')
        "/register"(resources:'register')
        "/api/project"(resources: 'project') {
            "/task"(resources: 'task') {
                "/comment"(resources: 'comment')
            }
        }
        "/"(view:"/welcome")
        "500"(view:'/error')
	}
}

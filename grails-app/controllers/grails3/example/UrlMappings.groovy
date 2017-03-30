package grails3.example

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

//        "/"(view:"/index")\
        "/" (controller: 'home', action: 'index', view:"/index_login")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}

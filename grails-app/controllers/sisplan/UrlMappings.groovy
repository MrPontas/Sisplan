package sisplan

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        //  "/" {
        //     controller = "index"
        // }
        "/"(controller:"index", action: "index")
        // "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}

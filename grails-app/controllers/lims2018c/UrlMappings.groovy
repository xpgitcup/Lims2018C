package lims2018c

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/indexLims")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}

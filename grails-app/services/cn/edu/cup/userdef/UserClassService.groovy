package cn.edu.cup.userdef

import grails.gorm.services.Service

@Service(UserClass)
interface UserClassService {

    UserClass get(Serializable id)

    List<UserClass> list(Map args)

    Long count()

    void delete(Serializable id)

    UserClass save(UserClass userClass)

}
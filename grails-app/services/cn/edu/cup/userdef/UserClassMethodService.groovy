package cn.edu.cup.userdef

import grails.gorm.services.Service

@Service(UserClassMethod)
interface UserClassMethodService {

    UserClassMethod get(Serializable id)

    List<UserClassMethod> list(Map args)

    Long count()

    void delete(Serializable id)

    UserClassMethod save(UserClassMethod userClassMethod)

}
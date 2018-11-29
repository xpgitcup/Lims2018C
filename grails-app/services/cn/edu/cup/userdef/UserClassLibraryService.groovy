package cn.edu.cup.userdef

import grails.gorm.services.Service

@Service(UserClassLibrary)
interface UserClassLibraryService {

    UserClassLibrary get(Serializable id)

    List<UserClassLibrary> list(Map args)

    Long count()

    void delete(Serializable id)

    UserClassLibrary save(UserClassLibrary userClassLibrary)

}
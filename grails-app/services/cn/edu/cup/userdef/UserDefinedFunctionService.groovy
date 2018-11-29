package cn.edu.cup.userdef

import grails.gorm.services.Service

@Service(UserDefinedFunction)
interface UserDefinedFunctionService {

    UserDefinedFunction get(Serializable id)

    List<UserDefinedFunction> list(Map args)

    Long count()

    void delete(Serializable id)

    UserDefinedFunction save(UserDefinedFunction userDefinedFunction)

}
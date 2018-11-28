package cn.edu.cup.system

import grails.gorm.services.Service

@Service(SystemProcedure)
interface SystemProcedureService {

    SystemProcedure get(Serializable id)

    List<SystemProcedure> list(Map args)

    Long count()

    void delete(Serializable id)

    SystemProcedure save(SystemProcedure systemProcedure)

}
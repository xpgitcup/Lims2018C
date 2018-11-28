package cn.edu.cup.system

import grails.gorm.services.Service

@Service(SystemDefaultMenu)
interface SystemDefaultMenuService {

    SystemDefaultMenu get(Serializable id)

    List<SystemDefaultMenu> list(Map args)

    Long count()

    void delete(Serializable id)

    SystemDefaultMenu save(SystemDefaultMenu systemDefaultMenu)

}
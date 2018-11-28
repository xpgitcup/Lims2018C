package cn.edu.cup.system

import grails.gorm.services.Service

@Service(SystemSponser)
interface SystemSponserService {

    SystemSponser get(Serializable id)

    List<SystemSponser> list(Map args)

    Long count()

    void delete(Serializable id)

    SystemSponser save(SystemSponser systemSponser)

}
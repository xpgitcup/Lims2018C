package cn.edu.cup.system

import grails.gorm.services.Service

@Service(SystemTitle)
interface SystemTitleService {

    SystemTitle get(Serializable id)

    List<SystemTitle> list(Map args)

    Long count()

    void delete(Serializable id)

    SystemTitle save(SystemTitle systemTitle)

}
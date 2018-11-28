package cn.edu.cup.system

import grails.gorm.services.Service

@Service(SystemChat)
interface SystemChatService {

    SystemChat get(Serializable id)

    List<SystemChat> list(Map args)

    Long count()

    void delete(Serializable id)

    SystemChat save(SystemChat systemChat)

}
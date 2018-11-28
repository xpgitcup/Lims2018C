package cn.edu.cup.dictionary

import grails.gorm.services.Service

@Service(DataKey)
interface DataKeyService {

    DataKey get(Serializable id)

    List<DataKey> list(Map args)

    Long count()

    void delete(Serializable id)

    DataKey save(DataKey dataKey)

}
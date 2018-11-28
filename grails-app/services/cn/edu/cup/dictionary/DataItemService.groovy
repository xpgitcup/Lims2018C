package cn.edu.cup.dictionary

import grails.gorm.services.Service

@Service(DataItem)
interface DataItemService {

    DataItem get(Serializable id)

    List<DataItem> list(Map args)

    Long count()

    void delete(Serializable id)

    DataItem save(DataItem dataItem)

}
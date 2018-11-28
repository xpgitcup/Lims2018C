package cn.edu.cup.dictionary

import grails.gorm.services.Service

@Service(DataDictionary)
interface DataDictionaryService {

    DataDictionary get(Serializable id)

    List<DataDictionary> list(Map args)

    Long count()

    void delete(Serializable id)

    DataDictionary save(DataDictionary dataDictionary)

}
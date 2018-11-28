package cn.edu.cup.dictionary

import grails.gorm.services.Service

@Service(ChartConfig)
interface ChartConfigService {

    ChartConfig get(Serializable id)

    List<ChartConfig> list(Map args)

    Long count()

    void delete(Serializable id)

    ChartConfig save(ChartConfig chartConfig)

}
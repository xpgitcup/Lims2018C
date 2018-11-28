package cn.edu.cup.system

import grails.gorm.services.Service

@Service(SystemCarousel)
interface SystemCarouselService {

    SystemCarousel get(Serializable id)

    List<SystemCarousel> list(Map args)

    Long count()

    void delete(Serializable id)

    SystemCarousel save(SystemCarousel systemCarousel)

}
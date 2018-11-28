package cn.edu.cup.system

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class SystemCarouselServiceSpec extends Specification {

    SystemCarouselService systemCarouselService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new SystemCarousel(...).save(flush: true, failOnError: true)
        //new SystemCarousel(...).save(flush: true, failOnError: true)
        //SystemCarousel systemCarousel = new SystemCarousel(...).save(flush: true, failOnError: true)
        //new SystemCarousel(...).save(flush: true, failOnError: true)
        //new SystemCarousel(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //systemCarousel.id
    }

    void "test get"() {
        setupData()

        expect:
        systemCarouselService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<SystemCarousel> systemCarouselList = systemCarouselService.list(max: 2, offset: 2)

        then:
        systemCarouselList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        systemCarouselService.count() == 5
    }

    void "test delete"() {
        Long systemCarouselId = setupData()

        expect:
        systemCarouselService.count() == 5

        when:
        systemCarouselService.delete(systemCarouselId)
        sessionFactory.currentSession.flush()

        then:
        systemCarouselService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        SystemCarousel systemCarousel = new SystemCarousel()
        systemCarouselService.save(systemCarousel)

        then:
        systemCarousel.id != null
    }
}

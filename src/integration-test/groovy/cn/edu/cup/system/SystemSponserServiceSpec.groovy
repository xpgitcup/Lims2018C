package cn.edu.cup.system

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class SystemSponserServiceSpec extends Specification {

    SystemSponserService systemSponserService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new SystemSponser(...).save(flush: true, failOnError: true)
        //new SystemSponser(...).save(flush: true, failOnError: true)
        //SystemSponser systemSponser = new SystemSponser(...).save(flush: true, failOnError: true)
        //new SystemSponser(...).save(flush: true, failOnError: true)
        //new SystemSponser(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //systemSponser.id
    }

    void "test get"() {
        setupData()

        expect:
        systemSponserService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<SystemSponser> systemSponserList = systemSponserService.list(max: 2, offset: 2)

        then:
        systemSponserList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        systemSponserService.count() == 5
    }

    void "test delete"() {
        Long systemSponserId = setupData()

        expect:
        systemSponserService.count() == 5

        when:
        systemSponserService.delete(systemSponserId)
        sessionFactory.currentSession.flush()

        then:
        systemSponserService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        SystemSponser systemSponser = new SystemSponser()
        systemSponserService.save(systemSponser)

        then:
        systemSponser.id != null
    }
}

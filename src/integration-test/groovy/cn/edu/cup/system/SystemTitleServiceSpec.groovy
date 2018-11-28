package cn.edu.cup.system

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class SystemTitleServiceSpec extends Specification {

    SystemTitleService systemTitleService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new SystemTitle(...).save(flush: true, failOnError: true)
        //new SystemTitle(...).save(flush: true, failOnError: true)
        //SystemTitle systemTitle = new SystemTitle(...).save(flush: true, failOnError: true)
        //new SystemTitle(...).save(flush: true, failOnError: true)
        //new SystemTitle(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //systemTitle.id
    }

    void "test get"() {
        setupData()

        expect:
        systemTitleService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<SystemTitle> systemTitleList = systemTitleService.list(max: 2, offset: 2)

        then:
        systemTitleList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        systemTitleService.count() == 5
    }

    void "test delete"() {
        Long systemTitleId = setupData()

        expect:
        systemTitleService.count() == 5

        when:
        systemTitleService.delete(systemTitleId)
        sessionFactory.currentSession.flush()

        then:
        systemTitleService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        SystemTitle systemTitle = new SystemTitle()
        systemTitleService.save(systemTitle)

        then:
        systemTitle.id != null
    }
}

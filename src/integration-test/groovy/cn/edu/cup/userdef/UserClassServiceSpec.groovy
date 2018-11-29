package cn.edu.cup.userdef

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class UserClassServiceSpec extends Specification {

    UserClassService userClassService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new UserClass(...).save(flush: true, failOnError: true)
        //new UserClass(...).save(flush: true, failOnError: true)
        //UserClass userClass = new UserClass(...).save(flush: true, failOnError: true)
        //new UserClass(...).save(flush: true, failOnError: true)
        //new UserClass(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //userClass.id
    }

    void "test get"() {
        setupData()

        expect:
        userClassService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<UserClass> userClassList = userClassService.list(max: 2, offset: 2)

        then:
        userClassList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        userClassService.count() == 5
    }

    void "test delete"() {
        Long userClassId = setupData()

        expect:
        userClassService.count() == 5

        when:
        userClassService.delete(userClassId)
        sessionFactory.currentSession.flush()

        then:
        userClassService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        UserClass userClass = new UserClass()
        userClassService.save(userClass)

        then:
        userClass.id != null
    }
}

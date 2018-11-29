package cn.edu.cup.userdef

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class UserClassMethodServiceSpec extends Specification {

    UserClassMethodService userClassMethodService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new UserClassMethod(...).save(flush: true, failOnError: true)
        //new UserClassMethod(...).save(flush: true, failOnError: true)
        //UserClassMethod userClassMethod = new UserClassMethod(...).save(flush: true, failOnError: true)
        //new UserClassMethod(...).save(flush: true, failOnError: true)
        //new UserClassMethod(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //userClassMethod.id
    }

    void "test get"() {
        setupData()

        expect:
        userClassMethodService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<UserClassMethod> userClassMethodList = userClassMethodService.list(max: 2, offset: 2)

        then:
        userClassMethodList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        userClassMethodService.count() == 5
    }

    void "test delete"() {
        Long userClassMethodId = setupData()

        expect:
        userClassMethodService.count() == 5

        when:
        userClassMethodService.delete(userClassMethodId)
        sessionFactory.currentSession.flush()

        then:
        userClassMethodService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        UserClassMethod userClassMethod = new UserClassMethod()
        userClassMethodService.save(userClassMethod)

        then:
        userClassMethod.id != null
    }
}

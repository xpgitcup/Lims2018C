package cn.edu.cup.userdef

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class UserDefinedFunctionServiceSpec extends Specification {

    UserDefinedFunctionService userDefinedFunctionService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new UserDefinedFunction(...).save(flush: true, failOnError: true)
        //new UserDefinedFunction(...).save(flush: true, failOnError: true)
        //UserDefinedFunction userDefinedFunction = new UserDefinedFunction(...).save(flush: true, failOnError: true)
        //new UserDefinedFunction(...).save(flush: true, failOnError: true)
        //new UserDefinedFunction(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //userDefinedFunction.id
    }

    void "test get"() {
        setupData()

        expect:
        userDefinedFunctionService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<UserDefinedFunction> userDefinedFunctionList = userDefinedFunctionService.list(max: 2, offset: 2)

        then:
        userDefinedFunctionList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        userDefinedFunctionService.count() == 5
    }

    void "test delete"() {
        Long userDefinedFunctionId = setupData()

        expect:
        userDefinedFunctionService.count() == 5

        when:
        userDefinedFunctionService.delete(userDefinedFunctionId)
        sessionFactory.currentSession.flush()

        then:
        userDefinedFunctionService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        UserDefinedFunction userDefinedFunction = new UserDefinedFunction()
        userDefinedFunctionService.save(userDefinedFunction)

        then:
        userDefinedFunction.id != null
    }
}

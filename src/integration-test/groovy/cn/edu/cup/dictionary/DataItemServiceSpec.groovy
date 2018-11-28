package cn.edu.cup.dictionary

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class DataItemServiceSpec extends Specification {

    DataItemService dataItemService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new DataItem(...).save(flush: true, failOnError: true)
        //new DataItem(...).save(flush: true, failOnError: true)
        //DataItem dataItem = new DataItem(...).save(flush: true, failOnError: true)
        //new DataItem(...).save(flush: true, failOnError: true)
        //new DataItem(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //dataItem.id
    }

    void "test get"() {
        setupData()

        expect:
        dataItemService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<DataItem> dataItemList = dataItemService.list(max: 2, offset: 2)

        then:
        dataItemList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        dataItemService.count() == 5
    }

    void "test delete"() {
        Long dataItemId = setupData()

        expect:
        dataItemService.count() == 5

        when:
        dataItemService.delete(dataItemId)
        sessionFactory.currentSession.flush()

        then:
        dataItemService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        DataItem dataItem = new DataItem()
        dataItemService.save(dataItem)

        then:
        dataItem.id != null
    }
}

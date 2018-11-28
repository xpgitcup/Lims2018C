package cn.edu.cup.dictionary

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ChartConfigServiceSpec extends Specification {

    ChartConfigService chartConfigService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new ChartConfig(...).save(flush: true, failOnError: true)
        //new ChartConfig(...).save(flush: true, failOnError: true)
        //ChartConfig chartConfig = new ChartConfig(...).save(flush: true, failOnError: true)
        //new ChartConfig(...).save(flush: true, failOnError: true)
        //new ChartConfig(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //chartConfig.id
    }

    void "test get"() {
        setupData()

        expect:
        chartConfigService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<ChartConfig> chartConfigList = chartConfigService.list(max: 2, offset: 2)

        then:
        chartConfigList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        chartConfigService.count() == 5
    }

    void "test delete"() {
        Long chartConfigId = setupData()

        expect:
        chartConfigService.count() == 5

        when:
        chartConfigService.delete(chartConfigId)
        sessionFactory.currentSession.flush()

        then:
        chartConfigService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        ChartConfig chartConfig = new ChartConfig()
        chartConfigService.save(chartConfig)

        then:
        chartConfig.id != null
    }
}

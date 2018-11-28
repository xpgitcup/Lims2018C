package cn.edu.cup.dictionary

class ChartConfig {

    DataKey dataKeyA
    ChartType chartType

    static constraints = {
    }

    String toString() {
        return "${chartType}.${dataKeyA}"
    }
}

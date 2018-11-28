package cn.edu.cup.dictionary

class DataDictionary {

    String name

    static hasMany = [datakeys: DataKey]

    static constraints = {
        name(unique: true)
    }

    static mapping = {
        datakeys sort: 'dataTag', 'id'
    }

    String toString() {
        return "${name}/(${datakeys?.size()})"
    }

    def modelCount() {
        datakeys.countBy { e->
            e.subDataKeys.size() > 0
        }
    }
}

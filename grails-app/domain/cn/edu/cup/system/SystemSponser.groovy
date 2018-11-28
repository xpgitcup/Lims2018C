package cn.edu.cup.system

class SystemSponser {

    String name
    String logo

    static belongsTo = [systemTitle: SystemTitle]

    static constraints = {
    }

    String toString() {
        return "${name}"
    }
}

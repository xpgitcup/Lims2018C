package cn.edu.cup.system

class SystemCarousel {

    String name
    String imageName

    static belongsTo = [systemTitle: SystemTitle]

    static constraints = {
    }

    String toString() {
        return "${name}"
    }
}

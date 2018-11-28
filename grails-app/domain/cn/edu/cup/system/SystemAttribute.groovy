package cn.edu.cup.system

/*
* 重新定义属性，以树形结构来维护
* */
class SystemAttribute {

    String name
    SystemAttribute upAttribute

    static hasMany = [subAttribues: SystemAttribute]

    static mapping = {
        name(sort: "asc")
        subAttribues sort: 'name'  //这是排序的标准做法
    }

    static constraints = {
        name(nullable: false, unique: true)
        upAttribute(nullable: true)
    }

    String toString() {
        return "${name}(${subAttribues?.size()})"
    }

}

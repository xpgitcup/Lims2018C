package cn.edu.cup.system

class SystemTitle {
    
    String applicationTitle //题目
    String applicationLogo  //程序图标Logo
    String applicationLayout

    static hasMany = [systemSponser: SystemSponser, systemCarousel: SystemCarousel]

    static constraints = {
        applicationLayout(nullable: true)
    }

    static mapping = {
    }
    
    String toString() {
        return "${applicationTitle}"
    }
    
}

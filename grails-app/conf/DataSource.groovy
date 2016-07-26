import grails.plugin.heroku.PostgresqlServiceInfo

dataSource {
    pooled = true
    jmxExport = true
    driverClassName = "org.h2.Driver"
    username = "sa"
    password = ""
}
grails {
    mongo {
      
    }
}

hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory' // Hibernate 3
//    cache.region.factory_class = 'org.hibernate.cache.ehcache.EhCacheRegionFactory' // Hibernate 4
    singleSession = true // configure OSIV singleSession mode
}

// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "" // one of 'create', 'create-drop', 'update', 'validate', ''
            url = ""
            driverClassName = ""
            username = ""
            password = ""
        }
    }
    test {
        dataSource {
//            dbCreate = "update"
//            url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE"

            dbCreate = "create"
            driverClassName = "org.postgresql.Driver"
            dialect = "org.hibernate.dialect.PostgreSQLDialect"
            url = "jdbc:postgresql://qpckuzpbhpjeil:PcDj49WmtL5wbVXvaboP7UE1SV@ec2-54-243-249-132.compute-1.amazonaws.com:5432/d6r205vcrnqpbq?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory"
            username = "qpckuzpbhpjeil"
            password = "PcDj49WmtL5wbVXvaboP7UE1SV"


        }
    }
    production {
        dataSource {
            dbCreate = "update"
            driverClassName = "org.postgresql.Driver"
            dialect = "org.hibernate.dialect.PostgreSQLDialect"
            String DATABASE_URL = System.getenv('DATABASE_URL')
            PostgresqlServiceInfo info = new PostgresqlServiceInfo()
            if (DATABASE_URL) {
                try {
                    println "\nPostgreSQL service ($DATABASE_URL): url='$info.url', " +
                            "user='$info.username', password='$info.password'\n"
                }
                catch (e) {
                    println "Error occurred parsing DATABASE_URL: $e.message"
                }
            }

            url = "jdbc:"+info.url
            username = info.username
            password = info.password

        }
    }
}

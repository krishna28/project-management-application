dataSource {
    pooled = true
    jmxExport = true
    driverClassName = "org.h2.Driver"
    username = "sa"
    password = ""
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
        }
    }
    test {
        dataSource {
//            dbCreate = "update"
//            url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE"

            dbCreate = "" // one of 'create', 'create-drop', 'update', 'validate', ''
            url = ""
            driverClassName = ""
            username = ""
            password = ""


        }
    }
    production {
        dataSource {
            dbCreate = "update"
            driverClassName = "org.postgresql.Driver"
            dialect = "org.hibernate.dialect.PostgreSQLDialect"
            dataSource.url="postgres://oxuuylxlacqdqz:TSvo071GzosOHJKTw0kT0LitaN@ec2-54-225-81-90.compute-1.amazonaws.com:5432/dbpkhsts2tpbno"
            dataSource.username="oxuuylxlacqdqz"
            dataSource.password="TSvo071GzosOHJKTw0kT0LitaN"

        }
    }
}

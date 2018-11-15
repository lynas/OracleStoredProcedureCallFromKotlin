package com.lynas.hibernatestoredprocedure

import org.hibernate.SessionFactory
import org.hibernate.ejb.HibernateEntityManagerFactory
import org.hibernate.internal.SessionImpl
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.transaction.annotation.Transactional


@SpringBootApplication
@EnableTransactionManagement
class Application{
    @Bean
    fun sessionFactory(hemf:HibernateEntityManagerFactory)  = hemf.sessionFactory
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}

@Service
class SampleService(val sessionFactory:SessionFactory) {
    @Transactional
    fun sampleMethod() {
        (sessionFactory.currentSession as SessionImpl).connection().prepareCall("{call storedProcedureName()}").execute()
    }
}

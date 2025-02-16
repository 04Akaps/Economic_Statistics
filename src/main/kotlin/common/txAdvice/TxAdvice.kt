package org.economic.statistics.common.txAdvice

import org.springframework.stereotype.Component
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.TransactionDefinition
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.support.DefaultTransactionDefinition

@Component
class TxAdvice(
    private val transactionManagerMap: Map<String, PlatformTransactionManager> = emptyMap()
) {
    private val advice = Advice() // 내부에서 직접 생성

    fun <T> run(function: () -> T?): T? {
        return advice.run(function)
    }

    fun <T> readOnly(function: () -> T?): T? {
        return advice.readOnly(function)
    }

    fun <T> runNewTransaction(function: () -> T?): T? {
        return advice.runNewTransaction(function)
    }

    fun <T> runWithManager(manager: String, function: () -> T?): T? {
        return advice.runWithManager(manager, transactionManagerMap, function)
    }

    open class Advice {
        @Transactional
        open fun <T> run(function: () -> T?): T? {
            return function()
        }

        @Transactional(readOnly = true)
        open fun <T> readOnly(function: () -> T?): T? {
            return function()
        }

        @Transactional(propagation = Propagation.REQUIRES_NEW)
        open fun <T> runNewTransaction(function: () -> T?): T? {
            return function()
        }

        fun <T> runWithManager(
            manager: String,
            transactionManagerMap: Map<String, PlatformTransactionManager>,
            function: () -> T?
        ): T? {
            val transactionManager = transactionManagerMap[manager]
                ?: throw IllegalArgumentException("Invalid transaction manager: $manager")

            val transactionDefinition = DefaultTransactionDefinition().apply {
                propagationBehavior = TransactionDefinition.PROPAGATION_REQUIRES_NEW
            }
            val status = transactionManager.getTransaction(transactionDefinition)

            return try {
                val result = function()
                transactionManager.commit(status)
                result
            } catch (ex: Exception) {
                transactionManager.rollback(status)
                throw ex
            }
        }
    }
}

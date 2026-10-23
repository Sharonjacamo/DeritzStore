
package com.deritzstore.app.util

import kotlin.math.round

object PaymentCalculator {

    private const val FIRST_PAYMENT_RATE = 0.50

    fun calculateTotal(
        unitPrice: Double,
        quantity: Int
    ): Double {
        require(unitPrice >= 0.0) {
            "Unit price cannot be negative"
        }
        require(quantity > 0) {
            "Quantity must be greater than zero"
        }

        return roundMoney(unitPrice * quantity)
    }

    fun calculateFirstPayment(totalAmount: Double): Double {
        require(totalAmount >= 0.0) {
            "Total amount cannot be negative"
        }

        return roundMoney(totalAmount * FIRST_PAYMENT_RATE)
    }

    fun calculateRemainingBalance(
        totalAmount: Double,
        totalPaid: Double
    ): Double {
        require(totalAmount >= 0.0 && totalPaid >= 0.0) {
            "Amounts cannot be negative"
        }

        return roundMoney(
            (totalAmount - totalPaid).coerceAtLeast(0.0)
        )
    }

    fun isFullyPaid(
        totalAmount: Double,
        totalPaid: Double
    ): Boolean {
        return totalAmount > 0.0 && totalPaid >= totalAmount
    }

    private fun roundMoney(amount: Double): Double {
        return round(amount * 100) / 100
    }
}

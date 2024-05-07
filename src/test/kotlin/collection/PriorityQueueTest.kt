package collection

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import java.util.PriorityQueue

/**
 * PriorityQueue 학습 테스트입니다.
 *  우선순위는 다음과 같습니다.
 *  1. 예약을 한 손님
 *  2. 주문 번호가 빠른 손님
 *
 *  PriorityQueue는 기본적으로 naturalOrder를 따릅니다.
 *  compareTo는 음수가 더 빠른 즉, 우선순위가 높다고 평가 됩니다.
 * */
class PriorityQueueTest {
    data class Customer(val orderNumber: Int, val isBooked: Boolean)
    data class CustomerWithComparable(val orderNumber: Int, val isBooked: Boolean) :
        Comparable<CustomerWithComparable> {
        override fun compareTo(other: CustomerWithComparable): Int {
            if (isBooked && !other.isBooked) return -1
            if (!isBooked && other.isBooked) return 1
            return orderNumber.compareTo(other.orderNumber)
        }
    }

    @Test
    fun `priorityQueue는 기본 구현으로 최소 값이 우선순위가 높다`() {
        // given
        val pq = PriorityQueue<Int>().apply {
            add(2) // add는 offer를 호출하는 구조입니다.
            offer(1) // 결국에 같은 결과를 냅니다.
            add(3)
        }

        // when
        val actual = pq.poll()

        // then
        assertThat(actual).isEqualTo(1)
    }

    @Test
    fun `priorityQueue는 기본 구현으로 최대 값을 우선순위로 하고싶으면 reverseOrder()를 사용한다`() {
        // given
        val pq = PriorityQueue<Int>(reverseOrder()).apply {
            add(2)
            add(1)
            add(3)
        }

        // when
        val actual = pq.poll()

        // then
        assertThat(actual).isEqualTo(3)
    }

    @Test
    fun `priorityQueue는 기본 자료형이 아닌 객체를 담을 때 Comparable을 구현하지 않았다면 예외를 던집니다`() {
        // given
        val pq = PriorityQueue<Customer>()

        // when & then
        assertThrows<ClassCastException> { pq.add(Customer(orderNumber = 1, isBooked = false)) }
    }

    @Test
    fun `priorityQueue는 Comparable을 구현한 객체를 담을 수 있습니다`() {
        // given
        val pq = PriorityQueue<CustomerWithComparable>().apply {
            add(CustomerWithComparable(orderNumber = 4, isBooked = true))
            add(CustomerWithComparable(orderNumber = 2, isBooked = true))
            add(CustomerWithComparable(orderNumber = 1, isBooked = false))
            add(CustomerWithComparable(orderNumber = 3, isBooked = false))
        }

        // when
        val actual1 = pq.poll()
        val actual2 = pq.poll()
        val actual3 = pq.poll()
        val actual4 = pq.poll()

        // then
        assertAll(
            { assertThat(actual1).isEqualTo(CustomerWithComparable(orderNumber = 2, isBooked = true)) },
            { assertThat(actual2).isEqualTo(CustomerWithComparable(orderNumber = 4, isBooked = true)) },
            { assertThat(actual3).isEqualTo(CustomerWithComparable(orderNumber = 1, isBooked = false)) },
            { assertThat(actual4).isEqualTo(CustomerWithComparable(orderNumber = 3, isBooked = false)) },
        )
    }

    @Test
    fun `코틀린은 좀 더 편하게 Comparator를 정의해줄 수 있습니다`() {
        // given
        val pq = PriorityQueue<Customer>(
            compareBy({ if (it.isBooked) -1 else 1 }, { it.orderNumber })
        ).apply {
            add(Customer(orderNumber = 4, isBooked = true))
            add(Customer(orderNumber = 2, isBooked = true))
            add(Customer(orderNumber = 1, isBooked = false))
            add(Customer(orderNumber = 3, isBooked = false))
        }

        // when
        val actual1 = pq.poll()
        val actual2 = pq.poll()
        val actual3 = pq.poll()
        val actual4 = pq.poll()

        // then
        assertAll(
            { assertThat(actual1).isEqualTo(Customer(orderNumber = 2, isBooked = true)) },
            { assertThat(actual2).isEqualTo(Customer(orderNumber = 4, isBooked = true)) },
            { assertThat(actual3).isEqualTo(Customer(orderNumber = 1, isBooked = false)) },
            { assertThat(actual4).isEqualTo(Customer(orderNumber = 3, isBooked = false)) },
        )
    }
}

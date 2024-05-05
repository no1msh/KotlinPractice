package scopefunctions

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * - 컨텍스트 객체는 인수(it)로 사용할 수 있습니다.
 * - 반환 값은 람다 결과입니다.
 *
 * let을 사용하여 호출 체인의 결과에 대해 하나 이상의 함수를 호출할 수 있습니다.
 *
 * */
class LetTest {
    @Test
    fun `let은 it으로 자기 자신을 참조할 수 있다`() {
        // given
        val user = User(name = "bandal", age = 23)
        val actual: User

        // when
        user.let {
            actual = it
        }

        // then
        assertThat(actual).isEqualTo(user)
    }

    @Test
    fun `let의 반환 값은 람다의 결과값이다`() {
        // given
        val name = "badnal"
        val user = User(name = name, age = 23)

        // when
        val userName = user.let {
            it.name
        }

        // then
        assertThat(userName).isEqualTo(name)
    }

    @Test
    fun `let은 안전 호출 연산자 ? 와 함께 사용할 수 있다`() {
        // given
        val user: User? = null
        var isChecked = false

        // when
        user?.let {
            isChecked = true
        }

        // then
        assertThat(isChecked).isFalse
    }

    @Test
    fun `let은 다음과 같이 변수에 할당하는 행위를 줄일 수 있다`() {
        // given
        val numbers = mutableListOf("one", "two", "three", "four", "five")
        fun getResultList(list: List<Int>): List<Int> {
            return list.toList()
        }

        // when without let
        val tempList = numbers.map { it.length }.filter { it > 3 }
        val resultList = getResultList(tempList)

        // when with use
        val useLetResultList = numbers.map { it.length }.filter { it > 3 }.let { getResultList(it) }

        // then
        assertThat(resultList).isEqualTo(useLetResultList)
    }
}

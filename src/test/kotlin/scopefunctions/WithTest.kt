package scopefunctions

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * - 컨텍스트 객체는 수신자(this)로 사용할 수 있습니다.
 * - 반환 값은 람다 결과입니다.
 *
 * with은 확장함수가 아닙니다.
 *
 * 컨텍스트 객체는 인자로 전달되지만 람다 내부에서는 수신자(this)로 사용할 수 있습니다.
 *
 * 코드에서는 with을 "이 객체를 사용하여 다음을 수행합니다."로 읽을 수 있습니다.
 *
 * (with this object, do the following.)
 * */
class WithTest {
    @Test
    fun `with은 this 또는 아무것도 적지 않고 자기 자신을 참조할 수 있다`() {
        // given
        val numbers = mutableListOf(1, 2, 3)
        var sum = 0

        // when
        with(numbers) {
            val left = this[0]
            val right = last()
            sum = left + right // this. 또는 안적어줘도 작동합니다.
        }

        // then
        assertThat(sum).isEqualTo(4)
    }

    @Test
    fun `with의 반환값은 람다 결과값이다`() {
        // given
        val numbers = mutableListOf("one", "two", "three")

        // when
        val firstAndLast: String = with(numbers) {
            "First: ${this.first()}, Last: ${last()}" // this. 또는 안적어줘도 작동합니다.
        }

        // then
        assertThat(firstAndLast).isEqualTo("First: one, Last: three")
    }
}

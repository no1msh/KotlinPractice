package scopefunctions

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * - 컨텍스트 객체는 인자(it)로 사용할 수 있습니다.
 * - 반환 값은 객체 자체입니다.
 *
 * also는 컨텍스트 객체를 사용하는 일부 작업을 수행하는 데 유용합니다.
 *
 * 객체의 속성이나 함수가 아닌 객체에 대한 참조가 필요한 작업이나
 *
 * 외부 범위에서 이 참조를 섀도잉하고 싶지 않은 경우에도 사용할 수 있습니다.
 *
 * 코드에서는 "객체에 대해 다음 작업을 수행합니다.라고 읽을 수 있습니다.
 *
 * (and also do the following with the object.)
 * */
class AlsoTest {
    @Test
    fun `also는 it으로 자기 자신을 참조할 수 있다`() {
        // given
        val numbers = mutableListOf("one", "two", "three")
        val beforeNumbers: List<String>

        // when
        numbers
            .also {
                beforeNumbers = it.toList()
            }
            .add("four")

        // then
        assertThat(beforeNumbers).isEqualTo(listOf("one", "two", "three"))
    }

    @Test
    fun `also는 객체 자체를 반환한다`() {
        // given
        val numbers = mutableListOf("one", "two", "three")

        // when
        val actual: MutableList<String> = numbers.also {
            it.add("four")
        }

        // then
        assertThat(actual).isSameAs(numbers)
    }
}
